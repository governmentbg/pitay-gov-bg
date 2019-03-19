package indexbg.pdoi.system;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import com.indexbg.ocr.dao.ApplicationTree;
import com.indexbg.system.BaseBean;
import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.Files;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**Основен клас, който трябва да наследяват всички бийнове.
 * Тук има методи които са полезни във всички бийнове
 * 
 * @author krasi
 *
 */
public class PDoiBean extends BaseBean {


	private static final long serialVersionUID = 5602813538155040944L;

	
	
	@Override
	protected UserData getUserData() throws ObjectNotFoundException {

		return (UserData)JSFUtils.getManagedBean("userData");
	}
	
	public boolean hasMessages(String clientId) {
		return JSFUtils.getFacesContext().getMessages(clientId).hasNext();
	    
	}

	//ako imame polence ot tablica ;)
	public boolean hasMessages(String prefixId,String numRow,String clientId) {
		
		return JSFUtils.getFacesContext().getMessages(prefixId+":"+numRow+":"+clientId).hasNext();
	    
	}
	
	public SystemData getSystemData(){
		return (SystemData) JSFUtils.getManagedBean("systemData");//(SystemData)super.getSystemData();
	}
	
	/**
	 * Method adds new key value pair into Flash scope of JSF 2 app
	 * @param key - key for value to be associated with 
	 * @param value - value to put in.
	 * @return - the previous value associated with key, or null if there was no mapping for key. 
	 */
	public final Object addFlashScopeValue(String key, Object value){
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getExternalContext().getFlash().put(key, value);
	}
	
	/**
	 * Method returns value added to flash scope
	 * @param key - key of the value
	 * @return - value
	 */
	public final Object getFlashScopeValue(String key){
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getExternalContext().getFlash().get(key);
	}
	
	/**
	 * Method adds new key value pair into Session scope of JSF 2 app
	 * @param key - key for value to be associated with 
	 * @param value - value to put in.
	 */
	public final void addSessionScopeAttribute(String key, Object value){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		if(session != null){
			session.setAttribute(key, value);
		}
	}
	
	/**
	 * Method returns value added to session scope and if it exists it will be removed and returned
	 * @param key - key of the value
	 * @return - value
	 */
	public final Object getSessionScopeValue(String key){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		if(session == null){
			return null;
		}else{
			Object value = session.getAttribute(key);
			//session.removeAttribute(key); - ne bi trqbvalo da se maha
			return value;
		}
	}
	
	/**
	 * Method redirects out of JSF scope to provided URL
	 * @param url - URL to redirect to
	 */
	public final void redirectExternal(String url) throws IOException{
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(url);
		} catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 * Method redirects to JSF based outcome
	 * @param url - outcome to redirect to
	 */
	public final void redirect(String url){
		FacesContext context = FacesContext.getCurrentInstance();
		context.getApplication().getNavigationHandler().handleNavigation(context, null, url);
	}
	
	/**
	 * Method adds new Faces Message to display 
	 * @param compId - component id related to this message
	 * @param message - message to display
	 */
	public final void displayFacesMessage(String compId, FacesMessage message){
		FacesContext fContext = FacesContext.getCurrentInstance();
		fContext.addMessage(compId, message);
	}
	
	/**
	 * Method is used to call different value expressions from bean's code.
	 * @param valueExpression - value expression to execute.
	 * @param returnType - return type of the value expression.
	 * @return - result of the invocation. 
	 */
	public final Object invokeValueExpression(String valueExpression, Class<?> returnType){
		FacesContext fContext = FacesContext.getCurrentInstance();
		ELContext elContext = fContext.getELContext();
		ExpressionFactory expFactory = fContext.getApplication().getExpressionFactory();
		ValueExpression vExpression = expFactory.createValueExpression(elContext, valueExpression, returnType);
		return vExpression.getValue(elContext);
	}
	
	/**
	 * Method is used to call different method expressions from bean's code
	 * @param expression - method expression to be executed.
	 * @param returnType - class of the return type of the method expression.
	 * @param parameters - array with class types of the parameters of the method.
	 * @param parameterValues - array with objects which are values of the parameters ordered into correct order
	 * @return result of the invocation. 
	 */
	public final Object invokeMethodExpression(String expression, Class<?> returnType, Class<?>[] parameters,Object[] parameterValues){
		FacesContext fContext = FacesContext.getCurrentInstance();
		ELContext elContext = fContext.getELContext();
		ExpressionFactory expFactory = fContext.getApplication().getExpressionFactory();
		MethodExpression mExpression = expFactory.createMethodExpression(elContext, expression, returnType, parameters);
		return mExpression.invoke(elContext, parameterValues);
	}
	
	
	/** Метода се използва за изчисляване на дни според денят на който се подава заявлението
	 * @param dayOfWeek - 
	 * @param days
	 * @return число
	 */
	public int calcPeriod(int dayOfWeek,int days){
		
		switch (dayOfWeek) {
	        case GregorianCalendar.FRIDAY:
	        	 return days+3;
	        case GregorianCalendar.SATURDAY:
	        	 return days+2;
	        case GregorianCalendar.SUNDAY:
	        	 return days+1;
	        default:
	        	return days;
	    } 
		
	}

	/**
	 * Пресъздава пълнотекстовия индекс на даден Application
	 * @param applicationId
	 */
	protected void indexApplication(Long applicationId) {
		Transaction tx = null;
		try {
			EntityManager entityManager = JPA.getUtil().getEntityManager();
			FullTextSession fullTextSession = Search.getFullTextSession(entityManager.unwrap(Session.class));
			tx = fullTextSession.beginTransaction();
			ApplicationTree app = entityManager.find(ApplicationTree.class, applicationId);
			fullTextSession.index(app);
		} finally {
			if(tx!=null && tx.getStatus()== TransactionStatus.ACTIVE)
				tx.commit();
		}
	}
	
	protected boolean checkForUploadedFileByName(String name, List<Files> filesList) {
		
		if(name==null) return false;
		
		for(Files f:filesList) {
			if(f.getFilename().equals(name)) {
				return true;
			}
			
		}
		return false;
	}
}
