package indexbg.pdoi.db.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.TrackableDAO;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.utils.SearchUtils;

import indexbg.pdoi.db.Event;
import indexbg.pdoi.system.Constants;

public class EventDAO extends TrackableDAO<Event> {	

	static final Logger LOGGER = LoggerFactory.getLogger(EventDAO.class);
	
	public EventDAO (Long userId){
		
		super(userId);		
	}
	
	/** Измъкват се параметрите, които да се зареждат на страницата по вид на събитието
	 * 
	 * @param codeEvent
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> eventsControlList (Long codeEvent) throws DbErrorException {
		
		try {
			
			Query query = createNativeQuery(" select app_status_code, app_status, reason, days, old_resp_subject, new_resp_subject, event_status, reason_not_approved, add_text, files, event_delete, mail_to_admin, mail_to_app, mail_to_new_admin "
										  + " from pdoi_event_control where app_event = ? "); 
			
			query.setParameter(1, codeEvent);	
			
			return query.getResultList();			
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на контролите за събитията по код на събитие", e);
		}
		
	}
	
	/**  Измъкват се параметрите, които да се зареждат на страницата за всички събития
	 * 
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> eventsControlList() throws DbErrorException {
		
		try {
			
			Query query = createNativeQuery("select app_event, app_status_code, app_status, reason, days, old_resp_subject, new_resp_subject, event_status, reason_not_approved, add_text, files, event_delete, mail_to_admin, mail_to_app, mail_to_new_admin "
										  + " from pdoi_event_control  "); 
			
			return query.getResultList();			
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на контролите за събитията по код на събитие", e);
		}
		
	}
	
	/** Изтриване на събитието с всички последващи действия, които произтичат от изтриването
	 * 
	 * @param idEvent
	 * @param idApp
	 * @throws DbErrorException
	 */
	public void deleteEvent (Long idEvent, Long idApp) throws DbErrorException {
		
		try {
			
			Date today = SearchUtils.asDate(new Date());
			Event tmpEvent = findById(idEvent);
			
			if (tmpEvent.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_EXTEND_TERM)) {
				
				Date responseEndTime = null;
				
				if (tmpEvent.getEventReason().equals(Constants.CODE_ZNACHENIE_REASON_EXTENSION_REQ_THIRD_PART)) {
					//responseEndTime - 14 дни от response_end_time на заявлението	
					
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(tmpEvent.getEventEndDate());
					gc.add(Calendar.DAY_OF_YEAR, - 14); 
					gc.set(Calendar.MINUTE, 0);
					gc.set(Calendar.SECOND, 0);
					gc.set(Calendar.HOUR_OF_DAY, 0);
					
					responseEndTime = gc.getTime();
				}
				
				if (tmpEvent.getEventReason().equals(Constants.CODE_ZNACHENIE_REASON_EXTENSION_LARGE_INFO)) {
					//responseEndTime - 10 дни от response_end_time на заявлението	
					
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTime(tmpEvent.getEventEndDate());
					gc.add(Calendar.DAY_OF_YEAR, - 10); 
					gc.set(Calendar.MINUTE, 0);
					gc.set(Calendar.SECOND, 0);
					gc.set(Calendar.HOUR_OF_DAY, 0);
					
					responseEndTime = gc.getTime();
				}
				
				Query queryUpdateDate = createNativeQuery(" update pdoi_application set response_end_time = ?2, user_last_mod = ?3, date_last_mod = ?4 where id = ?1 "); 
				
				queryUpdateDate.setParameter(1, idApp);
				queryUpdateDate.setParameter(2, responseEndTime);
				queryUpdateDate.setParameter(3, getUserId());
				queryUpdateDate.setParameter(4, today);
				
				queryUpdateDate.executeUpdate();
			}
			
			if (tmpEvent.getEventType().equals(Constants.CODE_ZNACHENIE_TYPE_EVENT_FINAL_SOLUTION)) {
				
				Query queryUpdateStatus = createNativeQuery("update pdoi_application set status = " + Constants.CODE_ZNACHENIE_STATUS_APP_REGISTERED + ", status_date = ?2, response_date = null, response = null, user_last_mod = ?3, date_last_mod = ?2 where id = ?1 ");
				
				queryUpdateStatus.setParameter(1, idApp);
				queryUpdateStatus.setParameter(2, today);
				queryUpdateStatus.setParameter(3, getUserId());
				
				queryUpdateStatus.executeUpdate();
			}
			
			Query queryDeleteFiles = createNativeQuery(" delete from files where code_object = " + Constants.CODE_OBJECT_EVENT + " and id_object = ? "); 
			
			queryDeleteFiles.setParameter(1, idEvent);			
			
			queryDeleteFiles.executeUpdate();
			
			deleteById(idEvent);
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при изтриване на събитие", e);
		}
		
	}
	
	/** Зареждане на списък със събития по ид на заявление
	 * 
	 * @param appId
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Event> listFromEventsByAppId (Long appId) throws DbErrorException { 
		
		try {
			
			Query query = createNativeQuery(" select id, event_type, event_date from pdoi_event where application_id = ? order by id ", "filterByIdTypeAndDate"); 
			
			query.setParameter(1, appId);	
			
			return query.getResultList();			
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на списък със събития по ид на заявлението!", e);
		}
		
	}
	
	/** Зареждане на списък със събития по ид на заявление и вид на събитие
	 * 
	 * @param appId
	 * @param eventType
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Event> listFromEventsByAppId (Long appId, Long eventType) throws DbErrorException { 
		
		try {
			List<Event> listEventRes = new ArrayList<Event>();
			Query query = createNativeQuery(" select id, application_id, event_type, event_date, add_text from pdoi_event where application_id = ? and event_type = ? order by event_date "); 
			
			query.setParameter(1, appId);	
			query.setParameter(2, eventType);	
			List<Object[]> obj = query.getResultList();
			for (int i = 0; i < obj.size(); i++) {
				Object[] tmpEvent = obj.get(i);
				Event e = new Event();
				e.setId(Long.valueOf(tmpEvent[0].toString()));
				e.setApplicationId(Long.valueOf(tmpEvent[1].toString()));
				e.setEventType(Long.valueOf(tmpEvent[2].toString()));
				e.setEventDate((Date)tmpEvent[3]);
				e.setAddText((String)tmpEvent[4]);
				listEventRes.add(e);
			}
			
			return listEventRes;
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на списък със събития по ид на заявлението!", e);
		}
		
	}
	
	/** Зареждане на следващо събитие спрямо последното подадено събитие
	 * 
	 * @param codeEvent
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Object> nextEventsForApp(Long codeEvent) throws DbErrorException{
		try {
			
			Query query = createNativeQuery(" select en.event from pdoi_event_next en join pdoi_event_control ec on en.event_control_id = ec.id  where ec.app_event = ? "); 
			
			query.setParameter(1, codeEvent);	
			
			return query.getResultList();			
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на последващи събития", e);
		}
		
	}
	
}
