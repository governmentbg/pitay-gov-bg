
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DcMessageDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DcMessageDetails"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcMessage"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AttachedDocuments" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}ArrayOfDcDocument" minOccurs="0"/&gt;
 *         &lt;element name="MessageText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TimeStampContent" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcTimeStampMessageContent" minOccurs="0"/&gt;
 *         &lt;element name="TimeStampNRD" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcTimeStamp" minOccurs="0"/&gt;
 *         &lt;element name="TimeStampNRO" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcTimeStamp" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DcMessageDetails", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", propOrder = {
    "attachedDocuments",
    "messageText",
    "timeStampContent",
    "timeStampNRD",
    "timeStampNRO"
})
public class DcMessageDetails
    extends DcMessage
{

    @XmlElementRef(name = "AttachedDocuments", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDcDocument> attachedDocuments;
    @XmlElementRef(name = "MessageText", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<String> messageText;
    @XmlElementRef(name = "TimeStampContent", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<DcTimeStampMessageContent> timeStampContent;
    @XmlElementRef(name = "TimeStampNRD", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<DcTimeStamp> timeStampNRD;
    @XmlElementRef(name = "TimeStampNRO", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<DcTimeStamp> timeStampNRO;

    /**
     * Gets the value of the attachedDocuments property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDcDocument }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDcDocument> getAttachedDocuments() {
        return attachedDocuments;
    }

    /**
     * Sets the value of the attachedDocuments property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDcDocument }{@code >}
     *     
     */
    public void setAttachedDocuments(JAXBElement<ArrayOfDcDocument> value) {
        this.attachedDocuments = value;
    }

    /**
     * Gets the value of the messageText property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMessageText() {
        return messageText;
    }

    /**
     * Sets the value of the messageText property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMessageText(JAXBElement<String> value) {
        this.messageText = value;
    }

    /**
     * Gets the value of the timeStampContent property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcTimeStampMessageContent }{@code >}
     *     
     */
    public JAXBElement<DcTimeStampMessageContent> getTimeStampContent() {
        return timeStampContent;
    }

    /**
     * Sets the value of the timeStampContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcTimeStampMessageContent }{@code >}
     *     
     */
    public void setTimeStampContent(JAXBElement<DcTimeStampMessageContent> value) {
        this.timeStampContent = value;
    }

    /**
     * Gets the value of the timeStampNRD property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcTimeStamp }{@code >}
     *     
     */
    public JAXBElement<DcTimeStamp> getTimeStampNRD() {
        return timeStampNRD;
    }

    /**
     * Sets the value of the timeStampNRD property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcTimeStamp }{@code >}
     *     
     */
    public void setTimeStampNRD(JAXBElement<DcTimeStamp> value) {
        this.timeStampNRD = value;
    }

    /**
     * Gets the value of the timeStampNRO property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcTimeStamp }{@code >}
     *     
     */
    public JAXBElement<DcTimeStamp> getTimeStampNRO() {
        return timeStampNRO;
    }

    /**
     * Sets the value of the timeStampNRO property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcTimeStamp }{@code >}
     *     
     */
    public void setTimeStampNRO(JAXBElement<DcTimeStamp> value) {
        this.timeStampNRO = value;
    }

}
