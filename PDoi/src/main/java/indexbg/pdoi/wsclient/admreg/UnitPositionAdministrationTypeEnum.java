
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnitPositionAdministrationTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnitPositionAdministrationTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CommonAdministration"/&gt;
 *     &lt;enumeration value="SpecializedAdministration"/&gt;
 *     &lt;enumeration value="OtherAdministration"/&gt;
 *     &lt;enumeration value="TerritorialUnit"/&gt;
 *     &lt;enumeration value="DirectionAdministration"/&gt;
 *     &lt;enumeration value="TerritorialCommonAdministration"/&gt;
 *     &lt;enumeration value="TerritorialSpecializedAdministration"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "UnitPositionAdministrationTypeEnum")
@XmlEnum
public enum UnitPositionAdministrationTypeEnum {

    @XmlEnumValue("CommonAdministration")
    COMMON_ADMINISTRATION("CommonAdministration"),
    @XmlEnumValue("SpecializedAdministration")
    SPECIALIZED_ADMINISTRATION("SpecializedAdministration"),
    @XmlEnumValue("OtherAdministration")
    OTHER_ADMINISTRATION("OtherAdministration"),
    @XmlEnumValue("TerritorialUnit")
    TERRITORIAL_UNIT("TerritorialUnit"),
    @XmlEnumValue("DirectionAdministration")
    DIRECTION_ADMINISTRATION("DirectionAdministration"),
    @XmlEnumValue("TerritorialCommonAdministration")
    TERRITORIAL_COMMON_ADMINISTRATION("TerritorialCommonAdministration"),
    @XmlEnumValue("TerritorialSpecializedAdministration")
    TERRITORIAL_SPECIALIZED_ADMINISTRATION("TerritorialSpecializedAdministration");
    private final String value;

    UnitPositionAdministrationTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnitPositionAdministrationTypeEnum fromValue(String v) {
        for (UnitPositionAdministrationTypeEnum c: UnitPositionAdministrationTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
