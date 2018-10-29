
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CorrespondenceDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CorrespondenceDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Phone" type="{http://iisda.government.bg/RAS/Common}CorrespondenceDataPhoneType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="CorrespDataID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="InterSettlementCallingCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="FaxNumber" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Email" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="WebSiteUrl" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="FaxIncludesSettlementCallCode" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorrespondenceDataType", namespace = "http://iisda.government.bg/RAS/Common", propOrder = {
    "phone"
})
public class CorrespondenceDataType {

    @XmlElement(name = "Phone")
    protected List<CorrespondenceDataPhoneType> phone;
    @XmlAttribute(name = "CorrespDataID", required = true)
    protected long correspDataID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "InterSettlementCallingCode")
    protected String interSettlementCallingCode;
    @XmlAttribute(name = "FaxNumber")
    protected String faxNumber;
    @XmlAttribute(name = "Email")
    protected String email;
    @XmlAttribute(name = "WebSiteUrl")
    protected String webSiteUrl;
    @XmlAttribute(name = "FaxIncludesSettlementCallCode")
    protected Boolean faxIncludesSettlementCallCode;

    /**
     * Gets the value of the phone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CorrespondenceDataPhoneType }
     * 
     * 
     */
    public List<CorrespondenceDataPhoneType> getPhone() {
        if (phone == null) {
            phone = new ArrayList<CorrespondenceDataPhoneType>();
        }
        return this.phone;
    }

    /**
     * Gets the value of the correspDataID property.
     * 
     */
    public long getCorrespDataID() {
        return correspDataID;
    }

    /**
     * Sets the value of the correspDataID property.
     * 
     */
    public void setCorrespDataID(long value) {
        this.correspDataID = value;
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
     * Gets the value of the interSettlementCallingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterSettlementCallingCode() {
        return interSettlementCallingCode;
    }

    /**
     * Sets the value of the interSettlementCallingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterSettlementCallingCode(String value) {
        this.interSettlementCallingCode = value;
    }

    /**
     * Gets the value of the faxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Sets the value of the faxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxNumber(String value) {
        this.faxNumber = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the webSiteUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    /**
     * Sets the value of the webSiteUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebSiteUrl(String value) {
        this.webSiteUrl = value;
    }

    /**
     * Gets the value of the faxIncludesSettlementCallCode property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFaxIncludesSettlementCallCode() {
        return faxIncludesSettlementCallCode;
    }

    /**
     * Sets the value of the faxIncludesSettlementCallCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFaxIncludesSettlementCallCode(Boolean value) {
        this.faxIncludesSettlementCallCode = value;
    }

}
