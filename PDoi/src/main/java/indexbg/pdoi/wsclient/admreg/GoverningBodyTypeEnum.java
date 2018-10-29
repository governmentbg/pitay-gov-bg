
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GoverningBodyTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GoverningBodyTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SingleAdvBoard"/&gt;
 *     &lt;enumeration value="CollectiveAdvBoard"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "GoverningBodyTypeEnum")
@XmlEnum
public enum GoverningBodyTypeEnum {

    @XmlEnumValue("SingleAdvBoard")
    SINGLE_ADV_BOARD("SingleAdvBoard"),
    @XmlEnumValue("CollectiveAdvBoard")
    COLLECTIVE_ADV_BOARD("CollectiveAdvBoard");
    private final String value;

    GoverningBodyTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GoverningBodyTypeEnum fromValue(String v) {
        for (GoverningBodyTypeEnum c: GoverningBodyTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
