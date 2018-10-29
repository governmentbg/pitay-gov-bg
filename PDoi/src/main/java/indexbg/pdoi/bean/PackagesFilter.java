package indexbg.pdoi.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.DialectConstructor;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.dao.ApplicationDAO;
import indexbg.pdoi.db.dao.EgovMessagesDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

@ManagedBean(name = "packageFilter")
@ViewScoped
public class PackagesFilter extends PDoiBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7188023826236224067L;
	static final Logger LOGGER = LoggerFactory.getLogger(PackagesFilter.class);
	
	//for the filter list
	private LazyDataModelSQL2Array messList = null;	
	private Long userId;
	private EgovMessagesDAO dao;
	
	//for the period
	private Long period;
	private Date dateFrom;
	private Date dateTo;
	
	//second colum tip, vid message
	private Long typeMessage;
	
	private List<SelectItem> msgFormList = new ArrayList<SelectItem>(); 
	private String formMessage;
	
	//third coumn status message, sending
	private List<SelectItem> msgStatusList = new ArrayList<SelectItem>();
	private String statusMessage;
	
	private List<SelectItem> msgCommStatusList = new ArrayList<SelectItem>(); 
	private Long statusSending;
	
	private String docGUID;
	
	//podatel
	private String senderText;	
	private Long senderCode;
	
	//poluchatel
	private String receiverText;	
	private Long receiverCode;
	
	
	
	public PackagesFilter() {
		
	}
	
	/**
	 * Зарежда се информацията, преди извикването на конструктора
	 */
	@PostConstruct
	public void initData(){
			try {
				userId = getUserData().getUserId();
				dao =  new EgovMessagesDAO(userId);			
				
				typeMessage=2l;
				
				ArrayList<Object[]> tmpList = null;

				tmpList = dao.createMsgTypesList();
				if(tmpList !=null && !tmpList.isEmpty()){
					msgFormList = ConvertInSelectItemStr(tmpList);
					formMessage = "MSG_DocumentRegistrationRequest";
				}
				
				tmpList.clear();
				tmpList = dao.createMsgStatusList();
				if(tmpList !=null && !tmpList.isEmpty()){
					msgStatusList = ConvertInSelectItemStr(tmpList);
				}
				tmpList.clear();
				tmpList =  dao.createCommStatusList();
				if(tmpList !=null && !tmpList.isEmpty()){
					msgCommStatusList = ConvertInSelectItemStr(tmpList);
				}
				
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
		
		setMessList(null);
		
		period = null;
		dateFrom = null;
		dateTo = null;
		
		typeMessage = null;
		formMessage="";
		
		statusMessage = "";
		statusSending=null;
		
		docGUID="";
		
		senderText="";
		senderCode=null;
		
		receiverText="";
		receiverCode=null;
	}
	
	/**
	 * Метод, който търси според зададените критерии в таблицата със съобщения
	 */
	public void actionSearch(){
		try {		  
			
			SelectMetadata smd= dao.createFilterMsgSQL(formMessage, statusMessage, statusSending, typeMessage, dateFrom, dateTo);
			messList = new LazyDataModelSQL2Array(smd, "A02MSGDAT");  
						
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
	
	
	/*==================================================================================================
	 *  																									SETTERS && GETTERS 
	 *  ==================================================================================================*/

	public LazyDataModelSQL2Array getMessList() {
		return messList;
	}

	public void setMessList(LazyDataModelSQL2Array messList) {
		this.messList = messList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(Long typeMessage) {
		this.typeMessage = typeMessage;
	}

	public String getFormMessage() {
		return formMessage;
	}

	public void setFormMessage(String formMessage) {
		this.formMessage = formMessage;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Long getStatusSending() {
		return statusSending;
	}

	public void setStatusSending(Long statusSending) {
		this.statusSending = statusSending;
	}

	public String getDocGUID() {
		return docGUID;
	}

	public void setDocGUID(String docGUID) {
		this.docGUID = docGUID;
	}

	public String getSenderText() {
		return senderText;
	}

	public void setSenderText(String senderText) {
		this.senderText = senderText;
	}

	public Long getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(Long senderCode) {
		this.senderCode = senderCode;
	}

	public String getReceiverText() {
		return receiverText;
	}

	public void setReceiverText(String receiverText) {
		this.receiverText = receiverText;
	}

	public Long getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(Long receiverCode) {
		this.receiverCode = receiverCode;
	}
	
	public Date getToday(){
		return new Date();
	}
	
	public EgovMessagesDAO getDao() {
		return dao;
	}

	public void setDao(EgovMessagesDAO dao) {
		this.dao = dao;
	}

	public List<SelectItem> getMsgFormList() {
		return msgFormList;
	}

	public List<SelectItem> getMsgStatusList() {
		return msgStatusList;
	}

	public List<SelectItem> getMsgCommStatusList() {
		return msgCommStatusList;
	}
	
	/*==================================================================================================
	 *  																									 UTILS 
	 *  ==================================================================================================*/
	
	/**
	 * Method that converts list in selectItems for the drop down list
	 */
	public static List<SelectItem> ConvertInSelectItemStr(ArrayList<Object[]> lst) {	
		List<SelectItem> items = new ArrayList<SelectItem>();
		Iterator<Object[]> it = lst.iterator();
		while (it.hasNext()) {
			Object[] item = (Object[]) it.next();
			if(item != null && item[0]!=null && item[1]!=null){
				items.add(new SelectItem( item[0].toString(),item[1].toString()));
			}
		}
		return items;
	}
	

	

}
