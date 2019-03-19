package indexbg.pdoi.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.datagrid.DataGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.dao.ApplicationDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

/**
 * @author idineva
 *
 */

@ManagedBean(name = "myApplications")
@ViewScoped
public class MyApplications extends PDoiBean {	
	
	/**Клас,който изкарва заявленията, които е подал потребител и тези,за които се е абонирал.
	 * 
	 */
	private static final long serialVersionUID = 8534288990429849550L;

	static final Logger LOGGER = LoggerFactory.getLogger(MyApplications.class);
	
	private ApplicationDAO appDao;
	private LazyDataModelSQL2Array appList = null;	
	private LazyDataModelSQL2Array subsList = null;	
    private Long userID;
    
    private Long initTables;

	
	public MyApplications() {
		
	}
	
	/**
	 * Метод,който зарежда списъците с подадени заявления и абонаменти.
	 */
	@PostConstruct
	public void initData(){
			try {
				String sappSortMyApp   = (String) getSessionScopeValue("sappSortMyApp");	
				String sappSortSubsApp = (String) getSessionScopeValue("sappSortSubsApp");
				
				if(sappSortMyApp==null) { sappSortMyApp = "A0"; }
				if(sappSortSubsApp==null) { sappSortSubsApp = "A0"; }
				
				userID = getUserData().getUserId();		
				appDao =  new ApplicationDAO(userID, getSystemData());
				SelectMetadata smd;
				smd = appDao.findApplications(null, null,null,null,null,null,null,userID,false,true,null);
				
				appList = new LazyDataModelSQL2Array(smd, sappSortMyApp);  
				
				SelectMetadata smd1;
				smd1 = appDao.findSubscriptions(userID);
			
				subsList = new LazyDataModelSQL2Array(smd1, sappSortSubsApp);  
				
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
	
	public void actionSort(String sortCol) {
		if(sortCol!=null && !"".equals(sortCol)) {
			try {
				
				SelectMetadata smd = appDao.findApplications(null, null,null,null,null,null,null,userID,false,true,null);
				String defaultSortColumn = sortCol; 
				appList = new LazyDataModelSQL2Array(smd, defaultSortColumn);  	
				
				addSessionScopeAttribute("sappSortMyApp", sortCol);
			}
			catch (Exception e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}finally {
				JPA.getUtil().closeConnection();
			}
		}
	}
	
	public void actionSortSubs(String sortCol) {
		if(sortCol!=null && !"".equals(sortCol)) {
			try {
				
				SelectMetadata smd = appDao.findSubscriptions(userID);
				
				subsList = new LazyDataModelSQL2Array(smd, sortCol); 
				
				addSessionScopeAttribute("sappSortSubsApp", sortCol);
			}
			catch (Exception e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}finally {
				JPA.getUtil().closeConnection();
			}
		}
	}
	
	public void changePageMyApp() {
		
		DataGrid d = (DataGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("appFilterForm:tbl");
		
		if(d!=null) { 
			addSessionScopeAttribute("sappPageMyApp", d.getFirst());		
		}
	}
	
	public void changePageSubsApp() {
		
		DataGrid d = (DataGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("appFilterForm:tbl1");
		
		if(d!=null) { 
			addSessionScopeAttribute("sappPageSubsApp", d.getFirst());		
		}
	}
	
	
	public LazyDataModelSQL2Array getAppList() {
		return appList;
	}

	public void setAppList(LazyDataModelSQL2Array appList) {
		this.appList = appList;
	}
	
	public Date getToday(){
		return new Date();
	}


	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public LazyDataModelSQL2Array getSubsList() {
		return subsList;
	}

	public void setSubsList(LazyDataModelSQL2Array subsList) {
		this.subsList = subsList;
	}

	public Long getInitTables() {
		
		if(initTables==null) { initTables=1L;
			if(getSessionScopeValue("sappPageMyApp") != null) { 
				DataGrid d = (DataGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("appFilterForm:tbl");
				if(d!=null) { 
					int page = (int) getSessionScopeValue("sappPageMyApp");
					d.setFirst(page); 
				}
			}
			if(getSessionScopeValue("sappPageSubsApp") != null) { 
				DataGrid d1 = (DataGrid) FacesContext.getCurrentInstance().getViewRoot().findComponent("appFilterForm:tbl1");
				if(d1!=null) { 
					int page1 = (int) getSessionScopeValue("sappPageSubsApp");
					d1.setFirst(page1); 
				}
			}
		
		}
				
		return initTables;
	}

	public void setInitTables(Long initTables) {
		this.initTables = initTables;
	}

}
