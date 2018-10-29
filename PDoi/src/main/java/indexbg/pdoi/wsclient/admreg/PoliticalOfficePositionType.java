
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PoliticalOfficePositionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PoliticalOfficePositionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PositionPersonData" type="{http://iisda.government.bg/RAS/}PolOfficePostnPersonType" minOccurs="0"/&gt;
 *         &lt;element name="PrevPositionPersonData" type="{http://iisda.government.bg/RAS/}PolOfficePostnPersonType" minOccurs="0"/&gt;
 *         &lt;element name="Function" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="PolOfficePostnID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="IsPositionEmpty" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Rank" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PoliticalOfficePositionType", propOrder = {
    "positionPersonData",
    "prevPositionPersonData",
    "function"
})
public class PoliticalOfficePositionType {

    @XmlElement(name = "PositionPersonData")
    protected PolOfficePostnPersonType positionPersonData;
    @XmlElement(name = "PrevPositionPersonData")
    protected PolOfficePostnPersonType prevPositionPersonData;
    @XmlElement(name = "Function")
    protected List<PowerCompetenceFunctionType> function;
    @XmlAttribute(name = "PolOfficePostnID", required = true)
    protected long polOfficePostnID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "IsPositionEmpty")
    protected Boolean isPositionEmpty;
    @XmlAttribute(name = "Rank")
    protected Integer rank;

    /**
     * Gets the value of the positionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link PolOfficePostnPersonType }
     *     
     */
    public PolOfficePostnPersonType getPositionPersonData() {
        return positionPersonData;
    }

    /**
     * Sets the value of the positionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolOfficePostnPersonType }
     *     
     */
    public void setPositionPersonData(PolOfficePostnPersonType value) {
        this.positionPersonData = value;
    }

    /**
     * Gets the value of the prevPositionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link PolOfficePostnPersonType }
     *     
     */
    public PolOfficePostnPersonType getPrevPositionPersonData() {
        return prevPositionPersonData;
    }

    /**
     * Sets the value of the prevPositionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PolOfficePostnPersonType }
     *     
     */
    public void setPrevPositionPersonData(PolOfficePostnPersonType value) {
        this.prevPositionPersonData = value;
    }

    /**
     * Gets the value of the function property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the function property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFunction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PowerCompetenceFunctionType }
     * 
     * 
     */
    public List<PowerCompetenceFunctionType> getFunction() {
        if (function == null) {
            function = new ArrayList<PowerCompetenceFunctionType>();
        }
        return this.function;
    }

    /**
     * Gets the value of the polOfficePostnID property.
     * 
     */
    public long getPolOfficePostnID() {
        return polOfficePostnID;
    }

    /**
     * Sets the value of the polOfficePostnID property.
     * 
     */
    public void setPolOfficePostnID(long value) {
        this.polOfficePostnID = value;
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
     * Gets the value of the isPositionEmpty property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPositionEmpty() {
        return isPositionEmpty;
    }

    /**
     * Sets the value of the isPositionEmpty property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPositionEmpty(Boolean value) {
        this.isPositionEmpty = value;
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
