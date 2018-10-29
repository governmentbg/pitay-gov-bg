package indexbg.pdoi.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.db.dto.SystemClassif;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;
import com.indexbg.system.utils.ValidationUtils;

import indexbg.pdoi.db.ResponseSubject;
import indexbg.pdoi.db.dao.ResponseSubjectDao;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

@ManagedBean(name = "responseSubjects")
@ViewScoped
public class ResponseSubjects extends PDoiBean {	
	
	/**
	 * Клас за въвеждане и актуализация на задължени субекти
	 */
	private static final long serialVersionUID = 8534288990429849550L;

	static final Logger LOGGER = LoggerFactory.getLogger(ResponseSubjects.class);
	
	private ResponseSubject rs = new ResponseSubject();
    private ResponseSubjectDao rsDao;
    private Long userId;
	
    private List<SystemClassif> oblastList;
	private List<SystemClassif> obshtiniList = new ArrayList<SystemClassif>();
	private List<SystemClassif> nasMestoList = new ArrayList<SystemClassif>();
    
	public ResponseSubjects() {
		
	}
	
	/**Метод,който зарежда зададения за редакция задължен субект,или инициализира нов,ако не е подаден ззапис за редакция.
	 * 
	 */
	@PostConstruct
	public void initData(){
			try {
				this.userId = getUserData().getUserId();
				rsDao =  new ResponseSubjectDao(userId);
				String idObj =JSFUtils.getRequestParameter("idObj");
				if (idObj!=null) {
					rs = rsDao.findById(Long.valueOf(idObj));
					fillObshtina();
					actionChangeObshtina();
				}else {
					rs = new ResponseSubject();
					rs.setDateFrom(new Date());
				}
			} catch (ObjectNotFoundException e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
			catch (Exception e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
	}
	
	/**Запазва направените промени или новия задължен субект.
	 * 
	 */
	public void actionSave() {
		if(checkData()){
			return;
		}
		try {
			rs.setAdmRegister(false);
			JPA.getUtil().begin();
			rsDao.save(rs);
			JPA.getUtil().commit();
			
			// Тук се прави подготовка за актуализация на кешираната динамична класификация, за да не се налага рестартирането на сървъра/ИЦ
			getSystemData().reloadClassif(Constants.CODE_SYSCLASS_ADM_REGISTRY);
			
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesSaveMsg"));	
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при запис на задължен субект! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
		
		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при работа със системата!!!", e);	
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.exception"));					
		
		}finally {
			JPA.getUtil().closeConnection();
		}
		
	}
	
	/**Зачиства обекта,за да може да се въведе нов задължен субект.
	 * 
	 */
	public void actionNew() {
		
		rs= new ResponseSubject();
	}
	
	/** проверява коректността на въведените от потребителя стойности и изкарва съобщения при намиране на грешка.
	 * @return
	 */
	private boolean checkData() {
		
		boolean flagSave = false;
		
		if(rs.getEik()==null || "".equals(rs.getEik())){
			JSFUtils.addMessage("respSubForm:eik",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.pleaseInsert",getMessageResourceString("labels", "responseSubjects.eik")));	
	
			flagSave=true;
		}
		
          if(rs.getEik()!=null && !"".equals(rs.getEik()) && !ValidationUtils.isValidBULSTAT(rs.getEik())) {
			
			JSFUtils.addMessage("respSubForm:eik",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.errEik"));						
			flagSave=true;
          }
		
		if(rs.getAdmLevel()==null){
			JSFUtils.addMessage("respSubForm:admLevel",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.pleaseInsert",getMessageResourceString("labels", "responseSubjects.nivo")));		
			flagSave=true;
		}
		
		if(rs.getSubjectName()==null || "".equals(rs.getSubjectName())){
			JSFUtils.addMessage("respSubForm:name",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.pleaseInsert",getMessageResourceString("labels", "general.name")));		
			flagSave=true;
		}
		
		if(rs.getDateFrom()==null){
			JSFUtils.addMessage("respSubForm:dateFrom",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.pleaseInsert",getMessageResourceString("labels", "general.dataOt")));		
			flagSave=true;
		}
		
		if(rs.getDateFrom()!=null && rs.getDateTo()!=null && rs.getDateFrom().after(rs.getDateTo())){
			JSFUtils.addMessage("respSubForm:dateTo",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.errDateFromDateTo"));		
			flagSave=true;
		}
		
		if(rs.getRegion()==null ){
			JSFUtils.addMessage("respSubForm:oblast",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.pleaseInsert",getMessageResourceString("labels", "general.oblast")));		
			flagSave=true;
		}
				
		if(rs.getMunicipality()==null ){
			JSFUtils.addMessage("respSubForm:obshtina",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.pleaseInsert",getMessageResourceString("labels", "general.obshtina")));		
			flagSave=true;
		}
		
		if(rs.getTown()==null){
			JSFUtils.addMessage("respSubForm:nasMiasto",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.pleaseInsert",getMessageResourceString("labels", "general.nasMiasto")));		
			flagSave=true;
		}
		
		if(rs.getAddress()==null || "".equals(rs.getAddress())){
			JSFUtils.addMessage("respSubForm:address",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.pleaseInsert",getMessageResourceString("labels", "general.adress")));		
			flagSave=true;
		}
		
		if(rs.getEmail()!=null && !"".equals(rs.getEmail()) && !ValidationUtils.isEmailValid(rs.getEmail()) ) {
			
			JSFUtils.addMessage("respSubForm:mail",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.errEmail"));	
			flagSave=true;	
			
		}

		return flagSave;
	}
	
	/**Изтрива задължен субект
	 * 
	 */
	public void actionDelete(){
		if(rs.getId()!=null){
			try {
				JPA.getUtil().begin();
				rsDao.deleteById(rs.getId());
				JPA.getUtil().commit();	
				
				// Тук се прави подготовка за актуализация на кешираната динамична класификация, за да не се налага рестартирането на сървъра/ИЦ
				getSystemData().reloadClassif(Constants.CODE_SYSCLASS_ADM_REGISTRY);
				
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesDeleteMsg"));	
			} catch (DbErrorException e) {
				JPA.getUtil().rollback();
				LOGGER.error("Грешка при изтриване на задължен субект! ", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
			} catch (Exception e) {
				JPA.getUtil().rollback();
				LOGGER.error("Грешка при работа със системата!!!", e);	
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.exception"));					
			
			}finally {
				JPA.getUtil().closeConnection();
			}
			
			actionNew();
		}		
	}
	
	/**Проверява дали въведеното еик е валидно.Ако не е валидно,изкарва съобщение за грешка.
	 * Ако е валидно проверява в базата дали вече има запис с това еик.Ако се намери такъв запис,се извежда за редакция.
	 */
	public void checkValidEik() {
		
		if(rs.getEik()!=null && !"".equals(rs.getEik())) {
			
			if(!ValidationUtils.isValidBULSTAT(rs.getEik())) {
			
			JSFUtils.addMessage("respSubForm:eik",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.errEik"));	
			rs = new ResponseSubject();			
		    }else  {
		    	try {
					ResponseSubject rs1=  rsDao.findByEik(rs.getEik());
					if(rs1!=null) {
						rs = rs1;
						fillObshtina();
						actionChangeObshtina();
					}
				} catch (DbErrorException e) {
				
					LOGGER.error("Грешка при запис на задължен субект! ", e);				
				} catch (Exception e) {
					LOGGER.error("Грешка при работа със системата!!!", e);						
				}
		    }
	    }else {
	    	rs = new ResponseSubject();
	    }
	}
	
	/**Проверка за валидност на мейл.
	 * 
	 */
	public void checkValidMail() {
		
		if(rs.getEmail()!=null && !"".equals(rs.getEmail()) && !ValidationUtils.isEmailValid(rs.getEmail()) ) {
			
			JSFUtils.addMessage("respSubForm:mail",FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.errEmail"));	
						
		}
	}
	
	/** Зарежда списъка с областите.
	 * @param ekateList
	 * @param code
	 */
	private void filEkateList(List<SystemClassif> ekateList ,Long code) {
		
		ekateList.clear();
		
		if(code==null){
			return;
		}
		
		try {
			ekateList.addAll( getSystemData().getChildrenOnNextLevel(Constants.CODE_SYSCLASS_EKATTE, code.longValue(),  new Date(), getCurrentLang(), userId) );
					
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
		} catch (Exception e) {
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
			LOGGER.error(e.getMessage(), e);
		} finally {
			JPA.getUtil().closeConnection();
		}
		
	}
	
	/**
	 * При избор на област зарежда списъка с общините
	 */
	public void actionChangeOblast(){
		fillObshtina();
		nasMestoList.clear();
		rs.setTown(null);
	}
	
	private void fillObshtina() {
		if(rs.getRegion()!=null){
			filEkateList(obshtiniList ,rs.getRegion());
		} else {
			obshtiniList.clear();
			rs.setMunicipality(null);
		}
	}
	
	/**
	 * При избор на община зарежда списъка с населени места.
	 */
	public void actionChangeObshtina(){
		if(rs.getMunicipality()!=null){
			filEkateList(nasMestoList ,rs.getMunicipality());
		} else {
			nasMestoList.clear();
			rs.setTown(null);
		}
	}

	public ResponseSubject getRs() {
		return rs;
	}

	public void setRs(ResponseSubject rs) {
		this.rs = rs;
	}	
	
	public Date getToday(){
		return new Date();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<SystemClassif> getOblastList() {
		if(oblastList==null){
			oblastList = new ArrayList<SystemClassif>();
			filEkateList(oblastList ,0L);
		}
		return oblastList;
	}

	public void setOblastList(List<SystemClassif> oblastList) {
		this.oblastList = oblastList;
	}

	public List<SystemClassif> getObshtiniList() {
		return obshtiniList;
	}

	public void setObshtiniList(List<SystemClassif> obshtiniList) {
		this.obshtiniList = obshtiniList;
	}

	public List<SystemClassif> getNasMestoList() {
		return nasMestoList;
	}

	public void setNasMestoList(List<SystemClassif> nasMestoList) {
		this.nasMestoList = nasMestoList;
	}
	

}
