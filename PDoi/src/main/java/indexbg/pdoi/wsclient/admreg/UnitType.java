
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnitType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://iisda.government.bg/RAS/}UnitPositionCommonDataType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UnitHead" type="{http://iisda.government.bg/RAS/}PositionType" minOccurs="0"/&gt;
 *         &lt;element name="CorrespondenceData" type="{http://iisda.government.bg/RAS/Common}CorrespondenceDataType" minOccurs="0"/&gt;
 *         &lt;element name="Address" type="{http://iisda.government.bg/RAS/Common}AddressType" minOccurs="0"/&gt;
 *         &lt;element name="WorkingTime" type="{http://iisda.government.bg/RAS/Common}WorkingTimeType" minOccurs="0"/&gt;
 *         &lt;element name="RemoteWorkplace" type="{http://iisda.government.bg/RAS/}UnitRemoteWorkplaceType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="UnitTerritorialRange" type="{http://iisda.government.bg/RAS/Common}EkatteAddressType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="CanIssueAdmAct" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="EqualToAdmUnit" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="IsTerritorial" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="UnitHeadUnitNameIncluded" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitType", propOrder = {
    "unitHead",
    "correspondenceData",
    "address",
    "workingTime",
    "remoteWorkplace",
    "unitTerritorialRange"
})
public class UnitType
    extends UnitPositionCommonDataType
{

    @XmlElement(name = "UnitHead")
    protected PositionType unitHead;
    @XmlElement(name = "CorrespondenceData")
    protected CorrespondenceDataType correspondenceData;
    @XmlElement(name = "Address")
    protected AddressType address;
    @XmlElement(name = "WorkingTime")
    protected WorkingTimeType workingTime;
    @XmlElement(name = "RemoteWorkplace")
    protected List<UnitRemoteWorkplaceType> remoteWorkplace;
    @XmlElement(name = "UnitTerritorialRange")
    protected List<EkatteAddressType> unitTerritorialRange;
    @XmlAttribute(name = "CanIssueAdmAct", required = true)
    protected boolean canIssueAdmAct;
    @XmlAttribute(name = "EqualToAdmUnit", required = true)
    protected boolean equalToAdmUnit;
    @XmlAttribute(name = "IsTerritorial", required = true)
    protected boolean isTerritorial;
    @XmlAttribute(name = "UnitHeadUnitNameIncluded")
    protected Boolean unitHeadUnitNameIncluded;

    /**
     * Gets the value of the unitHead property.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getUnitHead() {
        return unitHead;
    }

    /**
     * Sets the value of the unitHead property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setUnitHead(PositionType value) {
        this.unitHead = value;
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
     * Gets the value of the remoteWorkplace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remoteWorkplace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemoteWorkplace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnitRemoteWorkplaceType }
     * 
     * 
     */
    public List<UnitRemoteWorkplaceType> getRemoteWorkplace() {
        if (remoteWorkplace == null) {
            remoteWorkplace = new ArrayList<UnitRemoteWorkplaceType>();
        }
        return this.remoteWorkplace;
    }

    /**
     * Gets the value of the unitTerritorialRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unitTerritorialRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnitTerritorialRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EkatteAddressType }
     * 
     * 
     */
    public List<EkatteAddressType> getUnitTerritorialRange() {
        if (unitTerritorialRange == null) {
            unitTerritorialRange = new ArrayList<EkatteAddressType>();
        }
        return this.unitTerritorialRange;
    }

    /**
     * Gets the value of the canIssueAdmAct property.
     * 
     */
    public boolean isCanIssueAdmAct() {
        return canIssueAdmAct;
    }

    /**
     * Sets the value of the canIssueAdmAct property.
     * 
     */
    public void setCanIssueAdmAct(boolean value) {
        this.canIssueAdmAct = value;
    }

    /**
     * Gets the value of the equalToAdmUnit property.
     * 
     */
    public boolean isEqualToAdmUnit() {
        return equalToAdmUnit;
    }

    /**
     * Sets the value of the equalToAdmUnit property.
     * 
     */
    public void setEqualToAdmUnit(boolean value) {
        this.equalToAdmUnit = value;
    }

    /**
     * Gets the value of the isTerritorial property.
     * 
     */
    public boolean isIsTerritorial() {
        return isTerritorial;
    }

    /**
     * Sets the value of the isTerritorial property.
     * 
     */
    public void setIsTerritorial(boolean value) {
        this.isTerritorial = value;
    }

    /**
     * Gets the value of the unitHeadUnitNameIncluded property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUnitHeadUnitNameIncluded() {
        return unitHeadUnitNameIncluded;
    }

    /**
     * Sets the value of the unitHeadUnitNameIncluded property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUnitHeadUnitNameIncluded(Boolean value) {
        this.unitHeadUnitNameIncluded = value;
    }

}
