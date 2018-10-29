package indexbg.pdoi.bean;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.indexui.navigation.Navigation;
import com.indexbg.indexui.navigation.NavigationData;
import com.indexbg.indexui.navigation.NavigationDataHolder;
import com.indexbg.system.db.JPA;
import com.indexbg.system.db.dto.SystemClassif;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;
import com.indexbg.system.utils.SearchUtils;

import indexbg.pdoi.db.Application;
import indexbg.pdoi.db.EgovOrganisations;
import indexbg.pdoi.db.Event;
import indexbg.pdoi.db.Files;
import indexbg.pdoi.db.dao.ApplicationDAO;
import indexbg.pdoi.db.dao.EgovMessagesDAO;
import indexbg.pdoi.db.dao.EventDAO;
import indexbg.pdoi.db.dao.FilesDAO;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.db.dao.ResponseSubjectDao;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.MyRunnableMail;
import indexbg.pdoi.system.PDoiBean;

/**
 * @author dkarapetrova
 *
 */
@ManagedBean(name = "events")
@ViewScoped
public class Events extends PDoiBean {	

	/** Основен java клас за въвеждане/актуализация на събитията към заявленията
	 * 
	 */
	private static final long serialVersionUID = -618417540382044905L;
	
	static final Logger LOGGER = LoggerFactory.getLogger(Events.class);
	
	private Long userId;
	private EventDAO eventsDAO;
	private Event event = new Event();
	private Application app = new Application();
	private ApplicationDAO appDAO;
	
	private String zadaljenSubText;
	private Long zadaljenSub;	
	
	private Files file = new Files();
	private FilesDAO filesDAO;
	private List<Files> filesList = new ArrayList<>();
	private List<Files> deleteFilesList = new ArrayList<>();
	
	private Long endSolution;
	private List <SelectItem> endSolutionList;
	
	private Map<Long, Boolean> showAttr = new HashMap<>();
	private Integer days;
	private Long statusEvent;
	private Map<Long, Integer> daysForExtension = new HashMap<>();
	private Date respEndTime;
	private String uriForSearch;
		
	/** Иницира първоначалните стойности на обекта
	 * 
	 * Зарежда обекта по подаден параметър от заявлението
	 */
	@PostConstruct
	public void initData(){
		
		LOGGER.debug("PostConstruct!!!!");
		
		try {			
					
			this.userId = getUserData().getUserId();
			this.eventsDAO = new EventDAO(this.userId);	
			this.appDAO = new ApplicationDAO(this.userId);
			this.filesDAO = new FilesDAO(this.userId);
			
			this.showAttr = new HashMap<>();
			
			this.event = new Event();
			
			Long idObj = null;
			Long eventType = null;
			Long appId = null;
			
			if (JSFUtils.getRequestParameter("idObj") != null && !JSFUtils.getRequestParameter("idObj").equals("")) {
				idObj = Long.valueOf(JSFUtils.getRequestParameter("idObj"));	
			}	
			
			if (JSFUtils.getRequestParameter("eventCode") != null && !JSFUtils.getRequestParameter("eventCode").equals("")) {
				eventType = Long.valueOf(JSFUtils.getRequestParameter("eventCode"));
			}
			
			if (JSFUtils.getRequestParameter("applicId") != null && !JSFUtils.getRequestParameter("applicId").equals("")) {
				appId = Long.valueOf(JSFUtils.getRequestParameter("applicId"));
			}
			
			if (idObj != null) {
				
				this.event = this.eventsDAO.findById(idObj);
				this.filesList = this.filesDAO.findByCodeObjAndIdObj(event.getCodeMainObject(), event.getId());	
				appId = this.event.getApplicationId();
				eventType = this.event.getEventType();
				
			
			} else {
				
				this.event.setApplicationId(appId);
				this.event.setEventType(eventType); 
				this.event.setEventDate(getToday()); 
			}
			
			List<Object[]> tmpList =  this.eventsDAO.eventsControlList(eventType);
			
			for (Object[] obj : tmpList) {
								
				if (obj[0] != null) { // app_status_code
					this.showAttr.put(getAppStatusCode(), Boolean.TRUE);
				}	
				
				if (obj[1] != null) { // app_status
					this.showAttr.put(getAppStatus(), Boolean.TRUE);
				}
				
				if (obj[2] != null) { // reason
					this.showAttr.put(getReason(), Boolean.TRUE);
				}
				
				if (obj[3] != null) { // days
					this.showAttr.put(getDaysCode(), Boolean.TRUE);
					this.days = SearchUtils.asInteger(obj[3]);
				}
				
				if (SearchUtils.asBoolean(obj[4]).equals(true)) { // old_resp_subject
					this.showAttr.put(getOldRespSubject(), Boolean.TRUE);
				}
				
				if (SearchUtils.asBoolean(obj[5]).equals(true)) { // new_resp_subject 
					this.showAttr.put(getNewRespSubject(), Boolean.TRUE);
				}
				
				if (obj[6] != null) { // event_status
					this.statusEvent = SearchUtils.asLong(obj[6]);
				}
				
				if (obj[7] != null) { // not_approved
					this.showAttr.put(getNotApproved(), Boolean.TRUE);
				}
				
				if (SearchUtils.asBoolean(obj[8]).equals(true)) { // add_text
					this.showAttr.put(getAddText(), Boolean.TRUE);
				}
				
				if (SearchUtils.asBoolean(obj[9]).equals(true)) { // files
					this.showAttr.put(getFilesCode(), Boolean.TRUE); 
				}
				
				this.daysForExtension.put(SearchUtils.asLong(obj[2]), this.days);
			}
			
			this.app = this.appDAO.findById(appId);			
			
			if(this.event.getNewRespSubjectId() != null) {
				this.zadaljenSubText = getSystemData().decodeItem(Constants.CODE_SYSCLASS_ADM_REGISTRY, this.event.getNewRespSubjectId(), getCurrentLang(), getToday(), this.userId);
			} else {
				this.zadaljenSubText = "";
			}
			
			if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FORW_APPLICATION) && this.event.getOldRespSubjectId() == null) {
				this.event.setOldRespSubjectId(this.app.getResponseSubjectId()); 
			}
			
			if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_REQ_ADD_INFORMATION)) {
				//статус на заявлението - Constants.CODE_ZNACHENIE_STATUS_APP_REGISTERED
				//статус на събитието - Constants.CODE_ZNACHENIE_STATUS_EVENT_UNCOMPLETED
				// срок на изпълнение - + 30 дни от датата на събитието
				
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(this.event.getEventDate());
				gc.add(Calendar.DAY_OF_YEAR, days); 
				gc.set(Calendar.MINUTE, 0);
				gc.set(Calendar.SECOND, 0);
				gc.set(Calendar.HOUR_OF_DAY, 0);
				
				this.event.setEventEndDate(gc.getTime());				
			}
			
			if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION) && idObj != null) {
				
				if (this.event.getReasonNotApproved() != null) {
					this.endSolution = Long.valueOf(Constants.CODE_ZNACHENIE_STATUS_APP_NON_APPROVED);
				} else {
					this.endSolution = this.app.getStatus();
				}				
			}
			
			respEndTime = this.app.getResponseEndTime();
		
		} catch (ObjectNotFoundException e) {
			
			this.userId = -1L;

		} catch (Exception e) {
			LOGGER.error("Грешка при работа със системата!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
		
		} finally {
			JPA.getUtil().closeConnection();
		}
		
	}
	
	/** Избор на причина за удължаване на срока и увеличаването на крайния срок спрямо причината
	 * 
	 */
	public void selectReasonForExtend() { 
		
		if (this.event.getEventReason().equals(Constants.CODE_ZNACHENIE_REASON_EXTENSION_REQ_THIRD_PART)) {
			//responseEndTime + 14 дни от датата на регистрация на заявлението
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(respEndTime);
			gc.add(Calendar.DAY_OF_YEAR, this.daysForExtension.get(Constants.CODE_ZNACHENIE_REASON_EXTENSION_REQ_THIRD_PART)); 
			gc.set(Calendar.MINUTE, 0);
			gc.set(Calendar.SECOND, 0);
			gc.set(Calendar.HOUR_OF_DAY, 0);
			
			this.app.setResponseEndTime(gc.getTime());
			this.event.setEventEndDate(gc.getTime());
		}
		
		if (this.event.getEventReason().equals(Constants.CODE_ZNACHENIE_REASON_EXTENSION_LARGE_INFO)) {
			//responseEndTime + 10 дни отдатата на регистрация на заявлението
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(respEndTime);
			gc.add(Calendar.DAY_OF_YEAR, this.daysForExtension.get(Constants.CODE_ZNACHENIE_REASON_EXTENSION_LARGE_INFO)); 
			gc.set(Calendar.MINUTE, 0);
			gc.set(Calendar.SECOND, 0);
			gc.set(Calendar.HOUR_OF_DAY, 0);
			
			this.app.setResponseEndTime(gc.getTime());
			this.event.setEventEndDate(gc.getTime());
		}
	}
	
	/** Проверка на въведените данни на събитието, преди запис в БД
	 * 
	 * @return true при успешна проверка
	 */
	private boolean checkData() {

		boolean save = true;		
		
		if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FORW_APPLICATION)) {
			if (this.zadaljenSub == null) {
				JSFUtils.addMessage("formEvents:zadSubTxt",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert", getMessageResourceString("labels", "events.newZadSub")));
				save = false;
			}
		}
		
		if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_REQ_ADD_INFORMATION)
				|| this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_PROVID_ADD_INFORMATION)) {
			if (this.event.getAddText() == null || this.event.getAddText().isEmpty()) {
				JSFUtils.addMessage("formEvents:addTxt",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert", getMessageResourceString("labels", "general.dopInfo")));
				save = false;
			}
		}
		
		if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_EXTEND_TERM)) {
			if (this.event.getEventReason() == null) {
				JSFUtils.addMessage("formEvents:reason",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert", getMessageResourceString("labels", "events.reason")));
				save = false;				
			}
		}
		
		if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION)) {
			
			if (this.endSolution == null) {
				JSFUtils.addMessage("formEvents:endSolution",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert", getMessageResourceString("labels", "events.endSolution")));
				save = false;
			}
			
			if (this.endSolution != null && this.endSolution.equals(Constants.CODE_ZNACHENIE_STATUS_APP_NON_APPROVED) && this.event.getReasonNotApproved() == null) {
				JSFUtils.addMessage("formEvents:reasonNotApproved",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert", getMessageResourceString("labels", "events.reason")));
				save = false;				
			}
			
		}

	return save;
}
	
	/** Записва на въведените данни за събитието в БД и изпраща различни мейли спрямо вида на събитието
	 * 
	 */
	public void actionSave() {
		
		if (checkData()) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			String zdoi = "";
			EgovOrganisations eOrg = null;
			boolean startThreadMails = false;
			Long responseId = null;
			
			List<Object[]> dataForAdmin = null;
		
			try {
			
				JPA.getUtil().begin();
				
				//проверка дали задължения субект има електронен документооборот
				eOrg = new ResponseSubjectDao(userId).responseSubjectSEOS(this.app.getResponseSubjectId());
				
				if (event.getId() == null) {
					if (this.statusEvent != null) {
						this.event.setStatus(this.statusEvent);	
					} else {
						this.event.setStatus(Constants.CODE_ZNACHENIE_STATUS_EVENT_COMPLETED);	
					}								
				} 
				
				if (this.zadaljenSub != null) {
					this.event.setNewRespSubjectId(this.zadaljenSub); 
				}		
				
				this.event = this.eventsDAO.save(this.event);
				
				JPA.getUtil().flush();
				
				if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FORW_APPLICATION)) {
					responseId = this.event.getNewRespSubjectId();
				} else {
					responseId = this.app.getResponseSubjectId();
				}
				
				zdoi = getSystemData().decodeItem(Constants.CODE_SYSCLASS_ADM_REGISTRY, responseId, getCurrentLang(), getToday(), this.userId);	
				
				if (!this.deleteFilesList.isEmpty()) {
					for (int i = 0; i < deleteFilesList.size(); i++) {
						if (deleteFilesList.get(i).getId() != null) {
							this.filesDAO.deleteById(deleteFilesList.get(i).getId());
						}
					}
				}
		
				for (int i = 0; i < filesList.size(); i++) {
					
					if (filesList.get(i).getId() == null) {
						filesList.get(i).setCodeObject(event.getCodeMainObject());
						filesList.get(i).setIdObject(event.getId());	
						
						this.filesDAO.save(filesList.get(i));
					
					} else { // при редакция не е измъкнат целия обект и затова ще се прави ъпдейт на 4 полета - description, visibleOnSite, userLastMod, dateLastMod
						
						this.filesDAO.updateFile(filesList.get(i).getDescription(), filesList.get(i).isVisibleOnSite(), this.userId, getToday(), filesList.get(i).getId()); 		
					}			
				}
				
				JPA.getUtil().flush();
				
				if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_PROVID_ADD_INFORMATION)) {	
					
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(this.event.getEventDate());
					gc.add(Calendar.DAY_OF_YEAR, 14); 
					gc.set(Calendar.MINUTE, 0);
					gc.set(Calendar.SECOND, 0);
					gc.set(Calendar.HOUR_OF_DAY, 0);
					
					this.app.setResponseEndTime(gc.getTime());
					
					this.appDAO.updateResponseEndDate(this.app.getResponseEndTime(), this.userId, getToday(), this.event.getApplicationId());
				}
				
				if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FORW_APPLICATION)) {
					
					if (this.event.getNewRespSubjectId() != null) {			
						
						this.appDAO.updateResponseFromEvent(this.event.getNewRespSubjectId(), this.userId, getToday(), this.event.getApplicationId()); //04.10.2018 - сменяме и статуса на заявлениео при смяна за задължения субект!						
					
						//Изпращане на СЕОС
						if(eOrg != null){
							new EgovMessagesDAO(userId).saveEgovMessage(this.app, filesList, eOrg, getSystemData());
						}
					}
				}
				
				if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_EXTEND_TERM)) {
					this.appDAO.updateResponseEndDate(this.app.getResponseEndTime(), this.userId, getToday(), this.event.getApplicationId());
				}
				
				if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION)) {
					
					if (this.endSolution != null) {
						this.appDAO.updateStatusFromEvent(this.endSolution, this.event.getEventDate(), this.event.getAddText(), this.userId, getToday(), this.event.getApplicationId(), this.event.getAppIdForView());
					}
				}
				
				if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_CONFIRM_FROM_SYSTEM)) {
					
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(this.event.getEventDate());
					gc.add(Calendar.DAY_OF_YEAR, 14); 
					gc.set(Calendar.MINUTE, 0);
					gc.set(Calendar.SECOND, 0);
					gc.set(Calendar.HOUR_OF_DAY, 0);
					
					this.app.setResponseEndTime(gc.getTime());	
					
					this.appDAO.updateAppWithEventConfirm(this.event.getEventDate(), this.app.getResponseEndTime(), Constants.CODE_ZNACHENIE_STATUS_APP_REGISTERED, getToday(), this.userId, getToday(), this.event.getApplicationId());
				}
				
				dataForAdmin = new PublicationDAO(this.userId).findAdminEmailByOrgCode(responseId); 
				
				JPA.getUtil().commit();		
				
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesSaveMsg"));
				
				startThreadMails = true;				
			    
			} catch (DbErrorException e) {
				JPA.getUtil().rollback();
				LOGGER.error("Грешка при запис на събитие! ", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
	
			} catch (Exception e) {
				JPA.getUtil().rollback();
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));
	
			} finally {
				JPA.getUtil().closeConnection();
				deleteFilesList.clear(); // да се зачисти след записа
			}			
			
			if (startThreadMails) {
				
				try {

					// Изпращане на мейл-и!!!!
					
					String srok = null;
					String name = null;
					String link = null;

					if (this.app.getApplicantType().equals(Constants.CODE_ZNACHENIE_TYPE_APPLICANT_FIZ_LICE)) {
						name = this.app.getFullNames();
					}

					ArrayList<String> mailsTo = new ArrayList<>();

					Thread t;

					// мейла на заявителя
					if (this.app.getEmail() != null && !this.app.getEmail().isEmpty()) {
						mailsTo.add(this.app.getEmail());
					}

					if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FORW_APPLICATION)) {

						//съобщение до Заявител - мейла на заявителя					
						if (!mailsTo.isEmpty()) {
							t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_PREPRASHTANE_APPLIC,	mailsTo, this.app.getApplicationUri(), zdoi, srok, name, null, null, null, link));
							t.start();
						}

						//съобщение до Администратор на ЗДОИ без СОЕС					
						if (eOrg == null) {

							GregorianCalendar gc = new GregorianCalendar();
							gc.setTime(this.event.getEventDate());
							gc.add(Calendar.DAY_OF_YEAR, calcPeriod(gc.get(GregorianCalendar.DAY_OF_WEEK),14));
							gc.set(Calendar.MINUTE, 0);
							gc.set(Calendar.SECOND, 0);
							gc.set(Calendar.HOUR_OF_DAY, 0);

							srok = sdf.format(SearchUtils.asDate(gc.getTime()));

							ArrayList<String> mailsToAdm = new ArrayList<String>();

							for (Object[] tmpObj : dataForAdmin) {
								if (tmpObj[1] != null) {
									mailsToAdm.add(SearchUtils.asString(tmpObj[1]));
								}
							}

							if (!mailsToAdm.isEmpty()) {
								t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_PREPRASHTANE_ADMIN_ZDOI_BEZ_SOES, mailsToAdm, this.app.getApplicationUri(), zdoi, srok, null, null, null, null, link));
								t.start();
							}
						}
					}

					if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_REQ_ADD_INFORMATION)) {

						//съобщение до Заявител					
						if (!mailsTo.isEmpty()) {
							t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_ISKANE_DOP_INFO_OT_APPLIC, mailsTo, this.app.getApplicationUri(), zdoi, srok, name, null, null, null, link));
							t.start();
						}
					}

					if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_PROVID_ADD_INFORMATION)) {
						//съобщение до Администратор на ЗДОИ без СОЕС
						
						//if (!zdoiWithSEOS) { // Трябва да изпраща мейл на всички администратори - независимо дали имат СЕОС!

							ArrayList<String> mailsToAdm = new ArrayList<String>();
	
							for (Object[] tmpObj : dataForAdmin) {
								if (tmpObj[1] != null) {
									mailsToAdm.add(SearchUtils.asString(tmpObj[1]));
								}
							}
	
							if (!mailsToAdm.isEmpty()) {
								t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_PREDOSTAV_DOP_INFO_DO_ADMIN_ZDOI_BEZ_SOES, mailsToAdm, this.app.getApplicationUri(), zdoi, srok, null, null, null, null, link));
								t.start();
							}
						//}
					}

					if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_EXTEND_TERM)) {

						srok = sdf.format(SearchUtils.asDate(this.event.getEventEndDate()));

						//съобщение до Заявител
						if (!mailsTo.isEmpty()) {

							if (this.event.getEventReason().equals(Constants.CODE_ZNACHENIE_REASON_EXTENSION_REQ_THIRD_PART)) {
								t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_UDALJAVANE_SROK_TRETO_LICE_DO_APPLIC, mailsTo, this.app.getApplicationUri(), zdoi, srok, name, null, null, null, link));
								t.start();
							}

							if (this.event.getEventReason().equals(Constants.CODE_ZNACHENIE_REASON_EXTENSION_LARGE_INFO)) {
								t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_UDALJAVANE_SROK_GOL_KOLICH_INFO_DO_APPLIC, mailsTo, this.app.getApplicationUri(), zdoi, srok, name, null, null, null, link));
								t.start();
							}
						}
					}

					if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION)) {

						//съобщение до Заявител					
						if (!mailsTo.isEmpty()) {
							t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_KRAINO_RESHENIE_DO_APPLIC, mailsTo, this.app.getApplicationUri(), zdoi, srok, name, null, null, null, link));
							t.start();
						}
					}
					
					if (this.event.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_CONFIRM_FROM_SYSTEM)) {
						
						//съобщение до Заявител					
						if (!mailsTo.isEmpty()) {
							t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_POTV_APPLIC_ZDOI_S_SOES, mailsTo, this.app.getApplicationUri(), zdoi, sdf.format(SearchUtils.asDate(this.app.getResponseEndTime())), name, null, null, null, link)); 
							t.start();
						}
					}

				} catch (Exception e) {
					JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.sendMailErr"));
					LOGGER.error(e.getMessage(), e);
				}
				
				try { // Обновяване на данните на заявлението и връщането в екрана на заявлението с променените данни
					
					Navigation navHolder = new Navigation();			
				    int i = navHolder.getNavPath().size();	
				   
				    NavigationDataHolder dataHoslder = (NavigationDataHolder) JSFUtils.getManagedBean("navigationSessionDataHolder");
				    Stack<NavigationData> stackPath = dataHoslder.getPageList();
				    NavigationData nd = (NavigationData) stackPath.get(i-2);
				    Map<String, Object> mapV = nd.getViewMap();
				    
				    ApplicationForm appForm = (ApplicationForm) mapV.get("applicationForm");
				    
				    appForm.setShowBntMoreInfo(false);
				    
				    if( this.event.getEventType().longValue() == Constants.CODE_ZNACHENIE_TYPE_EVENT_REQ_ADD_INFORMATION){
				    	
				    	appForm.setShowBntMoreInfo(true);
				    	appForm.setResponseEndTime("Очаква се предоставяне на допълнителна информация");
				    
				    } else if (this.event.getEventType().longValue() == Constants.CODE_ZNACHENIE_TYPE_EVENT_PROVID_ADD_INFORMATION) {	
				    	
				    	appForm.getApplic().setResponseEndTime(this.app.getResponseEndTime());	   
				    	appForm.setResponseEndTime(new SimpleDateFormat("dd.MM.yyyy").format(app.getResponseEndTime()));						
				    
				    } else if (this.event.getEventType().longValue() == Constants.CODE_ZNACHENIE_TYPE_EVENT_FORW_APPLICATION) {
				    	
				    	appForm.getApplic().setResponseSubjectId(this.event.getNewRespSubjectId());
				    	appForm.getApplic().setStatus(Constants.CODE_ZNACHENIE_STATUS_APP_EXPECTED_REG);
				    
				    } else if (this.event.getEventType().longValue() == Constants.CODE_ZNACHENIE_TYPE_EVENT_EXTEND_TERM) {
				    	
				    	appForm.getApplic().setResponseEndTime(this.app.getResponseEndTime());
				    	appForm.setResponseEndTime(new SimpleDateFormat("dd.MM.yyyy").format(app.getResponseEndTime()));	
					
				    } else if (this.event.getEventType().longValue() == Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION) {				    	
				    	
				    	appForm.getApplic().setStatus(this.endSolution);
				    	appForm.getApplic().setStatusDate(this.event.getEventDate());
				    	appForm.getApplic().setResponse(this.event.getAddText());
				    	appForm.getApplic().setResponseDate(this.event.getEventDate());					
					
				    } else if (this.event.getEventType().longValue() == Constants.CODE_ZNACHENIE_TYPE_EVENT_CONFIRM_FROM_SYSTEM) {				    	
				    	
				    	appForm.getApplic().setRegistrationDate(this.event.getEventDate()); 
				    	appForm.getApplic().setResponseEndTime(this.app.getResponseEndTime());
				    	appForm.setResponseEndTime(new SimpleDateFormat("dd.MM.yyyy").format(app.getResponseEndTime()));	
				    	appForm.getApplic().setStatus(Long.valueOf(Constants.CODE_ZNACHENIE_STATUS_APP_REGISTERED)); 
				    	appForm.getApplic().setStatusDate(this.event.getEventDate());
				    }
				    
				    appForm.getEventsList().add(event); // добавяне на новото събитие към листа със събития в завлението	 
					
				    // контрола за събитията да се презаредят като се върне към заявлението
					List<Object[]> eControlList = eventsDAO.eventsControlList();
					for (Object[] eObj : eControlList) {
						 appForm.getEventsControl().put(SearchUtils.asLong(eObj[0]), eObj);
					}
				 
				    // в бутона за ново събитие в заявлението да може да се покажат събитията, както следват, след записа на новото събитие
				    List<SystemClassif> listItems = new ArrayList<>();
				    
				    if(appForm.getEventsList()!=null){
				    	List<Object> nextEvents = eventsDAO.nextEventsForApp(appForm.getEventsList().get(appForm.getEventsList().size()-1).getEventType());
						for(Object o:nextEvents){
							listItems.add( getSystemData().decodeItemFull(Constants.CODE_SYSCLASS_TYPE_EVENT, SearchUtils.asLong(o), getCurrentLang(), new Date(), userId));
						}
					}
				    
				   appForm.setMenuButton(new DefaultMenuModel());
				    
				    for(SystemClassif item : listItems){
						DefaultMenuItem ajaxAction = new DefaultMenuItem(item.getTekst());
						ajaxAction.setCommand("eventsInt.jsf");
						ajaxAction.setParam("eventCode", item.getCode());
						ajaxAction.setParam("applicId", app.getId());
						ajaxAction.setAjax(false);
						appForm.getMenuButton().addElement(ajaxAction);
					}
				   
				    navHolder.goBack();
				
				} catch (DbErrorException e) {
					LOGGER.error("Грешка при връщането към заявлението от страницата на събитието! ", e);
		
				} catch (Exception e) {
					LOGGER.error("Грешка при връщането към заявлението от страницата на събитието!", e);					
		
				} finally {
					JPA.getUtil().closeConnection();					
				}				
			}			
		}
	}
	
	/** Изтриване на събитието от БД
	 * 
	 */
	public void actionDelete() {
		
		try {
		
		JPA.getUtil().begin();		
		
//		this.eventsDAO.deleteEvent(this.event.getEventType(), this.event.getId(), this.event.getApplicationId()); 
		
		if (!this.filesList.isEmpty()) {
			for (int i = 0; i < filesList.size(); i++) {				
				this.filesDAO.deleteById(Long.valueOf(filesList.get(i).getId()));				
			}
		}
		
		JPA.getUtil().flush();
		
		this.eventsDAO.deleteById(Long.valueOf(event.getId())); 
			
		JPA.getUtil().commit();			
			
		JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesDeleteMsg"));

		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при запис на събитие! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));

		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при работа със системата!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));

		} finally {
			JPA.getUtil().closeConnection();
			this.event = new Event();
		}
	}
	
	public void actionSearchApp() {
		
		if (this.uriForSearch != null && !"".equals(this.uriForSearch)) {
			
			try {
				
				this.event.setAppIdForView(this.appDAO.findAppIdByUri(this.uriForSearch));
			
			} catch (DbErrorException e) {
				LOGGER.error("Грешка при търсене на ид на заявление по ури! ", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
			
			} catch (Exception e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			
			} finally {
				JPA.getUtil().closeConnection();
			}
						
		} else {			
			JSFUtils.addMessage("formEvents:uri",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert", getMessageResourceString("labels", "events.searchAppByUri")));
			
		}
	}
	
	// ************************************************* FILES ***************************************************** //
			
	/** Избор на файлове за прикачване към събитието
	 * 
	 * @param event
	 */
	public void uploadFileListener(FileUploadEvent event){		
		
		try {
			
			UploadedFile upFile = event.getFile();
			
			Files fileObject = new Files();
			fileObject.setFilename(upFile.getFileName());
			fileObject.setContentType(upFile.getContentType());
			fileObject.setContent(upFile.getContents());	
			
			this.filesList.add(fileObject);
		
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.getMessage());	
		} 
	}
		
	/** Извличане от БД на запазените файлове към събитието
	 * 
	 * @param file
	 */
	public void download(Files file){ 
		
		boolean ok =true;
		
		if(file.getContent() == null && file.getId() != null) {
			try {
				FilesDAO filesDAO = new FilesDAO(userId);
				file = filesDAO.findById(file.getId());
				
				if(file.getPath() != null && !file.getPath().isEmpty()){
					Path path = Paths.get(file.getPath());
					file.setContent(java.nio.file.Files.readAllBytes(path));
				}
			} catch (DbErrorException e) {
				LOGGER.error("DbErrorException: " + e.getMessage());
				ok =false;
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
			} catch (IOException e) {
				LOGGER.error("IOException: " + e.getMessage());
				ok =false;
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
				LOGGER.error(e.getMessage(), e);
			} catch (Exception e) {
				LOGGER.error("Exception: " + e.getMessage());
				ok =false;
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));
			}  finally {
				JPA.getUtil().closeConnection();
			}
		}
		
		if(ok){
			try {
				FacesContext facesContext = FacesContext.getCurrentInstance();
			    ExternalContext externalContext = facesContext.getExternalContext();
			    externalContext.setResponseHeader("Content-Type", "application/x-download");
			    externalContext.setResponseHeader("Content-Length", file.getContent().length + "");
			    externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + file.getFilename() + "\"");
				externalContext.getResponseOutputStream().write(file.getContent());
				facesContext.responseComplete();
			} catch (IOException e) {
				LOGGER.error("IOException: " + e.getMessage());
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
				LOGGER.error(e.getMessage(), e);
			} catch (Exception e) {
				LOGGER.error("Exception: " + e.getMessage());
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));
			} 
		}
		
	}
		
	/** Премахва избрания файл
	 * 
	 * @param file
	 */
	public void remove(Files file){
			
		this.filesList.remove(file);
		this.deleteFilesList.add(file);			
	}
	
	/** Прави избраният файл видим на сайта
	 * 
	 * @param file
	 */
	public void actionChangeFileVis(Files file) {		
		file.setVisibleOnSite(true);	
	}

	/** Прави избрания сайт невидим на сайта
	 * 
	 * @param file
	 */
	public void actionChangeFileNonVis(Files file) {		
		file.setVisibleOnSite(false);	
	}	
		
	// ************************************************* END FILES ***************************************************** //
	
	// ************************************************* GET & SET ***************************************************** //

	/** GET & SET
	 * 
	 * 
	 */
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}	
	
	public Application getApp() {
		return app;
	}

	public void setApp(Application app) {
		this.app = app;
	}

	public String getZadaljenSubText() {
		return zadaljenSubText;
	}

	public void setZadaljenSubText(String zadaljenSubText) {
		this.zadaljenSubText = zadaljenSubText;
	}
	
	public Long getZadaljenSub() {
		return zadaljenSub;
	}

	public void setZadaljenSub(Long zadaljenSub) {
		this.zadaljenSub = zadaljenSub;
	}	

	public Files getFile() {
		return file;
	}

	public void setFile(Files file) {
		this.file = file;
	}	
	
	public List<Files> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<Files> filesList) {
		this.filesList = filesList;
	}

	public List<Files> getDeleteFilesList() {
		return deleteFilesList;
	}

	public void setDeleteFilesList(List<Files> deleteFilesList) {
		this.deleteFilesList = deleteFilesList;
	}	
	
	public Long getEndSolution() {
		return endSolution;
	}

	public void setEndSolution(Long endSolution) {
		this.endSolution = endSolution;
	}

	public List <SelectItem> getEndSolutionList() {
		
		if (this.endSolutionList == null) {
			
			this.endSolutionList = new ArrayList<SelectItem>();
			
			try {
				
				List<SystemClassif> endSolList = getSystemData().getSysClassification(Constants.CODE_SYSCLASS_FINAL_DECISION, getToday(), getCurrentLang(), this.userId);
				
				for (SystemClassif item : endSolList) {
					
					this.endSolutionList.add(new SelectItem(item.getCode(), item.getTekst()));
				}
				
				Collections.sort(this.endSolutionList, comparator);
			
			} catch (DbErrorException e) {				
				LOGGER.error("Грешка при зареждане на системната класификация! ", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
			
			} finally {
				JPA.getUtil().closeConnection();
			}			
		}
		
		return endSolutionList;
	}

	public void setEndSolutionList(List <SelectItem> endSolutionList) {
		this.endSolutionList = endSolutionList;
	}
	
	public Map<Long, Boolean> getShowAttr() {
		return showAttr;
	}

	public void setShowAttr(Map<Long, Boolean> showAttr) {
		this.showAttr = showAttr;
	}	
	
	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
	
	public Long getStatusEvent() {
		return statusEvent;
	}

	public void setStatusEvent(Long statusEvent) {
		this.statusEvent = statusEvent;
	}
	
	public Map<Long, Integer> getDaysForExtension() {
		return daysForExtension;
	}

	public void setDaysForExtension(Map<Long, Integer> daysForExtension) {
		this.daysForExtension = daysForExtension;
	}
	
	public Date getRespEndTime() {
		return respEndTime;
	}

	public void setRespEndTime(Date respEndTime) {
		this.respEndTime = respEndTime;
	}
	
	public String getUriForSearch() {
		return uriForSearch;
	}

	public void setUriForSearch(String uriForSearch) {
		this.uriForSearch = uriForSearch;
	}	
	
	// ************************************************* END GET & SET ***************************************************** //	
	
	public Date getToday(){
		return new Date();
	}	
		
	public Long getAppStatusCode() { // app_status_code
		return Long.valueOf(1L);		
	} 
	
	public Long getAppStatus() { // app_status
		return Long.valueOf(2L);
	} 
	
	public Long getReason() { // reason
		return Long.valueOf(3L);
	} 
	
	public Long getDaysCode() { // days
		return Long.valueOf(4L);
	} 
	
	public Long getOldRespSubject() { // old_resp_subject
		return Long.valueOf(5L);
	} 
	
	public Long getNewRespSubject() { // new_resp_subject
		return Long.valueOf(6L);
	} 
	
	public Long getNotApproved() { // not_approved
		return Long.valueOf(7L);
	} 
	
	public Long getAddText() { // add_text
		return Long.valueOf(8L);
	} 

	public Long getFilesCode() { // files
		return Long.valueOf(9L);
	} 	
	
	/** Comparator за сравняване на стойности
	 * 
	 */
	Comparator<SelectItem> comparator = new Comparator<SelectItem>() {	   
	    public int compare(SelectItem s1, SelectItem s2) {	        
	        return (s1.getValue().toString()).compareTo((s2.getValue().toString()));
	    }
	};
	
}
