
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GBPositionBatchType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GBPositionBatchType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Act" type="{http://iisda.government.bg/RAS/Common}ActBaseType" minOccurs="0"/&gt;
 *         &lt;element name="Batch" type="{http://iisda.government.bg/RAS/}BatchIdentificationInfoType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="GbPositionBatchID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="Rank" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GBPositionBatchType", propOrder = {
    "act",
    "batch"
})
public class GBPositionBatchType {

    @XmlElement(name = "Act")
    protected ActBaseType act;
    @XmlElement(name = "Batch")
    protected BatchIdentificationInfoType batch;
    @XmlAttribute(name = "GbPositionBatchID", required = true)
    protected long gbPositionBatchID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "Rank")
    protected Integer rank;

    /**
     * Gets the value of the act property.
     * 
     * @return
     *     possible object is
     *     {@link ActBaseType }
     *     
     */
    public ActBaseType getAct() {
        return act;
    }

    /**
     * Sets the value of the act property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActBaseType }
     *     
     */
    public void setAct(ActBaseType value) {
        this.act = value;
    }

    /**
     * Gets the value of the batch property.
     * 
     * @return
     *     possible object is
     *     {@link BatchIdentificationInfoType }
     *     
     */
    public BatchIdentificationInfoType getBatch() {
        return batch;
    }

    /**
     * Sets the value of the batch property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchIdentificationInfoType }
     *     
     */
    public void setBatch(BatchIdentificationInfoType value) {
        this.batch = value;
    }

    /**
     * Gets the value of the gbPositionBatchID property.
     * 
     */
    public long getGbPositionBatchID() {
        return gbPositionBatchID;
    }

    /**
     * Sets the value of the gbPositionBatchID property.
     * 
     */
    public void setGbPositionBatchID(long value) {
        this.gbPositionBatchID = value;
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

    /**
     * Gets the value of the rank property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * Sets the value of the rank property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRank(Integer value) {
        this.rank = value;
    }

}
