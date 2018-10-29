
package indexbg.pdoi.wsclient.admreg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpFunctionCategoryTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UpFunctionCategoryTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Office"/&gt;
 *     &lt;enumeration value="FinancialAndEconomicActivities"/&gt;
 *     &lt;enumeration value="LegalActivities"/&gt;
 *     &lt;enumeration value="PropertyManagement"/&gt;
 *     &lt;enumeration value="HumanResources"/&gt;
 *     &lt;enumeration value="ManagementDefenseMobilization"/&gt;
 *     &lt;enumeration value="InformationServicesAndTechnology"/&gt;
 *     &lt;enumeration value="AdministrativeServices"/&gt;
 *     &lt;enumeration value="ProtocolFunctionsAndPublicRelations"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "UpFunctionCategoryTypeEnum")
@XmlEnum
public enum UpFunctionCategoryTypeEnum {

    @XmlEnumValue("Office")
    OFFICE("Office"),
    @XmlEnumValue("FinancialAndEconomicActivities")
    FINANCIAL_AND_ECONOMIC_ACTIVITIES("FinancialAndEconomicActivities"),
    @XmlEnumValue("LegalActivities")
    LEGAL_ACTIVITIES("LegalActivities"),
    @XmlEnumValue("PropertyManagement")
    PROPERTY_MANAGEMENT("PropertyManagement"),
    @XmlEnumValue("HumanResources")
    HUMAN_RESOURCES("HumanResources"),
    @XmlEnumValue("ManagementDefenseMobilization")
    MANAGEMENT_DEFENSE_MOBILIZATION("ManagementDefenseMobilization"),
    @XmlEnumValue("InformationServicesAndTechnology")
    INFORMATION_SERVICES_AND_TECHNOLOGY("InformationServicesAndTechnology"),
    @XmlEnumValue("AdministrativeServices")
    ADMINISTRATIVE_SERVICES("AdministrativeServices"),
    @XmlEnumValue("ProtocolFunctionsAndPublicRelations")
    PROTOCOL_FUNCTIONS_AND_PUBLIC_RELATIONS("ProtocolFunctionsAndPublicRelations");
    private final String value;

    UpFunctionCategoryTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UpFunctionCategoryTypeEnum fromValue(String v) {
        for (UpFunctionCategoryTypeEnum c: UpFunctionCategoryTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
