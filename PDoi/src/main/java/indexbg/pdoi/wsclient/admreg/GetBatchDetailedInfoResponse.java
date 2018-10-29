
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GetBatchDetailedInfoResult" type="{http://iisda.government.bg/RAS/IntegrationServices}ArrayOfBatchType" minOccurs="0"/&gt;
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
    "getBatchDetailedInfoResult"
})
@XmlRootElement(name = "GetBatchDetailedInfoResponse", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class GetBatchDetailedInfoResponse {

    @XmlElement(name = "GetBatchDetailedInfoResult", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected ArrayOfBatchType getBatchDetailedInfoResult;

    /**
     * Gets the value of the getBatchDetailedInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBatchType }
     *     
     */
    public ArrayOfBatchType getGetBatchDetailedInfoResult() {
        return getBatchDetailedInfoResult;
    }

    /**
     * Sets the value of the getBatchDetailedInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBatchType }
     *     
     */
    public void setGetBatchDetailedInfoResult(ArrayOfBatchType value) {
        this.getBatchDetailedInfoResult = value;
    }

}
