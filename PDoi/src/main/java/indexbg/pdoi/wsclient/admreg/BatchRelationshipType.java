
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchRelationshipType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchRelationshipType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RelatedBatch" type="{http://iisda.government.bg/RAS/}BatchIdentificationInfoType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="BatchRelationshipID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchRelationshipType", propOrder = {
    "relatedBatch"
})
public class BatchRelationshipType {

    @XmlElement(name = "RelatedBatch")
    protected BatchIdentificationInfoType relatedBatch;
    @XmlAttribute(name = "BatchRelationshipID", required = true)
    protected long batchRelationshipID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;

    /**
     * Gets the value of the relatedBatch property.
     * 
     * @return
     *     possible object is
     *     {@link BatchIdentificationInfoType }
     *     
     */
    public BatchIdentificationInfoType getRelatedBatch() {
        return relatedBatch;
    }

    /**
     * Sets the value of the relatedBatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchIdentificationInfoType }
     *     
     */
    public void setRelatedBatch(BatchIdentificationInfoType value) {
        this.relatedBatch = value;
    }

    /**
     * Gets the value of the batchRelationshipID property.
     * 
     */
    public long getBatchRelationshipID() {
        return batchRelationshipID;
    }

    /**
     * Sets the value of the batchRelationshipID property.
     * 
     */
    public void setBatchRelationshipID(long value) {
        this.batchRelationshipID = value;
    }

    /**
     * Gets the value of the versionID property.
     * 
     */
    public long getVersionID() {
        return versionID;
    }

    /**
     * Sets the value of the versionID property.
     * 
     */
    public void setVersionID(long value) {
        this.versionID = value;
    }

}
