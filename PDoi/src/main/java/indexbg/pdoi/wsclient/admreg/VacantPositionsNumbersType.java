
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VacantPositionsNumbersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VacantPositionsNumbersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="VacantPositionsNumbersID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="TotalVacantPositions" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="CsVacantPosition" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="EmpltVacantPositions" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VacantPositionsNumbersType", namespace = "http://iisda.government.bg/RAS/Common")
public class VacantPositionsNumbersType {

    @XmlAttribute(name = "VacantPositionsNumbersID", required = true)
    protected long vacantPositionsNumbersID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "TotalVacantPositions")
    protected Double totalVacantPositions;
    @XmlAttribute(name = "CsVacantPosition")
    protected Double csVacantPosition;
    @XmlAttribute(name = "EmpltVacantPositions")
    protected Double empltVacantPositions;

    /**
     * Gets the value of the vacantPositionsNumbersID property.
     * 
     */
    public long getVacantPositionsNumbersID() {
        return vacantPositionsNumbersID;
    }

    /**
     * Sets the value of the vacantPositionsNumbersID property.
     * 
     */
    public void setVacantPositionsNumbersID(long value) {
        this.vacantPositionsNumbersID = value;
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
     * Gets the value of the totalVacantPositions property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalVacantPositions() {
        return totalVacantPositions;
    }

    /**
     * Sets the value of the totalVacantPositions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalVacantPositions(Double value) {
        this.totalVacantPositions = value;
    }

    /**
     * Gets the value of the csVacantPosition property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCsVacantPosition() {
        return csVacantPosition;
    }

    /**
     * Sets the value of the csVacantPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCsVacantPosition(Double value) {
        this.csVacantPosition = value;
    }

    /**
     * Gets the value of the empltVacantPositions property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEmpltVacantPositions() {
        return empltVacantPositions;
    }

    /**
     * Sets the value of the empltVacantPositions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEmpltVacantPositions(Double value) {
        this.empltVacantPositions = value;
    }

}
