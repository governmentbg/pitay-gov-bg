
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetSentDocumentContentResult" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}DcDocument" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getSentDocumentContentResult"
})
@XmlRootElement(name = "GetSentDocumentContentResponse")
public class GetSentDocumentContentResponse {

    @XmlElementRef(name = "GetSentDocumentContentResult", namespace = "https://edelivery.egov.bg/services/integration", type = JAXBElement.class, required = false)
    protected JAXBElement<DcDocument> getSentDocumentContentResult;

    /**
     * Gets the value of the getSentDocumentContentResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcDocument }{@code >}
     *     
     */
    public JAXBElement<DcDocument> getGetSentDocumentContentResult() {
        return getSentDocumentContentResult;
    }

    /**
     * Sets the value of the getSentDocumentContentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcDocument }{@code >}
     *     
     */
    public void setGetSentDocumentContentResult(JAXBElement<DcDocument> value) {
        this.getSentDocumentContentResult = value;
    }

}
