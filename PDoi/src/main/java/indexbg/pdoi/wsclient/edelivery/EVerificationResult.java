
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eVerificationResult.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eVerificationResult"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Success"/&gt;
 *     &lt;enumeration value="InvalidFile"/&gt;
 *     &lt;enumeration value="NoSignatureFound"/&gt;
 *     &lt;enumeration value="DetachedSignature"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "eVerificationResult", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums")
@XmlEnum
public enum EVerificationResult {

    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("InvalidFile")
    INVALID_FILE("InvalidFile"),
    @XmlEnumValue("NoSignatureFound")
    NO_SIGNATURE_FOUND("NoSignatureFound"),
    @XmlEnumValue("DetachedSignature")
    DETACHED_SIGNATURE("DetachedSignature");
    private final String value;

    EVerificationResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EVerificationResult fromValue(String v) {
        for (EVerificationResult c: EVerificationResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
