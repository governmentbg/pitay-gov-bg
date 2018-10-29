
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GoverningBodyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GoverningBodyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HeadPosition" type="{http://iisda.government.bg/RAS/}GoverningBodyPositionType" minOccurs="0"/&gt;
 *         &lt;element name="DeputyPosition" type="{http://iisda.government.bg/RAS/}GoverningBodyPositionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="MemberPosition" type="{http://iisda.government.bg/RAS/}GoverningBodyPositionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="MinisterWithoutPortfolioPosition" type="{http://iisda.government.bg/RAS/}GoverningBodyPositionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Competence" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Power" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="GoverningBodyID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="IsExecutiveBody" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Type" use="required" type="{http://iisda.government.bg/RAS/}GoverningBodyTypeEnum" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GoverningBodyType", propOrder = {
    "headPosition",
    "deputyPosition",
    "memberPosition",
    "ministerWithoutPortfolioPosition",
    "competence",
    "power"
})
public class GoverningBodyType {

    @XmlElement(name = "HeadPosition")
    protected GoverningBodyPositionType headPosition;
    @XmlElement(name = "DeputyPosition")
    protected List<GoverningBodyPositionType> deputyPosition;
    @XmlElement(name = "MemberPosition")
    protected List<GoverningBodyPositionType> memberPosition;
    @XmlElement(name = "MinisterWithoutPortfolioPosition")
    protected List<GoverningBodyPositionType> ministerWithoutPortfolioPosition;
    @XmlElement(name = "Competence")
    protected List<PowerCompetenceFunctionType> competence;
    @XmlElement(name = "Power")
    protected List<PowerCompetenceFunctionType> power;
    @XmlAttribute(name = "GoverningBodyID", required = true)
    protected long governingBodyID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "IsExecutiveBody", required = true)
    protected boolean isExecutiveBody;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Type", required = true)
    protected GoverningBodyTypeEnum type;

    /**
     * Gets the value of the headPosition property.
     * 
     * @return
     *     possible object is
     *     {@link GoverningBodyPositionType }
     *     
     */
    public GoverningBodyPositionType getHeadPosition() {
        return headPosition;
    }

    /**
     * Sets the value of the headPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link GoverningBodyPositionType }
     *     
     */
    public void setHeadPosition(GoverningBodyPositionType value) {
        this.headPosition = value;
    }

    /**
     * Gets the value of the deputyPosition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deputyPosition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeputyPosition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GoverningBodyPositionType }
     * 
     * 
     */
    public List<GoverningBodyPositionType> getDeputyPosition() {
        if (deputyPosition == null) {
            deputyPosition = new ArrayList<GoverningBodyPositionType>();
        }
        return this.deputyPosition;
    }

    /**
     * Gets the value of the memberPosition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the memberPosition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMemberPosition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GoverningBodyPositionType }
     * 
     * 
     */
    public List<GoverningBodyPositionType> getMemberPosition() {
        if (memberPosition == null) {
            memberPosition = new ArrayList<GoverningBodyPositionType>();
        }
        return this.memberPosition;
    }

    /**
     * Gets the value of the ministerWithoutPortfolioPosition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ministerWithoutPortfolioPosition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMinisterWithoutPortfolioPosition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GoverningBodyPositionType }
     * 
     * 
     */
    public List<GoverningBodyPositionType> getMinisterWithoutPortfolioPosition() {
        if (ministerWithoutPortfolioPosition == null) {
            ministerWithoutPortfolioPosition = new ArrayList<GoverningBodyPositionType>();
        }
        return this.ministerWithoutPortfolioPosition;
    }

    /**
     * Gets the value of the competence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the competence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompetence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PowerCompetenceFunctionType }
     * 
     * 
     */
    public List<PowerCompetenceFunctionType> getCompetence() {
        if (competence == null) {
            competence = new ArrayList<PowerCompetenceFunctionType>();
        }
        return this.competence;
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
     * Gets the value of the governingBodyID property.
     * 
     */
    public long getGoverningBodyID() {
        return governingBodyID;
    }

    /**
     * Sets the value of the governingBodyID property.
     * 
     */
    public void setGoverningBodyID(long value) {
        this.governingBodyID = value;
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
     * Gets the value of the isExecutiveBody property.
     * 
     */
    public boolean isIsExecutiveBody() {
        return isExecutiveBody;
    }

    /**
     * Sets the value of the isExecutiveBody property.
     * 
     */
    public void setIsExecutiveBody(boolean value) {
        this.isExecutiveBody = value;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link GoverningBodyTypeEnum }
     *     
     */
    public GoverningBodyTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link GoverningBodyTypeEnum }
     *     
     */
    public void setType(GoverningBodyTypeEnum value) {
        this.type = value;
    }

}
