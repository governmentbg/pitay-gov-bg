
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DcMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DcMessage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DateCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="DateReceived" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="DateSent" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="IsDraft" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="ReceiverLogin" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcLogin" minOccurs="0"/&gt;
 *         &lt;element name="ReceiverProfile" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcProfile" minOccurs="0"/&gt;
 *         &lt;element name="SenderLogin" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcLogin" minOccurs="0"/&gt;
 *         &lt;element name="SenderProfile" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcProfile" minOccurs="0"/&gt;
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DcMessage", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", propOrder = {
    "dateCreated",
    "dateReceived",
    "dateSent",
    "id",
    "isDraft",
    "receiverLogin",
    "receiverProfile",
    "senderLogin",
    "senderProfile",
    "title"
})
@XmlSeeAlso({
    DcMessageDetails.class
})
public class DcMessage {

    @XmlElement(name = "DateCreated")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreated;
    @XmlElementRef(name = "DateReceived", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> dateReceived;
    @XmlElementRef(name = "DateSent", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> dateSent;
    @XmlElement(name = "Id")
    protected Integer id;
    @XmlElement(name = "IsDraft")
    protected Boolean isDraft;
    @XmlElementRef(name = "ReceiverLogin", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<DcLogin> receiverLogin;
    @XmlElementRef(name = "ReceiverProfile", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<DcProfile> receiverProfile;
    @XmlElementRef(name = "SenderLogin", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<DcLogin> senderLogin;
    @XmlElementRef(name = "SenderProfile", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<DcProfile> senderProfile;
    @XmlElementRef(name = "Title", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> title;

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the dateReceived property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getDateReceived() {
        return dateReceived;
    }

    /**
     * Sets the value of the dateReceived property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setDateReceived(JAXBElement<XMLGregorianCalendar> value) {
        this.dateReceived = value;
    }

    /**
     * Gets the value of the dateSent property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getDateSent() {
        return dateSent;
    }

    /**
     * Sets the value of the dateSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setDateSent(JAXBElement<XMLGregorianCalendar> value) {
        this.dateSent = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the isDraft property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDraft() {
        return isDraft;
    }

    /**
     * Sets the value of the isDraft property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDraft(Boolean value) {
        this.isDraft = value;
    }

    /**
     * Gets the value of the receiverLogin property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcLogin }{@code >}
     *     
     */
    public JAXBElement<DcLogin> getReceiverLogin() {
        return receiverLogin;
    }

    /**
     * Sets the value of the receiverLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcLogin }{@code >}
     *     
     */
    public void setReceiverLogin(JAXBElement<DcLogin> value) {
        this.receiverLogin = value;
    }

    /**
     * Gets the value of the receiverProfile property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcProfile }{@code >}
     *     
     */
    public JAXBElement<DcProfile> getReceiverProfile() {
        return receiverProfile;
    }

    /**
     * Sets the value of the receiverProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcProfile }{@code >}
     *     
     */
    public void setReceiverProfile(JAXBElement<DcProfile> value) {
        this.receiverProfile = value;
    }

    /**
     * Gets the value of the senderLogin property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcLogin }{@code >}
     *     
     */
    public JAXBElement<DcLogin> getSenderLogin() {
        return senderLogin;
    }

    /**
     * Sets the value of the senderLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcLogin }{@code >}
     *     
     */
    public void setSenderLogin(JAXBElement<DcLogin> value) {
        this.senderLogin = value;
    }

    /**
     * Gets the value of the senderProfile property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcProfile }{@code >}
     *     
     */
    public JAXBElement<DcProfile> getSenderProfile() {
        return senderProfile;
    }

    /**
     * Sets the value of the senderProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcProfile }{@code >}
     *     
     */
    public void setSenderProfile(JAXBElement<DcProfile> value) {
        this.senderProfile = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTitle(JAXBElement<String> value) {
        this.title = value;
    }

}
