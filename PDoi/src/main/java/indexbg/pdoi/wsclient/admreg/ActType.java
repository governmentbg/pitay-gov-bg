
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://iisda.government.bg/RAS/Common}ActBaseType"&gt;
 *       &lt;attribute name="ActID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="VersionID" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActType", namespace = "http://iisda.government.bg/RAS/Common")
public class ActType
    extends ActBaseType
{

    @XmlAttribute(name = "ActID", required = true)
    protected long actID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;

    /**
     * Gets the value of the actID property.
     * 
     */
    public long getActID() {
        return actID;
    }

    /**
     * Sets the value of the actID property.
     * 
     */
    public void setActID(long value) {
        this.actID = value;
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

}
