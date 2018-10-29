
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
 *         &lt;element name="GetTerritorialAdmStructureResult" type="{http://iisda.government.bg/RAS/}TerritorialAdmStructure" minOccurs="0"/&gt;
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
    "getTerritorialAdmStructureResult"
})
@XmlRootElement(name = "GetTerritorialAdmStructureResponse", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class GetTerritorialAdmStructureResponse {

    @XmlElement(name = "GetTerritorialAdmStructureResult", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected TerritorialAdmStructure getTerritorialAdmStructureResult;

    /**
     * Gets the value of the getTerritorialAdmStructureResult property.
     * 
     * @return
     *     possible object is
     *     {@link TerritorialAdmStructure }
     *     
     */
    public TerritorialAdmStructure getGetTerritorialAdmStructureResult() {
        return getTerritorialAdmStructureResult;
    }

    /**
     * Sets the value of the getTerritorialAdmStructureResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TerritorialAdmStructure }
     *     
     */
    public void setGetTerritorialAdmStructureResult(TerritorialAdmStructure value) {
        this.getTerritorialAdmStructureResult = value;
    }

}
