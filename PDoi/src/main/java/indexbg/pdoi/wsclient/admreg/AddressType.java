
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EkatteAddress" type="{http://iisda.government.bg/RAS/Common}EkatteAddressType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="AddressID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="PostCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="AddressText" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", namespace = "http://iisda.government.bg/RAS/Common", propOrder = {
    "ekatteAddress"
})
public class AddressType {

    @XmlElement(name = "EkatteAddress")
    protected EkatteAddressType ekatteAddress;
    @XmlAttribute(name = "AddressID", required = true)
    protected long addressID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "PostCode")
    protected String postCode;
    @XmlAttribute(name = "AddressText")
    protected String addressText;

    /**
     * Gets the value of the ekatteAddress property.
     * 
     * @return
     *     possible object is
     *     {@link EkatteAddressType }
     *     
     */
    public EkatteAddressType getEkatteAddress() {
        return ekatteAddress;
    }

    /**
     * Sets the value of the ekatteAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link EkatteAddressType }
     *     
     */
    public void setEkatteAddress(EkatteAddressType value) {
        this.ekatteAddress = value;
    }

    /**
     * Gets the value of the addressID property.
     * 
     */
    public long getAddressID() {
        return addressID;
    }

    /**
     * Sets the value of the addressID property.
     * 
     */
    public void setAddressID(long value) {
        this.addressID = value;
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
     * Gets the value of the postCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    /**
     * Gets the value of the addressText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressText() {
        return addressText;
    }

    /**
     * Sets the value of the addressText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressText(String value) {
        this.addressText = value;
    }

}
