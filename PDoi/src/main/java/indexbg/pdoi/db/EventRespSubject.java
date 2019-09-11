package indexbg.pdoi.db;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name = "pdoi_event_resp_subjects")
public class EventRespSubject implements Serializable{	
	

	private static final long serialVersionUID = -8525831662459569132L;

	@SequenceGenerator(name = "EventRespSubject", sequenceName = "seq_event_resp_subj", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "EventRespSubject")
	@Column(name = "id", nullable = false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name="id_resp_subject", nullable = false, precision = 10, scale = 0)
	private Long idRespSubject; 
	
	/** default constructor */
	public EventRespSubject() {
		
	}
	
	public EventRespSubject(EventRespSubject ers) {
		this.idRespSubject = ers.idRespSubject;
	}
	
	public EventRespSubject(Long idRespSubject) {
		this.idRespSubject = idRespSubject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Long getIdRespSubject() {
		return idRespSubject;
	}



	public void setIdRespSubject(Long idRespSubject) {
		this.idRespSubject = idRespSubject;
	}
	
	
}
