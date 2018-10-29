
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ActBaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActBaseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="StateGazetteNumber" type="{http://iisda.government.bg/RAS/Common}StateGazetteNumberType" minOccurs="0"/&gt;
 *           &lt;element name="AdoptionDate" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="ActChange" type="{http://iisda.government.bg/RAS/Common}ActChangeType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="GroupActName" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="IsPublishedSG" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActBaseType", namespace = "http://iisda.government.bg/RAS/Common", propOrder = {
    "stateGazetteNumber",
    "adoptionDate",
    "actChange"
})
@XmlSeeAlso({
    ActType.class
})
public class ActBaseType {

    @XmlElement(name = "StateGazetteNumber")
    protected StateGazetteNumberType stateGazetteNumber;
    @XmlElement(name = "AdoptionDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar adoptionDate;
    @XmlElement(name = "ActChange")
    protected List<ActChangeType> actChange;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "GroupActName")
    protected String groupActName;
    @XmlAttribute(name = "IsPublishedSG", required = true)
    protected boolean isPublishedSG;

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

    /**
     * Gets the value of the adoptionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAdoptionDate() {
        return adoptionDate;
    }

    /**
     * Sets the value of the adoptionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAdoptionDate(XMLGregorianCalendar value) {
        this.adoptionDate = value;
    }

    /**
     * Gets the value of the actChange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actChange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActChange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActChangeType }
     * 
     * 
     */
    public List<ActChangeType> getActChange() {
        if (actChange == null) {
            actChange = new ArrayList<ActChangeType>();
        }
        return this.actChange;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the groupActName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupActName() {
        return groupActName;
    }

    /**
     * Sets the value of the groupActName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupActName(String value) {
        this.groupActName = value;
    }

    /**
     * Gets the value of the isPublishedSG property.
     * 
     */
    public boolean isIsPublishedSG() {
        return isPublishedSG;
    }

    /**
     * Sets the value of the isPublishedSG property.
     * 
     */
    public void setIsPublishedSG(boolean value) {
        this.isPublishedSG = value;
    }

}
