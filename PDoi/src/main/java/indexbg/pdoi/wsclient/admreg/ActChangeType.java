
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ActChangeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActChangeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="DateChanged" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *           &lt;element name="StateGazetteNumber" type="{http://iisda.government.bg/RAS/Common}StateGazetteNumberType" minOccurs="0"/&gt;
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
@XmlType(name = "ActChangeType", namespace = "http://iisda.government.bg/RAS/Common", propOrder = {
    "dateChanged",
    "stateGazetteNumber"
})
public class ActChangeType {

    @XmlElement(name = "DateChanged")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateChanged;
    @XmlElement(name = "StateGazetteNumber")
    protected StateGazetteNumberType stateGazetteNumber;

    /**
     * Gets the value of the dateChanged property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateChanged() {
        return dateChanged;
    }

    /**
     * Sets the value of the dateChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateChanged(XMLGregorianCalendar value) {
        this.dateChanged = value;
    }

    /**
     * Gets the value of the stateGazetteNumber property.
     * 
     * @return
     *     possible object is
     *     {@link StateGazetteNumberType }
     *     
     */
    public StateGazetteNumberType getStateGazetteNumber() {
        return stateGazetteNumber;
    }

    /**
     * Sets the value of the stateGazetteNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateGazetteNumberType }
     *     
     */
    public void setStateGazetteNumber(StateGazetteNumberType value) {
        this.stateGazetteNumber = value;
    }

}
