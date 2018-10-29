package indexbg.pdoi.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;
import indexbg.pdoi.db.Publication;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;


@ManagedBean(name = "sectHome")
@ViewScoped
public class SectionHomeBean  extends PDoiBean{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4892486907501437603L;

	/**
	 * JavaBean клас за динамично зареждане на текст в началната страница на портала
	 */

	static final Logger LOGGER = LoggerFactory.getLogger(SectionHomeBean.class);
	
	private Long idUser=null;
	private Long codeSection=null;
	private List<Publication> publ = new ArrayList<Publication>();
	private HashMap<Long,String> dynText = new HashMap<Long,String>();
	
	
	/**
	 * Инициира/сетва първоначалните стойности на атрибутите, свързани с показване на данните в началната страница на портала. 
	 */
	@PostConstruct
	public void initData(){
		
		try{
			this.idUser=getUserData().getUserId();
		} catch (ObjectNotFoundException e) {
			this.idUser = -1L;
		}
		
		this.codeSection=Long.valueOf(Constants.CODE_ZNACHENIE_SECT_HOME);
		this.publ = new ArrayList<Publication>();
		this.dynText = new HashMap<Long,String>();
		
		loadHomeSectData(this.codeSection);

		
	}

	
	
	public Long getIdUser() {
		return idUser;
	}


	public Long getCodeSection() {
		return codeSection;
	}

	public Date getCurrentDate() {
		return new Date();
	}

	public Long getLang() {
		return getCurrentLang();
	}

	/** Метод за извличане данни за конкретна публикация от БД
	 * @param idPubl - ид.
	 */
	public void loadHomeSectData(Long idHomeSect){
		
		try {
		
			this.publ = (List<Publication>) new PublicationDAO(this.idUser).loadHomeData(this.codeSection, new Date());
			this.dynText.put(Long.valueOf(Constants.CODE_ZNACHENIE_LANG_BG), "");
			this.dynText.put(Long.valueOf(Constants.CODE_ZNACHENIE_LANG_EN), "");
			
			for (Publication item :this.publ){
				if (null != item){
					if (item.getOther()!=null && item.getOther().trim().toLowerCase().equals("bg")){
						this.dynText.put(Long.valueOf(Constants.CODE_ZNACHENIE_LANG_BG), item.getFullText());
					}else if (item.getOther()!=null && item.getOther().trim().toLowerCase().equals("en")){
						this.dynText.put(Long.valueOf(Constants.CODE_ZNACHENIE_LANG_EN), item.getFullText());
					}
				}
			}
			
			} catch (DbErrorException e) {
				LOGGER.error(e.getMessage(), e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
				
			} finally {
				JPA.getUtil().closeConnection();
			}

	}

	public List<Publication> getPubl() {
		return publ;
	}

	public void setPubl(List<Publication> publ) {
		this.publ = publ;
	}



	public HashMap<Long,String> getDynText() {
		return dynText;
	}



	public void setDynText(HashMap<Long,String> dynText) {
		this.dynText = dynText;
	}
	
}