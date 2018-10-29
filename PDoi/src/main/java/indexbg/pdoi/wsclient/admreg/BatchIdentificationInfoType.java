
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchIdentificationInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchIdentificationInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="BatchID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="Type" use="required" type="{http://iisda.government.bg/RAS/}BatchTypeEnum" /&gt;
 *       &lt;attribute name="IdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="AdmStructureKind" use="required" type="{http://iisda.government.bg/RAS/}AdmStructureKindsEnum" /&gt;
 *       &lt;attribute name="UIC" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Status" use="required" type="{http://iisda.government.bg/RAS/}BatchStatusEnum" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchIdentificationInfoType")
public class BatchIdentificationInfoType {

    @XmlAttribute(name = "BatchID", required = true)
    protected long batchID;
    @XmlAttribute(name = "Type", required = true)
    protected BatchTypeEnum type;
    @XmlAttribute(name = "IdentificationNumber")
    protected String identificationNumber;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "AdmStructureKind", required = true)
    protected AdmStructureKindsEnum admStructureKind;
    @XmlAttribute(name = "UIC")
    protected String uic;
    @XmlAttribute(name = "Status", required = true)
    protected BatchStatusEnum status;

    /**
     * Gets the value of the batchID property.
     * 
     */
    public long getBatchID() {
        return batchID;
    }

    /**
     * Sets the value of the batchID property.
     * 
     */
    public void setBatchID(long value) {
        this.batchID = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link BatchTypeEnum }
     *     
     */
    public BatchTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchTypeEnum }
     *     
     */
    public void setType(BatchTypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the identificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the value of the identificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationNumber(String value) {
        this.identificationNumber = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the uic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUIC() {
        return uic;
    }

    /**
     * Sets the value of the uic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUIC(String value) {
        this.uic = value;
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

}
