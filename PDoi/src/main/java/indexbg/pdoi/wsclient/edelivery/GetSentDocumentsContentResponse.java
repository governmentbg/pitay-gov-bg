
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
 *         &lt;element name="GetSentDocumentsContentResult" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}ArrayOfDcDocument" minOccurs="0"/&gt;
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
    "getSentDocumentsContentResult"
})
@XmlRootElement(name = "GetSentDocumentsContentResponse")
public class GetSentDocumentsContentResponse {

    @XmlElementRef(name = "GetSentDocumentsContentResult", namespace = "https://edelivery.egov.bg/services/integration", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDcDocument> getSentDocumentsContentResult;

    /**
     * Gets the value of the getSentDocumentsContentResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDcDocument }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDcDocument> getGetSentDocumentsContentResult() {
        return getSentDocumentsContentResult;
    }

    /**
     * Sets the value of the getSentDocumentsContentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDcDocument }{@code >}
     *     
     */
    public void setGetSentDocumentsContentResult(JAXBElement<ArrayOfDcDocument> value) {
        this.getSentDocumentsContentResult = value;
    }

}
