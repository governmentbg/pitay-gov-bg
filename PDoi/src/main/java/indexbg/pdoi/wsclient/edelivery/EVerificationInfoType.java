
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eVerificationInfoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eVerificationInfoType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Certificate"/&gt;
 *     &lt;enumeration value="EID"/&gt;
 *     &lt;enumeration value="AdministrativeAct"/&gt;
 *     &lt;enumeration value="NOIToken"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "eVerificationInfoType", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums")
@XmlEnum
public enum EVerificationInfoType {

    @XmlEnumValue("Certificate")
    CERTIFICATE("Certificate"),
    EID("EID"),
    @XmlEnumValue("AdministrativeAct")
    ADMINISTRATIVE_ACT("AdministrativeAct"),
    @XmlEnumValue("NOIToken")
    NOI_TOKEN("NOIToken");
    private final String value;

    EVerificationInfoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EVerificationInfoType fromValue(String v) {
        for (EVerificationInfoType c: EVerificationInfoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
