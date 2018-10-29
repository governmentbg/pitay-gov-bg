
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DcPartialListOfDcMessageHR29gRRX complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DcPartialListOfDcMessageHR29gRRX"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AllItemsCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Items" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}ArrayOfDcMessage" minOccurs="0"/&gt;
 *         &lt;element name="SortColumn" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums}eSortColumn" minOccurs="0"/&gt;
 *         &lt;element name="SortOrder" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums}eSortOrder" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DcPartialListOfDcMessageHR29gRRX", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", propOrder = {
    "allItemsCount",
    "items",
    "sortColumn",
    "sortOrder"
})
public class DcPartialListOfDcMessageHR29GRRX {

    @XmlElement(name = "AllItemsCount")
    protected Integer allItemsCount;
    @XmlElementRef(name = "Items", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDcMessage> items;
    @XmlElement(name = "SortColumn")
    @XmlSchemaType(name = "string")
    protected ESortColumn sortColumn;
    @XmlElement(name = "SortOrder")
    @XmlSchemaType(name = "string")
    protected ESortOrder sortOrder;

    /**
     * Gets the value of the allItemsCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAllItemsCount() {
        return allItemsCount;
    }

    /**
     * Sets the value of the allItemsCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAllItemsCount(Integer value) {
        this.allItemsCount = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDcMessage }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDcMessage> getItems() {
        return items;
    }

    /**
     * Sets the value of the items property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDcMessage }{@code >}
     *     
     */
    public void setItems(JAXBElement<ArrayOfDcMessage> value) {
        this.items = value;
    }

    /**
     * Gets the value of the sortColumn property.
     * 
     * @return
     *     possible object is
     *     {@link ESortColumn }
     *     
     */
    public ESortColumn getSortColumn() {
        return sortColumn;
    }

    /**
     * Sets the value of the sortColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ESortColumn }
     *     
     */
    public void setSortColumn(ESortColumn value) {
        this.sortColumn = value;
    }

    /**
     * Gets the value of the sortOrder property.
     * 
     * @return
     *     possible object is
     *     {@link ESortOrder }
     *     
     */
    public ESortOrder getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the value of the sortOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link ESortOrder }
     *     
     */
    public void setSortOrder(ESortOrder value) {
        this.sortOrder = value;
    }

}
