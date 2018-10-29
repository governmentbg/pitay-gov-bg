
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BatchTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="COM"/&gt;
 *     &lt;enumeration value="AdmStructure"/&gt;
 *     &lt;enumeration value="AdvisoryBoard"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "BatchTypeEnum")
@XmlEnum
public enum BatchTypeEnum {

    COM("COM"),
    @XmlEnumValue("AdmStructure")
    ADM_STRUCTURE("AdmStructure"),
    @XmlEnumValue("AdvisoryBoard")
    ADVISORY_BOARD("AdvisoryBoard");
    private final String value;

    BatchTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BatchTypeEnum fromValue(String v) {
        for (BatchTypeEnum c: BatchTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
