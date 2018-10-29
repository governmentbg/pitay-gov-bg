
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eRevokationResult.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eRevokationResult"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="Revoked"/&gt;
 *     &lt;enumeration value="CanNotDetermine"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "eRevokationResult", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums")
@XmlEnum
public enum ERevokationResult {

    OK("OK"),
    @XmlEnumValue("Revoked")
    REVOKED("Revoked"),
    @XmlEnumValue("CanNotDetermine")
    CAN_NOT_DETERMINE("CanNotDetermine");
    private final String value;

    ERevokationResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ERevokationResult fromValue(String v) {
        for (ERevokationResult c: ERevokationResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
