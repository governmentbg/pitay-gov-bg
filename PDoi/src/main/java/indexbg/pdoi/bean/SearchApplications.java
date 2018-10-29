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
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.dao.ApplicationDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

/**
 * @author idineva
 *
 */

@ManagedBean(name = "searchApplications")
@ViewScoped
public class SearchApplications extends PDoiBean {	
	
	/**
	 * Търсене на заявления по зададени критерии от външни потребители.
	 */
	private static final long serialVersionUID = 8534288990429849550L;

	static final Logger LOGGER = LoggerFactory.getLogger(SearchApplications.class);
	
	private ApplicationDAO appDao;
	private LazyDataModel appList = null;
    private Long userID;

	private String text = "";
	
	private Long period;
	private Date dateFrom;
	private Date dateTo;
	private Long status;
	private String zadaljenSubText;	
	private Long responseSubj;
	private String tematika = "";
	private String nomer;
	private List<Long> selectedThemas=new ArrayList<Long>();

	
	public SearchApplications() {
		
	}
	
	/**
	 * Инициализира променливите в класа.
	 */
	@PostConstruct
	public void initData(){
			try {
		   
				userID = getUserData().getUserId();		
				appDao =  new ApplicationDAO( userID);				
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
	 *  Извежда списък с резултати по зададени критерии.
	 */
	public void actionSearch(){
		try {
		
			SelectMetadata smd;
//				smd = appDao.findApplications(dateFrom, dateTo,status,responseSubj,text,nomer,selectedThemas,null,false,true,null);
			smd = new SelectMetadata();
			Map<String, Object> params = new HashMap();
			smd.setSqlParameters(params);
			params.put("dateFrom", dateFrom);
			params.put("dateTo", dateTo);
			params.put("text", text);
			params.put("status",status);
			params.put("tematika",tematika);
			params.put("responseSubj",responseSubj);
			params.put("nomer",nomer);


//			String defaultSortColumn = "A7";//A7 ,A0
			appList = new LazyDataModelNOSQL(smd, null);
						
//		} catch (DbErrorException e) {
//			LOGGER.error("Грешка при търсене на заявления! ", e);
//			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
		} catch (Exception e) {
			LOGGER.error("Грешка при работа със системата!!!", e);	
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.exception"));					
		
		}finally {
			JPA.getUtil().closeConnection();
		}
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
		responseSubj = null;
		zadaljenSubText = "";
		text = "";
		nomer = "";
		tematika = "";
		selectedThemas = new ArrayList<Long>();
	}
	
	
	public LazyDataModel getAppList() {
		return appList;
	}

	public void setAppList(LazyDataModel appList) {
		this.appList = appList;
	}
	
	public Date getToday(){
		return new Date();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
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

	public String getZadaljenSubText() {
		return zadaljenSubText;
	}

	public void setZadaljenSubText(String zadaljenSubText) {
		this.zadaljenSubText = zadaljenSubText;
	}

	public Long getResponseSubj() {
		return responseSubj;
	}

	public void setResponseSubj(Long responseSubj) {
		this.responseSubj = responseSubj;
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

	public List<Long> getSelectedThemas() {
		return selectedThemas;
	}

	public void setSelectedThemas(List<Long> selectedThemas) {
		this.selectedThemas = selectedThemas;
	}


}
