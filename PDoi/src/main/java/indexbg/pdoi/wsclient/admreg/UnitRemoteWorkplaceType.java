
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnitRemoteWorkplaceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnitRemoteWorkplaceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StaffNumbers" type="{http://iisda.government.bg/RAS/Common}StaffNumbersType" minOccurs="0"/&gt;
 *         &lt;element name="CorrespondenceData" type="{http://iisda.government.bg/RAS/Common}CorrespondenceDataType" minOccurs="0"/&gt;
 *         &lt;element name="Address" type="{http://iisda.government.bg/RAS/Common}AddressType" minOccurs="0"/&gt;
 *         &lt;element name="WorkingTime" type="{http://iisda.government.bg/RAS/Common}WorkingTimeType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="RemoteWorkplaceID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitRemoteWorkplaceType", propOrder = {
    "staffNumbers",
    "correspondenceData",
    "address",
    "workingTime"
})
public class UnitRemoteWorkplaceType {

    @XmlElement(name = "StaffNumbers")
    protected StaffNumbersType staffNumbers;
    @XmlElement(name = "CorrespondenceData")
    protected CorrespondenceDataType correspondenceData;
    @XmlElement(name = "Address")
    protected AddressType address;
    @XmlElement(name = "WorkingTime")
    protected WorkingTimeType workingTime;
    @XmlAttribute(name = "RemoteWorkplaceID", required = true)
    protected long remoteWorkplaceID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;

    /**
     * Gets the value of the staffNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link StaffNumbersType }
     *     
     */
    public StaffNumbersType getStaffNumbers() {
        return staffNumbers;
    }

    /**
     * Sets the value of the staffNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link StaffNumbersType }
     *     
     */
    public void setStaffNumbers(StaffNumbersType value) {
        this.staffNumbers = value;
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
     * Gets the value of the remoteWorkplaceID property.
     * 
     */
    public long getRemoteWorkplaceID() {
        return remoteWorkplaceID;
    }

    /**
     * Sets the value of the remoteWorkplaceID property.
     * 
     */
    public void setRemoteWorkplaceID(long value) {
        this.remoteWorkplaceID = value;
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
