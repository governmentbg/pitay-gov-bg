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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.TrackableEntity;

import indexbg.pdoi.system.Constants;



@Entity
@Table(name = "pdoi_response_subject")
public class ResponseSubject extends TrackableEntity {


	/** Основен java Entity клас на задължените субекти
	 * 
	 */
	private static final long serialVersionUID = 375800505421805552L;

	static final Logger LOGGER = LoggerFactory.getLogger(ResponseSubject.class);
	
	@SequenceGenerator(name = "ResponseSubject", sequenceName = "seq_response_subject", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ResponseSubject")
	@Column(name = "id", nullable=false, precision = 10, scale = 0)
	private Long id; // id
	
	@Column(name="eik", nullable = false, length= 13)	
	private String eik; 	
	
	@Column(name="subject_name", nullable = false, length= 255)	
	private String subjectName; 
	
	@Column(name="region", nullable = false)	
	private Long region; 	
	
	@Column(name="municipality", nullable = false)	
	private Long municipality; 	
	
	@Column(name="town", nullable = false)
	private Long town; 
	
	@Column(name="address", nullable = false, length= 100)	
	private String address; 
	
	@Column(name="phone",  length= 50)	
	private String phone; 
	
	@Column(name="fax",  length= 255)	
	private String fax; 
	
	@Column(name="email",  length= 50)	
	private String email; 
	
	@Column(name="add_info",  length= 500)	
	private String addInfo; 
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_from", length = 7)
	private Date dateFrom;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_to", length = 7)
	private Date dateTo;
	
	@Column(name="zip_code", nullable = false)	
	private Long zipCode; 	
	
	@Column(name="adm_register")	
	private Boolean admRegister; 
	
	@Column(name="adm_level", precision = 10, scale = 0)	
	private Long admLevel; 	
	
	@Column(name="nomer_register",  length= 25)	
	private String nomerRegister; 
	
	
	
	public String getNomerRegister() {
		return nomerRegister;
	}

	public void setNomerRegister(String nomerRegister) {
		this.nomerRegister = nomerRegister;
	}

	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return Constants.CODE_OBJECT_REQ_SUBJECT;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
    
	public String getEik() {
		return eik;
	}

	public void setEik(String eik) {
		this.eik = eik;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Long getRegion() {
		return region;
	}

	public void setRegion(Long region) {
		this.region = region;
	}

	public Long getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Long municipality) {
		this.municipality = municipality;
	}

	public Long getTown() {
		return town;
	}

	public void setTown(Long town) {
		this.town = town;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddInfo() {
		return addInfo;
	}

	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Boolean getAdmRegister() {
		return admRegister;
	}

	public void setAdmRegister(Boolean admRegister) {
		this.admRegister = admRegister;
	}

	public Long getAdmLevel() {
		return admLevel;
	}

	public void setAdmLevel(Long admLevel) {
		this.admLevel = admLevel;
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
	
	public String toString() {
		return subjectName  + "  " +  nomerRegister;
	}
}
