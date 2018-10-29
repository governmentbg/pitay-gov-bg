
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PoliticalOfficeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PoliticalOfficeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="StaffNumber" type="{http://iisda.government.bg/RAS/Common}StaffNumbersType" minOccurs="0"/&gt;
 *         &lt;element name="VacantPositionsNumbers" type="{http://iisda.government.bg/RAS/Common}VacantPositionsNumbersType" minOccurs="0"/&gt;
 *         &lt;element name="Function" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Position" type="{http://iisda.government.bg/RAS/}PoliticalOfficePositionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="PoliticalOfficeID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PoliticalOfficeType", propOrder = {
    "staffNumber",
    "vacantPositionsNumbers",
    "function",
    "position"
})
public class PoliticalOfficeType {

    @XmlElement(name = "StaffNumber")
    protected StaffNumbersType staffNumber;
    @XmlElement(name = "VacantPositionsNumbers")
    protected VacantPositionsNumbersType vacantPositionsNumbers;
    @XmlElement(name = "Function")
    protected List<PowerCompetenceFunctionType> function;
    @XmlElement(name = "Position")
    protected List<PoliticalOfficePositionType> position;
    @XmlAttribute(name = "PoliticalOfficeID", required = true)
    protected long politicalOfficeID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;

    /**
     * Gets the value of the staffNumber property.
     * 
     * @return
     *     possible object is
     *     {@link StaffNumbersType }
     *     
     */
    public StaffNumbersType getStaffNumber() {
        return staffNumber;
    }

    /**
     * Sets the value of the staffNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link StaffNumbersType }
     *     
     */
    public void setStaffNumber(StaffNumbersType value) {
        this.staffNumber = value;
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
     * Gets the value of the position property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the position property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPosition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PoliticalOfficePositionType }
     * 
     * 
     */
    public List<PoliticalOfficePositionType> getPosition() {
        if (position == null) {
            position = new ArrayList<PoliticalOfficePositionType>();
        }
        return this.position;
    }

    /**
     * Gets the value of the politicalOfficeID property.
     * 
     */
    public long getPoliticalOfficeID() {
        return politicalOfficeID;
    }

    /**
     * Sets the value of the politicalOfficeID property.
     * 
     */
    public void setPoliticalOfficeID(long value) {
        this.politicalOfficeID = value;
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
