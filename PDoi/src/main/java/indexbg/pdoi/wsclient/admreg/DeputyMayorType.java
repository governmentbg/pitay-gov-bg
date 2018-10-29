
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeputyMayorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeputyMayorType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CorrespondenceData" type="{http://iisda.government.bg/RAS/Common}CorrespondenceDataType" minOccurs="0"/&gt;
 *         &lt;element name="TerritorialRange" type="{http://iisda.government.bg/RAS/Common}EkatteAddressType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DmPower" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DmPositionPersonData" type="{http://iisda.government.bg/RAS/}DmPositionPersonDataType" minOccurs="0"/&gt;
 *         &lt;element name="PrevDmPositionPersonData" type="{http://iisda.government.bg/RAS/}DmPositionPersonDataType" minOccurs="0"/&gt;
 *         &lt;element name="Location" type="{http://iisda.government.bg/RAS/Common}EkatteAddressType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="DeputyMayorID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeputyMayorType", propOrder = {
    "correspondenceData",
    "territorialRange",
    "dmPower",
    "dmPositionPersonData",
    "prevDmPositionPersonData",
    "location"
})
public class DeputyMayorType {

    @XmlElement(name = "CorrespondenceData")
    protected CorrespondenceDataType correspondenceData;
    @XmlElement(name = "TerritorialRange")
    protected List<EkatteAddressType> territorialRange;
    @XmlElement(name = "DmPower")
    protected List<PowerCompetenceFunctionType> dmPower;
    @XmlElement(name = "DmPositionPersonData")
    protected DmPositionPersonDataType dmPositionPersonData;
    @XmlElement(name = "PrevDmPositionPersonData")
    protected DmPositionPersonDataType prevDmPositionPersonData;
    @XmlElement(name = "Location")
    protected EkatteAddressType location;
    @XmlAttribute(name = "DeputyMayorID", required = true)
    protected long deputyMayorID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;

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
     * Gets the value of the dmPower property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dmPower property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDmPower().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PowerCompetenceFunctionType }
     * 
     * 
     */
    public List<PowerCompetenceFunctionType> getDmPower() {
        if (dmPower == null) {
            dmPower = new ArrayList<PowerCompetenceFunctionType>();
        }
        return this.dmPower;
    }

    /**
     * Gets the value of the dmPositionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link DmPositionPersonDataType }
     *     
     */
    public DmPositionPersonDataType getDmPositionPersonData() {
        return dmPositionPersonData;
    }

    /**
     * Sets the value of the dmPositionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link DmPositionPersonDataType }
     *     
     */
    public void setDmPositionPersonData(DmPositionPersonDataType value) {
        this.dmPositionPersonData = value;
    }

    /**
     * Gets the value of the prevDmPositionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link DmPositionPersonDataType }
     *     
     */
    public DmPositionPersonDataType getPrevDmPositionPersonData() {
        return prevDmPositionPersonData;
    }

    /**
     * Sets the value of the prevDmPositionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link DmPositionPersonDataType }
     *     
     */
    public void setPrevDmPositionPersonData(DmPositionPersonDataType value) {
        this.prevDmPositionPersonData = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link EkatteAddressType }
     *     
     */
    public EkatteAddressType getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link EkatteAddressType }
     *     
     */
    public void setLocation(EkatteAddressType value) {
        this.location = value;
    }

    /**
     * Gets the value of the deputyMayorID property.
     * 
     */
    public long getDeputyMayorID() {
        return deputyMayorID;
    }

    /**
     * Sets the value of the deputyMayorID property.
     * 
     */
    public void setDeputyMayorID(long value) {
        this.deputyMayorID = value;
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
