
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="batchIdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="batchUIC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="admStructureKind" type="{http://iisda.government.bg/RAS/}AdmStructureKindsEnum"/&gt;
 *         &lt;element name="batchType" type="{http://iisda.government.bg/RAS/}BatchTypeEnum"/&gt;
 *         &lt;element name="status" type="{http://iisda.government.bg/RAS/}BatchStatusEnum"/&gt;
 *         &lt;element name="dateAt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="versionIDAt" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "batchIdentificationNumber",
    "batchUIC",
    "admStructureKind",
    "batchType",
    "status",
    "dateAt",
    "versionIDAt"
})
@XmlRootElement(name = "SearchBatchesIdentificationInfo", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class SearchBatchesIdentificationInfo {

    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected String batchIdentificationNumber;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected String batchUIC;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected AdmStructureKindsEnum admStructureKind;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected BatchTypeEnum batchType;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected BatchStatusEnum status;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAt;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, type = Long.class, nillable = true)
    protected Long versionIDAt;

    /**
     * Gets the value of the batchIdentificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchIdentificationNumber() {
        return batchIdentificationNumber;
    }

    /**
     * Sets the value of the batchIdentificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchIdentificationNumber(String value) {
        this.batchIdentificationNumber = value;
    }

    /**
     * Gets the value of the batchUIC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchUIC() {
        return batchUIC;
    }

    /**
     * Sets the value of the batchUIC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchUIC(String value) {
        this.batchUIC = value;
    }

    /**
     * Gets the value of the admStructureKind property.
     * 
     * @return
     *     possible object is
     *     {@link AdmStructureKindsEnum }
     *     
     */
    public AdmStructureKindsEnum getAdmStructureKind() {
        return admStructureKind;
    }

    /**
     * Sets the value of the admStructureKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdmStructureKindsEnum }
     *     
     */
    public void setAdmStructureKind(AdmStructureKindsEnum value) {
        this.admStructureKind = value;
    }

    /**
     * Gets the value of the batchType property.
     * 
     * @return
     *     possible object is
     *     {@link BatchTypeEnum }
     *     
     */
    public BatchTypeEnum getBatchType() {
        return batchType;
    }

    /**
     * Sets the value of the batchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchTypeEnum }
     *     
     */
    public void setBatchType(BatchTypeEnum value) {
        this.batchType = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link BatchStatusEnum }
     *     
     */
    public BatchStatusEnum getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchStatusEnum }
     *     
     */
    public void setStatus(BatchStatusEnum value) {
        this.status = value;
    }

    /**
     * Gets the value of the dateAt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateAt() {
        return dateAt;
    }

    /**
     * Sets the value of the dateAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateAt(XMLGregorianCalendar value) {
        this.dateAt = value;
    }

    /**
     * Gets the value of the versionIDAt property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVersionIDAt() {
        return versionIDAt;
    }

    /**
     * Sets the value of the versionIDAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVersionIDAt(Long value) {
        this.versionIDAt = value;
    }

}
