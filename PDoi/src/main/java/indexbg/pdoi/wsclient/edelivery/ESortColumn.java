
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eSortColumn.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="eSortColumn"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="None"/&gt;
 *     &lt;enumeration value="Status"/&gt;
 *     &lt;enumeration value="ReceiverName"/&gt;
 *     &lt;enumeration value="Title"/&gt;
 *     &lt;enumeration value="SenderName"/&gt;
 *     &lt;enumeration value="DateSent"/&gt;
 *     &lt;enumeration value="DateReceived"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "eSortColumn", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums")
@XmlEnum
public enum ESortColumn {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Status")
    STATUS("Status"),
    @XmlEnumValue("ReceiverName")
    RECEIVER_NAME("ReceiverName"),
    @XmlEnumValue("Title")
    TITLE("Title"),
    @XmlEnumValue("SenderName")
    SENDER_NAME("SenderName"),
    @XmlEnumValue("DateSent")
    DATE_SENT("DateSent"),
    @XmlEnumValue("DateReceived")
    DATE_RECEIVED("DateReceived");
    private final String value;

    ESortColumn(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ESortColumn fromValue(String v) {
        for (ESortColumn c: ESortColumn.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
