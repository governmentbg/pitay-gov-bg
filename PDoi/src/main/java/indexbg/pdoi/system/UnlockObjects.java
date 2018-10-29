package indexbg.pdoi.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/*import java.util.HashMap;
import java.util.Iterator;*/
import java.util.List;
//import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.InvalidParameterException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.JSFUtils;
//import com.indexbg.system.SysConstants;

//import indexbg.pdoi.db.Files;



@ManagedBean(name = "unlockObjects")
@ViewScoped
public class UnlockObjects extends PDoiBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006803456321838447L;

	static final Logger LOGGER = LoggerFactory.getLogger(UnlockObjects.class);

	
	private Long codeObj;
//	private List <SelectItem> objectsList;
	
	
	private String regNom;
	
	private Long idPeriod;
	private Long idUser=null;
	private Date dateFrom;
	private Date dateTo;
	private LazyDataModelSQL2Array lockedList = null;
	
	
	private List<Object[]> selListLocked = new ArrayList<Object[]>();
	
	@PostConstruct
	public void initData(){
		try{
			this.idUser=getUserData().getUserId();
		} catch (ObjectNotFoundException e) {
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.objectNotFound"), e.getMessage());
			LOGGER.error(e.getMessage(), e);
			this.idUser = -1L;
		}
		
		actionClear();
	}
    
	public void actionSearch(){
		
		try {
			SelectMetadata smd;
			smd = new Locker().createFilterLocked(codeObj, regNom, null, dateFrom, dateTo, this.idUser); // getUserData().getIdRegistratura()
			
			String defaultSortColumn = "A2";
			this.lockedList= newLazyDataModel(smd, defaultSortColumn); 
			
			
		} catch (DbErrorException e) {
			LOGGER.error("Грешка при търсене на заявления! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
		
		} catch (InvalidParameterException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());	

		}finally {
			JPA.getUtil().closeConnection();
		}
		
		
	}	
	
	
	/** Извлича данни от БД по зададен sql и атрибут за сортиране на данните
	 * @param smd - sql за избор на данни 
	 * @param defaultSortColumn - колона от таблица в базата данни, по която се сортират данните
	 * @return
	 * @throws DbErrorException
	 */
	public LazyDataModelSQL2Array newLazyDataModel (SelectMetadata smd, String defaultSortColumn ) throws DbErrorException { 
		   
		if (smd == null) return null;
		 LazyDataModelSQL2Array list = null;
		try {
							
			list = new LazyDataModelSQL2Array(smd, defaultSortColumn);  
				
		} catch (DbErrorException e) { 
			LOGGER.error(e.getMessage(), e);
			throw new DbErrorException (getMessageResourceString("beanMessages", "unLockObject.findError") +e.getCause().getMessage(), e);
		}
	
		return list;
	}
	
	public void actionClear(){
		regNom="";
		idPeriod=null;
		dateFrom=null;
		dateTo=null;
		
		this.setSelListLocked(new ArrayList<Object[]>());

	}

	public void actionUnlock(){
						
		boolean ok_ = false;
		
		if(null!=this.selListLocked && this.selListLocked.size()>0){
			List<Long> selIdLocked = new ArrayList<Long>(); 
			for (Object[] item: this.selListLocked) {
				if(null!=item[0])
					selIdLocked.add(Long.valueOf(item[0].toString()));
			}
			
			
			try {
				JPA.getUtil().begin();
				new Locker().unlockObjectArray(selIdLocked, codeObj);
				
				JPA.getUtil().commit();
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "unLockObject.succsesUnlockObject"));
				ok_=true;
			
			} catch (DbErrorException e) {
				LOGGER.error(e.getMessage(), e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
				JPA.getUtil().rollback();		
			} finally {
				JPA.getUtil().closeConnection();
			}
		}else{
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","unLockObject.selObject"));
		}
		
		if(ok_){ 
			this.setSelListLocked(new ArrayList<Object[]>());
			actionSearch();
		}
	}

	
	public void changePeriod () {
    	if (this.idPeriod != null) {
			Date[] di;
			di = DateUtils.calculatePeriod(this.idPeriod);
			this.setDateFrom(di[0]);
			this.setDateTo(di[1]);
		} else {
			this.setDateFrom(null);
			this.setDateTo(null);
		}
		return ;
    }


	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Date getDateTo() {
		return dateTo;
	}

	
	
	public Long getIdPeriod() {
		return idPeriod;
	}

	public void setIdPeriod(Long idPeriod) {
		this.idPeriod = idPeriod;
	}

	public void setRegNom(String regNom) {
		this.regNom = regNom;
	}

	public String getRegNom() {
		return regNom;
	}

	/*public void setObjectsList(List <SelectItem> objectsList) {
		this.objectsList = objectsList;
	}

	public List <SelectItem> getObjectsList() {
		if(objectsList==null){
			objectsList = new ArrayList<SelectItem>();
			String zajavlText="";
			try{	
				zajavlText=getSystemData().decodeItem(Constants.CODE_CLASSIF_OBJECTS, Constants.CODE_OBJECT_APPLICATION, getCurrentLang(), new Date(), this.idUser);
			} catch (DbErrorException e) {
			 	LOGGER.error(e.getMessage(), e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
				
			} finally {
				JPA.getUtil().closeConnection();
			}
			
			objectsList.add(new SelectItem(Constants.CODE_OBJECT_APPLICATION, zajavlText));
			
		}
		return objectsList;
	}*/

	public void setCodeObj(Long codeObj) {
		this.codeObj = codeObj;
	}

	public Long getCodeObj() {
		return codeObj;
	}

	
	public LazyDataModelSQL2Array getLockedList() {
		return lockedList;
	}

	public void setLockedList(LazyDataModelSQL2Array lockedList) {
		this.lockedList = lockedList;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public Date getCurrentDate() {
		return new Date();
	}
	
	/**Verify from Date and to Date
	 * @param nom
	 */
	public boolean checkDates(int nom){
		boolean rez=true;
		this.setIdPeriod(null);
		if (this.getDateFrom() != null && this.getDateTo() != null) {
			if(this.getDateFrom().compareTo(this.getDateTo()) > 0) {
				rez=false;
				if (nom==1){
				}else{
				}
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","publ.dateFromLessDateTo"));
			}
		} 
		return rez;
	}

	public List<Object[]> getSelListLocked() {
		return selListLocked;
	}

	public void setSelListLocked(List<Object[]> selListLocked) {
		this.selListLocked = selListLocked;
	}

}