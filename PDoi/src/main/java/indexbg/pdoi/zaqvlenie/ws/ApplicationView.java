package indexbg.pdoi.zaqvlenie.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class ApplicationView {

	
// лични данни 	
//  private Long applicantType; // Тип заявител 	
//	private String fullNames; //Имена/Наименование на юридическо лице
//	private String headoffice; // 	Седалище на юридическо лице
//	private Long country; //	Държава
//	private Long region; //	Област
//	private Long municipality; //	Община
//	private Long town; //	Населено място
//	private String address; //	Квартал, Улица, №, вх. ет. ап. 
//	private String postCode; //	п.к.
//	private String phone; //	Телефон
//	private String email; //	Имейл
	
	private Long responseSubjectId; //	Актуален задължен субект код
	private String responseSubjectText; //	Актуален задължен субект текст
	
	private String applicationUri; //	УРИ на заявление
	
	private Long status;      //	Актуален статус на заявление
	private String statusText;
	private Date statusDate; //	Дата на актуален статус
	
	private Date registrationDate;//Актуална дата на деловодна регистрация
	
	private Date responseEndTime; //Текуща крайна дата за решение 
	
	private String request; //Описание на запитването + допълнителни отговори ако е имало искане на поясняваща информация
	
	private Date responseDate; //	Дата на отговора
	private String response; //	Текст на отговора
	
	private Long fwApp;	 //индикатор че заявлението е препратено по компетентност (koda na organizaciqta koqto go e prepratila)
	private String fwAppText;// (naimenovanie  na organizaciqta koqto go e prepratila)
	
	private List<FilesView> filesListRequest = new ArrayList<FilesView>();
	private List<FilesView> filesListResponse = new ArrayList<FilesView>();
	
	public ApplicationView() {}
	
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

//	public String getFullNames() {
//		return fullNames;
//	}
//
//	public void setFullNames(String fullNames) {
//		this.fullNames = fullNames;
//	}
//
//	public String getHeadoffice() {
//		return headoffice;
//	}
//
//	public void setHeadoffice(String headoffice) {
//		this.headoffice = headoffice;
//	}
//
//	public Long getCountry() {
//		return country;
//	}
//
//	public void setCountry(Long country) {
//		this.country = country;
//	}
//
//	public Long getRegion() {
//		return region;
//	}
//
//	public void setRegion(Long region) {
//		this.region = region;
//	}
//
//	public Long getMunicipality() {
//		return municipality;
//	}
//
//	public void setMunicipality(Long municipality) {
//		this.municipality = municipality;
//	}
//
//	public Long getTown() {
//		return town;
//	}
//
//	public void setTown(Long town) {
//		this.town = town;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	
//	public String getPostCode() {
//		return postCode;
//	}
//
//	public void setPostCode(String postCode) {
//		this.postCode = postCode;
//	}
	
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

	public Long getFwApp() {
		return fwApp;
	}

	public void setFwApp(Long fwApp) {
		this.fwApp = fwApp;
	}

	public String getResponseSubjectText() {
		return responseSubjectText;
	}

	public void setResponseSubjectText(String responseSubjectText) {
		this.responseSubjectText = responseSubjectText;
	}

	public String getFwAppText() {
		return fwAppText;
	}

	public void setFwAppText(String fwAppText) {
		this.fwAppText = fwAppText;
	}

	public List<FilesView> getFilesListRequest() {
		return filesListRequest;
	}

	public void setFilesListRequest(List<FilesView> filesListRequest) {
		this.filesListRequest = filesListRequest;
	}

	public List<FilesView> getFilesListResponse() {
		return filesListResponse;
	}

	public void setFilesListResponse(List<FilesView> filesListResponse) {
		this.filesListResponse = filesListResponse;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
}
