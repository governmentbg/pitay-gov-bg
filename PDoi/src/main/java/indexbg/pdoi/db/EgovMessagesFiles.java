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

@Entity
@Table(name = "egov_messages_files")
public class EgovMessagesFiles implements PersistentEntity  {

	private static final long serialVersionUID = -3063804870630448946L;
	
	
	@TableGenerator(name = "EgovMessagesFiles", table = "sid", pkColumnName = "object", valueColumnName = "next_val", pkColumnValue = "egovmessagesfiels", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EgovMessagesFiles")
	@Column(name = "id", nullable=false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name="id_message",insertable = false, updatable = false)
	private Long idMessage;
	
	@Column(name = "filename", length = 4000)
	private String filename;
	
	@Column(name = "mime", length = 4000)
	private String mime;
	
	@Column(name = "blobcontent")
	private byte[] 	blobcontent;
	
	public EgovMessagesFiles() {
		
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public byte[] getBlobcontent() {
		return blobcontent;
	}

	public void setBlobcontent(byte[] blobcontent) {
		this.blobcontent = blobcontent;
	}



	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}


}
