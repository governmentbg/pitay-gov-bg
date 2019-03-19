package indexbg.pdoi.db.dao;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.Query;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.AddressingFeature;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.db.TrackableDAO;
import com.indexbg.system.db.dto.SystemClassif;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.UnexpectedResultException;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.SearchUtils;
import com.indexbg.system.utils.StringUtils;

import indexbg.pdoi.db.EgovOrganisations;
import indexbg.pdoi.db.ResponseSubject;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.SystemData;
import indexbg.pdoi.wsclient.admreg.ArrayOfBatchIdentificationInfoType;
import indexbg.pdoi.wsclient.admreg.ArrayOfBatchType;
import indexbg.pdoi.wsclient.admreg.ArrayOfString;
import indexbg.pdoi.wsclient.admreg.BatchIdentificationInfoType;
import indexbg.pdoi.wsclient.admreg.BatchStatusEnum;
import indexbg.pdoi.wsclient.admreg.BatchType;
import indexbg.pdoi.wsclient.admreg.CorrespondenceDataPhoneType;
import indexbg.pdoi.wsclient.admreg.IBatchInfoService;

public class ResponseSubjectDao extends TrackableDAO<ResponseSubject> {	

	static final Logger LOGGER = LoggerFactory.getLogger(ResponseSubjectDao.class);
	
	private SystemData sd;
	
	public ResponseSubjectDao (Long userId ,SystemData sd){
		
		super(userId);	
		this.sd = sd;
	}
	
	/** Търсене на задължени субекти по зададени параметри
	 * @param eik
	 * @param name
	 * @return
	 */
	public SelectMetadata findSubjects(String eik,String name) {
		
		
		String sql = " SELECT id as A0," // 
				+ " eik as A1,"
				+ " subject_name as A2,"
				+ " ADDRESS as A3, " 
				+ " adm_register as A4"
				+ " FROM pdoi_response_subject ";
		 		
		String 	whereClause =  " ";
		String and=" ";
		String where = "where";
		
		
		if(eik != null && !"".equals(eik)){
			whereClause += where + " eik LIKE '%" + eik.trim() + "%'";	
			and = " AND ";
			where = "";
		}
		
		if(name != null && !"".equals(name)){
			whereClause += where +  and + " upper(subject_name) LIKE '%" + name.trim().toUpperCase() +"%'";
	        
		}
		
		SelectMetadata smd = new SelectMetadata();
		
		smd.setSql(sql+whereClause);
		smd.setSqlCount("SELECT COUNT(distinct ID) as counter FROM pdoi_response_subject "+ whereClause);			
		return smd;
	}
	
	
	private ArrayList<ResponseSubject> getSubjectsfromAdmRegister(Date dat) throws UnexpectedResultException, DbErrorException {
		
		TreeSet<String> tip1 = new TreeSet<String>();
		TreeSet<String> tip2 = new TreeSet<String>();
		List<SystemClassif> classif = sd.getSysClassification(Constants.CODE_SYSCLASS_VID_SUBJECT, dat, Constants.CODE_DEFAULT_LANG, -1L);
		
		
		ArrayList<ResponseSubject> subjects = new ArrayList<ResponseSubject>();
		try {
			URL url = new URL("https://iisda.government.bg/Services/RAS/RAS.Integration.Host/BatchInfoService.svc?singleWsdl");
			QName qname = new QName("http://iisda.government.bg/RAS/IntegrationServices", "BatchInfoService");
			//		http://wsmf.delo.indexbg.com
			Service service = Service.create(url, qname);

			// Hello helloPort = service.getHelloPort();
			IBatchInfoService sPort = (IBatchInfoService) service
					.getPort(IBatchInfoService.class,new AddressingFeature(true, true));
			
			BatchStatusEnum status = BatchStatusEnum.ACTIVE;
			ArrayOfBatchIdentificationInfoType searchBatchesIdentificationInfo = sPort.searchBatchesIdentificationInfo(null, null, null, null, status , DateUtils.toXMLGregorianCalendar(dat), null);
			@SuppressWarnings("unused")
						
			List<BatchIdentificationInfoType> organizations = searchBatchesIdentificationInfo.getBatchIdentificationInfoType();
			
			for (BatchIdentificationInfoType org : organizations) {
				
				
				ArrayOfString batchIdentificationNumber = new ArrayOfString() ;
				batchIdentificationNumber .getString().add(org.getIdentificationNumber());
				
							
				 ArrayOfBatchType all = sPort.getBatchDetailedInfo(batchIdentificationNumber, DateUtils.toXMLGregorianCalendar(dat), null);
				 if (all != null && all.getBatchType() != null && all.getBatchType().size() == 1) {
					 tip1.add(all.getBatchType().get(0).getAdmStructureKind().value());
					 tip2.add(all.getBatchType().get(0).getType().value());
					 ResponseSubject res = convertToResponseSubject(all.getBatchType().get(0), classif);
					 if (res != null) {
						 subjects.add(res);
					 }
				 }
			}	 	
		} catch (Exception e) {
			LOGGER.error("Error in IBatchInfoService Web Service !", e);
			throw new UnexpectedResultException("Грешка при извикване на административния регистър !", e);
		}
		
		System.out.println(tip1);
		System.out.println(tip2);
		return subjects;
	}
	
	@SuppressWarnings("unchecked")
	private ResponseSubject convertToResponseSubject(BatchType batchType, List<SystemClassif> classif) throws UnexpectedResultException {
		try {
			
			
			
			
			
			ResponseSubject subject = new ResponseSubject();
			subject.setAdmRegister(true);
			subject.setSubjectName(batchType.getName());
			subject.setNomerRegister(batchType.getIdentificationNumber());
			subject.setDateTo(null);
			
//			if (subject.getSubjectName().equals("Антидопингов център")) {
//				System.out.println("dsfsd");
//			}
			
			
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");				
			subject.setDateFrom(sdf.parse("01.01.1900"));			
			
			
			
			if (batchType.getAdmStructureKind() != null) {
				
				Long admCode = null;
				for (SystemClassif item : classif) {
					if (item.getCodeExt() != null && item.getCodeExt().equalsIgnoreCase(batchType.getAdmStructureKind().value())) {
						admCode = item.getCode();
						break;
					}					
				}
				if (admCode!= null) {
					subject.setAdmLevel(admCode);
				}else {
					subject.setAdmLevel(Constants.CODE_ZNACHENIE_DRUGI_SUBJECT);
				}
				
				
				//subject.setAddInfo(batchType.getAdmStructureKind().value());
			}else {
				subject.setAdmRegister(true);
				subject.setAdmLevel(Constants.CODE_ZNACHENIE_DRUGI_SUBJECT);
			}
			
			
			
			if (batchType.getAdministration() != null) {
				
				if (batchType.getAdministration().getUIC() != null) {
					subject.setEik(batchType.getAdministration().getUIC());
				}else {
					subject.setEik("N/A");
				}
				
				if (batchType.getAdministration().getAddress() != null) {
					long ekatte = Long.valueOf(batchType.getAdministration().getAddress().getEkatteAddress().getSettlementEkatteCode());
					subject.setAddress(batchType.getAdministration().getAddress().getAddressText());
					subject.setTown(ekatte);
					if (batchType.getAdministration().getAddress().getEkatteAddress() != null && batchType.getAdministration().getAddress().getEkatteAddress().getSettlementEkatteCode() != null) {
						Query q = JPA.getUtil().getEntityManager().createNativeQuery("select ekatte_oblasti.id obl_id, ekatte_obstini.id obsht_id from ekatte_att, ekatte_oblasti, ekatte_obstini where ekatte_att.oblast = ekatte_oblasti.oblast and  ekatte_att.obstina = ekatte_obstini.obstina and  ekatte_att.ekatte = :cod");
						q.setParameter("cod", ekatte);
						
						List<Object[]> mesta = q.getResultList();
						if (mesta.size() == 1) {
							subject.setMunicipality(SearchUtils.asLong(mesta.get(0)[1]));
							subject.setRegion(SearchUtils.asLong(mesta.get(0)[0]));
						}
					}
					
					
				}
				
				if (batchType.getAdministration().getCorrespondenceData() != null) {
					//String code = batchType.getAdministration().getCorrespondenceData().getInterSettlementCallingCode();
				    
					String phone = "";
					for (CorrespondenceDataPhoneType tek : batchType.getAdministration().getCorrespondenceData().getPhone()) {
						//if (tek.isIncludesSettlementCallCode()) {
							phone += tek.getPhoneNumber() + "; ";
	//					}else {
	//						phone += code + " " + tek.getPhoneNumber() + ";";
	//					}
						
					}			
					subject.setPhone(phone);
					
					subject.setFax(batchType.getAdministration().getCorrespondenceData().getFaxNumber());
					subject.setEmail(batchType.getAdministration().getCorrespondenceData().getEmail());
				}
				
			}else {				
				//return null;
				subject.setEik("N/A");				
			}
			
			
				
			
			
			
			
			
	//		if (batchType.getAdministration() != null && batchType.getAdministration().getAddress() != null) {
	//			subject.setAddress(batchType.getAdministration().getAddress().getAddressText());
	//		}
		
		
		
		return subject;
		} catch (Exception e) {
			LOGGER.error("Error in IBatchInfoService Web Service !", e);
			throw new UnexpectedResultException("Грешка при извикване и обработване на данни от  административния регистър !", e);
		}
	}
	
	
	
	/** Търсене на задлжени субекти по номер от адм.регистър
	 * @param nomRegister
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	private ResponseSubject findByNomReg(String nomRegister) throws DbErrorException {
		try {
			Query q = JPA.getUtil().getEntityManager().createQuery("from  ResponseSubject where nomerRegister = :nom");
			q.setParameter("nom", nomRegister);
			List<ResponseSubject> subjects = q.getResultList();
			if (subjects.size() > 0) {
				return subjects.get(0);
			}else {
				return null;
			}
			
			
		} catch (Exception e) {
			throw new DbErrorException("Грешка при извикаване на запис за задължен субек по номер от адм. регистър", e);
		}
		
	}
	
	/** Търсене на задължени субекти по ЕИК 
	 * @param eik
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public ResponseSubject findByEik(String eik) throws DbErrorException {
		try {
			Query q = JPA.getUtil().getEntityManager().createQuery("from  ResponseSubject where eik = :eik");
			q.setParameter("eik", eik);
			List<ResponseSubject> subjects = q.getResultList();
			if (subjects.size() > 0) {
				return subjects.get(0);
			}else {
				return null;
			}
			
			
		} catch (Exception e) {
			throw new DbErrorException("Грешка при извикаване на запис за задължен субек по ЕИК", e);
		}
		
	}
	
	private void mergeResSubject(ResponseSubject oldSubj, ResponseSubject newSubj) {
		
		oldSubj.setAddInfo(newSubj.getAddInfo());
		oldSubj.setAddress(newSubj.getAddress());
		oldSubj.setAdmLevel(newSubj.getAdmLevel());
		oldSubj.setAdmRegister(newSubj.getAdmRegister());		
		oldSubj.setEik(newSubj.getEik());
		oldSubj.setEmail(newSubj.getEmail());
		oldSubj.setFax(newSubj.getFax());
		oldSubj.setMunicipality(newSubj.getMunicipality());
		oldSubj.setNomerRegister(newSubj.getNomerRegister());
		oldSubj.setPhone(newSubj.getPhone());
		oldSubj.setRegion(newSubj.getRegion());
		oldSubj.setSubjectName(newSubj.getSubjectName());
		oldSubj.setTown(newSubj.getTown());
		//oldSubj.setZipCode(newSubj.getZipCode());
		
	}
	
	
	private ArrayList<String> getClosedSujectsIds(Date dat) throws UnexpectedResultException {
		
		ArrayList<String> ids = new ArrayList<String>();
		
		try {
			URL url = new URL("https://iisda.government.bg/Services/RAS/RAS.Integration.Host/BatchInfoService.svc?singleWsdl");
			QName qname = new QName("http://iisda.government.bg/RAS/IntegrationServices", "BatchInfoService");
			//		http://wsmf.delo.indexbg.com
			Service service = Service.create(url, qname);

			// Hello helloPort = service.getHelloPort();
			IBatchInfoService sPort = (IBatchInfoService) service
					.getPort(IBatchInfoService.class,new AddressingFeature(true, true));
			
			BatchStatusEnum status = BatchStatusEnum.CLOSED;
			ArrayOfBatchIdentificationInfoType searchBatchesIdentificationInfo = sPort.searchBatchesIdentificationInfo(null, null, null, null, status , DateUtils.toXMLGregorianCalendar(dat), null);
			List<BatchIdentificationInfoType> organizations = searchBatchesIdentificationInfo.getBatchIdentificationInfoType();			
			for (BatchIdentificationInfoType org : organizations) {
				ids.add(org.getIdentificationNumber());
			}
			
			return ids;
			
		} catch (Exception e) {
			throw new UnexpectedResultException("Грешка при извикаване търсене на неактивни субекти", e);
		}
		
	}
	
	
	
	public String updateAdmRegisterEntries() throws DbErrorException, UnexpectedResultException {
		
		long brUpdated = 0;
		long brInserted = 0;
		long brClosed = 0;
		
		try {
			JPA.getUtil().begin();
			
			ArrayList<ResponseSubject> records = getSubjectsfromAdmRegister(new Date());
			
			
			for (ResponseSubject tek: records) {
				System.out.println(tek.getSubjectName());
				ResponseSubject subject = findByNomReg(tek.getNomerRegister());
				if (subject != null) {
					mergeResSubject(subject, tek);
					save(subject);
					brUpdated++;
				}else {
					save(tek);
					brInserted++;
				}
			}
			
			ArrayList<String> ids = getClosedSujectsIds(new Date());
			for (String id : ids) {
				ResponseSubject subject = findByNomReg(id);				
				if (subject != null && subject.getDateTo() == null) {
					subject.setDateTo(new Date());
					save(subject);
					brClosed++;
				}
			}
			
			JPA.getUtil().commit();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			String result = "Дата и час на извикване : " + sdf.format(new Date()) + "\r\n";
			result += "Брой нови: " + brInserted + "\r\n";
			result += "Брой променени: " + brUpdated + "\r\n";
			result += "Брой затворени: " + brClosed + "\r\n";
			return result;
			
			
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("DBError in  adm register !", e);
			throw new DbErrorException("Грешка при запис на субекти от административния регистър !\r\n" + StringUtils.stack2string(e)); 
		} catch (UnexpectedResultException e) {
			JPA.getUtil().rollback();
			LOGGER.error("Unexpected error in adm registry load !");
			throw new UnexpectedResultException("Грешка при четене на данни за субекти от административния регистър !\r\n" + StringUtils.stack2string(e));
		}
	}
	
	
	public EgovOrganisations responseSubjectSEOS(Long idRS) throws DbErrorException{
		
		try{
			
			Query q = JPA.getUtil().getEntityManager().createNativeQuery("select eo.id ,eo.guid ,eo.parrent_guid, eo.eik ,eo.administrative_body_name  from pdoi_response_subject rs join egov_organisations eo on rs.eik = eo.eik where rs.id=?","filterEgovOrg");
			q.setParameter(1, idRS);
			
			if(q.getResultList()==null || q.getResultList().isEmpty()){
				return null;
			} else {
				return (EgovOrganisations) q.getResultList().get(0);
			}
	
			
		} catch (HibernateException e) {
			throw new DbErrorException(	"Грешка при търсене на EgovOrganisations", e);
		}
		
		
	}
	
	public boolean isResponseSubjectSEOS(Long idRS) throws DbErrorException{
		
		try{
			
			Query q = JPA.getUtil().getEntityManager().createNativeQuery("select 1 from pdoi_response_subject rs join egov_organisations eo on rs.eik = eo.eik where rs.id=?");
			q.setParameter(1, idRS);
			
			if(q.getResultList()==null || q.getResultList().isEmpty()){
				return false;
			} else {
				return true;
			}
	
			
		} catch (HibernateException e) {
			throw new DbErrorException(	"Грешка при търсене на EgovOrganisations", e);
		}
		
		
	}
	
	
}
