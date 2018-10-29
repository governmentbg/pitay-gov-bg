
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WorkingTimeTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WorkingTimeTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Regular"/&gt;
 *     &lt;enumeration value="Flexible"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "WorkingTimeTypeEnum", namespace = "http://iisda.government.bg/RAS/Common")
@XmlEnum
public enum WorkingTimeTypeEnum {

    @XmlEnumValue("Regular")
    REGULAR("Regular"),
    @XmlEnumValue("Flexible")
    FLEXIBLE("Flexible");
    private final String value;

    WorkingTimeTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WorkingTimeTypeEnum fromValue(String v) {
        for (WorkingTimeTypeEnum c: WorkingTimeTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
