
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchIAAPositionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchIAAPositionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Power" type="{http://iisda.government.bg/RAS/Common}PowerCompetenceFunctionType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Act" type="{http://iisda.government.bg/RAS/Common}ActDataType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="BatchIAAPositionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="PositionName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchIAAPositionType", namespace = "http://iisda.government.bg/RAS/Common", propOrder = {
    "power",
    "act"
})
public class BatchIAAPositionType {

    @XmlElement(name = "Power")
    protected List<PowerCompetenceFunctionType> power;
    @XmlElement(name = "Act")
    protected List<ActDataType> act;
    @XmlAttribute(name = "BatchIAAPositionID", required = true)
    protected long batchIAAPositionID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "PositionName")
    protected String positionName;

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
     * {@link ActDataType }
     * 
     * 
     */
    public List<ActDataType> getAct() {
        if (act == null) {
            act = new ArrayList<ActDataType>();
        }
        return this.act;
    }

    /**
     * Gets the value of the batchIAAPositionID property.
     * 
     */
    public long getBatchIAAPositionID() {
        return batchIAAPositionID;
    }

    /**
     * Sets the value of the batchIAAPositionID property.
     * 
     */
    public void setBatchIAAPositionID(long value) {
        this.batchIAAPositionID = value;
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
     * Gets the value of the positionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * Sets the value of the positionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPositionName(String value) {
        this.positionName = value;
    }

}
