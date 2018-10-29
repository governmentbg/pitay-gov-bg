
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBatchIdentificationInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBatchIdentificationInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BatchIdentificationInfoType" type="{http://iisda.government.bg/RAS/}BatchIdentificationInfoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBatchIdentificationInfoType", namespace = "http://iisda.government.bg/RAS/IntegrationServices", propOrder = {
    "batchIdentificationInfoType"
})
public class ArrayOfBatchIdentificationInfoType {

    @XmlElement(name = "BatchIdentificationInfoType", nillable = true)
    protected List<BatchIdentificationInfoType> batchIdentificationInfoType;

    /**
     * Gets the value of the batchIdentificationInfoType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchIdentificationInfoType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchIdentificationInfoType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchIdentificationInfoType }
     * 
     * 
     */
    public List<BatchIdentificationInfoType> getBatchIdentificationInfoType() {
        if (batchIdentificationInfoType == null) {
            batchIdentificationInfoType = new ArrayList<BatchIdentificationInfoType>();
        }
        return this.batchIdentificationInfoType;
    }

}
