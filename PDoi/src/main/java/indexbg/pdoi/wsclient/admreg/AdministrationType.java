
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdministrationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdministrationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UnitPositionsSubjectOfHeadOfAdm" type="{http://iisda.government.bg/RAS/}UnitPositionCommonDataType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="UnitPositionsInAdm" type="{http://iisda.government.bg/RAS/}UnitPositionCommonDataType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CorrespondenceData" type="{http://iisda.government.bg/RAS/Common}CorrespondenceDataType" minOccurs="0"/&gt;
 *         &lt;element name="Address" type="{http://iisda.government.bg/RAS/Common}AddressType" minOccurs="0"/&gt;
 *         &lt;element name="WorkingTime" type="{http://iisda.government.bg/RAS/Common}WorkingTimeType" minOccurs="0"/&gt;
 *         &lt;element name="AdmTerritorialRange" type="{http://iisda.government.bg/RAS/Common}EkatteAddressType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="SecretaryAbsenceReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *           &lt;element name="SecretaryPosition" type="{http://iisda.government.bg/RAS/}PositionType" minOccurs="0"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="BatchID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="UIC" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="HeadPositionID" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdministrationType", propOrder = {
    "unitPositionsSubjectOfHeadOfAdm",
    "unitPositionsInAdm",
    "correspondenceData",
    "address",
    "workingTime",
    "admTerritorialRange",
    "secretaryAbsenceReason",
    "secretaryPosition"
})
public class AdministrationType {

    @XmlElement(name = "UnitPositionsSubjectOfHeadOfAdm")
    protected List<UnitPositionCommonDataType> unitPositionsSubjectOfHeadOfAdm;
    @XmlElement(name = "UnitPositionsInAdm")
    protected List<UnitPositionCommonDataType> unitPositionsInAdm;
    @XmlElement(name = "CorrespondenceData")
    protected CorrespondenceDataType correspondenceData;
    @XmlElement(name = "Address")
    protected AddressType address;
    @XmlElement(name = "WorkingTime")
    protected WorkingTimeType workingTime;
    @XmlElement(name = "AdmTerritorialRange")
    protected List<EkatteAddressType> admTerritorialRange;
    @XmlElement(name = "SecretaryAbsenceReason")
    protected String secretaryAbsenceReason;
    @XmlElement(name = "SecretaryPosition")
    protected PositionType secretaryPosition;
    @XmlAttribute(name = "BatchID", required = true)
    protected long batchID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "UIC")
    protected String uic;
    @XmlAttribute(name = "HeadPositionID")
    protected Long headPositionID;

    /**
     * Gets the value of the unitPositionsSubjectOfHeadOfAdm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unitPositionsSubjectOfHeadOfAdm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitPositionsSubjectOfHeadOfAdm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnitPositionCommonDataType }
     * 
     * 
     */
    public List<UnitPositionCommonDataType> getUnitPositionsSubjectOfHeadOfAdm() {
        if (unitPositionsSubjectOfHeadOfAdm == null) {
            unitPositionsSubjectOfHeadOfAdm = new ArrayList<UnitPositionCommonDataType>();
        }
        return this.unitPositionsSubjectOfHeadOfAdm;
    }

    /**
     * Gets the value of the unitPositionsInAdm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unitPositionsInAdm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitPositionsInAdm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnitPositionCommonDataType }
     * 
     * 
     */
    public List<UnitPositionCommonDataType> getUnitPositionsInAdm() {
        if (unitPositionsInAdm == null) {
            unitPositionsInAdm = new ArrayList<UnitPositionCommonDataType>();
        }
        return this.unitPositionsInAdm;
    }

    /**
     * Gets the value of the correspondenceData property.
     * 
     * @return
     *     possible object is
     *     {@link CorrespondenceDataType }
     *     
     */
    public CorrespondenceDataType getCorrespondenceData() {
        return correspondenceData;
    }

    /**
     * Sets the value of the correspondenceData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorrespondenceDataType }
     *     
     */
    public void setCorrespondenceData(CorrespondenceDataType value) {
        this.correspondenceData = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setAddress(AddressType value) {
        this.address = value;
    }

    /**
     * Gets the value of the workingTime property.
     * 
     * @return
     *     possible object is
     *     {@link WorkingTimeType }
     *     
     */
    public WorkingTimeType getWorkingTime() {
        return workingTime;
    }

    /**
     * Sets the value of the workingTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkingTimeType }
     *     
     */
    public void setWorkingTime(WorkingTimeType value) {
        this.workingTime = value;
    }

    /**
     * Gets the value of the admTerritorialRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the admTerritorialRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdmTerritorialRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EkatteAddressType }
     * 
     * 
     */
    public List<EkatteAddressType> getAdmTerritorialRange() {
        if (admTerritorialRange == null) {
            admTerritorialRange = new ArrayList<EkatteAddressType>();
        }
        return this.admTerritorialRange;
    }

    /**
     * Gets the value of the secretaryAbsenceReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecretaryAbsenceReason() {
        return secretaryAbsenceReason;
    }

    /**
     * Sets the value of the secretaryAbsenceReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecretaryAbsenceReason(String value) {
        this.secretaryAbsenceReason = value;
    }

    /**
     * Gets the value of the secretaryPosition property.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getSecretaryPosition() {
        return secretaryPosition;
    }

    /**
     * Sets the value of the secretaryPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setSecretaryPosition(PositionType value) {
        this.secretaryPosition = value;
    }

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
     * Gets the value of the headPositionID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHeadPositionID() {
        return headPositionID;
    }

    /**
     * Sets the value of the headPositionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHeadPositionID(Long value) {
        this.headPositionID = value;
    }

}
