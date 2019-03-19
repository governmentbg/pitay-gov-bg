package indexbg.pdoi.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.SysConstants;
import com.indexbg.system.db.JPA;
import com.indexbg.system.quartz.BaseJobResult;
import com.indexbg.system.utils.SearchUtils;

import indexbg.pdoi.db.EgovOrganisations;
import indexbg.pdoi.db.Event;
import indexbg.pdoi.db.dao.EventDAO;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.db.dao.ResponseSubjectDao;

@DisallowConcurrentExecution
public class MyRunnableEgovMessages implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyRunnableEgovMessages.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		BaseJobResult jobResult = new BaseJobResult();
		jobResult.setStatus(SysConstants.JOB_STATUS_OK);
		
		
		try {
			
			String linkAdmin = "";
			String linkUser = "";
			
			Query queryl1 = JPA.getUtil().getEntityManager().createNativeQuery ("select option_value from system_options where option_label = 'linkToInternalSite'");
			Object objl1  =  queryl1.getSingleResult();
			if(objl1!=null) { linkAdmin =objl1.toString(); }
			Query queryl2 = JPA.getUtil().getEntityManager().createNativeQuery ("select option_value from system_options where option_label = 'linkToExternalSite'");
			Object objl2  = queryl2.getSingleResult();
			if(objl2!=null) { linkUser =objl2.toString(); }
			
			
			Query query = JPA.getUtil().getEntityManager().createNativeQuery ("select a.id id, em.msg_reg_dat date,  em.msg_reg_dat + '14 days' srok ,a.application_uri ,a.email ,a.response_subject_id ,a.applicant_type  ,a.full_names from egov_messages em, pdoi_application a where em.id = a.egov_mess_id and a.status = "
					+ Constants.CODE_ZNACHENIE_STATUS_APP_EXPECTED_REG + " and em.msg_rn is not null and em.msg_reg_dat is not null");
			
			List<Object[]> obj = (ArrayList<Object[]>) query.getResultList();
			
			if(obj.size() > 0) {
				JPA.getUtil().begin();
				
				for (int i = 0; i < obj.size(); i++) {
					Object[] tmp = obj.get(i);
					
					Long idApp = SearchUtils.asLong(tmp[0]);
					Long    rsId     =  SearchUtils.asLong(tmp[5]);
					String  appUri   =  SearchUtils.asString(tmp[3]);
					String  appEmail =  SearchUtils.asString(tmp[4]);
					String  srok     =  new SimpleDateFormat("dd.MM.yyyy").format(SearchUtils.asDate(tmp[2])); 
					Long    appType  =  SearchUtils.asLong(tmp[6]);
					String  fullNames=  SearchUtils.asString(tmp[7]);
					
					//запис в заявлението
					Query queryUpdate = JPA.getUtil().getEntityManager().createNativeQuery ("update pdoi_application set status =?, status_date =?, registration_date =?, response_end_time=? where id =?");
					queryUpdate.setParameter(1, Constants.CODE_ZNACHENIE_STATUS_APP_REGISTERED ); // status
					queryUpdate.setParameter(2, new Date() );
					queryUpdate.setParameter(3, SearchUtils.asDate(tmp[1]) ); 
					queryUpdate.setParameter(4, SearchUtils.asDate(tmp[2]) );// srok
					queryUpdate.setParameter(5,  idApp); // id
					queryUpdate.executeUpdate(); 
					
					
					
					
					//запис събитие - Потвърждение от деловодна система
					Event eventPodavane = new Event();
					
					eventPodavane.setApplicationId(idApp);
					eventPodavane.setEventDate(new Date());
					eventPodavane.setEventType(Constants.CODE_ZNACHENIE_TYPE_EVENT_CONFIRM_FROM_SYSTEM);
					eventPodavane.setStatus(Constants.CODE_ZNACHENIE_STATUS_EVENT_COMPLETED);
					
					new EventDAO(-100L, null).save(eventPodavane);
					
					// ismakvame imeilite na adminite na zadaljenite subekti -to
					List<Object[]> dataForAdmin = new PublicationDAO(-100L).findAdminEmailByOrgCode(rsId); 
					EgovOrganisations eOrg = new ResponseSubjectDao(-100L,null).responseSubjectSEOS(rsId);
					
					
					//уведомителните съобщения, които ще се изпращат
					try {
						String name = null;
						
						
						if (appType.longValue() == Constants.CODE_ZNACHENIE_TYPE_APPLICANT_FIZ_LICE) { 
							name = fullNames;										
						}
					   
						ArrayList<String> mailsTo;
						Thread t;
						
						mailsTo = new ArrayList<>();
						mailsTo.add(appEmail);			
						t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_POTV_APPLIC_ZDOI_S_SOES, mailsTo, appUri, eOrg.getAdministrativeBodyName(), srok, name,  -2L, null, linkUser+idApp));	
						t.start();
						
						
						
						 ArrayList<String> mailsToAdm = new ArrayList<String>();
						if(dataForAdmin!=null && !dataForAdmin.isEmpty()){
							for(Object[] ea:dataForAdmin){
								if(ea[1]!=null){
									mailsToAdm.add(SearchUtils.asString(ea[1]));
								}
							}
							t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_POTV_ADMIN_ZDOI_S_SOES, mailsToAdm, appUri, eOrg.getAdministrativeBodyName(), srok, null, -2L, null, linkAdmin+idApp));	
							t.start();
						
						}
						
					} catch (Exception e) {
						LOGGER.error("Грешка при startirane na izprashtane na imeili!!!", e);
					}
				
				}
				
				JPA.getUtil().commit();
				
			}
			
		} catch(Exception e) {
			JPA.getUtil().rollback();
			jobResult.setStatus(SysConstants.JOB_STATUS_WARN);
			LOGGER.error("Възникна грешка при изпълнение нa проверката за обработени Егов съобщения!!!", e);
			JobExecutionException ex = new JobExecutionException(e);
			throw ex;
		} finally {
			JPA.getUtil().closeConnection();
		}
		
		
		
		
	}

}
