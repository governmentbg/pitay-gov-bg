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
@Table(name = "pdoi_application")
public class Application extends TrackableEntity {

	/** Основен java Entity клас на заявленията
	 * 
	 */
	private static final long serialVersionUID = -1079806292866063168L;

	static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	@SequenceGenerator(name = "Application", sequenceName = "seq_application", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Application")
	@Column(name = "id", nullable=false, precision = 10, scale = 0)
	private Long id; // id
	
	@Column(name = "doc_guid", length = 38)
	private String docGuid; //Уникален идентификатор за заявлението под който ще тече кореспонденцията в СЕОС
	
	@Column(name = "applicant_type", nullable=false, precision = 10, scale = 0)
	private Long applicantType; // Тип заявител 
	
	@Column(name = "full_names", nullable=false, length = 100)
	private String fullNames; //4.	Имена/Наименование на юридическо лице
	
	@Column(name = "headoffice", length = 100)
	private String headoffice; // 5.	Седалище на юридическо лице
	
	@Column(name = "country", nullable=false, precision = 10, scale = 0)
	private Long country; //6.1.	Държава
	
	@Column(name = "region", precision = 10, scale = 0)
	private Long region; //6.2.	Област
	
	@Column(name = "municipality", precision = 10, scale = 0)
	private Long municipality; //6.3.	Община
	
	@Column(name = "town", precision = 10, scale = 0)
	private Long town; //6.4.	Населено място
	
	@Column(name = "address", length = 100)
	private String address; //6.5.	Квартал, Улица, №, вх. ет. ап. 
	
	@Column(name = "post_code", length = 50)
	private String postCode; //6.6.	п.к.

	@Column(name = "phone",nullable=false, length = 50)
	private String phone; //7.	Телефон
	
	@Column(name = "email",nullable=false, length = 255)
	private String email; //7.1	Имейл
	
	@Column(name = "response_subject_id", nullable=false, precision = 10, scale = 0)
	private Long responseSubjectId; //8.	Актуален задължен субект
	
	@Column(name = "request",nullable=false, length = 10000)
	private String request; //9.	Описание на запитването
	
	@Column(name = "status", nullable=false, precision = 10, scale = 0)
	private Long status; //11.	Актуален статус на заявление
	
	@Temporal(TemporalType.DATE)
	@Column(name = "status_date",nullable=false)
	private Date statusDate; //12.	Дата на актуален статус
		
	@Column(name = "application_uri", nullable=false, length = 50)
	private String applicationUri; //13.	УРИ на заявление
	
	@Temporal(TemporalType.DATE)
	@Column(name = "registration_date" ,nullable=false)
	private Date registrationDate;//14.	Актуална дата на деловодна регистрация
	
	@Column(name = "response", length = 10000)
	private String response; //17.	Текст на отговора
	
	@Temporal(TemporalType.DATE)
	@Column(name = "response_date")	
	private Date responseDate; //18.	Дата на отговора
	
	@Temporal(TemporalType.DATE)
	@Column(name = "response_end_time")
	private Date responseEndTime; //15.	Текуща крайна дата за решение ???????
	
	@Column(name = "replay_in_time",  precision = 5, scale = 0)
	private Long replayInTime; //19.	Отговорът е в срок
	
	@Column(name = "number_of_visits", precision = 10, scale = 0)
	private Long numberOfVisits; //23.	Посещения
	
	@Column(name = "usefulness", precision = 5, scale = 0)
	private Long usefulness; //24.	Полезност
	
	@Column(name = "add_info", length = 5000)
	private String add_info; // допълнителна информация
	
	@Column(name = "names_publication", nullable = false)
	private boolean namesPublication;
	
	@Column(name = "address_publication", nullable = false)
	private boolean addressPublication;
	
	@Column(name = "headoffice_publication", nullable = false)
	private boolean headofficePublication;
	
	@Column(name = "email_publication", nullable = false)
	private boolean emailPublication;
	
	@Column(name = "phone_publication", nullable = false)
	private boolean phonePpublication;
	
	@Column(name="app_id_for_view", precision = 10, scale = 0)	
	private Long appIdForView;	
	
	@Override
	public Long getCodeMainObject() {
		return Constants.CODE_OBJECT_APPLICATION;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public Long getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(Long applicantType) {
		this.applicantType = applicantType;
	}

	public String getFullNames() {
		return fullNames;
	}

	public void setFullNames(String fullNames) {
		this.fullNames = fullNames;
	}

	public String getHeadoffice() {
		return headoffice;
	}

	public void setHeadoffice(String headoffice) {
		this.headoffice = headoffice;
	}

	public Long getCountry() {
		return country;
	}

	public void setCountry(Long country) {
		this.country = country;
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

	public Long getResponseSubjectId() {
		return responseSubjectId;
	}

	public void setResponseSubjectId(Long responseSubjectId) {
		this.responseSubjectId = responseSubjectId;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getApplicationUri() {
		return applicationUri;
	}

	public void setApplicationUri(String applicationUri) {
		this.applicationUri = applicationUri;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public Date getResponseEndTime() {
		return responseEndTime;
	}

	public void setResponseEndTime(Date responseEndTime) {
		this.responseEndTime = responseEndTime;
	}

	public Long getReplayInTime() {
		return replayInTime;
	}

	public void setReplayInTime(Long replayInTime) {
		this.replayInTime = replayInTime;
	}

	public Long getNumberOfVisits() {
		return numberOfVisits;
	}

	public void setNumberOfVisits(Long numberOfVisits) {
		this.numberOfVisits = numberOfVisits;
	}

	public Long getUsefulness() {
		return usefulness;
	}

	public void setUsefulness(Long usefulness) {
		this.usefulness = usefulness;
	}

	public String getAdd_info() {
		return add_info;
	}

	public void setAdd_info(String add_info) {
		this.add_info = add_info;
	}

	public boolean isNamesPublication() {
		return namesPublication;
	}

	public void setNamesPublication(boolean namesPublication) {
		this.namesPublication = namesPublication;
	}

	public boolean isAddressPublication() {
		return addressPublication;
	}

	public void setAddressPublication(boolean addressPublication) {
		this.addressPublication = addressPublication;
	}

	public boolean isHeadofficePublication() {
		return headofficePublication;
	}

	public void setHeadofficePublication(boolean headofficePublication) {
		this.headofficePublication = headofficePublication;
	}

	public boolean isEmailPublication() {
		return emailPublication;
	}

	public void setEmailPublication(boolean emailPublication) {
		this.emailPublication = emailPublication;
	}

	public boolean isPhonePpublication() {
		return phonePpublication;
	}

	public void setPhonePpublication(boolean phonePpublication) {
		this.phonePpublication = phonePpublication;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getDocGuid() {
		return docGuid;
	}

	public void setDocGuid(String docGuid) {
		this.docGuid = docGuid;
	}

	public Long getAppIdForView() {
		return appIdForView;
	}

	public void setAppIdForView(Long appIdForView) {
		this.appIdForView = appIdForView;
	}	
	
	public String getIdentInfo() {
		return "Рег.ном :"+applicationUri+" Подател: "+fullNames;
	}
}
