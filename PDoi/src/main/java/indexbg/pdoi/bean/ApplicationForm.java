package indexbg.pdoi.bean;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.db.dto.SystemClassif;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.InvalidParameterException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;
import com.indexbg.system.utils.SearchUtils;
import com.indexbg.system.utils.TreeUtils;

import indexbg.pdoi.db.AppThemes;
import indexbg.pdoi.db.Application;
import indexbg.pdoi.db.EgovOrganisations;
import indexbg.pdoi.db.Event;
import indexbg.pdoi.db.Files;
import indexbg.pdoi.db.dao.AppThemesDAO;
import indexbg.pdoi.db.dao.ApplicationDAO;
import indexbg.pdoi.db.dao.EgovMessagesDAO;
import indexbg.pdoi.db.dao.EventDAO;
import indexbg.pdoi.db.dao.FilesDAO;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.db.dao.ResponseSubjectDao;
import indexbg.pdoi.db.dao.SubscriptionDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.Locker;
import indexbg.pdoi.system.MyRunnableMail;
import indexbg.pdoi.system.PDoiBean;

/**
 * @author igg
 *
 */
@ManagedBean(name = "applicationForm")
@ViewScoped
public class ApplicationForm extends PDoiBean {
	
	/** Основен java клас за въвеждане/актуализация на заявления
	 * 
	 */
	private static final long serialVersionUID = 7483467840810680304L;

	static final Logger LOGGER = LoggerFactory.getLogger(ApplicationForm.class);
	
	
	private Long userId;
	
	private Application applic;
	
	// генерираното заявление;
	private Files filledFileShablon;
	
	private Files file = new Files();
	private List<Files> filesList = new ArrayList<>();
	private List<Files> deleteFilesList = new ArrayList<>();
	
	private String admZvText;
	private Long admZv;	
	
	//----- submission atributes ------
	private int keyTab=0;
	
	//izbor na subekt
	private TreeNode subectRootNode; 
	private TreeNode subectSelectedNode;
	
	private String searchWordClass;
	
	//ekateta
	private List<SystemClassif> oblastList;
	private List<SystemClassif> obshtiniList = new ArrayList<SystemClassif>();
	private List<SystemClassif> nasMestoList = new ArrayList<SystemClassif>();	
	
	//butona sys spisyka na sabitiyata
	private MenuModel menuButton = new DefaultMenuModel();	
	
	private List<Event> eventsList = new ArrayList<Event>();
	
	private Map<Long ,Object[]> eventsControl;
	
	private List<Files> filesResponseList = new ArrayList<>();
	
	private String responseEndTime="";
	
	private boolean adminMod;
	
	private boolean showBntMoreInfo;
	
	//tematikite
	private List<AppThemes> themesList = new ArrayList<>();	
	private List<Long> idThemes = new ArrayList<Long>();
	private String themesText = "";
	private AppThemesDAO themesDAO;
	 
	// obratno ot vanshnata stranica
	private String viewBackBtn;
	
	
	private boolean tabAnswareRendered;
	
	/** Иницира първоначалните стойности на обекта
	 * 
	 */
	@PostConstruct
	public void initData(){
		LOGGER.debug("PostConstruct!!!!");
		
		tabAnswareRendered = false;
		
		applic = new Application();
		applic.setApplicantType(Constants.CODE_ZNACHENIE_TYPE_APPLICANT_FIZ_LICE);
		applic.setCountry(Constants.CODE_ZNACHENIE_BG);
		applic.setStatus(Constants.CODE_ZNACHENIE_STATUS_APP_ACCEPTED_PLATFORM);
		applic.setStatusDate(new Date());
		applic.setDateReg(new Date());
		
		try {
			userId = getUserData().getUserId();
			
			adminMod = false;
			if(getUserData().getTypeUser()!=null && getUserData().getTypeUser().longValue() == Constants.CODE_ZNACHENIE_TYPE_USER_MODERATOR ){
				adminMod = true;
			}
			
			applic.setEmail(getUserData().getEmailUser());
			applic.setFullNames(getUserData().getLiceNames());
			
			if (JSFUtils.getRequestParameter("baBtn")!=null && !JSFUtils.getRequestParameter("baBtn").isEmpty()) {
				setViewBackBtn(JSFUtils.getRequestParameter("baBtn")+".jsf");
			}
			
		} catch (ObjectNotFoundException e) {
			userId = -1L;
		} 
		
		try {
			//------- load applic ------------------
			
			String idObj = JSFUtils.getRequestParameter("idObj");
		
			if(idObj!=null && !idObj.isEmpty()){
				
				boolean success=true;
				if(adminMod){// Locking objects...
					
					try {
						JPA.getUtil().begin();
						
						Locker locker_ = new Locker();
						// 1. unlock all objects of current user
						locker_.unlockObjects(userId); 
						// 2. lock current object
						locker_.lockObject(Long.valueOf(idObj), Long.valueOf(Constants.CODE_OBJECT_APPLICATION), getUserData());
						JPA.getUtil().commit();	
					} catch (DbErrorException e) {
						LOGGER.error(e.getMessage(),e);
						JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
						success=false;
					} catch (Exception e) {
						JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
						LOGGER.error(e.getMessage(), e);
						success=false;
					} finally {
						JPA.getUtil().closeConnection();
					}
				}

				//ako zakluchvaniyata sa minali i vsichko e tochno zarezdam obekta
				
				if(success){
					applic = new ApplicationDAO(userId, getSystemData()).findById(Long.valueOf(idObj));
					filesList = new FilesDAO(userId).findByCodeObjAndIdObjAndRel(applic.getCodeMainObject(), applic.getId());	
					
					//------- load events for applic ------
					EventDAO eventDAO = new EventDAO(userId, getSystemData());
					eventsList = eventDAO.listFromEventsByAppId(applic.getId());
					
					//------- zamrazqvane na sroka --------
					if(applic.getResponseEndTime()!=null){
						responseEndTime = new SimpleDateFormat("dd.MM.yyyy").format(applic.getResponseEndTime());
					}
					
					showBntMoreInfo = false;
					if(eventsList!=null && !eventsList.isEmpty()){
						if( eventsList.get(eventsList.size()-1).getEventType() == Constants.CODE_ZNACHENIE_TYPE_EVENT_REQ_ADD_INFORMATION){
							responseEndTime = "Очаква се предоставяне на допълнителна информация";
							if(applic.getUserReg().equals(userId)){
								showBntMoreInfo = true;
							}
						}
						
						for (Event tmpEvent : eventsList) {					
							if (tmpEvent.getEventType().longValue() == Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION) {							
								filesResponseList = new FilesDAO(userId).findByCodeObjAndIdObjAndRel(tmpEvent.getCodeMainObject(), tmpEvent.getId());		
								tabAnswareRendered = true;
							}
						}
					}
					
					//------- load events lists for moderators only -------------
					List<SystemClassif> listItems = new ArrayList<>();
					
					if(eventsList!=null && !eventsList.isEmpty()){
						
						Long currEventType = eventsList.get(eventsList.size()-1).getEventType(); 
//						//19.2.2019 sled testvane i pregled e poiskano
//						//ako e preprateno kam drug zadaljen subekt i toi e bez ceos shte tryabva da izvlichame sabitiqta za "подаване на заяв без сеос"
//						if(eventsList.get(eventsList.size()-1).getEventType().longValue() == Constants.CODE_ZNACHENIE_TYPE_EVENT_FORW_APPLICATION) {
//							
//							EgovOrganisations eOrg = new ResponseSubjectDao(userId,getSystemData()).responseSubjectSEOS(applic.getResponseSubjectId());
//							if(eOrg==null) {
//								//kogato namq ceos organizaciqta izkarvame sabitiqta kakto sled подаване на заяв без сеос
//								currEventType = Long.valueOf(Constants.CODE_ZNACHENIE_TYPE_EVENT_SUBMIT_APP); 
//							}
//						}
						
						List<Object> nextEvents = eventDAO.nextEventsForApp(currEventType);
						
						if(eventsList!=null){
							for(Object o:nextEvents){
								listItems.add( getSystemData(). decodeItemFull(Constants.CODE_SYSCLASS_TYPE_EVENT,SearchUtils.asLong(o), getCurrentLang(), new Date(), userId));
							}
						}
					} else { //nebi trqbvalo da zarejdam vsichko
						//listItems = getSystemData().getSysClassification(Constants.CODE_SYSCLASS_TYPE_EVENT, new Date(), getCurrentLang(), userId);
					}
					
					for(SystemClassif item : listItems){
						DefaultMenuItem ajaxAction = new DefaultMenuItem(item.getTekst());
						//ajaxAction.setUpdate("message");
						ajaxAction.setCommand("eventsInt.jsf");
						ajaxAction.setParam("eventCode", item.getCode());
						ajaxAction.setParam("applicId", applic.getId());
						ajaxAction.setAjax(false);
						//ajaxAction.setUrl("eventsInt.jsf?eventCode="+item.getCode()+"&applicId="+applic.getId());
						menuButton.addElement(ajaxAction);
					}
				
					//-----------------zarejdam controla za events ---------------
					eventsControl = new HashMap<Long ,Object[]>();
					List<Object[]> eControlList = eventDAO.eventsControlList();
					for (Object[] eObj : eControlList) {
						eventsControl.put(SearchUtils.asLong(eObj[0]), eObj);
					}
					//------------------------------------------------------------
					
					//------- load app_themes for applic ------
					themesDAO = new AppThemesDAO(userId);
					themesList = this.themesDAO.findByApplicId(applic.getId()); 	
					
					//------------------------------------------------------------			
				
				}
				
			}
		
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
		} catch (Exception e) {
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
			LOGGER.error(e.getMessage(), e);
		} finally {
			JPA.getUtil().closeConnection();
		}
	
		
	}	
	
	@PreDestroy
	public void destroyData(){
		
		LOGGER.debug("PreDestroy!");
		if(adminMod){
			
			try {
				JPA.getUtil().begin();
				
				Locker locker_ = new Locker();
				// 1. unlock all objects of current user
				locker_.unlockObjects(userId); 
				
				JPA.getUtil().commit();	
			} catch (DbErrorException e) {
				LOGGER.error(e.getMessage(),e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
			} catch (Exception e) {
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
				LOGGER.error(e.getMessage(), e);
			} finally {
				JPA.getUtil().closeConnection();
			}
		}
	}
	
	/** Промяна на типа на заявлението само при подаване
	 * 
	 */
	public void actionChangeAppType(){
		//TODO promqna na tipa na zaqvlenieto samo pri podavane
	}
	
	/** Промяна на държава
	 * 
	 */
	public void actionChangeCountry(){
		applic.setRegion(null);
		applic.setMunicipality(null);
		applic.setTown(null);
	}
	
	/** Проверка на въведените данни на всяка стъпа при подаване на заявление
	 * 
	 * @param step
	 * @return
	 */
	private boolean checkDataSteps(int step){
		boolean returned = true;
		if(keyTab==0){
			if(applic.getFullNames()==null || applic.getFullNames().trim().isEmpty()){
				JSFUtils.addMessage("formApplic:fullNames",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "general.names")));
				returned = false;
			} else {
				String[] names = applic.getFullNames().split(" ");
				List<String> namesClear = new ArrayList<String>();
				for(String m:names) {
					if(m!=null && !m.trim().isEmpty()) {
						namesClear.add(m);
					}
				}
				if(namesClear.size()<3) {
					JSFUtils.addMessage("formApplic:fullNames",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "general.imePrezimeFamilia")));
					returned = false;
				}
			}
			
			if(applic.getApplicantType().equals(Constants.CODE_ZNACHENIE_TYPE_APPLICANT_URID_LICE)){
				if(applic.getHeadoffice()==null || applic.getHeadoffice().trim().isEmpty()){
					JSFUtils.addMessage("formApplic:headoffice",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "general.sedalishte")));
					returned = false;
				}
			}
			
			if(applic.getCountry().equals(Constants.CODE_ZNACHENIE_BG)){
				if(applic.getRegion() == null ){
					JSFUtils.addMessage("formApplic:oblast",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "general.oblast")));
					returned = false;
				}
				if(applic.getMunicipality() == null ){
					JSFUtils.addMessage("formApplic:obshtina",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "general.obshtina")));
					returned = false;
				}
				if(applic.getTown() == null ){
					JSFUtils.addMessage("formApplic:nasMesto",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "general.nasMiasto")));
					returned = false;
				}
			}
			
			if(applic.getAddress()==null || applic.getAddress().trim().isEmpty()){
				JSFUtils.addMessage("formApplic:address",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "general.adress")));
				returned = false;
			}
			
//			if(applic.getPostCode()==null || applic.getPostCode().trim().isEmpty()){
//				JSFUtils.addMessage("formApplic:postCode",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "general.pk")));
//				returned = false;
//			}
			
			if(applic.getRequest()==null || applic.getRequest().trim().isEmpty()){
				JSFUtils.addMessage("formApplic:requestInfo",FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "application.requestTitle")));
				returned = false;
			}
			
		} else  if(keyTab==1){
			if(applic.getResponseSubjectId()==null){
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR,getMessageResourceString(Constants.beanMessages,"general.pleaseInsert",getMessageResourceString("labels", "application.step2")));
				returned = false;
			} else {
				//търсене в users дали за избрания ЗС има администратори
				returned = checkForModerators (applic.getResponseSubjectId());
			}
			
		}
		
		return returned;
	}
	
	/** Преминаване към следваща стъпка при подаване на заявление
	 * 
	 * 
	 */
	public void actionNextStep(){
		
		if(checkDataSteps(keyTab)){
			keyTab++;
			
			if(keyTab==2){
				//save
				 actionSave();
			}
		}
		
	//	System.out.println("actionNextStep--->");
	}	
	
	/** Връща стъпка назад при подаване на заявление
	 * 
	 */
	public void actionBackStep(){
		keyTab--;
	//	System.out.println("actionBackStep--->");
	}
	
	/** Преминава към някоя стъпка при подаването на заявлението
	 * 
	 * @param keyTab
	 */
	public void actionGoToStep(int keyTab){
		this.keyTab = keyTab;
	}
	
	
	/** Записва на въведените данни за заявлението в БД, изпраща на СЕОС съобщение, записва събития при пдоаването и изпраща различни мейли срямо вида на събитието
	 * 
	 * 
	 */
	private void actionSave(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String zdoi ="";
		EgovOrganisations eOrg = null;
		boolean startThreadMails = false;
		
		List<Object[]> dataForAdmin = null; 
		
		try { 
			
			JPA.getUtil().begin();   //if(1==1) {throw new Exception();} za testove
			
			//prowerka dali zaduljeniqt subekt ima elektronen dokumentooborot
			eOrg = new ResponseSubjectDao(userId,getSystemData()).responseSubjectSEOS(applic.getResponseSubjectId());
			
			
			//при запис на заявлението трябва да изчислиш крайната дата + 14 дни от датата на регистрация 
			if (eOrg == null) { // само ако няма връзка адм със СЕОС-а
				
				applic.setRegistrationDate(new Date()); 
				
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(applic.getRegistrationDate());
				gc.add(Calendar.DAY_OF_YEAR, calcPeriod(gc.get(GregorianCalendar.DAY_OF_WEEK),14)); 
				gc.set(Calendar.MINUTE, 0);
				gc.set(Calendar.SECOND, 0);
				gc.set(Calendar.HOUR_OF_DAY, 0);
				
				applic.setResponseEndTime(gc.getTime());
			}
			
			//----- generirane na uri...........................
			applic.setApplicationUri(new Date().getTime()+"-"+sdf.format(applic.getDateReg())); //TODO
			
			//----- status na zayavlenieto --
			if(eOrg!=null){
				applic.setStatus(Constants.CODE_ZNACHENIE_STATUS_APP_EXPECTED_REG);
			} else {
				applic.setStatus(Constants.CODE_ZNACHENIE_STATUS_APP_REGISTERED);
				
			}
			applic.setStatusDate(new Date());
			//--------------------------------
			applic.setDocGuid("{"+java.util.UUID.randomUUID().toString().toUpperCase()+"}");
			
			applic = new ApplicationDAO(userId, getSystemData()).save(applic);
			
			JPA.getUtil().flush();
			
			//генерираме заявлението като pdf/doc файл
			filledFileShablon = new CreateShablons().createShablApplic(applic,filesList, getSystemData(), getUserData());
			filesList.add(0, filledFileShablon);
			
			//записваме подадените файлове
			FilesDAO filesDAO = new FilesDAO(userId);
			
			for (int i = 0; i < filesList.size(); i++) {
				
				if (filesList.get(i).getId() == null) {
					filesList.get(i).setCodeObject(applic.getCodeMainObject());
					filesList.get(i).setIdObject(applic.getId());	
					
					filesDAO.save(filesList.get(i));
				} 
			}
					
			//Изпращане на СЕОС
			if(eOrg!=null){
				new EgovMessagesDAO(userId).saveEgovMessage(applic, filesList, eOrg, getSystemData());
			} else {
				zdoi = getSystemData().decodeItem(Constants.CODE_SYSCLASS_ADM_REGISTRY, applic.getResponseSubjectId(), getCurrentLang(), applic.getDateReg(), this.userId);			
			}
			
			//------------ събитията които ще се подават при ново заявление ------------------------------------------------------
			
			EventDAO eventsDAO = new EventDAO(this.userId, getSystemData());	
			
			//Подаване на заявление - Constants.CODE_ZNACHENIE_TYPE_EVENT_SUBMIT_APP
			
			Event eventPodavane = new Event(getSystemData());
			
			eventPodavane.setApplicationId(applic.getId());
			eventPodavane.setEventDate(new Date());
			eventPodavane.setEventType(Constants.CODE_ZNACHENIE_TYPE_EVENT_SUBMIT_APP);
			eventPodavane.setStatus(Constants.CODE_ZNACHENIE_STATUS_EVENT_COMPLETED);
			
			eventsDAO.save(eventPodavane);
			
			
			
			if(eOrg!=null){
				//Изпратено към деловодна система
				Event eventToSEOS = new Event(getSystemData());
				
				eventToSEOS.setApplicationId(applic.getId());
				eventToSEOS.setEventDate(new Date());
				eventToSEOS.setEventType(Constants.CODE_ZNACHENIE_TYPE_EVENT_SEND_TO_SYSTEM);
				eventToSEOS.setStatus(Constants.CODE_ZNACHENIE_STATUS_EVENT_COMPLETED);
				
				eventsDAO.save(eventToSEOS);
			
			} 
			
			// ismakvame imeilite na adminite na zadaljenite subekti -to
			dataForAdmin = new PublicationDAO(this.userId).findAdminEmailByOrgCode(applic.getResponseSubjectId()); 

			JPA.getUtil().commit();	

			indexApplication(applic.getId());

			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesSaveMsg"));
			
			startThreadMails = true;
		
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при запис на заявление! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));
			keyTab--;

		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при запис на заявление!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));
			keyTab--;

		} finally {
			JPA.getUtil().closeConnection();
			deleteFilesList.clear(); // да се зачисти след записа
		}
		
		//mails events
		if(startThreadMails){
			//уведомителните съобщения, които ще се изпращат след всяко събитие
			try {
				String name = null;
				String link = null;
				
				if (applic.getApplicantType().equals(Constants.CODE_ZNACHENIE_TYPE_APPLICANT_FIZ_LICE)) { 
					name = applic.getFullNames();										
				}
			   
				ArrayList<String> mailsTo;
				Thread t;
				
				String srok ="";
				
				if(applic.getResponseEndTime()!=null){
					srok = sdf.format(SearchUtils.asDate(applic.getResponseEndTime()));
				}
				
				if(eOrg!=null){
					mailsTo = new ArrayList<>();
					mailsTo.add(applic.getEmail());	
					link = getSystemData().getSettingsValue("linkToExternalSite") + this.applic.getId();
					
					t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_PODAVANE_APPLIC_ZDOI_S_SOES, mailsTo, applic.getApplicationUri(), eOrg.getAdministrativeBodyName(), srok, name,  userId, null, link,applic.getFullNames()));	
					t.start();					
				
				} else { //съобщение до Заявител при ЗДОИ без СЕОС				
					mailsTo = new ArrayList<>();	
					mailsTo.add(applic.getEmail());	
					link = getSystemData().getSettingsValue("linkToExternalSite") + this.applic.getId();
					
					t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_PODAVANE_APPLIC_ZDOI_BEZ_SOES, mailsTo, applic.getApplicationUri(), zdoi, srok, name,  userId, null, link,applic.getFullNames()));	
					t.start();		
					
					 ArrayList<String> mailsToAdm = new ArrayList<String>();
					
					if(dataForAdmin!=null && !dataForAdmin.isEmpty()){
						for(Object[] ea:dataForAdmin){
							if(ea[1]!=null){
								mailsToAdm.add(SearchUtils.asString(ea[1]));
							}
						}
						
						link = getSystemData().getSettingsValue("linkToInternalSite") + this.applic.getId();
						
						t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_PODAVANE_ADMIN_ZDOI_BEZ_SOES, mailsToAdm, applic.getApplicationUri(), zdoi, srok, null, userId, null, link,applic.getFullNames()));	
						t.start();
					
					}
				}
			} catch (Exception e) {
				LOGGER.error("Грешка при startirane na izprashtane na imeili!!!", e);
			}
			
		}
	}

	/** Изтриване на последното събитие в таблицата със списъка на събития към заявление и изпраща мейли при изтриване
	 * 
	 */
	public void actionDeleteEvent(){
		
		String idEvent = JSFUtils.getRequestParameter("idEvent");
		String codeEvent = JSFUtils.getRequestParameter("codeEvent");
		
		boolean sentmail = false;
		String eventName="";
				
		ArrayList<String> mailsToAbonament = null;
		try {
			
			JPA.getUtil().begin();
			
			mailsToAbonament = (ArrayList<String>) new SubscriptionDAO(userId).findMailsSubscrUsersByIdApp(applic.getId());
			
			Date responseEndTime = new EventDAO(userId, getSystemData()).deleteEvent(Long.valueOf(idEvent), applic.getId());
			
			JPA.getUtil().commit();		
			
			eventsList.remove(eventsList.size()-1); // remove last event
						
			if(codeEvent!=null && applic.getEmail()!=null){
				eventName = getSystemData().decodeItem(Constants.CODE_SYSCLASS_TYPE_EVENT,Long.valueOf(codeEvent), getCurrentLang(), new Date(), userId);
			}
			
			if(responseEndTime!=null) { 
				applic.setResponseEndTime(responseEndTime);
			}
			sentmail = true;
			
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesSaveMsg"));
			
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при запис на заявление! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));

		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при запис на заявление!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));

		} finally {
			JPA.getUtil().closeConnection();
			deleteFilesList.clear(); // да се зачисти след записа
		}
		
		if(sentmail && applic.getEmail()!=null && !applic.getEmail().trim().isEmpty()){
			
			String name = null;
			String link = null;
			
			if (applic.getApplicantType().equals(Constants.CODE_ZNACHENIE_TYPE_APPLICANT_FIZ_LICE)) { 
				name = applic.getFullNames();										
			}
			
			String srok = new SimpleDateFormat("dd.MM.yyyy").format(SearchUtils.asDate(applic.getResponseEndTime()));
			
			ArrayList<String> mailsTo = new ArrayList<>();
			mailsTo.add(applic.getEmail());	
			link = getSystemData().getSettingsValue("linkToExternalSite") + applic.getId();
			
			Thread t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_DELETE_EVENT_DO_APPLIC, mailsTo, applic.getApplicationUri(), null, srok, name,  userId, eventName, link,applic.getFullNames()));	
			t.start();	
			
			
			//съобщения до Абонамент
			if(mailsToAbonament != null && !mailsToAbonament.isEmpty()) {
				link = getSystemData().getSettingsValue("linkToExternalSite") + applic.getId();
				
				t = new Thread(new MyRunnableMail(Constants.CODE_ZNACHENIE_SHABLON_DELETE_EVENT_SUBSCRIBED, mailsToAbonament, applic.getApplicationUri(), null, srok, null,  userId, eventName, link,applic.getFullNames()));	
				t.start();
			}
			
		}
		
		try {
			
			//this.applic = new ApplicationDAO(userId, getSystemData()).findById(applic.getId());	 //	20.2.2019 komentirano zashtoto responseEndTime go zarejdame po gore i nqma nujda da se izvlicha pak 	
			
			//------- zamrazqvane na sroka --------
			if(applic.getResponseEndTime()!=null){
				responseEndTime = new SimpleDateFormat("dd.MM.yyyy").format(applic.getResponseEndTime());
			}
			
			showBntMoreInfo = false;
			if(eventsList!=null && !eventsList.isEmpty()){
				if( eventsList.get(eventsList.size()-1).getEventType() == Constants.CODE_ZNACHENIE_TYPE_EVENT_REQ_ADD_INFORMATION){
					responseEndTime = "Очаква се предоставяне на допълнителна информация";
					if(applic.getUserReg().equals(userId)){
						showBntMoreInfo = true;
					}
				}
			}
			
			EventDAO eventDAO = new EventDAO(userId, getSystemData());
			
			eventsList = eventDAO.listFromEventsByAppId(applic.getId());
			
			eventsControl = new HashMap<Long ,Object[]>();
			List<Object[]> eControlList = eventDAO.eventsControlList();
			for (Object[] eObj : eControlList) {
				eventsControl.put(SearchUtils.asLong(eObj[0]), eObj);
			}
						
		    // в бутона за ново събитие в заявлението да може да се покажат събитията, както следват, след записа на новото събитие
		    List<SystemClassif> listItems = new ArrayList<>();
		    
		    if(this.eventsList != null){
		    	List<Object> nextEvents = eventDAO.nextEventsForApp(this.eventsList.get(this.eventsList.size()-1).getEventType());
				for(Object o:nextEvents){
					listItems.add( getSystemData().decodeItemFull(Constants.CODE_SYSCLASS_TYPE_EVENT, SearchUtils.asLong(o), getCurrentLang(), new Date(), userId));
				}
			}		    
		    
		   filesResponseList = new ArrayList<>();		    
		    
		   setMenuButton(new DefaultMenuModel());
		    
		    for(SystemClassif item : listItems){
				DefaultMenuItem ajaxAction = new DefaultMenuItem(item.getTekst());
				ajaxAction.setCommand("eventsInt.jsf");
				ajaxAction.setParam("eventCode", item.getCode());
				ajaxAction.setParam("applicId", applic.getId());
				ajaxAction.setAjax(false);
				getMenuButton().addElement(ajaxAction);
			}
		    
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
		} finally {
			JPA.getUtil().closeConnection();
		}
		
	}
		
	/** Търси по зададена дума в дървото със задължените субекти
	 * 
	 * @throws DbErrorException
	 */
	public void search() {

//		if(searchWordClass==null || searchWordClass.length()<3) {
//			return;
//		}
//		
		LOGGER.info("Searching for classif with: " + getSearchWordClass());
		try {
			
			List<SystemClassif> classifList =  getSystemData().getSysClassification(Constants.CODE_SYSCLASS_ADM_REGISTRY, new Date(), getCurrentLang(),userId);
			TreeNode rootNode = new TreeUtils().fTree(classifList, getSearchWordClass(), true, true ,null ,null);
			setSubectRootNode(rootNode);
			
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
		} finally {
			JPA.getUtil().closeConnection();
		}
}

	/** Обновява дървото със задължените субекти
	 * 
	 * @throws DbErrorException
	 */
	public void refreshTree() throws DbErrorException{ 
		setSearchWordClass(null);
		subectRootNode =null;
		getSubectRootNode();
	}
	
	/** Подчертава избраното значение от дървото със задължените субекти
	 * 
	 * @param event
	 */
	public void onNodeSelect(NodeSelectEvent event) {
		SystemClassif item = (SystemClassif) event.getTreeNode().getData();
		
		if(event.getTreeNode().isLeaf()){
			applic.setResponseSubjectId(item.getCode());
			//търсене в users дали за избрания ЗС има администратори
			checkForModerators (item.getCode());
		} else {
			applic.setResponseSubjectId(null);
			
			if(event.getTreeNode().isExpanded()){
				event.getTreeNode().setExpanded(false);
        	} else {
        		event.getTreeNode().setExpanded(true);
        	}
			event.getTreeNode().setSelected(false);
		}
    }
 
    /**  Премахва избраното значение от дървото със задължените субекти
     * 
     * @param event
     */
    public void onNodeUnselect(NodeUnselectEvent event) {
        applic.setResponseSubjectId(null);
    }

    
    private boolean checkForModerators (Long code) {
    	
    	List<Long> userTypes=new ArrayList<Long>();
		userTypes.add(Long.valueOf(Constants.CODE_ZNACHENIE_TIP_POTR_VATR));
		
		try {
			List<Object[]> admins =  new PublicationDAO(getUserId()).findAdminDataByOrgCode(code, userTypes);
			if(admins==null || admins.size()<1) {
				JSFUtils.addMessage(null,FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "application.noAdmins"));
				return false;
			}
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
		} catch (Exception e) {
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
			LOGGER.error(e.getMessage(), e);
		} finally {
			JPA.getUtil().closeConnection();
		}
    	
    	return true;
    }
	/** GET & SET
	 * 
	 *
	 */
	public Application getApplic() {
		
		return applic;
	}

	public void setApplic(Application applic) {
		this.applic = applic;
	}


	public Files getFile() {
		return file;
	}


	public void setFile(Files file) {
		this.file = file;
	}


	public List<Files> getFilesList() {
		return filesList;
	}


	public void setFilesList(List<Files> filesList) {
		this.filesList = filesList;
	}


	public List<Files> getDeleteFilesList() {
		return deleteFilesList;
	}


	public void setDeleteFilesList(List<Files> deleteFilesList) {
		this.deleteFilesList = deleteFilesList;
	}
		
	/**
	 * Save uploaded file
	 */
	public void uploadFileListener(FileUploadEvent event){		
		
		try {
			
			UploadedFile upFile = event.getFile();
			
			if(!checkForUploadedFileByName(upFile.getFileName(), filesList)) {
			
				Files fileObject = new Files();
				fileObject.setFilename(upFile.getFileName());
				fileObject.setContentType(upFile.getContentType());
				fileObject.setContent(upFile.getContents());
				fileObject.setIdObject(-1L);
				fileObject.setCodeObject(applic.getCodeMainObject());			
					
				filesList.add(fileObject);
			}
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.getMessage());
		} 
	}
	
	
	
	/**
	 * Download selected file
	 * @param file
	 */
	public void download(Files file){ 
		
		boolean ok =true;
		
		if(file.getContent() == null && file.getId() != null) {
			try {
				FilesDAO filesDAO = new FilesDAO(userId);
				file = filesDAO.findById(file.getId());
				
				if(file.getPath() != null && !file.getPath().isEmpty()){
					Path path = Paths.get(file.getPath());
					file.setContent(java.nio.file.Files.readAllBytes(path));
				}
			} catch (DbErrorException e) {
				LOGGER.error("DbErrorException: " + e.getMessage());
				ok =false;
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
			} catch (IOException e) {
				LOGGER.error("IOException: " + e.getMessage());
				ok =false;
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
				LOGGER.error(e.getMessage(), e);
			} catch (Exception e) {
				LOGGER.error("Exception: " + e.getMessage());
				ok =false;
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));
			}  finally {
				JPA.getUtil().closeConnection();
			}
		}
		
		if(ok){
			try {
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
			    ExternalContext externalContext = facesContext.getExternalContext();
				
				HttpServletRequest request =(HttpServletRequest)externalContext.getRequest();
				String agent = request.getHeader("user-agent");

				
				String codedfilename = ""; 
				
				if (null != agent &&  (-1 != agent.indexOf("MSIE") || (-1 != agent.indexOf("Mozilla") && -1 != agent.indexOf("rv:11")) || (-1 != agent.indexOf("Edge"))  ) ) {
					codedfilename = URLEncoder.encode(file.getFilename(), "UTF8");
				} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
					codedfilename = MimeUtility.encodeText(file.getFilename(), "UTF8", "B");
				} else {
					codedfilename = URLEncoder.encode(file.getFilename(), "UTF8");
				}
				
				
				
				
			    externalContext.setResponseHeader("Content-Type", "application/x-download");
			    externalContext.setResponseHeader("Content-Length", file.getContent().length + "");
			    externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + codedfilename + "\"");
				externalContext.getResponseOutputStream().write(file.getContent());
				facesContext.responseComplete();
			} catch (IOException e) {
				LOGGER.error("IOException: " + e.getMessage());
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
				LOGGER.error(e.getMessage(), e);
			} catch (Exception e) {
				LOGGER.error("Exception: " + e.getMessage());
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));
			} 
		}
		
	}
	
	/**
	 * Remove selected file
	 * @param file
	 */
	public void remove(Files file){
		
		try {
			filesList.remove(file);
			deleteFilesList.add(file);
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.getMessage());
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));
		}
	}
	
	public void actionChangeFileVis(Files file) {
		
		file.setVisibleOnSite(true);	}

	public void actionChangeFileNonVis(Files file) {
		
		file.setVisibleOnSite(false);	
	}


	public String getAdmZvText() {
		return admZvText;
	}


	public void setAdmZvText(String admZvText) {
		this.admZvText = admZvText;
	}


	public Long getAdmZv() {
		return admZv;
	}


	public void setAdmZv(Long admZv) {
		this.admZv = admZv;
	}


	public int getKeyTab() {
		return keyTab;
	}


	public void setKeyTab(int keyTab) {
		this.keyTab = keyTab;
	}


	public TreeNode getSubectRootNode() {
		if(subectRootNode==null){
			
			try {
				
				List<SystemClassif> listItems =  getSystemData().getSysClassification(Constants.CODE_SYSCLASS_ADM_REGISTRY, new Date(), getCurrentLang(), userId);
				
				subectRootNode = new TreeUtils().loadTreeData3(listItems, "", false,false ,null ,null);
				
			} catch (DbErrorException e) {
				LOGGER.error(e.getMessage(),e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
			} catch (Exception e) {
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
				LOGGER.error(e.getMessage(), e);
			} finally {
				JPA.getUtil().closeConnection();
			}
		}
		return subectRootNode;
	}


	/** За търсене на сходни заявления
	 * 
	 * 
	 */
	public void searchAppsRs() {
		clearSearchParamsFilter();
		
		if(applic.getResponseSubjectId()!=null) {
		Map<String, Object> params  = new HashMap<String, Object>();
		
		    List<Long> selectedSubj = new ArrayList<Long>();
		    selectedSubj.add(applic.getResponseSubjectId());
			params.put(("selectedSubj"),selectedSubj );
			try {
				
				addSessionScopeAttribute("zadaljenSubText", getSystemData().decodeItem(Constants.CODE_SYSCLASS_ADM_REGISTRY, applic.getResponseSubjectId(), getCurrentLang(), new Date(), getUserId())+ "; ");
			} catch (DbErrorException e) {
				LOGGER.error(e.getMessage(),e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
			} catch (Exception e) {
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
				LOGGER.error(e.getMessage(), e);
			} finally {
				JPA.getUtil().closeConnection();
			}
			addSessionScopeAttribute("sappSMDAttr", params);
		}
		
		
	}
	
	public void searchSimilarApp() {
		clearSearchParamsFilter();
		
		Map<String, Object> params  = new HashMap<String, Object>();
				  
		params.put(("text"),applic.getRequest() );
		addSessionScopeAttribute("sappSMDAttr", params);
	}
	
	public void searchSimilarAppRs() {
		clearSearchParamsFilter();
		
		Map<String, Object> params  = new HashMap<String, Object>();
		
	    List<Long> selectedSubj = new ArrayList<Long>();
	    selectedSubj.add(applic.getResponseSubjectId());
		params.put(("selectedSubj"),selectedSubj );
		try {
			
			addSessionScopeAttribute("zadaljenSubText", getSystemData().decodeItem(Constants.CODE_SYSCLASS_ADM_REGISTRY, applic.getResponseSubjectId(), getCurrentLang(), new Date(), getUserId())+ "; ");
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
		} catch (Exception e) {
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
			LOGGER.error(e.getMessage(), e);
		} finally {
			JPA.getUtil().closeConnection();
		}		
		params.put(("text"),applic.getRequest() );
		addSessionScopeAttribute("sappSMDAttr", params);	
		
	}
	
	private void clearSearchParamsFilter() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		session.removeAttribute("sappSMDAttr");
		session.removeAttribute("zadaljenSubText");
		session.removeAttribute("period");
		session.removeAttribute("sappPage");
		
	}
	
	public void setSubectRootNode(TreeNode subectRootNode) {
		this.subectRootNode = subectRootNode;
	}


	public TreeNode getSubectSelectedNode() {
		return subectSelectedNode;
	}


	public void setSubectSelectedNode(TreeNode subectSelectedNode) {
		this.subectSelectedNode = subectSelectedNode;
	}


	public String getSearchWordClass() {
		return searchWordClass;
	}


	public void setSearchWordClass(String searchWordClass) {
		this.searchWordClass = searchWordClass;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	//------- ekatetata ---------------------------------------
	
	public List<SystemClassif> getOblastList() {
		if(oblastList==null){
			oblastList = new ArrayList<SystemClassif>();
			filEkateList(oblastList ,0L);
		}
		return oblastList;
	}

	public void setOblastList(List<SystemClassif> oblastList) {
		this.oblastList = oblastList;
	}

	public List<SystemClassif> getObshtiniList() {
		return obshtiniList;
	}

	public void setObshtiniList(List<SystemClassif> obshtiniList) {
		this.obshtiniList = obshtiniList;
	}

	public List<SystemClassif> getNasMestoList() {
		return nasMestoList;
	}

	public void setNasMestoList(List<SystemClassif> nasMestoList) {
		this.nasMestoList = nasMestoList;
	}
	
	public void actionChangeOblast(){
		if(applic.getRegion()!=null){
			filEkateList(obshtiniList ,applic.getRegion());
		} else {
			obshtiniList.clear();
			applic.setMunicipality(null);
		}
		nasMestoList.clear();
		applic.setTown(null);
	}
	
	public void actionChangeObshtina(){
		if(applic.getMunicipality()!=null){
			filEkateList(nasMestoList ,applic.getMunicipality());
		} else {
			nasMestoList.clear();
			applic.setTown(null);
		}
	}
	
	private void filEkateList(List<SystemClassif> ekateList ,Long code) {
		
		ekateList.clear();
		
		if(code==null){
			return;
		}
		
		try {
			ekateList.addAll( getSystemData().getChildrenOnNextLevel(Constants.CODE_SYSCLASS_EKATTE, code.longValue(),  new Date(), getCurrentLang(), userId) );
					
			//System.out.println("ekateList--> "+ekateList.size());
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"));
		} catch (Exception e) {
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"));
			LOGGER.error(e.getMessage(), e);
		} finally {
			JPA.getUtil().closeConnection();
		}
		
	}
	
	//----------------------------------------------------------------------

	public MenuModel getMenuButton() {
		return menuButton;
	}

	public void setMenuButton(MenuModel menuButton) {
		this.menuButton = menuButton;
	}

	public List<Event> getEventsList() {
		return eventsList;
	}

	public void setEventsList(List<Event> eventsList) {
		this.eventsList = eventsList;
	}
	
	public List<Files> getFilesResponseList() {
		return filesResponseList;
	}

	public void setFilesResponseList(List<Files> filesResponseList) {
		this.filesResponseList = filesResponseList;
	}

	public Map<Long ,Object[]> getEventsControl() {
		return eventsControl;
	}

	public void setEventsControl(Map<Long ,Object[]> eventsControl) {
		this.eventsControl = eventsControl;
	}

	public String getResponseEndTime() {
		return responseEndTime;
	}

	public void setResponseEndTime(String responseEndTime) {
		this.responseEndTime = responseEndTime;
	}

	public boolean isShowBntMoreInfo() {
		return showBntMoreInfo;
	}

	public void setShowBntMoreInfo(boolean showBntMoreInfo) {
		this.showBntMoreInfo = showBntMoreInfo;
	}

	public boolean isAdminMod() {
		return adminMod;
	}

	public void setAdminMod(boolean adminMod) {
		this.adminMod = adminMod;
	}	
	
	//------------------------------------ шаблон за печат на заявлението ---------------------------------------------
	
	/** Генерира шаблона с попълнените данни от заявлението 
	 * 
	 */
	public void generateFile(){
		
		try {
			if(filledFileShablon==null){
				filledFileShablon = new CreateShablons().createShablApplic(applic,filesList, getSystemData(), getUserData());
			} 
			 
//			Показва попълнения шаблон			
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
			
			HttpServletRequest request =(HttpServletRequest)externalContext.getRequest();
			String agent = request.getHeader("user-agent");

			
			String codedfilename = ""; 
			
			if (null != agent &&  (-1 != agent.indexOf("MSIE") || (-1 != agent.indexOf("Mozilla") && -1 != agent.indexOf("rv:11")) || (-1 != agent.indexOf("Edge"))  ) ) {
				codedfilename = URLEncoder.encode(filledFileShablon.getFilename(), "UTF8");
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				codedfilename = MimeUtility.encodeText(filledFileShablon.getFilename(), "UTF8", "B");
			} else {
				codedfilename = URLEncoder.encode(filledFileShablon.getFilename(), "UTF8");
			}
		    
		    
		    externalContext.setResponseHeader("Content-Type", "application/x-download");
		    externalContext.setResponseHeader("Content-Length", filledFileShablon.getContent().length + "");
		    externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + codedfilename+ "\"");
			externalContext.getResponseOutputStream().write(filledFileShablon.getContent());
			facesContext.responseComplete();
			 
			 
				
			
		 } catch (InvalidParameterException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
		 } catch (ObjectNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
		 } catch (DbErrorException e) {
			LOGGER.error("Грешка четене  от базата!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR,getMessageResourceString("beanMessages","general.errDataBaseMsg"));
		 } catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
		} finally {
			JPA.getUtil().closeConnection();
		}
		
	}
	
	//------------------------------------ край на шаблон за печат на заявлението -------------------------------------

	//------------------------------------ тематики на заявлението ----------------------------------------------------
	
	public List<AppThemes> getThemesList() {		
		return themesList;
	}

	public void setThemesList(List<AppThemes> themesList) {		
		this.themesList = themesList;
	}
	
	public List<Long> getIdThemes() {
		return idThemes;
	}

	public void setIdThemes(List<Long> idThemes) {
		this.idThemes = idThemes;
		
		if (!this.idThemes.isEmpty()) {
			
			for (Long idThema : this.idThemes) {
				AppThemes thema = new AppThemes();
				thema.setThemeValue(idThema);
				
				this.themesList.add(thema);
			}			 
		}
		
		this.idThemes.clear();
	}

	public String getThemesText() {
		return themesText;
	}
	
	public void setThemesText(String themesText) {
		this.themesText = themesText;
	}
	
	/** Изтрива избраната тематика на заявление
	 * 
	 * @param thema
	 */
	public void deleteThema(AppThemes thema){
		
		try {
			
			JPA.getUtil().begin();
			
			List<AppThemes> deleteThemesList = new ArrayList<>();
			deleteThemesList.add(thema);
			
			if (!deleteThemesList.isEmpty()) {
				for (int i = 0; i < deleteThemesList.size(); i++) {
					if (deleteThemesList.get(i).getId() != null) {
						themesDAO.deleteById(deleteThemesList.get(i).getId());
					}
				}
			}
			
			JPA.getUtil().commit();		
			
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesDeleteMsg"));
			
			this.themesList.remove(thema);
		
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error("√решка при запис на за¤вление! ", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));

		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("√решка при запис на за¤вление!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));

		} finally {
			JPA.getUtil().closeConnection();
		}
	}
	
	/** Запис на избраните тематики към заявление
	 * 
	 * 
	 */
	public void actionSaveThemas() {
		
		if (!this.themesList.isEmpty()) {
			
			try {
				
				JPA.getUtil().begin();
			
				for (AppThemes thema : themesList) {
					
					if (thema.getId() == null) {
						
						thema.setApplicationId(this.applic.getId());
						
						this.themesDAO.save(thema);	
					}									
				}
				
				JPA.getUtil().commit();	
	
				/*	??????????????????		
				// Unlock locked objects
				Locker locker_ = new Locker();
				locker_.unlockObjects(this.userId);*/
				
				
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesSaveMsg"));
			
			} catch (DbErrorException e) {
				JPA.getUtil().rollback();
				LOGGER.error("√решка при запис на за¤вление! ", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"));

			} catch (Exception e) {
				JPA.getUtil().rollback();
				LOGGER.error("√решка при запис на за¤вление!!!", e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.exception"));

			} finally {
				JPA.getUtil().closeConnection();
			}
		}		
	}

	public Files getFilledFileShablon() {
		return filledFileShablon;
	}

	public void setFilledFileShablon(Files filledFileShablon) {
		this.filledFileShablon = filledFileShablon;
	}

	public String getViewBackBtn() {
		return viewBackBtn;
	}

	public void setViewBackBtn(String viewBackBtn) {
		this.viewBackBtn = viewBackBtn;
	}

	public boolean isTabAnswareRendered() {
		return tabAnswareRendered;
	}

	public void setTabAnswareRendered(boolean tabAnswareRendered) {
		this.tabAnswareRendered = tabAnswareRendered;
	}
	
	//------------------------------------ край на тематики на заявлението --------------------------------------------
}
