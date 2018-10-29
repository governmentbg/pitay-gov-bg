
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpFunctionCategoryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpFunctionCategoryType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="UpFunctionCategoryID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="FunctionCategory" use="required" type="{http://iisda.government.bg/RAS/}UpFunctionCategoryTypeEnum" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpFunctionCategoryType")
public class UpFunctionCategoryType {

    @XmlAttribute(name = "UpFunctionCategoryID", required = true)
    protected long upFunctionCategoryID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "FunctionCategory", required = true)
    protected UpFunctionCategoryTypeEnum functionCategory;

    /**
     * Gets the value of the upFunctionCategoryID property.
     * 
     */
    public long getUpFunctionCategoryID() {
        return upFunctionCategoryID;
    }

    /**
     * Sets the value of the upFunctionCategoryID property.
     * 
     */
    public void setUpFunctionCategoryID(long value) {
        this.upFunctionCategoryID = value;
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
     * Gets the value of the functionCategory property.
     * 
     * @return
     *     possible object is
     *     {@link UpFunctionCategoryTypeEnum }
     *     
     */
    public UpFunctionCategoryTypeEnum getFunctionCategory() {
        return functionCategory;
    }

    /**
     * Sets the value of the functionCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpFunctionCategoryTypeEnum }
     *     
     */
    public void setFunctionCategory(UpFunctionCategoryTypeEnum value) {
        this.functionCategory = value;
    }

}
