
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DcSignatureValidationResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DcSignatureValidationResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="_x003C_CertificateAlgorithm_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="_x003C_ChainCertificates_x003E_k__BackingField" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts}ArrayOfDcChainCertificate"/&gt;
 *         &lt;element name="_x003C_ChainErrors_x003E_k__BackingField" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring"/&gt;
 *         &lt;element name="_x003C_ContainsTimeStamp_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="_x003C_Format_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="_x003C_IsExpired_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="_x003C_IsIntegrityValid_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="_x003C_IsSignatureValid_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="_x003C_IsTrustedSigner_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="_x003C_Issuer_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="_x003C_RevocationStatus_x003E_k__BackingField" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums}eRevokationResult"/&gt;
 *         &lt;element name="_x003C_Status_x003E_k__BackingField" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.Enums}eVerificationResult"/&gt;
 *         &lt;element name="_x003C_SubjectEGN_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="_x003C_Subject_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="_x003C_TimeStampAuthority_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="_x003C_TimeStampDate_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="_x003C_ValidFrom_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="_x003C_ValidTo_x003E_k__BackingField" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DcSignatureValidationResult", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts", propOrder = {
    "x003CCertificateAlgorithmX003EKBackingField",
    "x003CChainCertificatesX003EKBackingField",
    "x003CChainErrorsX003EKBackingField",
    "x003CContainsTimeStampX003EKBackingField",
    "x003CFormatX003EKBackingField",
    "x003CIsExpiredX003EKBackingField",
    "x003CIsIntegrityValidX003EKBackingField",
    "x003CIsSignatureValidX003EKBackingField",
    "x003CIsTrustedSignerX003EKBackingField",
    "x003CIssuerX003EKBackingField",
    "x003CRevocationStatusX003EKBackingField",
    "x003CStatusX003EKBackingField",
    "x003CSubjectEGNX003EKBackingField",
    "x003CSubjectX003EKBackingField",
    "x003CTimeStampAuthorityX003EKBackingField",
    "x003CTimeStampDateX003EKBackingField",
    "x003CValidFromX003EKBackingField",
    "x003CValidToX003EKBackingField"
})
public class DcSignatureValidationResult {

    @XmlElement(name = "_x003C_CertificateAlgorithm_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CCertificateAlgorithmX003EKBackingField;
    @XmlElement(name = "_x003C_ChainCertificates_x003E_k__BackingField", required = true, nillable = true)
    protected ArrayOfDcChainCertificate x003CChainCertificatesX003EKBackingField;
    @XmlElement(name = "_x003C_ChainErrors_x003E_k__BackingField", required = true, nillable = true)
    protected ArrayOfstring x003CChainErrorsX003EKBackingField;
    @XmlElement(name = "_x003C_ContainsTimeStamp_x003E_k__BackingField")
    protected boolean x003CContainsTimeStampX003EKBackingField;
    @XmlElement(name = "_x003C_Format_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CFormatX003EKBackingField;
    @XmlElement(name = "_x003C_IsExpired_x003E_k__BackingField")
    protected boolean x003CIsExpiredX003EKBackingField;
    @XmlElement(name = "_x003C_IsIntegrityValid_x003E_k__BackingField")
    protected boolean x003CIsIntegrityValidX003EKBackingField;
    @XmlElement(name = "_x003C_IsSignatureValid_x003E_k__BackingField")
    protected boolean x003CIsSignatureValidX003EKBackingField;
    @XmlElement(name = "_x003C_IsTrustedSigner_x003E_k__BackingField")
    protected boolean x003CIsTrustedSignerX003EKBackingField;
    @XmlElement(name = "_x003C_Issuer_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CIssuerX003EKBackingField;
    @XmlElement(name = "_x003C_RevocationStatus_x003E_k__BackingField", required = true)
    @XmlSchemaType(name = "string")
    protected ERevokationResult x003CRevocationStatusX003EKBackingField;
    @XmlElement(name = "_x003C_Status_x003E_k__BackingField", required = true)
    @XmlSchemaType(name = "string")
    protected EVerificationResult x003CStatusX003EKBackingField;
    @XmlElement(name = "_x003C_SubjectEGN_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CSubjectEGNX003EKBackingField;
    @XmlElement(name = "_x003C_Subject_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CSubjectX003EKBackingField;
    @XmlElement(name = "_x003C_TimeStampAuthority_x003E_k__BackingField", required = true, nillable = true)
    protected String x003CTimeStampAuthorityX003EKBackingField;
    @XmlElement(name = "_x003C_TimeStampDate_x003E_k__BackingField", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar x003CTimeStampDateX003EKBackingField;
    @XmlElement(name = "_x003C_ValidFrom_x003E_k__BackingField", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar x003CValidFromX003EKBackingField;
    @XmlElement(name = "_x003C_ValidTo_x003E_k__BackingField", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar x003CValidToX003EKBackingField;

    /**
     * Gets the value of the x003CCertificateAlgorithmX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CCertificateAlgorithmX003EKBackingField() {
        return x003CCertificateAlgorithmX003EKBackingField;
    }

    /**
     * Sets the value of the x003CCertificateAlgorithmX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CCertificateAlgorithmX003EKBackingField(String value) {
        this.x003CCertificateAlgorithmX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CChainCertificatesX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDcChainCertificate }
     *     
     */
    public ArrayOfDcChainCertificate getX003CChainCertificatesX003EKBackingField() {
        return x003CChainCertificatesX003EKBackingField;
    }

    /**
     * Sets the value of the x003CChainCertificatesX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDcChainCertificate }
     *     
     */
    public void setX003CChainCertificatesX003EKBackingField(ArrayOfDcChainCertificate value) {
        this.x003CChainCertificatesX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CChainErrorsX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfstring }
     *     
     */
    public ArrayOfstring getX003CChainErrorsX003EKBackingField() {
        return x003CChainErrorsX003EKBackingField;
    }

    /**
     * Sets the value of the x003CChainErrorsX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfstring }
     *     
     */
    public void setX003CChainErrorsX003EKBackingField(ArrayOfstring value) {
        this.x003CChainErrorsX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CContainsTimeStampX003EKBackingField property.
     * 
     */
    public boolean isX003CContainsTimeStampX003EKBackingField() {
        return x003CContainsTimeStampX003EKBackingField;
    }

    /**
     * Sets the value of the x003CContainsTimeStampX003EKBackingField property.
     * 
     */
    public void setX003CContainsTimeStampX003EKBackingField(boolean value) {
        this.x003CContainsTimeStampX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CFormatX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CFormatX003EKBackingField() {
        return x003CFormatX003EKBackingField;
    }

    /**
     * Sets the value of the x003CFormatX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CFormatX003EKBackingField(String value) {
        this.x003CFormatX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CIsExpiredX003EKBackingField property.
     * 
     */
    public boolean isX003CIsExpiredX003EKBackingField() {
        return x003CIsExpiredX003EKBackingField;
    }

    /**
     * Sets the value of the x003CIsExpiredX003EKBackingField property.
     * 
     */
    public void setX003CIsExpiredX003EKBackingField(boolean value) {
        this.x003CIsExpiredX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CIsIntegrityValidX003EKBackingField property.
     * 
     */
    public boolean isX003CIsIntegrityValidX003EKBackingField() {
        return x003CIsIntegrityValidX003EKBackingField;
    }

    /**
     * Sets the value of the x003CIsIntegrityValidX003EKBackingField property.
     * 
     */
    public void setX003CIsIntegrityValidX003EKBackingField(boolean value) {
        this.x003CIsIntegrityValidX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CIsSignatureValidX003EKBackingField property.
     * 
     */
    public boolean isX003CIsSignatureValidX003EKBackingField() {
        return x003CIsSignatureValidX003EKBackingField;
    }

    /**
     * Sets the value of the x003CIsSignatureValidX003EKBackingField property.
     * 
     */
    public void setX003CIsSignatureValidX003EKBackingField(boolean value) {
        this.x003CIsSignatureValidX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CIsTrustedSignerX003EKBackingField property.
     * 
     */
    public boolean isX003CIsTrustedSignerX003EKBackingField() {
        return x003CIsTrustedSignerX003EKBackingField;
    }

    /**
     * Sets the value of the x003CIsTrustedSignerX003EKBackingField property.
     * 
     */
    public void setX003CIsTrustedSignerX003EKBackingField(boolean value) {
        this.x003CIsTrustedSignerX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CIssuerX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CIssuerX003EKBackingField() {
        return x003CIssuerX003EKBackingField;
    }

    /**
     * Sets the value of the x003CIssuerX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CIssuerX003EKBackingField(String value) {
        this.x003CIssuerX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CRevocationStatusX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link ERevokationResult }
     *     
     */
    public ERevokationResult getX003CRevocationStatusX003EKBackingField() {
        return x003CRevocationStatusX003EKBackingField;
    }

    /**
     * Sets the value of the x003CRevocationStatusX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link ERevokationResult }
     *     
     */
    public void setX003CRevocationStatusX003EKBackingField(ERevokationResult value) {
        this.x003CRevocationStatusX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CStatusX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link EVerificationResult }
     *     
     */
    public EVerificationResult getX003CStatusX003EKBackingField() {
        return x003CStatusX003EKBackingField;
    }

    /**
     * Sets the value of the x003CStatusX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link EVerificationResult }
     *     
     */
    public void setX003CStatusX003EKBackingField(EVerificationResult value) {
        this.x003CStatusX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CSubjectEGNX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CSubjectEGNX003EKBackingField() {
        return x003CSubjectEGNX003EKBackingField;
    }

    /**
     * Sets the value of the x003CSubjectEGNX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CSubjectEGNX003EKBackingField(String value) {
        this.x003CSubjectEGNX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CSubjectX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CSubjectX003EKBackingField() {
        return x003CSubjectX003EKBackingField;
    }

    /**
     * Sets the value of the x003CSubjectX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CSubjectX003EKBackingField(String value) {
        this.x003CSubjectX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CTimeStampAuthorityX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX003CTimeStampAuthorityX003EKBackingField() {
        return x003CTimeStampAuthorityX003EKBackingField;
    }

    /**
     * Sets the value of the x003CTimeStampAuthorityX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX003CTimeStampAuthorityX003EKBackingField(String value) {
        this.x003CTimeStampAuthorityX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CTimeStampDateX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getX003CTimeStampDateX003EKBackingField() {
        return x003CTimeStampDateX003EKBackingField;
    }

    /**
     * Sets the value of the x003CTimeStampDateX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setX003CTimeStampDateX003EKBackingField(XMLGregorianCalendar value) {
        this.x003CTimeStampDateX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CValidFromX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getX003CValidFromX003EKBackingField() {
        return x003CValidFromX003EKBackingField;
    }

    /**
     * Sets the value of the x003CValidFromX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setX003CValidFromX003EKBackingField(XMLGregorianCalendar value) {
        this.x003CValidFromX003EKBackingField = value;
    }

    /**
     * Gets the value of the x003CValidToX003EKBackingField property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getX003CValidToX003EKBackingField() {
        return x003CValidToX003EKBackingField;
    }

    /**
     * Sets the value of the x003CValidToX003EKBackingField property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setX003CValidToX003EKBackingField(XMLGregorianCalendar value) {
        this.x003CValidToX003EKBackingField = value;
    }

}
