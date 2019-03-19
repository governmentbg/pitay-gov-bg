package indexbg.pdoi.bean;

import java.util.Date;

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
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.Mail;
import indexbg.pdoi.db.dao.MailerDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;



@ManagedBean(name = "mailFilter")
@ViewScoped
public class MailFilter extends PDoiBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5946494787318312139L;
	
	static final Logger LOGGER = LoggerFactory.getLogger(MailFilter.class);
	
	private MailerDAO mDao;
	private LazyDataModelSQL2Array mailList = null;	
    private Long userID;
    
	private Long period;
	private Date dateFrom;
	private Date dateTo;
	
	//zdoi
	private String zdoiText;	
	private Long zdoiCode;
	
	private String email;
	private String nameLice;
	private String error;	
	private String uri;
	
	private String selMailError;

	public MailFilter() {
		
	}
	
	/**
	 * Зарежда се информацията, преди извикването на конструктора
	 */
	@PostConstruct
	public void initData(){
			try {
				userID = getUserData().getUserId();
				mDao =  new MailerDAO(userID);			
				

			} catch (ObjectNotFoundException e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
			catch (Exception e) {
				LOGGER.error("Грешка при работа със системата!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.exception"));
			}
	}
	
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
	 * Метод за зачистване на филтъра на търсенията
	 */
	public void actionClear(){
		
		setMailList(null);
		period = null;
		dateFrom = null;
		dateTo = null;
		
		email="";
		uri="";
		
		zdoiText="";	
		zdoiCode=null;
		
	
	}
	
	/**
	 * Метод, който търси според зададените критерии в таблицата със съобщения
	 */
	public void actionSearch(){
		try {		  
			
			SelectMetadata smd= mDao.createFilterMsgSQL(  uri,  email,  zdoiCode,  dateFrom,  dateTo);
			mailList = new LazyDataModelSQL2Array(smd, "A7_DATE_MAIL");  
						
		} catch (DbErrorException e) {
			LOGGER.error("Грешка при търсене на е-майл! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
		} catch (Exception e) {
			LOGGER.error("Грешка при работа със системата!!!", e);	
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.exception"));					
		
		}finally {
			JPA.getUtil().closeConnection();
		}
	}



	/*==================================================================================================
	 *  																									SETTERS && GETTERS 
	 *  ==================================================================================================*/
	
	public MailerDAO getmDao() {
		return mDao;
	}

	public void setmDao(MailerDAO mDao) {
		this.mDao = mDao;
	}

	public LazyDataModelSQL2Array getMailList() {
		return mailList;
	}

	public void setMailList(LazyDataModelSQL2Array mailList) {
		this.mailList = mailList;
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


	public String getZdoiText() {
		return zdoiText;
	}

	public void setZdoiText(String zdoiText) {
		this.zdoiText = zdoiText;
	}

	public Long getZdoiCode() {
		return zdoiCode;
	}

	public void setZdoiCode(Long zdoiCode) {
		this.zdoiCode = zdoiCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameLice() {
		return nameLice;
	}

	public void setNameLice(String nameLice) {
		this.nameLice = nameLice;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	

	public String getSelMailError() {
		return selMailError;
	}

	public void setSelMailError(String selMailError) {
		this.selMailError = selMailError;
	}

	public Date getToday(){
		return new Date();
	}

}
