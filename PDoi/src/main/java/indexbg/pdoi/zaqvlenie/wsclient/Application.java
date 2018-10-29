
package indexbg.pdoi.zaqvlenie.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for application complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="application"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ws.zaqvlenie.pdoi.indexbg/}trackableEntity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="add_info" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="addressPublication" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="applicantType" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="applicationUri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="emailPublication" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="fullNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="headoffice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="headofficePublication" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="municipality" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="namesPublication" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="numberOfVisits" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="phonePpublication" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="registrationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="replayInTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="request" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="response" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="responseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="responseEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="responseSubjectId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="statusDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="town" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="usefulness" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "application", propOrder = {
    "addInfo",
    "address",
    "addressPublication",
    "applicantType",
    "applicationUri",
    "country",
    "email",
    "emailPublication",
    "fullNames",
    "headoffice",
    "headofficePublication",
    "id",
    "municipality",
    "namesPublication",
    "numberOfVisits",
    "phone",
    "phonePpublication",
    "postCode",
    "region",
    "registrationDate",
    "replayInTime",
    "request",
    "response",
    "responseDate",
    "responseEndTime",
    "responseSubjectId",
    "status",
    "statusDate",
    "town",
    "usefulness"
})
public class Application
    extends TrackableEntity
{

    @XmlElement(name = "add_info")
    protected String addInfo;
    protected String address;
    protected boolean addressPublication;
    protected Long applicantType;
    protected String applicationUri;
    protected Long country;
    protected String email;
    protected boolean emailPublication;
    protected String fullNames;
    protected String headoffice;
    protected boolean headofficePublication;
    protected Long id;
    protected Long municipality;
    protected boolean namesPublication;
    protected Long numberOfVisits;
    protected String phone;
    protected boolean phonePpublication;
    protected String postCode;
    protected Long region;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar registrationDate;
    protected Long replayInTime;
    protected String request;
    protected String response;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar responseDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar responseEndTime;
    protected Long responseSubjectId;
    protected Long status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statusDate;
    protected Long town;
    protected Long usefulness;

    /**
     * Gets the value of the addInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddInfo() {
        return addInfo;
    }

    /**
     * Sets the value of the addInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddInfo(String value) {
        this.addInfo = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the addressPublication property.
     * 
     */
    public boolean isAddressPublication() {
        return addressPublication;
    }

    /**
     * Sets the value of the addressPublication property.
     * 
     */
    public void setAddressPublication(boolean value) {
        this.addressPublication = value;
    }

    /**
     * Gets the value of the applicantType property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getApplicantType() {
        return applicantType;
    }

    /**
     * Sets the value of the applicantType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setApplicantType(Long value) {
        this.applicantType = value;
    }

    /**
     * Gets the value of the applicationUri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationUri() {
        return applicationUri;
    }

    /**
     * Sets the value of the applicationUri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationUri(String value) {
        this.applicationUri = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCountry(Long value) {
        this.country = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the emailPublication property.
     * 
     */
    public boolean isEmailPublication() {
        return emailPublication;
    }

    /**
     * Sets the value of the emailPublication property.
     * 
     */
    public void setEmailPublication(boolean value) {
        this.emailPublication = value;
    }

    /**
     * Gets the value of the fullNames property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullNames() {
        return fullNames;
    }

    /**
     * Sets the value of the fullNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullNames(String value) {
        this.fullNames = value;
    }

    /**
     * Gets the value of the headoffice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeadoffice() {
        return headoffice;
    }

    /**
     * Sets the value of the headoffice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeadoffice(String value) {
        this.headoffice = value;
    }

    /**
     * Gets the value of the headofficePublication property.
     * 
     */
    public boolean isHeadofficePublication() {
        return headofficePublication;
    }

    /**
     * Sets the value of the headofficePublication property.
     * 
     */
    public void setHeadofficePublication(boolean value) {
        this.headofficePublication = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the municipality property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMunicipality() {
        return municipality;
    }

    /**
     * Sets the value of the municipality property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMunicipality(Long value) {
        this.municipality = value;
    }

    /**
     * Gets the value of the namesPublication property.
     * 
     */
    public boolean isNamesPublication() {
        return namesPublication;
    }

    /**
     * Sets the value of the namesPublication property.
     * 
     */
    public void setNamesPublication(boolean value) {
        this.namesPublication = value;
    }

    /**
     * Gets the value of the numberOfVisits property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNumberOfVisits() {
        return numberOfVisits;
    }

    /**
     * Sets the value of the numberOfVisits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNumberOfVisits(Long value) {
        this.numberOfVisits = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the phonePpublication property.
     * 
     */
    public boolean isPhonePpublication() {
        return phonePpublication;
    }

    /**
     * Sets the value of the phonePpublication property.
     * 
     */
    public void setPhonePpublication(boolean value) {
        this.phonePpublication = value;
    }

    /**
     * Gets the value of the postCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRegion(Long value) {
        this.region = value;
    }

    /**
     * Gets the value of the registrationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the value of the registrationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegistrationDate(XMLGregorianCalendar value) {
        this.registrationDate = value;
    }

    /**
     * Gets the value of the replayInTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReplayInTime() {
        return replayInTime;
    }

    /**
     * Sets the value of the replayInTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReplayInTime(Long value) {
        this.replayInTime = value;
    }

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequest(String value) {
        this.request = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponse(String value) {
        this.response = value;
    }

    /**
     * Gets the value of the responseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResponseDate() {
        return responseDate;
    }

    /**
     * Sets the value of the responseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResponseDate(XMLGregorianCalendar value) {
        this.responseDate = value;
    }

    /**
     * Gets the value of the responseEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResponseEndTime() {
        return responseEndTime;
    }

    /**
     * Sets the value of the responseEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResponseEndTime(XMLGregorianCalendar value) {
        this.responseEndTime = value;
    }

    /**
     * Gets the value of the responseSubjectId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getResponseSubjectId() {
        return responseSubjectId;
    }

    /**
     * Sets the value of the responseSubjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setResponseSubjectId(Long value) {
        this.responseSubjectId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStatus(Long value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatusDate() {
        return statusDate;
    }

    /**
     * Sets the value of the statusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatusDate(XMLGregorianCalendar value) {
        this.statusDate = value;
    }

    /**
     * Gets the value of the town property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTown() {
        return town;
    }

    /**
     * Sets the value of the town property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTown(Long value) {
        this.town = value;
    }

    /**
     * Gets the value of the usefulness property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUsefulness() {
        return usefulness;
    }

    /**
     * Sets the value of the usefulness property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUsefulness(Long value) {
        this.usefulness = value;
    }

}
