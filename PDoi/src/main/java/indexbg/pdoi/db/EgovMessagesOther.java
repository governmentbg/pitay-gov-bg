package indexbg.pdoi.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.indexbg.system.db.PersistentEntity;

// Generated 2010-5-19 17:04:58 by Hibernate Tools 3.2.2.GA



@Entity
@Table(name = "egov_messages_other")
public class EgovMessagesOther implements PersistentEntity  {

	private static final long serialVersionUID = -3063804870630448946L;
	
	@TableGenerator(name = "EgovMessagesOther", table = "sid", pkColumnName = "object", valueColumnName = "next_val", pkColumnValue = "egovmessagesother", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EgovMessagesOther")
	@Column(name = "id", nullable=false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name="id_message",insertable = false, updatable = false)
	private Long idMessage;
	
	@Column(name = "keyString", length = 250)
	private String keyString;
	
	@Column(name = "value_string")
	private String 	valueString;
	
	
	public EgovMessagesOther() {
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}

	public String getValueString() {
		return valueString;
	}

	public void setValueString(String valueString) {
		this.valueString = valueString;
	}


	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}


}
