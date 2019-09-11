package indexbg.pdoi.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.indexbg.system.db.PersistentEntity;


@Entity
@Table(name = "egov_messages")
public class EgovMessages  implements PersistentEntity  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1333191002904166701L;
	
	@TableGenerator(name = "EgovMessages", table = "sid", pkColumnName = "object", valueColumnName = "next_val", pkColumnValue = "egovmessages", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EgovMessages")
	@Column(name = "id", nullable=false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name = "msg_guid", length = 38)
	private String msgGuid;
	
	@Column(name = "sender_guid", length = 38)
	private String senderGuid;
	
	@Column(name = "sender_name", length = 2000)
	private String senderName;
	
	@Column(name = "sender_eik", length = 25)
	private String senderEik;
	
	@Column(name = "recipient_guid", length = 38)
	private String recepientGuid;
	
	@Column(name = "recipient_name", length = 2000)
	private String recepientName;
	
	@Column(name = "recipient_eik", length = 25)
	private String recepientEik;
	
	@Column(name = "msg_type", length = 255)
	private String msgType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "msg_dat")
	private Date msgDate;
	
	@Column(name = "msg_status", length = 255)
	private String msgStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "msg_status_dat")
	private Date msgStatusDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "msg_reg_dat")
	private Date msgRegDate;
		
	@Column(name = "msg_comment")
	private String 	msgComment;
	
	@Column(name = "msg_urgent", precision = 10, scale = 0)
	private Long msgUrgent;
	
	@Column(name = "msg_inout", precision = 10, scale = 0)
	private Long msgInOut;
	
	@Column(name = "msg_version", length = 255)
	private String msgVersion;
	
	@Column(name = "msg_xml")
	private String 	msgXml;
	
	@Column(name = "msg_rn", length = 255)
	private String msgRn;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "msg_rn_dat")
	private Date msgRnDate;
	
	@Column(name = "doc_guid", length = 38)
	private String docGuid;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "doc_dat")
	private Date docDate;
	
	@Column(name = "doc_rn", length = 255)
	private String docRn;
	
	@Column(name = "doc_uri_reg", length = 255)
	private String docUriReg;
	
	@Column(name = "doc_uri_batch", length = 255)
	private String docUriBtch;
	
	@Column(name = "doc_vid", length = 255)
	private String docVid;
	
	@Column(name = "doc_subject")
	private String 	docSubject;
	
	@Column(name = "doc_comment")
	private String docComment;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "doc_srok")
	private Date docSrok;
	
	@Column(name = "doc_nasochen", length = 255)
	private String docNasochen;
	
	@Column(name = "parent_guid", length = 38)
	private String parrentGuid;
	
	@Column(name = "parent_rn", length = 255)
	private String parrentRn;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "parent_dat")
	private Date parrentDate;
	
	@Column(name = "parent_uri_reg", length = 255)
	private String parrentUriReg;
	
	@Column(name = "parent_uri_batch", length = 255)
	private String parrentUriBatch;
	
	@Column(name = "comm_status", precision = 10, scale = 0)
	private Long commStatus;
	
	@Column(name = "comm_errror")
	private String commError;
	
	@Column(name = "prichina")
	private String prichina;
	
	@Column(name = "user_created", precision = 10, scale = 0)
	private Long userCreated;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_message", nullable = false, referencedColumnName = "id", insertable = true, updatable = true)
	private List<EgovMessagesCoresp> egovMessagesCoresp = new ArrayList<EgovMessagesCoresp>(0);
		
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_message", nullable = false, referencedColumnName = "id", insertable = true, updatable = true)
	private List<EgovMessagesOther>  egovMessagesOther = new ArrayList<EgovMessagesOther>(0);
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="id_message", nullable = false, referencedColumnName = "id", insertable = true, updatable = true)
	private List<EgovMessagesFiles> egovMessagesFiles = new ArrayList<EgovMessagesFiles>(0);
	
	public EgovMessages() {
		
	}
	
	

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgGuid() {
		return msgGuid;
	}

	public void setMsgGuid(String msgGuid) {
		this.msgGuid = msgGuid;
	}

	public String getSenderGuid() {
		return senderGuid;
	}

	public void setSenderGuid(String senderGuid) {
		this.senderGuid = senderGuid;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecepientGuid() {
		return recepientGuid;
	}

	public void setRecepientGuid(String recepientGuid) {
		this.recepientGuid = recepientGuid;
	}

	public String getRecepientName() {
		return recepientName;
	}

	public void setRecepientName(String recepientName) {
		this.recepientName = recepientName;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Date getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Date getMsgStatusDate() {
		return msgStatusDate;
	}

	public void setMsgStatusDate(Date msgStatusDate) {
		this.msgStatusDate = msgStatusDate;
	}

	public Date getMsgRegDate() {
		return msgRegDate;
	}

	public void setMsgRegDate(Date msgRegDate) {
		this.msgRegDate = msgRegDate;
	}

	

	public Long getMsgUrgent() {
		return msgUrgent;
	}

	public void setMsgUrgent(Long msgUrgent) {
		this.msgUrgent = msgUrgent;
	}

	public Long getMsgInOut() {
		return msgInOut;
	}

	public void setMsgInOut(Long msgInOut) {
		this.msgInOut = msgInOut;
	}

	public String getMsgVersion() {
		return msgVersion;
	}

	public void setMsgVersion(String msgVersion) {
		this.msgVersion = msgVersion;
	}

	

	

	public String getMsgRn() {
		return msgRn;
	}

	public void setMsgRn(String msgRn) {
		this.msgRn = msgRn;
	}

	public Date getMsgRnDate() {
		return msgRnDate;
	}

	public void setMsgRnDate(Date msgRnDate) {
		this.msgRnDate = msgRnDate;
	}

	public String getDocGuid() {
		return docGuid;
	}

	public void setDocGuid(String docGuid) {
		this.docGuid = docGuid;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getDocRn() {
		return docRn;
	}

	public void setDocRn(String docRn) {
		this.docRn = docRn;
	}

	public String getDocUriReg() {
		return docUriReg;
	}

	public void setDocUriReg(String docUriReg) {
		this.docUriReg = docUriReg;
	}

	public String getDocUriBtch() {
		return docUriBtch;
	}

	public void setDocUriBtch(String docUriBtch) {
		this.docUriBtch = docUriBtch;
	}

	public String getDocVid() {
		return docVid;
	}

	public void setDocVid(String docVid) {
		this.docVid = docVid;
	}

	public String getDocSubject() {
		return docSubject;
	}

	public void setDocSubject(String docSubject) {
		this.docSubject = docSubject;
	}

	public String getDocComment() {
		return docComment;
	}

	public void setDocComment(String docComment) {
		this.docComment = docComment;
	}

	public Date getDocSrok() {
		return docSrok;
	}

	public void setDocSrok(Date docSrok) {
		this.docSrok = docSrok;
	}

	public String getDocNasochen() {
		return docNasochen;
	}

	public void setDocNasochen(String docNasochen) {
		this.docNasochen = docNasochen;
	}

	public String getParrentGuid() {
		return parrentGuid;
	}

	public void setParrentGuid(String parrentGuid) {
		this.parrentGuid = parrentGuid;
	}

	public String getParrentRn() {
		return parrentRn;
	}

	public void setParrentRn(String parrentRn) {
		this.parrentRn = parrentRn;
	}

	public Date getParrentDate() {
		return parrentDate;
	}

	public void setParrentDate(Date parrentDate) {
		this.parrentDate = parrentDate;
	}

	public String getParrentUriReg() {
		return parrentUriReg;
	}

	public void setParrentUriReg(String parrentUriReg) {
		this.parrentUriReg = parrentUriReg;
	}

	public String getParrentUriBatch() {
		return parrentUriBatch;
	}

	public void setParrentUriBatch(String parrentUriBatch) {
		this.parrentUriBatch = parrentUriBatch;
	}

	public List<EgovMessagesCoresp> getEgovMessagesCoresp() {
		return egovMessagesCoresp;
	}

	public void setEgovMessagesCoresp(List<EgovMessagesCoresp> egovMessagesCoresp) {
		this.egovMessagesCoresp = egovMessagesCoresp;
	}

	public List<EgovMessagesOther> getEgovMessagesOther() {
		return egovMessagesOther;
	}

	public void setEgovMessagesOther(List<EgovMessagesOther> egovMessagesOther) {
		this.egovMessagesOther = egovMessagesOther;
	}

	public List<EgovMessagesFiles> getEgovMessagesFiles() {
		return egovMessagesFiles;
	}

	public void setEgovMessagesFiles(List<EgovMessagesFiles> egovMessagesFiles) {
		this.egovMessagesFiles = egovMessagesFiles;
	}

	public String getMsgComment() {
		return msgComment;
	}



	public void setMsgComment(String msgComment) {
		this.msgComment = msgComment;
	}



	public Long getCommStatus() {
		return commStatus;
	}



	public void setCommStatus(Long commStatus) {
		this.commStatus = commStatus;
	}
	
	public String getMsgXml() {
		return msgXml;
	}



	public void setMsgXml(String msgXml) {
		this.msgXml = msgXml;
	}



	


	public String getCommError() {
		return commError;
	}



	public void setCommError(String commError) {
		this.commError = commError;
	}



	public String getSenderEik() {
		return senderEik;
	}



	public void setSenderEik(String senderEik) {
		this.senderEik = senderEik;
	}



	public String getRecepientEik() {
		return recepientEik;
	}



	public void setRecepientEik(String recepientEik) {
		this.recepientEik = recepientEik;
	}



	public Long getUserCreated() {
		return userCreated;
	}



	public void setUserCreated(Long userCreated) {
		this.userCreated = userCreated;
	}



	public String getPrichina() {
		return prichina;
	}



	public void setPrichina(String prichina) {
		this.prichina = prichina;
	}



	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}

}
