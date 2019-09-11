package indexbg.pdoi.zaqvlenie.ws;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.utils.SearchUtils;

import indexbg.pdoi.db.Application;
import indexbg.pdoi.db.Event;
import indexbg.pdoi.db.Files;
import indexbg.pdoi.db.ResponseSubject;
import indexbg.pdoi.db.dao.ApplicationDAO;
import indexbg.pdoi.db.dao.EventDAO;
import indexbg.pdoi.db.dao.FilesDAO;
import indexbg.pdoi.db.dao.ResponseSubjectDao;
import indexbg.pdoi.system.Constants;


@WebService()
public class APIZaqvlenie {
	
	static final Logger LOGGER = LoggerFactory.getLogger(APIZaqvlenie.class);
	
	/**
	 * Version of the WebService.
	 * @return
	 */
	@WebMethod()
	public String version() {
	    return "1.0";
	}
	
	/**
	 * Method searches for Application data by given "uri".
	 * @param uri
	 * @return
	 * @throws WSFault
	 */
	@WebMethod(operationName="findApplication",action="findApplication")
	public ApplicationView findApplication(String uri) throws WSFault {
		
		ApplicationView applicView = null;
		
		try {
			FilesDAO filesDAO = new FilesDAO(-1L);
			EventDAO eventDAO = new EventDAO(-1L, null);
			ResponseSubjectDao respSubDAO = new ResponseSubjectDao(-1L,null);
			
			List<Files> filesList = new ArrayList<>();
			List<Files> filesListReshenie = new ArrayList<>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			
			Application applic = new ApplicationDAO(-1L, null).findByURI(uri);
			
			filesList = filesDAO.findByCodeObjAndIdObjAndRel(applic.getCodeMainObject(), applic.getId(), true);
			
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
					filesList.addAll(filesList.size(), filesDAO.findByCodeObjAndIdObjAndRel(Constants.CODE_OBJECT_EVENT, event.getId(), true));
				}
			}
			
			
			Event eventReshenie = null;
			List<Event> tmpEventReshen = eventDAO.listFromEventsByAppId(applic.getId(), Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION);
			if(tmpEventReshen != null && tmpEventReshen.size() > 0) {
				eventReshenie = tmpEventReshen.get(0);
				filesListReshenie = filesDAO.findByCodeObjAndIdObjAndRel(eventReshenie.getCodeMainObject(), eventReshenie.getId(), true);
				
			}
			
			
			applicView = new ApplicationView();
			applicView.setResponseSubjectId(applic.getResponseSubjectId());
			
			if(applic.getResponseSubjectId()!=null) {
				ResponseSubject rsub = respSubDAO.findById(applic.getResponseSubjectId());
				applicView.setResponseSubjectText(rsub.getSubjectName());
			}
			
			applicView.setApplicationUri(applic.getApplicationUri());
			
			applicView.setStatus(applic.getStatus());
			if(applic.getStatus()!=null) {
				
				Query q = JPA.getUtil().getEntityManager().createNativeQuery("select gettextatt(?,?,current_date,?)");
				q.setParameter(1, Constants.CODE_SYSCLASS_STATUS_APPLICATION);
				q.setParameter(2, applic.getStatus());
				q.setParameter(3, Constants.CODE_ZNACHENIE_LANG_BG);
				@SuppressWarnings("unchecked")
				List<Object> rez = q.getResultList();
				
				if(rez!=null && !rez.isEmpty()){
					applicView.setStatusText(SearchUtils.asString(rez.get(0))); 
				}
				
			}
			
			//convertirame fi v obekt FilesView izchisten ot kym poleta
			List<FilesView> filesListView = new ArrayList<>(); 
			List<FilesView> filesListReshenieView = new ArrayList<>(); 
			//List<Long> fIds = new ArrayList<Long>();
			if(!filesList.isEmpty()) {
//				for(Files f:filesList) {
//					fIds.add(f.getId());
//				}
//				filesList = filesDAO.findByIdsFull(fIds);
				for(Files f:filesList) {
					filesListView.add(new FilesView(f.getId() ,f.getFilename(), f.getDescription(), f.getContentType(), f.getContent()));
				}
			}
			
			if(!filesListReshenie.isEmpty()) {
//				fIds = new ArrayList<Long>();
				
//				for(Files f:filesListReshenie) {
//					fIds.add(f.getId());
//				}
//				filesListReshenie = filesDAO.findByIdsFull(fIds);
				for(Files f:filesListReshenie) {
					filesListReshenieView.add(new FilesView(f.getId(), f.getFilename(), f.getDescription(), f.getContentType(), f.getContent()));
				}
				
			}
			
			applicView.setStatusDate(applic.getStatusDate());
			applicView.setRegistrationDate(applic.getRegistrationDate());
			applicView.setResponseEndTime(applic.getResponseEndTime());
			applicView.setRequest(sbEvents.toString());
			applicView.setFilesListRequest(filesListView);
			
			if(eventReshenie!=null) {
				applicView.setResponse(eventReshenie.getAddText());
				applicView.setResponseDate(eventReshenie.getEventDate());
				applicView.setFilesListResponse(filesListReshenieView);
			}
			
			applicView.setFwApp(applic.getFwApp());
			if(applic.getFwApp()!=null) {
				ResponseSubject rsubFw = respSubDAO.findById(applic.getFwApp());
				applicView.setFwAppText(rsubFw.getSubjectName());
			}
			
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			throw new WSFault(e.getMessage(), e);
		} catch (HibernateException e) { 
			LOGGER.error(e.getMessage(),e);
			throw new WSFault(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new WSFault(e.getMessage(), e);
		} finally {
			JPA.getUtil().closeConnection();
		}
		return applicView;
	}
	
	/**
	 * Method searches for FilesView data by given "ident".
	 * @param uri
	 * @return
	 * @throws WSFault
	 */
	@WebMethod(operationName="loadFileContent",action="loadFileContent")
	public FilesView loadFileContent(Long ident) throws WSFault {
		FilesView fv = null; 
		try {
			Files f = new FilesDAO(-1L).findByIdFull(ident);
			if(f!=null) {
				fv =  new FilesView(f.getId() ,f.getFilename(), f.getDescription(), f.getContentType(), f.getContent());
			}
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			throw new WSFault(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new WSFault(e.getMessage(), e);
		} finally {
			JPA.getUtil().closeConnection();
		}
		
		return fv;
	}
}
