package indexbg.pdoi.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.indexbg.system.db.PersistentEntity;

@Entity
@Table(name = "pdoi_mail")
public class Mail implements PersistentEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6207905144179576288L;

	@SequenceGenerator(name = "Mail", sequenceName = "seq_job_mail", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Mail")
	@Column(name = "id", nullable=false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name = "id_user", nullable=true, precision = 10, scale = 0)
	private Long idUser;
	
	@Column(name = "zdoi",  length = 400)
	private String zdoi;
	
	@Column(name = "email", nullable=false, length = 400)
	private String email;
	
	@Column(name = "name_lice", length = 400)
	private String nameLice;
	
	@Column(name = "subject",  nullable=false, length = 250)
	private String subject;
	
	@Column(name = "msg",  nullable=false)
	private String msg;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_mail", nullable=false, length = 7)
	private Date dateEmail;
	
	@Column(name = "error")
	private String error;	
	
	@Column(name = "uri", length = 400)
	private String uri;
	
	public Mail() {

	}

	public Mail(Long idUser, String zdoi, String email, String nameLice, String subject, String msg,
			Date dateEmail, String error, String uri) {
	
		this.idUser = idUser;
		this.zdoi = zdoi;
		this.email = email;
		this.nameLice = nameLice;
		this.subject = subject;
		this.msg = msg;
		this.dateEmail = dateEmail;
		this.error = error;
		this.uri = uri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getZdoi() {
		return zdoi;
	}

	public void setZdoi(String zdoi) {
		this.zdoi = zdoi;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDateEmail() {
		return dateEmail;
	}

	public void setDateEmail(Date dateEmail) {
		this.dateEmail = dateEmail;
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

	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
