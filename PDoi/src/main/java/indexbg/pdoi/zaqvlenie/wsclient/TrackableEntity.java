
package indexbg.pdoi.zaqvlenie.wsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for trackableEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trackableEntity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dateLastMod" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="dateReg" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="userLastMod" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="userReg" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trackableEntity", propOrder = {
    "dateLastMod",
    "dateReg",
    "userLastMod",
    "userReg"
})
@XmlSeeAlso({
    Application.class
})
public abstract class TrackableEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateLastMod;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateReg;
    protected Long userLastMod;
    protected Long userReg;

    /**
     * Gets the value of the dateLastMod property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateLastMod() {
        return dateLastMod;
    }

    /**
     * Sets the value of the dateLastMod property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateLastMod(XMLGregorianCalendar value) {
        this.dateLastMod = value;
    }

    /**
     * Gets the value of the dateReg property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateReg() {
        return dateReg;
    }

    /**
     * Sets the value of the dateReg property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateReg(XMLGregorianCalendar value) {
        this.dateReg = value;
    }

    /**
     * Gets the value of the userLastMod property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUserLastMod() {
        return userLastMod;
    }

    /**
     * Sets the value of the userLastMod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUserLastMod(Long value) {
        this.userLastMod = value;
    }

    /**
     * Gets the value of the userReg property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUserReg() {
        return userReg;
    }

    /**
     * Sets the value of the userReg property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUserReg(Long value) {
        this.userReg = value;
    }

}
