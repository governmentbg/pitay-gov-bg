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
@Table(name = "egov_messages_coresp")
public class EgovMessagesCoresp implements PersistentEntity  {

	private static final long serialVersionUID = -3063804870630448946L;
	
	@TableGenerator(name = "EgovMessagesCoresp", table = "sid", pkColumnName = "object", valueColumnName = "next_val", pkColumnValue = "egovmessagescoresp", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EgovMessagesCoresp")
	@Column(name = "id", nullable=false, precision = 10, scale = 0)
	private Long id;
	
	@Column(name="id_message",insertable = false, updatable = false)
	private Long idMessage;
	
	@Column(name = "ime", length = 250)
	private String ime;
	
	@Column(name = "egn", length = 50)
	private String egn;
	
	@Column(name = "idcard", length = 50)
	private String idCard;
	
	@Column(name = "bulstat", length = 20)
	private String bulstat;
	
	@Column(name = "city", length = 50)
	private String city;
	
	@Column(name = "adres", length = 250)
	private String adres;
	
	@Column(name = "pk", length = 20)
	private String pk;
	
	@Column(name = "email", length = 250)
	private String email;
	
	@Column(name = "phone", length = 200)
	private String phone;
	
	@Column(name = "mobile_phone", length = 200)
	private String mobilePhone;
	
	@Column(name = "dop_info")
	private String	dopInfo;
	
	public EgovMessagesCoresp() {
		
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

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getEgn() {
		return egn;
	}

	public void setEgn(String egn) {
		this.egn = egn;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBulstat() {
		return bulstat;
	}

	public void setBulstat(String bulstat) {
		this.bulstat = bulstat;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDopInfo() {
		return dopInfo;
	}

	public void setDopInfo(String dopInfo) {
		this.dopInfo = dopInfo;
	}



	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
