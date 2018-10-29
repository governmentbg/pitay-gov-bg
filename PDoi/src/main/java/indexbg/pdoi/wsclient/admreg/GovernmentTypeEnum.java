
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GovernmentTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="GovernmentTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Regular"/&gt;
 *     &lt;enumeration value="\u041efficial"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "GovernmentTypeEnum")
@XmlEnum
public enum GovernmentTypeEnum {

    @XmlEnumValue("Regular")
    REGULAR("Regular"),
    @XmlEnumValue("\u041efficial")
    \u041eFFICIAL("\u041efficial");
    private final String value;

    GovernmentTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GovernmentTypeEnum fromValue(String v) {
        for (GovernmentTypeEnum c: GovernmentTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
