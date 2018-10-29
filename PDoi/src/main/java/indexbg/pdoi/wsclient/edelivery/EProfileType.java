
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eProfileType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eProfileType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Person"/&gt;
 *     &lt;enumeration value="LegalPerson"/&gt;
 *     &lt;enumeration value="Institution"/&gt;
 *     &lt;enumeration value="Administrator"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "eProfileType", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums")
@XmlEnum
public enum EProfileType {

    @XmlEnumValue("Person")
    PERSON("Person"),
    @XmlEnumValue("LegalPerson")
    LEGAL_PERSON("LegalPerson"),
    @XmlEnumValue("Institution")
    INSTITUTION("Institution"),
    @XmlEnumValue("Administrator")
    ADMINISTRATOR("Administrator");
    private final String value;

    EProfileType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EProfileType fromValue(String v) {
        for (EProfileType c: EProfileType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
