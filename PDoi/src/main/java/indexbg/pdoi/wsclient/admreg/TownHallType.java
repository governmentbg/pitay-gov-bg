
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TownHallType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TownHallType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CorrespondenceData" type="{http://iisda.government.bg/RAS/Common}CorrespondenceDataType" minOccurs="0"/&gt;
 *         &lt;element name="Address" type="{http://iisda.government.bg/RAS/Common}AddressType" minOccurs="0"/&gt;
 *         &lt;element name="WorkingTime" type="{http://iisda.government.bg/RAS/Common}WorkingTimeType" minOccurs="0"/&gt;
 *         &lt;element name="TerritorialRange" type="{http://iisda.government.bg/RAS/Common}EkatteAddressType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ThMayorPowers" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ThPositionPersonData" type="{http://iisda.government.bg/RAS/}ThPositionPersonDataType" minOccurs="0"/&gt;
 *         &lt;element name="PrevThPositionPersonData" type="{http://iisda.government.bg/RAS/}ThPositionPersonDataType" minOccurs="0"/&gt;
 *         &lt;element name="StaffNumbers" type="{http://iisda.government.bg/RAS/Common}StaffNumbersType" minOccurs="0"/&gt;
 *         &lt;element name="VacantPositionsNumbers" type="{http://iisda.government.bg/RAS/Common}VacantPositionsNumbersType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="TownHallID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="ThName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ThEkatteCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TownHallType", propOrder = {
    "correspondenceData",
    "address",
    "workingTime",
    "territorialRange",
    "thMayorPowers",
    "thPositionPersonData",
    "prevThPositionPersonData",
    "staffNumbers",
    "vacantPositionsNumbers"
})
public class TownHallType {

    @XmlElement(name = "CorrespondenceData")
    protected CorrespondenceDataType correspondenceData;
    @XmlElement(name = "Address")
    protected AddressType address;
    @XmlElement(name = "WorkingTime")
    protected WorkingTimeType workingTime;
    @XmlElement(name = "TerritorialRange")
    protected List<EkatteAddressType> territorialRange;
    @XmlElement(name = "ThMayorPowers")
    protected List<PowerCompetenceFunctionType> thMayorPowers;
    @XmlElement(name = "ThPositionPersonData")
    protected ThPositionPersonDataType thPositionPersonData;
    @XmlElement(name = "PrevThPositionPersonData")
    protected ThPositionPersonDataType prevThPositionPersonData;
    @XmlElement(name = "StaffNumbers")
    protected StaffNumbersType staffNumbers;
    @XmlElement(name = "VacantPositionsNumbers")
    protected VacantPositionsNumbersType vacantPositionsNumbers;
    @XmlAttribute(name = "TownHallID", required = true)
    protected long townHallID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "ThName")
    protected String thName;
    @XmlAttribute(name = "ThEkatteCode")
    protected String thEkatteCode;

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
     * Gets the value of the territorialRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the territorialRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTerritorialRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EkatteAddressType }
     * 
     * 
     */
    public List<EkatteAddressType> getTerritorialRange() {
        if (territorialRange == null) {
            territorialRange = new ArrayList<EkatteAddressType>();
        }
        return this.territorialRange;
    }

    /**
     * Gets the value of the thMayorPowers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the thMayorPowers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThMayorPowers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PowerCompetenceFunctionType }
     * 
     * 
     */
    public List<PowerCompetenceFunctionType> getThMayorPowers() {
        if (thMayorPowers == null) {
            thMayorPowers = new ArrayList<PowerCompetenceFunctionType>();
        }
        return this.thMayorPowers;
    }

    /**
     * Gets the value of the thPositionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link ThPositionPersonDataType }
     *     
     */
    public ThPositionPersonDataType getThPositionPersonData() {
        return thPositionPersonData;
    }

    /**
     * Sets the value of the thPositionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThPositionPersonDataType }
     *     
     */
    public void setThPositionPersonData(ThPositionPersonDataType value) {
        this.thPositionPersonData = value;
    }

    /**
     * Gets the value of the prevThPositionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link ThPositionPersonDataType }
     *     
     */
    public ThPositionPersonDataType getPrevThPositionPersonData() {
        return prevThPositionPersonData;
    }

    /**
     * Sets the value of the prevThPositionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ThPositionPersonDataType }
     *     
     */
    public void setPrevThPositionPersonData(ThPositionPersonDataType value) {
        this.prevThPositionPersonData = value;
    }

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
     * Gets the value of the vacantPositionsNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link VacantPositionsNumbersType }
     *     
     */
    public VacantPositionsNumbersType getVacantPositionsNumbers() {
        return vacantPositionsNumbers;
    }

    /**
     * Sets the value of the vacantPositionsNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link VacantPositionsNumbersType }
     *     
     */
    public void setVacantPositionsNumbers(VacantPositionsNumbersType value) {
        this.vacantPositionsNumbers = value;
    }

    /**
     * Gets the value of the townHallID property.
     * 
     */
    public long getTownHallID() {
        return townHallID;
    }

    /**
     * Sets the value of the townHallID property.
     * 
     */
    public void setTownHallID(long value) {
        this.townHallID = value;
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
     * Gets the value of the thName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThName() {
        return thName;
    }

    /**
     * Sets the value of the thName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThName(String value) {
        this.thName = value;
    }

    /**
     * Gets the value of the thEkatteCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThEkatteCode() {
        return thEkatteCode;
    }

    /**
     * Sets the value of the thEkatteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThEkatteCode(String value) {
        this.thEkatteCode = value;
    }

}
