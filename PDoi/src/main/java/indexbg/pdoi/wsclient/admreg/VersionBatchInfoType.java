
package indexbg.pdoi.wsclient.admreg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VersionBatchInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VersionBatchInfoType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://iisda.government.bg/RAS/Versioning}VersionInfo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ChangedBatch" type="{http://iisda.government.bg/RAS/}BatchIdentificationInfoType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VersionBatchInfoType", propOrder = {
    "changedBatch"
})
public class VersionBatchInfoType
    extends VersionInfo
{

    @XmlElement(name = "ChangedBatch")
    protected List<BatchIdentificationInfoType> changedBatch;

    /**
     * Gets the value of the changedBatch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the changedBatch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChangedBatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchIdentificationInfoType }
     * 
     * 
     */
    public List<BatchIdentificationInfoType> getChangedBatch() {
        if (changedBatch == null) {
            changedBatch = new ArrayList<BatchIdentificationInfoType>();
        }
        return this.changedBatch;
    }

}
