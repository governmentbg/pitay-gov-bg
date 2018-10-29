
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Administration" type="{http://iisda.government.bg/RAS/}AdministrationType" minOccurs="0"/&gt;
 *         &lt;element name="Act" type="{http://iisda.government.bg/RAS/Common}ActType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="PolicyArea" type="{http://iisda.government.bg/RAS/Common}PolicyAreaType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="GoverningBody" type="{http://iisda.government.bg/RAS/}GoverningBodyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Government" type="{http://iisda.government.bg/RAS/}GovernmentType" minOccurs="0"/&gt;
 *         &lt;element name="PrevGovernment" type="{http://iisda.government.bg/RAS/}GovernmentType" minOccurs="0"/&gt;
 *         &lt;element name="StewardWithMoneyBatch" type="{http://iisda.government.bg/RAS/}BatchRelationshipType" minOccurs="0"/&gt;
 *         &lt;element name="StandaloneStructsToBatch" type="{http://iisda.government.bg/RAS/}BatchRelationshipType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="AdvBoardsToComOrAdmStruct" type="{http://iisda.government.bg/RAS/}BatchRelationshipType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="TownHalls" type="{http://iisda.government.bg/RAS/}TownHallType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DeputyMayors" type="{http://iisda.government.bg/RAS/}DeputyMayorType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BatchIAAPosition" type="{http://iisda.government.bg/RAS/Common}BatchIAAPositionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="BatchIAAPerson" type="{http://iisda.government.bg/RAS/Common}BatchIAAPersonType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="StaffNumbers" type="{http://iisda.government.bg/RAS/Common}StaffNumbersType" minOccurs="0"/&gt;
 *         &lt;element name="VacantPositionsNumbers" type="{http://iisda.government.bg/RAS/Common}VacantPositionsNumbersType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="BatchID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="Type" use="required" type="{http://iisda.government.bg/RAS/}BatchTypeEnum" /&gt;
 *       &lt;attribute name="IdentificationNumber" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Status" use="required" type="{http://iisda.government.bg/RAS/}BatchStatusEnum" /&gt;
 *       &lt;attribute name="BudgetOfficerLevel" type="{http://www.w3.org/2001/XMLSchema}byte" /&gt;
 *       &lt;attribute name="AdmStructureKind" type="{http://iisda.government.bg/RAS/}AdmStructureKindsEnum" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchType", propOrder = {
    "administration",
    "act",
    "policyArea",
    "governingBody",
    "government",
    "prevGovernment",
    "stewardWithMoneyBatch",
    "standaloneStructsToBatch",
    "advBoardsToComOrAdmStruct",
    "townHalls",
    "deputyMayors",
    "batchIAAPosition",
    "batchIAAPerson",
    "staffNumbers",
    "vacantPositionsNumbers"
})
public class BatchType {

    @XmlElement(name = "Administration")
    protected AdministrationType administration;
    @XmlElement(name = "Act")
    protected List<ActType> act;
    @XmlElement(name = "PolicyArea")
    protected List<PolicyAreaType> policyArea;
    @XmlElement(name = "GoverningBody")
    protected List<GoverningBodyType> governingBody;
    @XmlElement(name = "Government")
    protected GovernmentType government;
    @XmlElement(name = "PrevGovernment")
    protected GovernmentType prevGovernment;
    @XmlElement(name = "StewardWithMoneyBatch")
    protected BatchRelationshipType stewardWithMoneyBatch;
    @XmlElement(name = "StandaloneStructsToBatch")
    protected List<BatchRelationshipType> standaloneStructsToBatch;
    @XmlElement(name = "AdvBoardsToComOrAdmStruct")
    protected List<BatchRelationshipType> advBoardsToComOrAdmStruct;
    @XmlElement(name = "TownHalls")
    protected List<TownHallType> townHalls;
    @XmlElement(name = "DeputyMayors")
    protected List<DeputyMayorType> deputyMayors;
    @XmlElement(name = "BatchIAAPosition")
    protected List<BatchIAAPositionType> batchIAAPosition;
    @XmlElement(name = "BatchIAAPerson")
    protected List<BatchIAAPersonType> batchIAAPerson;
    @XmlElement(name = "StaffNumbers")
    protected StaffNumbersType staffNumbers;
    @XmlElement(name = "VacantPositionsNumbers")
    protected VacantPositionsNumbersType vacantPositionsNumbers;
    @XmlAttribute(name = "BatchID", required = true)
    protected long batchID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "Type", required = true)
    protected BatchTypeEnum type;
    @XmlAttribute(name = "IdentificationNumber")
    protected String identificationNumber;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Status", required = true)
    protected BatchStatusEnum status;
    @XmlAttribute(name = "BudgetOfficerLevel")
    protected Byte budgetOfficerLevel;
    @XmlAttribute(name = "AdmStructureKind")
    protected AdmStructureKindsEnum admStructureKind;

    /**
     * Gets the value of the administration property.
     * 
     * @return
     *     possible object is
     *     {@link AdministrationType }
     *     
     */
    public AdministrationType getAdministration() {
        return administration;
    }

    /**
     * Sets the value of the administration property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdministrationType }
     *     
     */
    public void setAdministration(AdministrationType value) {
        this.administration = value;
    }

    /**
     * Gets the value of the act property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the act property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActType }
     * 
     * 
     */
    public List<ActType> getAct() {
        if (act == null) {
            act = new ArrayList<ActType>();
        }
        return this.act;
    }

    /**
     * Gets the value of the policyArea property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the policyArea property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolicyArea().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PolicyAreaType }
     * 
     * 
     */
    public List<PolicyAreaType> getPolicyArea() {
        if (policyArea == null) {
            policyArea = new ArrayList<PolicyAreaType>();
        }
        return this.policyArea;
    }

    /**
     * Gets the value of the governingBody property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the governingBody property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoverningBody().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GoverningBodyType }
     * 
     * 
     */
    public List<GoverningBodyType> getGoverningBody() {
        if (governingBody == null) {
            governingBody = new ArrayList<GoverningBodyType>();
        }
        return this.governingBody;
    }

    /**
     * Gets the value of the government property.
     * 
     * @return
     *     possible object is
     *     {@link GovernmentType }
     *     
     */
    public GovernmentType getGovernment() {
        return government;
    }

    /**
     * Sets the value of the government property.
     * 
     * @param value
     *     allowed object is
     *     {@link GovernmentType }
     *     
     */
    public void setGovernment(GovernmentType value) {
        this.government = value;
    }

    /**
     * Gets the value of the prevGovernment property.
     * 
     * @return
     *     possible object is
     *     {@link GovernmentType }
     *     
     */
    public GovernmentType getPrevGovernment() {
        return prevGovernment;
    }

    /**
     * Sets the value of the prevGovernment property.
     * 
     * @param value
     *     allowed object is
     *     {@link GovernmentType }
     *     
     */
    public void setPrevGovernment(GovernmentType value) {
        this.prevGovernment = value;
    }

    /**
     * Gets the value of the stewardWithMoneyBatch property.
     * 
     * @return
     *     possible object is
     *     {@link BatchRelationshipType }
     *     
     */
    public BatchRelationshipType getStewardWithMoneyBatch() {
        return stewardWithMoneyBatch;
    }

    /**
     * Sets the value of the stewardWithMoneyBatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link BatchRelationshipType }
     *     
     */
    public void setStewardWithMoneyBatch(BatchRelationshipType value) {
        this.stewardWithMoneyBatch = value;
    }

    /**
     * Gets the value of the standaloneStructsToBatch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the standaloneStructsToBatch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStandaloneStructsToBatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchRelationshipType }
     * 
     * 
     */
    public List<BatchRelationshipType> getStandaloneStructsToBatch() {
        if (standaloneStructsToBatch == null) {
            standaloneStructsToBatch = new ArrayList<BatchRelationshipType>();
        }
        return this.standaloneStructsToBatch;
    }

    /**
     * Gets the value of the advBoardsToComOrAdmStruct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the advBoardsToComOrAdmStruct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdvBoardsToComOrAdmStruct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchRelationshipType }
     * 
     * 
     */
    public List<BatchRelationshipType> getAdvBoardsToComOrAdmStruct() {
        if (advBoardsToComOrAdmStruct == null) {
            advBoardsToComOrAdmStruct = new ArrayList<BatchRelationshipType>();
        }
        return this.advBoardsToComOrAdmStruct;
    }

    /**
     * Gets the value of the townHalls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the townHalls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTownHalls().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TownHallType }
     * 
     * 
     */
    public List<TownHallType> getTownHalls() {
        if (townHalls == null) {
            townHalls = new ArrayList<TownHallType>();
        }
        return this.townHalls;
    }

    /**
     * Gets the value of the deputyMayors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deputyMayors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeputyMayors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeputyMayorType }
     * 
     * 
     */
    public List<DeputyMayorType> getDeputyMayors() {
        if (deputyMayors == null) {
            deputyMayors = new ArrayList<DeputyMayorType>();
        }
        return this.deputyMayors;
    }

    /**
     * Gets the value of the batchIAAPosition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchIAAPosition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchIAAPosition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchIAAPositionType }
     * 
     * 
     */
    public List<BatchIAAPositionType> getBatchIAAPosition() {
        if (batchIAAPosition == null) {
            batchIAAPosition = new ArrayList<BatchIAAPositionType>();
        }
        return this.batchIAAPosition;
    }

    /**
     * Gets the value of the batchIAAPerson property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchIAAPerson property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchIAAPerson().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchIAAPersonType }
     * 
     * 
     */
    public List<BatchIAAPersonType> getBatchIAAPerson() {
        if (batchIAAPerson == null) {
            batchIAAPerson = new ArrayList<BatchIAAPersonType>();
        }
        return this.batchIAAPerson;
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

    /**
     * Gets the value of the budgetOfficerLevel property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getBudgetOfficerLevel() {
        return budgetOfficerLevel;
    }

    /**
     * Sets the value of the budgetOfficerLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setBudgetOfficerLevel(Byte value) {
        this.budgetOfficerLevel = value;
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

}
