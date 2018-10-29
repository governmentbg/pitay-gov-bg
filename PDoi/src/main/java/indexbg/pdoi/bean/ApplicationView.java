package indexbg.pdoi.bean;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.Application;
import indexbg.pdoi.db.Event;
import indexbg.pdoi.db.Files;
import indexbg.pdoi.db.Subscription;
import indexbg.pdoi.db.dao.ApplicationDAO;
import indexbg.pdoi.db.dao.EventDAO;
import indexbg.pdoi.db.dao.FilesDAO;
import indexbg.pdoi.db.dao.SubscriptionDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

/** @author n.kosev
 * */
@ManagedBean(name = "applicationView")
@ViewScoped
public class ApplicationView extends PDoiBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6087305006189048115L;
	static final Logger LOGGER = LoggerFactory.getLogger(ApplicationView.class);
	
	private Application applic;
	private Long userId;
	private List<Files> filesList = new ArrayList<>();
	private List<Files> filesListReshenie = new ArrayList<>();
	private Event eventReshenie = new Event();  
	private String subscriptionText;
	private boolean existFinalSolution = false;
	private String likeCookie = "";
	private boolean viewLikeBtn = false;
	
	/** Метод за зареждане на необходимите данни от Заявлението и Събитията към него за показване на екрана на потребителя.
	 * */
	@PostConstruct
	public void loadApplicData() {
		
		ApplicationDAO appDAO = new ApplicationDAO(userId);
		FilesDAO filesDAO = new FilesDAO(userId);
		EventDAO eventDAO = new EventDAO(userId);
		
		try {
			userId = getUserData().getUserId();		
		
			String idObj = JSFUtils.getRequestParameter("idObj");
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

			if(idObj!=null && !idObj.isEmpty()){
				applic = appDAO.findById(Long.valueOf(idObj));
				filesList = filesDAO.findByCodeObjAndIdObj(applic.getCodeMainObject(), applic.getId(), true);
				
				//------- load events for applic ------
				List<Event> eventsList = eventDAO.listFromEventsByAppId(applic.getId(), Constants.CODE_ZNACHENIE_TYPE_EVENT_PROVID_ADD_INFORMATION);
				StringBuffer sbEvents = new StringBuffer(applic.getRequest());
				sbEvents.append(" <br /> ");
				if(eventsList != null) {
					for (int i = 0; i < eventsList.size(); i++) {
						Event event =  eventsList.get(i);
						sbEvents.append(sdf.format(event.getEventDate()) + ":");
						sbEvents.append(" <br /> ");
						sbEvents.append(event.getAddText());
						sbEvents.append(" <br /> ");
						filesList.addAll(filesList.size(), filesDAO.findByCodeObjAndIdObj(Constants.CODE_OBJECT_EVENT, event.getId(), true));
					}
				}
				applic.setAdd_info(sbEvents.toString());
				List<Event> tmpEventReshen = new EventDAO(userId).listFromEventsByAppId(applic.getId(), Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION);
				if(tmpEventReshen != null && tmpEventReshen.size() > 0) {
					eventReshenie = tmpEventReshen.get(0);
					filesListReshenie = filesDAO.findByCodeObjAndIdObj(eventReshenie.getCodeMainObject(), eventReshenie.getId(), true);
					
					existFinalSolution = true;
				}
				
				subscriptionText = "";
				
				if(getUserData().getSubscriptions()!=null && !getUserData().getSubscriptions().isEmpty()) {					
					if (getUserData().getSubscriptions().containsKey(applic.getId())) {
						
						subscriptionText = getMessageResourceString("labels", "appView.subscriptionOn") + " " + getUserData().getSubscriptions().get(applic.getId());				
					}					
				}
				
				//------- ъпдейт на броя посещения на заявление ------
				
				if( applic.getNumberOfVisits()==null){
					applic.setNumberOfVisits(0L);
				}
				try {
					JPA.getUtil().begin();
					appDAO.updateNumberOfVisits(applic.getId(),applic.getNumberOfVisits());
					JPA.getUtil().commit();
				} catch (NumberFormatException | DbErrorException e) {
					JPA.getUtil().rollback();
					LOGGER.error("Грешка при актуализация на броя посещения! ", e);
					JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
				}catch (Exception e) {
					JPA.getUtil().rollback();
					LOGGER.error("Грешка при работа със системата!!!", e);	
					JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.exception"));						
				}
				
			}
			
			
			if (applic.getUsefulness() == null) {
				applic.setUsefulness(0L);
			} 
			
			String cookieKey = "userCookieForLike" + applic.getId();
			
			likeCookie = JSFUtils.readCookie(cookieKey);
			
			if (likeCookie == null || likeCookie.equals("")) {
				viewLikeBtn = true;				
			}
				
		} catch (ObjectNotFoundException e) {
			userId = -1L;
		
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
		} finally {
			JPA.getUtil().closeConnection();
		}
		
	}
	

	public Application getApplic() {
		return applic;
	}
	
	/**
	 * Метод за изтегляне на файла от базата данни на системата.
	 * @param file - байтовете на самият файл.
	 */
	public void download(Files file){
		try {
			
			if(file.getContent() == null && file.getId() != null) {
			
				FilesDAO filesDAO = new FilesDAO(userId);
				file = filesDAO.findById(file.getId());
				
				if(file.getPath() != null && !file.getPath().isEmpty()){
					Path path = Paths.get(file.getPath());
					file.setContent(java.nio.file.Files.readAllBytes(path));
				}
			
			}
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    externalContext.setResponseHeader("Content-Type", "application/x-download");
		    externalContext.setResponseHeader("Content-Length", file.getContent().length + "");
		    externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + file.getFilename() + "\"");
			externalContext.getResponseOutputStream().write(file.getContent());
			facesContext.responseComplete();
			
		} catch (DbErrorException e) {
			LOGGER.error("DbErrorException: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("IOException: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/** метод за записване на абонамент към дадено заявление
	 * 
	 */
	public void actionSaveSubscription() {
		
		try {
			
			JPA.getUtil().begin();
		
			Subscription subscript = new Subscription();
			
			subscript.setApplicationId(this.applic.getId());
			subscript.setUserId(userId);
			subscript.setDateFrom(new Date());	
			
			subscript = new SubscriptionDAO(userId).save(subscript);
			
			JPA.getUtil().commit();	
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			
			subscriptionText = getMessageResourceString("labels", "appView.subscriptionOn") + " " + sdf.format(new Date());
			
			getUserData().getSubscriptions().put(applic.getId(), sdf.format(new Date()));
		
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при запис на абонамент! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));

		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при работа със системата!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));

		} finally {
			JPA.getUtil().closeConnection();
		}
				
	}
	
	/** метод за прекратяване на абонамент към дадено заявление
	 * 
	 * 
	 */
	public void actionDeleteSubscription() {
		
		try {
			
			JPA.getUtil().begin();
			
			Long idSubscript = new SubscriptionDAO(userId).findIdSubscrByUserIdAndAppId(userId, applic.getId());
			
			if (idSubscript != null) {
				new SubscriptionDAO(userId).deleteById(idSubscript); 
			}					
			
			JPA.getUtil().commit();	
			
			subscriptionText = "";
			
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "appView.cancelSubscription"));
		
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при прекратяване на абонамент! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));

		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при работа със системата!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));

		} finally {
			JPA.getUtil().closeConnection();
		}
		
	}
	
	/** метод за увеличаване бройката на харесванията към дадено заявление
	 * 
	 */
	public void actionUpdateLike() {
		
		try {
			
			JPA.getUtil().begin();
		
			new ApplicationDAO(userId).updateLike(applic.getId(), applic.getUsefulness());
			
			JPA.getUtil().commit();	
			
			String cookieKey = "userCookieForLike" + applic.getId(); 
			
			viewLikeBtn = false;
			
			JSFUtils.saveCookie(cookieKey, "1", "userCookieForLikeComment");			
		
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при запис на абонамент! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));

		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при работа със системата!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));

		} finally {
			JPA.getUtil().closeConnection();
			applic.setUsefulness(applic.getUsefulness() +1);
		}
				
	}
	
	/** Getters & Setters.
	 * */
	public void setApplic(Application applic) {
		this.applic = applic;
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Files> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<Files> filesList) {
		this.filesList = filesList;
	}	

	public List<Files> getFilesListReshenie() {
		return filesListReshenie;
	}

	public void setFilesListReshenie(List<Files> filesListReshenie) {
		this.filesListReshenie = filesListReshenie;
	}

	public Event getEventReshenie() {
		return eventReshenie;
	}

	public void setEventReshenie(Event eventReshenie) {
		this.eventReshenie = eventReshenie;
	}

	public String getSubscriptionText() {
		return subscriptionText;
	}

	public void setSubscriptionText(String subscriptionText) {
		this.subscriptionText = subscriptionText;
	}

	public boolean isExistFinalSolution() {
		return existFinalSolution;
	}

	public void setExistFinalSolution(boolean existFinalSolution) {
		this.existFinalSolution = existFinalSolution;
	}

	public String getLikeCookie() {
		return likeCookie;
	}

	public void setLikeCookie(String likeCookie) {
		this.likeCookie = likeCookie;
	}

	public boolean isViewLikeBtn() {
		return viewLikeBtn;
	}

	public void setViewLikeBtn(boolean viewLikeBtn) {
		this.viewLikeBtn = viewLikeBtn;
	}
	
}
