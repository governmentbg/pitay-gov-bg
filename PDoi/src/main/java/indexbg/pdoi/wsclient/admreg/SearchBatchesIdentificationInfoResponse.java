
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
 *         &lt;element name="SearchBatchesIdentificationInfoResult" type="{http://iisda.government.bg/RAS/IntegrationServices}ArrayOfBatchIdentificationInfoType" minOccurs="0"/&gt;
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
    "searchBatchesIdentificationInfoResult"
})
@XmlRootElement(name = "SearchBatchesIdentificationInfoResponse", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class SearchBatchesIdentificationInfoResponse {

    @XmlElement(name = "SearchBatchesIdentificationInfoResult", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected ArrayOfBatchIdentificationInfoType searchBatchesIdentificationInfoResult;

    /**
     * Gets the value of the searchBatchesIdentificationInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBatchIdentificationInfoType }
     *     
     */
    public ArrayOfBatchIdentificationInfoType getSearchBatchesIdentificationInfoResult() {
        return searchBatchesIdentificationInfoResult;
    }

    /**
     * Sets the value of the searchBatchesIdentificationInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBatchIdentificationInfoType }
     *     
     */
    public void setSearchBatchesIdentificationInfoResult(ArrayOfBatchIdentificationInfoType value) {
        this.searchBatchesIdentificationInfoResult = value;
    }

}
