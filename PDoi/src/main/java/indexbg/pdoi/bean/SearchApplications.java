package indexbg.pdoi.bean;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;
import org.primefaces.component.datagrid.DataGrid;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelNOSQL;
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
	private String zadaljenSubText = "";
	private List<Long> selectedSubj=new ArrayList<Long>();
	private Long responseSubj;
	private String tematika = "";
	private String nomer;
	private List<Long> selectedThemas=new ArrayList<Long>();

	private Long pageHidden;
	
	public SearchApplications() {
		
	}
	
	/**
	 * Инициализира променливите в класа.
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initData(){
			try {
		   
				userID = getUserData().getUserId();		
				appDao =  new ApplicationDAO(userID, getSystemData());				
			} catch (ObjectNotFoundException e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
			catch (Exception e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
			
			//--------- param -------
			Map<String, Object> params  = (Map<String, Object>) getSessionScopeValue("sappSMDAttr");	
			
			if(params!=null){
				try {
					SelectMetadata smd = new SelectMetadata();
					smd.setSqlParameters(params);
					appList = new LazyDataModelNOSQL(smd, "registrationDate",SortOrder.DESCENDING);	
					
					dateFrom = (Date) params.get("dateFrom");
					dateTo = (Date) params.get("dateTo");
					text = (String) params.get("text");
					status = (Long) params.get("status");
					tematika = (String) params.get("tematika");
					selectedThemas = (List<Long>) params.get("selectedThemas");
					responseSubj = (Long) params.get("responseSubj");
					selectedSubj = (List<Long>) params.get("selectedSubj");			
					nomer = (String) params.get("nomer");					
					period =  (Long) getSessionScopeValue("period");
					zadaljenSubText = (String) getSessionScopeValue("zadaljenSubText");
					
					
					if(dateFrom!=null||dateTo!=null|| status!=null || (selectedThemas!=null && !selectedThemas.isEmpty()) || (nomer!=null && !nomer.isEmpty())) {
						PrimeFaces.current().executeScript("$('#advanced-search').slideToggle();");
					}
					
				} catch (Exception e) {
					LOGGER.error("Грешка при работа със системата!!!", e);	
					JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.exception"));					
				
				} finally {
					JPA.getUtil().closeConnection();
				}
								
				
			} else { // по подразбиране да извади нещо
				 actionSearch();
			}
	}
	
	public void changePage() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);		
		
		session.removeAttribute("sappPage");
		
		DataGrid d = (DataGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("appFilterForm:tbl");
		
		if(d!=null) { 
			
			addSessionScopeAttribute("sappPage", d.getFirst());		
		}
	}
	
	/**
	 *  Извежда списък с резултати по зададени критерии.
	 */
	public void actionSearch(){
		
		Date dateOt = dateFrom;
		Date dateDo = dateTo;
				
		boolean paramsData = true; 
		if(dateFrom == null && dateTo ==null && status == null && (selectedSubj==null || selectedSubj.isEmpty()) && (selectedThemas == null || selectedThemas.isEmpty())
				&& (nomer==null|| "".equals(nomer)) && (text==null|| "".equals(text)) ) {
			
			//JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.insertParameters"));	
			
			dateDo = new Date();
			dateOt = Date.from(ZonedDateTime.now().minusMonths(2).toInstant());
			
			paramsData = false;
		}  
		
		try {
			
			SelectMetadata smd;
//				smd = appDao.findApplications(dateFrom, dateTo,status,responseSubj,text,nomer,selectedThemas,null,false,true,null);
			smd = new SelectMetadata();
			Map<String, Object> params = new HashMap<String, Object>();
			smd.setSqlParameters(params);
			
			params.put("dateFrom", dateOt);
			params.put("dateTo", dateDo);
			params.put("text", text);
			params.put("status",status);
			params.put("tematika",tematika);
			params.put("selectedThemas",selectedThemas);
			params.put("responseSubj",responseSubj);
			params.put("selectedSubj",selectedSubj);
			params.put("nomer",nomer);
			
//			String defaultSortColumn = "A7";//A7 ,A0
			appList = new LazyDataModelNOSQL(smd, "registrationDate",SortOrder.DESCENDING);
			
			if(paramsData) {
				addSessionScopeAttribute("sappSMDAttr", params);
				addSessionScopeAttribute("zadaljenSubText", zadaljenSubText);
				addSessionScopeAttribute("period", period);
			}
			
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
		selectedSubj = new ArrayList<Long>();
		
		//mahame zapazenite parametri ot sesiqta
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		session.removeAttribute("sappSMDAttr");
		session.removeAttribute("zadaljenSubText");
		session.removeAttribute("period");
		session.removeAttribute("sappPage");
		
		DataGrid d = (DataGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("appFilterForm:tbl");
		d.setFirst(0);		
		
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
	
	public List<Long> getSelectedSubj() {
		return selectedSubj;
	}

	public void setSelectedSubj(List<Long> selectedSubj) {
		this.selectedSubj = selectedSubj;
	}

	public Long getPageHidden() {
		if(pageHidden==null) { pageHidden=1L;
			if(getSessionScopeValue("sappPage") != null) { 
				
				
				DataGrid d = (DataGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("appFilterForm:tbl");
				
				if(d!=null) { 
					
					int page = (int) getSessionScopeValue("sappPage");
					d.setFirst(page); 
				}
			}
		}
		return pageHidden;
	}

	public void setPageHidden(Long pageHidden) {
		this.pageHidden = pageHidden;
	}	
	
}
