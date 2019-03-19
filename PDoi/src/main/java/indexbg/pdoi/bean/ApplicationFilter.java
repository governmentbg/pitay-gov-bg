package indexbg.pdoi.bean;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.indexbg.system.pagination.LazyDataModelNOSQL;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.dao.ApplicationDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.Locker;
import indexbg.pdoi.system.PDoiBean;

/**
 * @author idineva
 *
 */

@ManagedBean(name = "applicationFilter")
@ViewScoped
public class ApplicationFilter extends PDoiBean {	
	
	/**Клас,който служи за аадминистриране на заявления.
	 * 
	 */
	private static final long serialVersionUID = 8534288990429849550L;

	static final Logger LOGGER = LoggerFactory.getLogger(ApplicationFilter.class);
	
	private ApplicationDAO appDao;
	private LazyDataModel appList = null;
	private LazyDataModelSQL2Array subscriptionList = null;	
	private Long userId;
	private Long period;
	private Date dateFrom;
	private Date dateTo;
	private Long status;
	private String zadaljenSubText;	
	private Long responseSubj;
	private String text = "";
	private String tematika = "";
	private String nomer;
	private List<Long> selectedThemas=new ArrayList<Long>();
	private Long typeUser = null;
	
	public ApplicationFilter() {
		
	}
	
	/** Инициализира променливите и зарежда задължения субект на администратора.
	 * 
	 */
	@PostConstruct
	public void initData(){
			try {
				
			   
				userId = getUserData().getUserId();
				typeUser = getUserData().getTypeUser();
				appDao =  new ApplicationDAO(userId, getSystemData());		
				if(typeUser!=null && typeUser.longValue() != Constants.CODE_ZNACHENIE_TIP_POTR_VANSHEN) {
					responseSubj = getUserData().getCodeOrg();
					zadaljenSubText = getSystemData().decodeItem(Constants.CODE_SYSCLASS_ADM_REGISTRY, responseSubj, getCurrentLang(), new Date(), userId);
					
					SelectMetadata smd;

//					smd = appDao.findApplications(dateFrom, dateTo,status,responseSubj,text,nomer,selectedThemas,null,true,false,userId);
					smd = new SelectMetadata();
					smd.setSqlParameters(getSqlParameters());
					smd.getSqlParameters().put("fromAdmin","true");
					String defaultSortColumn = "registrationDate";
					appList = new LazyDataModelNOSQL(smd, defaultSortColumn);
				}			
			} catch (ObjectNotFoundException e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
			catch (Exception e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}finally {
				JPA.getUtil().closeConnection();
			}
	}
	
	
	/**
	 *  Търсене на заявления по зададени от потребителя критеии.
	 */
	public void actionSearch(){
		try {
			
			
		
			SelectMetadata smd;
			
			
				smd = appDao.findApplications(dateFrom, dateTo,status,responseSubj,text,nomer,selectedThemas,null,false,false,userId);
				String defaultSortColumn = "A2";
				appList = new LazyDataModelSQL2Array(smd, defaultSortColumn);  
						
		} catch (DbErrorException e) {
			LOGGER.error("Грешка при търсене на заявления! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
		} catch (Exception e) {
			LOGGER.error("Грешка при работа със системата!!!", e);	
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.exception"));					
		
		}finally {
			JPA.getUtil().closeConnection();
		}
	}

	/**
	 *  Пълнотекстово търсене на заявления по зададени от потребителя критеии.
	 */
	public void actionFullTextSearch() {
		SelectMetadata smd = new SelectMetadata();
		smd.setSqlParameters(getSqlParameters());
		appList = new LazyDataModelNOSQL(smd, "registrationDate");
	}

	private Map<String, Object> getSqlParameters() {
		Map<String, Object> params = new HashMap();

		params.put("dateFrom", dateFrom);
		params.put("dateTo", dateTo);
		params.put("text", text);
		params.put("status",status);
		params.put("selectedThemas",selectedThemas);
		params.put("responseSubj",responseSubj);
		params.put("nomer",nomer);
		return params;
	}

	/**Метод за смяна на датите при избор на период за търсене.
	 * 
	 */
	public void changePeriod () {
    	if (this.period != null) {
			Date[] di;
			di = DateUtils.calculatePeriod(this.period);
			this.setDateFrom(di[0]);
			this.setDateTo(di[1]);
		} else {
			this.setDateFrom(null);
			this.setDateTo(null);
		}
		return ;
    }
	
	/**
	 * Зачистване на зададените критерии за търсене
	 */
	public void actionClear(){
		
		appList = null;
		period = null;
		dateFrom = null;
		dateTo = null;
		status = null;
		if(typeUser!=null && typeUser.longValue() == Constants.CODE_ZNACHENIE_TIP_POTR_VATR) {
			responseSubj = null;
			zadaljenSubText = "";
		}
		
		text = "";
		nomer = "";
		tematika = "";
		selectedThemas = new ArrayList<Long>();
	}
	
	public String  actionGoToEdit() {
		
		String idObj = JSFUtils.getRequestParameter("idObj");
		
		if(idObj!=null && !idObj.isEmpty()){
		Locker locker = new Locker();
		String isLocked="";
			try {
				isLocked = locker.isObjectLocked(Long.valueOf(idObj), Constants.CODE_OBJECT_APPLICATION, getUserId());
			} catch (DbErrorException e) {
				LOGGER.error(e.getMessage(),e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
				
			} catch (Exception e) {
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
				LOGGER.error(e.getMessage(), e);
				
			} finally {
				JPA.getUtil().closeConnection();
			}
			if(isLocked!=null &&!"".equals(isLocked)) {
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"app.appInUse"));
			    return "";
			}
		}
		
		
		return "applicationForm.jsf";
	}

	public LazyDataModel getAppList() {
		return appList;
	}

	public void setAppList(LazyDataModelSQL2Array appList) {
		this.appList = appList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Date getToday(){
		return new Date();
	}

	public Long getPeriod() {
		return period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getResponseSubj() {
		return responseSubj;
	}

	public void setResponseSubj(Long responseSubj) {
		this.responseSubj = responseSubj;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTematika() {
		return tematika;
	}

	public void setTematika(String tematika) {
		this.tematika = tematika;
	}

	public String getNomer() {
		return nomer;
	}

	public void setNomer(String nomer) {
		this.nomer = nomer;
	}

	public String getZadaljenSubText() {
		return zadaljenSubText;
	}

	public void setZadaljenSubText(String zadaljenSubText) {
		this.zadaljenSubText = zadaljenSubText;
	}

	public List<Long> getSelectedThemas() {
		return selectedThemas;
	}

	public void setSelectedThemas(List<Long> selectedThemas) {
		this.selectedThemas = selectedThemas;
	}

	public LazyDataModelSQL2Array getSubscriptionList() {
		return subscriptionList;
	}

	public void setSubscriptionList(LazyDataModelSQL2Array subscriptionList) {
		this.subscriptionList = subscriptionList;
	}

	public Long getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(Long typeUser) {
		this.typeUser = typeUser;
	}
}
