
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PositionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PositionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://iisda.government.bg/RAS/}UnitPositionCommonDataType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PositionPersonData" type="{http://iisda.government.bg/RAS/}UpPositionPersonType" minOccurs="0"/&gt;
 *         &lt;element name="PrevPositionPersonData" type="{http://iisda.government.bg/RAS/}UpPositionPersonType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="DoesHeadExecutePosition" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PositionType", propOrder = {
    "positionPersonData",
    "prevPositionPersonData"
})
public class PositionType
    extends UnitPositionCommonDataType
{

    @XmlElement(name = "PositionPersonData")
    protected UpPositionPersonType positionPersonData;
    @XmlElement(name = "PrevPositionPersonData")
    protected UpPositionPersonType prevPositionPersonData;
    @XmlAttribute(name = "DoesHeadExecutePosition", required = true)
    protected boolean doesHeadExecutePosition;

    /**
     * Gets the value of the positionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link UpPositionPersonType }
     *     
     */
    public UpPositionPersonType getPositionPersonData() {
        return positionPersonData;
    }

    /**
     * Sets the value of the positionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpPositionPersonType }
     *     
     */
    public void setPositionPersonData(UpPositionPersonType value) {
        this.positionPersonData = value;
    }

    /**
     * Gets the value of the prevPositionPersonData property.
     * 
     * @return
     *     possible object is
     *     {@link UpPositionPersonType }
     *     
     */
    public UpPositionPersonType getPrevPositionPersonData() {
        return prevPositionPersonData;
    }

    /**
     * Sets the value of the prevPositionPersonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpPositionPersonType }
     *     
     */
    public void setPrevPositionPersonData(UpPositionPersonType value) {
        this.prevPositionPersonData = value;
    }

    /**
     * Gets the value of the doesHeadExecutePosition property.
     * 
     */
    public boolean isDoesHeadExecutePosition() {
        return doesHeadExecutePosition;
    }

    /**
     * Sets the value of the doesHeadExecutePosition property.
     * 
     */
    public void setDoesHeadExecutePosition(boolean value) {
        this.doesHeadExecutePosition = value;
    }

}
