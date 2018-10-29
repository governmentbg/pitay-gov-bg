package indexbg.pdoi.bean;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.system.PDoiBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.JSFUtils;


@ManagedBean(name = "sectList")
@ViewScoped
public class SectionListBean  extends PDoiBean{
	
	
	/**
	 * Основен javaBean клас за извличане на публикациите от дадена секция
	 * 
	 */
	private static final long serialVersionUID = -8378950879542749411L;
	static final Logger LOGGER = LoggerFactory.getLogger(SectionListBean.class);
	
	private Long idUser=null;
	private Long codeSection=null;
	private LazyDataModelSQL2Array sectListT = null;
	
	@PostConstruct
	
	
	/**
	 * Инициира/сетва първоначалните стойности на атрибутите, свързани с показване на публикациите от дадена секция. 
	 * Чете предадените параметри от други екрани
	 */
	public void initData(){
		
		try{
			this.idUser=getUserData().getUserId();
		} catch (ObjectNotFoundException e) {
			this.idUser = -1L;
		}
		
		this.codeSection=null;
		this.sectListT = null;
		
		String par =JSFUtils.getRequestParameter("codeSect");
		if (par != null && !par.trim().isEmpty()){
			this.codeSection=Long.valueOf(par);
		}
		if(null!=this.codeSection){
			actionFind();
		}else{
			LOGGER.error("null value parameter section!");
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, "Грешка при предаване на параметър-секция!");
			return;
		}
	}

	public Long getIdUser() {
		return idUser;
	}


	public Long getCodeSection() {
		return codeSection;
	}


	public LazyDataModelSQL2Array getSectListT() {
		return sectListT;
	}


	public void setSectListT(LazyDataModelSQL2Array sectListT) {
		this.sectListT = sectListT;
	}
	
	
	/** Метод за търсене в БД на публикации от дадена секция 
	 * 
	 */
	public void actionFind(){

		this.sectListT = null;

		SelectMetadata smd = null;
		
		PublicationDAO publDao = new PublicationDAO(this.idUser);
		
		try {
			smd = publDao.findSectFilter(new Date(), this.codeSection);
			String sortCol = "A0";
			this.sectListT = publDao.newLazyDataModel(smd, sortCol);
			
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
		} finally {
			JPA.getUtil().closeConnection();
		}
		return;
	}
	
	public Date getCurrentDate() {
		return new Date();
	}

	public Long getLang() {
		return getCurrentLang();
	}

	
}
