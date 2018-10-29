
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActDataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Act" type="{http://iisda.government.bg/RAS/Common}ActBaseType" minOccurs="0"/&gt;
 *           &lt;element name="ActID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActDataType", namespace = "http://iisda.government.bg/RAS/Common", propOrder = {
    "act",
    "actID"
})
public class ActDataType {

    @XmlElement(name = "Act")
    protected ActBaseType act;
    @XmlElement(name = "ActID")
    protected Long actID;

    /**
     * Gets the value of the act property.
     * 
     * @return
     *     possible object is
     *     {@link ActBaseType }
     *     
     */
    public ActBaseType getAct() {
        return act;
    }

    /**
     * Sets the value of the act property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActBaseType }
     *     
     */
    public void setAct(ActBaseType value) {
        this.act = value;
    }

    /**
     * Gets the value of the actID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActID() {
        return actID;
    }

    /**
     * Sets the value of the actID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActID(Long value) {
        this.actID = value;
    }

}
