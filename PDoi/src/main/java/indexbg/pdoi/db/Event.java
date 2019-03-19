package indexbg.pdoi.db;

import static javax.persistence.GenerationType.SEQUENCE;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.indexbg.system.db.TrackableEntity;
import com.indexbg.system.exceptions.DbErrorException;

import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.SystemData;

@SqlResultSetMapping(name = "filterByIdTypeAndDate", classes = {
		@ConstructorResult(targetClass = Event.class, 
				columns = { 
						@ColumnResult(name = "id", type = Long.class),						
						@ColumnResult(name = "event_type", type = Long.class),
						@ColumnResult(name = "event_date", type = Date.class)						 
						}) 
		})

@Entity
@Table(name = "pdoi_event")
public class Event extends TrackableEntity {	

	/** Основен java Entity клас на събитията
	 * 
	 */
	private static final long serialVersionUID = -8184086275745467345L;

	// properties for object Event	
	@SequenceGenerator(name = "Event", sequenceName = "seq_event", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "Event")
	@Column(name = "id", nullable = false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name="application_id", nullable = false, precision = 10, scale = 0)	
	private Long applicationId; 	
	
	@Column(name="event_type", nullable = false, precision = 10, scale = 0)	
	private Long eventType; 	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "event_date", nullable = false, length = 7)
	private Date eventDate;
	
	@Column(name="status", nullable = false, precision = 10, scale = 0)	
	private Long status; 		

	@Temporal(TemporalType.DATE)
	@Column(name = "event_end_date", length = 7)
	private Date eventEndDate;	
	
	@Column(name = "add_text", length = 5000)
	private String addText;
	
	@Column(name="event_reason", precision = 10, scale = 0)	
	private Long eventReason; 
	
	@Column(name="old_resp_subject_id", precision = 10, scale = 0)	
	private Long oldRespSubjectId;
	
	@Column(name="new_resp_subject_id", precision = 10, scale = 0)	
	private Long newRespSubjectId;
	
	@Column(name="reason_not_approved", precision = 10, scale = 0)	
	private Long reasonNotApproved;
	
	@Column(name="app_id_for_view", precision = 10, scale = 0)	
	private Long appIdForView;
	
	@Transient
	private transient SystemData systemData;
	
	/** default constructor */
	public Event() {
		
	}
	
	public Event(Long id, Long eventType, Date eventDate) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.eventDate = eventDate;
	}
	
	public Event(SystemData sd) {
		systemData = sd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	
	public Long getEventType() {
		return eventType;
	}

	public void setEventType(Long eventType) {
		this.eventType = eventType;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}	
	
	public String getAddText() {
		return addText;
	}

	public void setAddText(String addText) {
		this.addText = addText;
	}

	public Long getEventReason() {
		return eventReason;
	}

	public void setEventReason(Long eventReason) {
		this.eventReason = eventReason;
	}

	public Long getOldRespSubjectId() {
		return oldRespSubjectId;
	}

	public void setOldRespSubjectId(Long oldRespSubjectId) {
		this.oldRespSubjectId = oldRespSubjectId;
	}

	public Long getNewRespSubjectId() {
		return newRespSubjectId;
	}

	public void setNewRespSubjectId(Long newRespSubjectId) {
		this.newRespSubjectId = newRespSubjectId;
	}	

	public Long getReasonNotApproved() {
		return reasonNotApproved;
	}

	public void setReasonNotApproved(Long reasonNotApproved) {
		this.reasonNotApproved = reasonNotApproved;
	}

	public Long getAppIdForView() {
		return appIdForView;
	}

	public void setAppIdForView(Long appIdForView) {
		this.appIdForView = appIdForView;
	}

	@Override
	public Long getUserReg() {
		// TODO Auto-generated method stub
		return super.getUserReg();
	}
	
	@Override
	public Date getDateReg() {
		// TODO Auto-generated method stub
		return super.getDateReg();
	}
	
	@Override
	public Long getUserLastMod() {
		// TODO Auto-generated method stub
		return super.getUserLastMod();
	}
	
	@Override
	public Date getDateLastMod() {
		// TODO Auto-generated method stub
		return super.getDateLastMod();
	}
	
	public SystemData getSystemData() {
		return systemData;
	}

	public void setSystemData(SystemData systemData) {
		this.systemData = systemData;
	}

	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return Constants.CODE_OBJECT_EVENT;
	}
	
	@Override
	public String getIdentInfo() {
		
		String dopInfo = "";

		try {
			
			if (systemData != null) {
				
				dopInfo = "Събитие : " + systemData.decodeItem(Constants.CODE_SYSCLASS_TYPE_EVENT, eventType, Constants.CODE_DEFAULT_LANG , new Date(), getUserReg()) + 
						  " на дата: " + new SimpleDateFormat("dd.MM.yyyy").format(eventDate) + 
						  " към заявление с ид = " + applicationId ;
			} else {
				
				dopInfo = "Събитие : " + eventType  + 
						  " на дата: " + new SimpleDateFormat("dd.MM.yyyy").format(eventDate) + 
						  " към заявление с ид = " + applicationId ;
			}	
		
		} catch (DbErrorException e) {
			e.printStackTrace();
		} 		
		
		return dopInfo;
	}
	
}
