
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EkatteAddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EkatteAddressType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="DistrictEkatteCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="DistrictName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="MunicipalityEkatteCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="MunicipalityName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="SettlementEkatteCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="SettlementName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="AreaEkatteCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="AreaName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EkatteAddressType", namespace = "http://iisda.government.bg/RAS/Common")
public class EkatteAddressType {

    @XmlAttribute(name = "DistrictEkatteCode")
    protected String districtEkatteCode;
    @XmlAttribute(name = "DistrictName")
    protected String districtName;
    @XmlAttribute(name = "MunicipalityEkatteCode")
    protected String municipalityEkatteCode;
    @XmlAttribute(name = "MunicipalityName")
    protected String municipalityName;
    @XmlAttribute(name = "SettlementEkatteCode")
    protected String settlementEkatteCode;
    @XmlAttribute(name = "SettlementName")
    protected String settlementName;
    @XmlAttribute(name = "AreaEkatteCode")
    protected String areaEkatteCode;
    @XmlAttribute(name = "AreaName")
    protected String areaName;

    /**
     * Gets the value of the districtEkatteCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictEkatteCode() {
        return districtEkatteCode;
    }

    /**
     * Sets the value of the districtEkatteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictEkatteCode(String value) {
        this.districtEkatteCode = value;
    }

    /**
     * Gets the value of the districtName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * Sets the value of the districtName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictName(String value) {
        this.districtName = value;
    }

    /**
     * Gets the value of the municipalityEkatteCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityEkatteCode() {
        return municipalityEkatteCode;
    }

    /**
     * Sets the value of the municipalityEkatteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityEkatteCode(String value) {
        this.municipalityEkatteCode = value;
    }

    /**
     * Gets the value of the municipalityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityName() {
        return municipalityName;
    }

    /**
     * Sets the value of the municipalityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityName(String value) {
        this.municipalityName = value;
    }

    /**
     * Gets the value of the settlementEkatteCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementEkatteCode() {
        return settlementEkatteCode;
    }

    /**
     * Sets the value of the settlementEkatteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementEkatteCode(String value) {
        this.settlementEkatteCode = value;
    }

    /**
     * Gets the value of the settlementName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementName() {
        return settlementName;
    }

    /**
     * Sets the value of the settlementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementName(String value) {
        this.settlementName = value;
    }

    /**
     * Gets the value of the areaEkatteCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaEkatteCode() {
        return areaEkatteCode;
    }

    /**
     * Sets the value of the areaEkatteCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaEkatteCode(String value) {
        this.areaEkatteCode = value;
    }

    /**
     * Gets the value of the areaName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Sets the value of the areaName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaName(String value) {
        this.areaName = value;
    }

}
