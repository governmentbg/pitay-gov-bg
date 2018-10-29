
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DcAdministrativeActInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DcAdministrativeActInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ActNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreatedByInstitution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreatedOnDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="IsValid" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="VerificationInfoType" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums}eVerificationInfoType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DcAdministrativeActInfo", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject", propOrder = {
    "actNumber",
    "createdByInstitution",
    "createdOnDate",
    "isValid",
    "verificationInfoType"
})
public class DcAdministrativeActInfo {

    @XmlElementRef(name = "ActNumber", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject", type = JAXBElement.class, required = false)
    protected JAXBElement<String> actNumber;
    @XmlElementRef(name = "CreatedByInstitution", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject", type = JAXBElement.class, required = false)
    protected JAXBElement<String> createdByInstitution;
    @XmlElement(name = "CreatedOnDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdOnDate;
    @XmlElement(name = "IsValid")
    protected Boolean isValid;
    @XmlElement(name = "VerificationInfoType")
    @XmlSchemaType(name = "string")
    protected EVerificationInfoType verificationInfoType;

    /**
     * Gets the value of the actNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getActNumber() {
        return actNumber;
    }

    /**
     * Sets the value of the actNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setActNumber(JAXBElement<String> value) {
        this.actNumber = value;
    }

    /**
     * Gets the value of the createdByInstitution property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCreatedByInstitution() {
        return createdByInstitution;
    }

    /**
     * Sets the value of the createdByInstitution property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCreatedByInstitution(JAXBElement<String> value) {
        this.createdByInstitution = value;
    }

    /**
     * Gets the value of the createdOnDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedOnDate() {
        return createdOnDate;
    }

    /**
     * Sets the value of the createdOnDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedOnDate(XMLGregorianCalendar value) {
        this.createdOnDate = value;
    }

    /**
     * Gets the value of the isValid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsValid() {
        return isValid;
    }

    /**
     * Sets the value of the isValid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsValid(Boolean value) {
        this.isValid = value;
    }

    /**
     * Gets the value of the verificationInfoType property.
     * 
     * @return
     *     possible object is
     *     {@link EVerificationInfoType }
     *     
     */
    public EVerificationInfoType getVerificationInfoType() {
        return verificationInfoType;
    }

    /**
     * Sets the value of the verificationInfoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EVerificationInfoType }
     *     
     */
    public void setVerificationInfoType(EVerificationInfoType value) {
        this.verificationInfoType = value;
    }

}
