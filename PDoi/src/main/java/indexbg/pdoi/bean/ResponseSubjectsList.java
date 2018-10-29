package indexbg.pdoi.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.dao.ResponseSubjectDao;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

/**
 * @author idineva
 *
 */
@ManagedBean(name = "responseSubjectsList")
@ViewScoped
public class ResponseSubjectsList extends PDoiBean {	
	
	/**
	 * Клас за търсене на задължени субекти.Изкарва списък на ЗС по зададени критерии.
	 */
	private static final long serialVersionUID = 8534288990429849550L;

	static final Logger LOGGER = LoggerFactory.getLogger(ResponseSubjectsList.class);
	
	
    private ResponseSubjectDao rsDao;
	private String name;
	private String eik;
	private LazyDataModelSQL2Array rsList = null;	
	
	public ResponseSubjectsList() {
		
	}
	
	/**
	 * Инициализира променливите в класа
	 */
	@PostConstruct
	public void initData(){
			try {
				rsDao =  new ResponseSubjectDao(getUserData().getUserId());
			} catch (ObjectNotFoundException e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
			catch (Exception e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
	}
	
	
	/**
	 * Метод за търсене на ЗС
	 */
	public void actionSearch(){
		try {		  
				SelectMetadata smd = rsDao.findSubjects(eik, name);
				String defaultSortColumn = "A2";
				rsList = new LazyDataModelSQL2Array(smd, defaultSortColumn);  
						

			//}
		} catch (DbErrorException e) {
			LOGGER.error("Грешка при търсене на задължени субекти! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
		} catch (Exception e) {
			LOGGER.error("Грешка при работа със системата!!!", e);	
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.exception"));					
		
		}finally {
			JPA.getUtil().closeConnection();
		}
	}
	
	/**
	 * Зачистване на зададените от потребителя стойности за търсене
	 */
	public void actionClear(){
		name="";
		eik="";
		rsList = null;
	}
	
	/** Изтрива избран от списъка с резултати задължен субект
	 * @param id
	 */
	public void actionDelete(Long id){
		
		if(id!=null){
			try {
				JPA.getUtil().begin();
				rsDao.deleteById(id);
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
		}		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEik() {
		return eik;
	}

	public void setEik(String eik) {
		this.eik = eik;
	}

	public LazyDataModelSQL2Array getRsList() {
		return rsList;
	}

	public void setRsList(LazyDataModelSQL2Array rsList) {
		this.rsList = rsList;
	}
	
	
	


}
