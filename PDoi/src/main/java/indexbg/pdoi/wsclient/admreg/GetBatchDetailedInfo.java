
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="batchIdentificationNumber" type="{http://iisda.government.bg/RAS/IntegrationServices}ArrayOfString" minOccurs="0"/&gt;
 *         &lt;element name="dateAt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="versionIDAt" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "batchIdentificationNumber",
    "dateAt",
    "versionIDAt"
})
@XmlRootElement(name = "GetBatchDetailedInfo", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class GetBatchDetailedInfo {

    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected ArrayOfString batchIdentificationNumber;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAt;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, type = Long.class, nillable = true)
    protected Long versionIDAt;

    /**
     * Gets the value of the batchIdentificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getBatchIdentificationNumber() {
        return batchIdentificationNumber;
    }

    /**
     * Sets the value of the batchIdentificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setBatchIdentificationNumber(ArrayOfString value) {
        this.batchIdentificationNumber = value;
    }

    /**
     * Gets the value of the dateAt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateAt() {
        return dateAt;
    }

    /**
     * Sets the value of the dateAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateAt(XMLGregorianCalendar value) {
        this.dateAt = value;
    }

    /**
     * Gets the value of the versionIDAt property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVersionIDAt() {
        return versionIDAt;
    }

    /**
     * Sets the value of the versionIDAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVersionIDAt(Long value) {
        this.versionIDAt = value;
    }

}
