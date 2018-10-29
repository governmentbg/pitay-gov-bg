
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GoverningBodyPositionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GoverningBodyPositionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PositionPersonData" type="{http://iisda.government.bg/RAS/}GBPositionPersonDataType" minOccurs="0"/&gt;
 *         &lt;element name="PrevPositionPersonData" type="{http://iisda.government.bg/RAS/}GBPositionPersonDataType" minOccurs="0"/&gt;
 *         &lt;element name="PositionBatch" type="{http://iisda.government.bg/RAS/}GBPositionBatchType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Power" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="PoliticalOffice" type="{http://iisda.government.bg/RAS/}PoliticalOfficeType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="PositionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="FieldsOfActivityName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="IsHeadOfAdministration" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GoverningBodyPositionType", propOrder = {
    "positionPersonData",
    "prevPositionPersonData",
    "positionBatch",
    "power",
    "politicalOffice"
})
public class GoverningBodyPositionType {

    @XmlElement(name = "PositionPersonData")
    protected GBPositionPersonDataType positionPersonData;
    @XmlElement(name = "PrevPositionPersonData")
    protected GBPositionPersonDataType prevPositionPersonData;
    @XmlElement(name = "PositionBatch")
    protected List<GBPositionBatchType> positionBatch;
    @XmlElement(name = "Power")
    protected List<PowerCompetenceFunctionType> power;
    @XmlElement(name = "PoliticalOffice")
    protected PoliticalOfficeType politicalOffice;
    @XmlAttribute(name = "PositionID", required = true)
    protected long positionID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "FieldsOfActivityName")
    protected String fieldsOfActivityName;
    @XmlAttribute(name = "IsHeadOfAdministration", required = true)
    protected boolean isHeadOfAdministration;

    /**
     * Gets the value of the positionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link GBPositionPersonDataType }
     *     
     */
    public GBPositionPersonDataType getPositionPersonData() {
        return positionPersonData;
    }

    /**
     * Sets the value of the positionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link GBPositionPersonDataType }
     *     
     */
    public void setPositionPersonData(GBPositionPersonDataType value) {
        this.positionPersonData = value;
    }

    /**
     * Gets the value of the prevPositionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link GBPositionPersonDataType }
     *     
     */
    public GBPositionPersonDataType getPrevPositionPersonData() {
        return prevPositionPersonData;
    }

    /**
     * Sets the value of the prevPositionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link GBPositionPersonDataType }
     *     
     */
    public void setPrevPositionPersonData(GBPositionPersonDataType value) {
        this.prevPositionPersonData = value;
    }

    /**
     * Gets the value of the positionBatch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the positionBatch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPositionBatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GBPositionBatchType }
     * 
     * 
     */
    public List<GBPositionBatchType> getPositionBatch() {
        if (positionBatch == null) {
            positionBatch = new ArrayList<GBPositionBatchType>();
        }
        return this.positionBatch;
    }

    /**
     * Gets the value of the power property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the power property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPower().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PowerCompetenceFunctionType }
     * 
     * 
     */
    public List<PowerCompetenceFunctionType> getPower() {
        if (power == null) {
            power = new ArrayList<PowerCompetenceFunctionType>();
        }
        return this.power;
    }

    /**
     * Gets the value of the politicalOffice property.
     * 
     * @return
     *     possible object is
     *     {@link PoliticalOfficeType }
     *     
     */
    public PoliticalOfficeType getPoliticalOffice() {
        return politicalOffice;
    }

    /**
     * Sets the value of the politicalOffice property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoliticalOfficeType }
     *     
     */
    public void setPoliticalOffice(PoliticalOfficeType value) {
        this.politicalOffice = value;
    }

    /**
     * Gets the value of the positionID property.
     * 
     */
    public long getPositionID() {
        return positionID;
    }

    /**
     * Sets the value of the positionID property.
     * 
     */
    public void setPositionID(long value) {
        this.positionID = value;
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
     * Gets the value of the fieldsOfActivityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldsOfActivityName() {
        return fieldsOfActivityName;
    }

    /**
     * Sets the value of the fieldsOfActivityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldsOfActivityName(String value) {
        this.fieldsOfActivityName = value;
    }

    /**
     * Gets the value of the isHeadOfAdministration property.
     * 
     */
    public boolean isIsHeadOfAdministration() {
        return isHeadOfAdministration;
    }

    /**
     * Sets the value of the isHeadOfAdministration property.
     * 
     */
    public void setIsHeadOfAdministration(boolean value) {
        this.isHeadOfAdministration = value;
    }

}
