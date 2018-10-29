
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdmStructureKindsEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AdmStructureKindsEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="RegionalAdminisration"/&gt;
 *     &lt;enumeration value="MunicipalAdministration"/&gt;
 *     &lt;enumeration value="AreaMunicipalAdministration"/&gt;
 *     &lt;enumeration value="SpecializedLocalAdministration"/&gt;
 *     &lt;enumeration value="CouncilOfMinistersAdministration"/&gt;
 *     &lt;enumeration value="Ministry"/&gt;
 *     &lt;enumeration value="StateAgency"/&gt;
 *     &lt;enumeration value="StateCommisionAdministrion"/&gt;
 *     &lt;enumeration value="ExecutiveAgency"/&gt;
 *     &lt;enumeration value="ExecutivePowerAdministrativeStructure"/&gt;
 *     &lt;enumeration value="Act60AdiministrativeStructure"/&gt;
 *     &lt;enumeration value="Council"/&gt;
 *     &lt;enumeration value="StatePublicConsultativeCommission"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AdmStructureKindsEnum")
@XmlEnum
public enum AdmStructureKindsEnum {

    @XmlEnumValue("RegionalAdminisration")
    REGIONAL_ADMINISRATION("RegionalAdminisration"),
    @XmlEnumValue("MunicipalAdministration")
    MUNICIPAL_ADMINISTRATION("MunicipalAdministration"),
    @XmlEnumValue("AreaMunicipalAdministration")
    AREA_MUNICIPAL_ADMINISTRATION("AreaMunicipalAdministration"),
    @XmlEnumValue("SpecializedLocalAdministration")
    SPECIALIZED_LOCAL_ADMINISTRATION("SpecializedLocalAdministration"),
    @XmlEnumValue("CouncilOfMinistersAdministration")
    COUNCIL_OF_MINISTERS_ADMINISTRATION("CouncilOfMinistersAdministration"),
    @XmlEnumValue("Ministry")
    MINISTRY("Ministry"),
    @XmlEnumValue("StateAgency")
    STATE_AGENCY("StateAgency"),
    @XmlEnumValue("StateCommisionAdministrion")
    STATE_COMMISION_ADMINISTRION("StateCommisionAdministrion"),
    @XmlEnumValue("ExecutiveAgency")
    EXECUTIVE_AGENCY("ExecutiveAgency"),
    @XmlEnumValue("ExecutivePowerAdministrativeStructure")
    EXECUTIVE_POWER_ADMINISTRATIVE_STRUCTURE("ExecutivePowerAdministrativeStructure"),
    @XmlEnumValue("Act60AdiministrativeStructure")
    ACT_60_ADIMINISTRATIVE_STRUCTURE("Act60AdiministrativeStructure"),
    @XmlEnumValue("Council")
    COUNCIL("Council"),
    @XmlEnumValue("StatePublicConsultativeCommission")
    STATE_PUBLIC_CONSULTATIVE_COMMISSION("StatePublicConsultativeCommission");
    private final String value;

    AdmStructureKindsEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AdmStructureKindsEnum fromValue(String v) {
        for (AdmStructureKindsEnum c: AdmStructureKindsEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
