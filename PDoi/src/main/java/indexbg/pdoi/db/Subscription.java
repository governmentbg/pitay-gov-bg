package indexbg.pdoi.db;

import static javax.persistence.GenerationType.SEQUENCE;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.indexbg.system.db.TrackableEntity;
import com.indexbg.system.exceptions.DbErrorException;

import indexbg.pdoi.system.Constants;

@Entity
@Table(name = "pdoi_subscription")
public class Subscription extends TrackableEntity {	

	/** Основен java Entity клас за абонаментите 
	 * 
	 */
	private static final long serialVersionUID = 3797726392781532219L;

	// properties for object Subscription	
	@SequenceGenerator(name = "Subscription", sequenceName = "seq_subscription", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "Subscription")
	@Column(name = "id", nullable = false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name="application_id", nullable = false, precision = 10, scale = 0)	
	private Long applicationId; 	
	
	@Column(name="user_id", nullable = false, precision = 10, scale = 0)	
	private Long userId; 	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_from", nullable = false, length = 7)
	private Date dateFrom;	
	
	/** default constructor */
	public Subscription() {
		
	}
	
	public Subscription(Long id, Long applicationId, Long userId, Date dateFrom) {
		super();
		this.id = id;
		this.applicationId = applicationId;
		this.userId = userId;
		this.dateFrom = dateFrom;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
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
	
	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return Constants.CODE_OBJECT_SUBSCRIPTION;
	}
	
	@Override
	public String getIdentInfo() throws DbErrorException {
		// TODO Auto-generated method stub
		return "Абониране на потребител с id = " + userId + " на дата: " + new SimpleDateFormat("dd.MM.yyyy").format(dateFrom) + " към заявление с ид = " + applicationId ;
	}
	
}
