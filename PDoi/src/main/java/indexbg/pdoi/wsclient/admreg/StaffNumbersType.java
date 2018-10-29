
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StaffNumbersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StaffNumbersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="StaffNumbersID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="TotalStaffNumbers" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="ExpertsNumbers" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="AdvisorsNumbers" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="CivilServantNumbers" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="EmploymentNumbers" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="BodiesDeputiesNumbers" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StaffNumbersType", namespace = "http://iisda.government.bg/RAS/Common")
public class StaffNumbersType {

    @XmlAttribute(name = "StaffNumbersID", required = true)
    protected long staffNumbersID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "TotalStaffNumbers")
    protected Double totalStaffNumbers;
    @XmlAttribute(name = "ExpertsNumbers")
    protected Double expertsNumbers;
    @XmlAttribute(name = "AdvisorsNumbers")
    protected Double advisorsNumbers;
    @XmlAttribute(name = "CivilServantNumbers")
    protected Double civilServantNumbers;
    @XmlAttribute(name = "EmploymentNumbers")
    protected Double employmentNumbers;
    @XmlAttribute(name = "BodiesDeputiesNumbers")
    protected Double bodiesDeputiesNumbers;

    /**
     * Gets the value of the staffNumbersID property.
     * 
     */
    public long getStaffNumbersID() {
        return staffNumbersID;
    }

    /**
     * Sets the value of the staffNumbersID property.
     * 
     */
    public void setStaffNumbersID(long value) {
        this.staffNumbersID = value;
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
     * Gets the value of the totalStaffNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalStaffNumbers() {
        return totalStaffNumbers;
    }

    /**
     * Sets the value of the totalStaffNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalStaffNumbers(Double value) {
        this.totalStaffNumbers = value;
    }

    /**
     * Gets the value of the expertsNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getExpertsNumbers() {
        return expertsNumbers;
    }

    /**
     * Sets the value of the expertsNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setExpertsNumbers(Double value) {
        this.expertsNumbers = value;
    }

    /**
     * Gets the value of the advisorsNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAdvisorsNumbers() {
        return advisorsNumbers;
    }

    /**
     * Sets the value of the advisorsNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAdvisorsNumbers(Double value) {
        this.advisorsNumbers = value;
    }

    /**
     * Gets the value of the civilServantNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCivilServantNumbers() {
        return civilServantNumbers;
    }

    /**
     * Sets the value of the civilServantNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCivilServantNumbers(Double value) {
        this.civilServantNumbers = value;
    }

    /**
     * Gets the value of the employmentNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEmploymentNumbers() {
        return employmentNumbers;
    }

    /**
     * Sets the value of the employmentNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEmploymentNumbers(Double value) {
        this.employmentNumbers = value;
    }

    /**
     * Gets the value of the bodiesDeputiesNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBodiesDeputiesNumbers() {
        return bodiesDeputiesNumbers;
    }

    /**
     * Sets the value of the bodiesDeputiesNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBodiesDeputiesNumbers(Double value) {
        this.bodiesDeputiesNumbers = value;
    }

}
