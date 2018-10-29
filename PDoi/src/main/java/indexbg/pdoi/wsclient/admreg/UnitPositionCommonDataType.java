
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for UnitPositionCommonDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnitPositionCommonDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StaffNumbers" type="{http://iisda.government.bg/RAS/Common}StaffNumbersType" minOccurs="0"/&gt;
 *         &lt;element name="VacantPositionsNumbers" type="{http://iisda.government.bg/RAS/Common}VacantPositionsNumbersType" minOccurs="0"/&gt;
 *         &lt;element name="Function" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ChildPositionsAndUnit" type="{http://iisda.government.bg/RAS/}UnitPositionCommonDataType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="UpFunctionCategory" type="{http://iisda.government.bg/RAS/}UpFunctionCategoryType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="UnitPosID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Kind" type="{http://iisda.government.bg/RAS/}ObjectKindEnum" /&gt;
 *       &lt;attribute name="ParentID" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="AdministrationType" type="{http://iisda.government.bg/RAS/}UnitPositionAdministrationTypeEnum" /&gt;
 *       &lt;attribute name="Rank" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *       &lt;attribute name="ClosingDate" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *       &lt;attribute name="ProvideAccessForPeopleWithDisabilities" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="Status" use="required" type="{http://iisda.government.bg/RAS/}UnitPositionStatusEnum" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitPositionCommonDataType", propOrder = {
    "staffNumbers",
    "vacantPositionsNumbers",
    "function",
    "childPositionsAndUnit",
    "upFunctionCategory"
})
@XmlSeeAlso({
    UnitType.class,
    PositionType.class
})
public abstract class UnitPositionCommonDataType {

    @XmlElement(name = "StaffNumbers")
    protected StaffNumbersType staffNumbers;
    @XmlElement(name = "VacantPositionsNumbers")
    protected VacantPositionsNumbersType vacantPositionsNumbers;
    @XmlElement(name = "Function")
    protected List<PowerCompetenceFunctionType> function;
    @XmlElement(name = "ChildPositionsAndUnit")
    protected List<UnitPositionCommonDataType> childPositionsAndUnit;
    @XmlElement(name = "UpFunctionCategory")
    protected List<UpFunctionCategoryType> upFunctionCategory;
    @XmlAttribute(name = "UnitPosID", required = true)
    protected long unitPosID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Kind")
    protected ObjectKindEnum kind;
    @XmlAttribute(name = "ParentID")
    protected Long parentID;
    @XmlAttribute(name = "AdministrationType")
    protected UnitPositionAdministrationTypeEnum administrationType;
    @XmlAttribute(name = "Rank", required = true)
    protected long rank;
    @XmlAttribute(name = "CreationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationDate;
    @XmlAttribute(name = "ClosingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar closingDate;
    @XmlAttribute(name = "ProvideAccessForPeopleWithDisabilities", required = true)
    protected boolean provideAccessForPeopleWithDisabilities;
    @XmlAttribute(name = "Status", required = true)
    protected UnitPositionStatusEnum status;

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
     * Gets the value of the childPositionsAndUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childPositionsAndUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildPositionsAndUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UnitPositionCommonDataType }
     * 
     * 
     */
    public List<UnitPositionCommonDataType> getChildPositionsAndUnit() {
        if (childPositionsAndUnit == null) {
            childPositionsAndUnit = new ArrayList<UnitPositionCommonDataType>();
        }
        return this.childPositionsAndUnit;
    }

    /**
     * Gets the value of the upFunctionCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the upFunctionCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpFunctionCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpFunctionCategoryType }
     * 
     * 
     */
    public List<UpFunctionCategoryType> getUpFunctionCategory() {
        if (upFunctionCategory == null) {
            upFunctionCategory = new ArrayList<UpFunctionCategoryType>();
        }
        return this.upFunctionCategory;
    }

    /**
     * Gets the value of the unitPosID property.
     * 
     */
    public long getUnitPosID() {
        return unitPosID;
    }

    /**
     * Sets the value of the unitPosID property.
     * 
     */
    public void setUnitPosID(long value) {
        this.unitPosID = value;
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
     * Gets the value of the kind property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectKindEnum }
     *     
     */
    public ObjectKindEnum getKind() {
        return kind;
    }

    /**
     * Sets the value of the kind property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectKindEnum }
     *     
     */
    public void setKind(ObjectKindEnum value) {
        this.kind = value;
    }

    /**
     * Gets the value of the parentID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getParentID() {
        return parentID;
    }

    /**
     * Sets the value of the parentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setParentID(Long value) {
        this.parentID = value;
    }

    /**
     * Gets the value of the administrationType property.
     * 
     * @return
     *     possible object is
     *     {@link UnitPositionAdministrationTypeEnum }
     *     
     */
    public UnitPositionAdministrationTypeEnum getAdministrationType() {
        return administrationType;
    }

    /**
     * Sets the value of the administrationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitPositionAdministrationTypeEnum }
     *     
     */
    public void setAdministrationType(UnitPositionAdministrationTypeEnum value) {
        this.administrationType = value;
    }

    /**
     * Gets the value of the rank property.
     * 
     */
    public long getRank() {
        return rank;
    }

    /**
     * Sets the value of the rank property.
     * 
     */
    public void setRank(long value) {
        this.rank = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the closingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getClosingDate() {
        return closingDate;
    }

    /**
     * Sets the value of the closingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setClosingDate(XMLGregorianCalendar value) {
        this.closingDate = value;
    }

    /**
     * Gets the value of the provideAccessForPeopleWithDisabilities property.
     * 
     */
    public boolean isProvideAccessForPeopleWithDisabilities() {
        return provideAccessForPeopleWithDisabilities;
    }

    /**
     * Sets the value of the provideAccessForPeopleWithDisabilities property.
     * 
     */
    public void setProvideAccessForPeopleWithDisabilities(boolean value) {
        this.provideAccessForPeopleWithDisabilities = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link UnitPositionStatusEnum }
     *     
     */
    public UnitPositionStatusEnum getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitPositionStatusEnum }
     *     
     */
    public void setStatus(UnitPositionStatusEnum value) {
        this.status = value;
    }

}
