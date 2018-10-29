
package indexbg.pdoi.wsclient.edelivery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DcSubjectInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DcSubjectInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject}DcSubjectPublicInfo"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Address" type="{http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject}DcAddress" minOccurs="0"/&gt;
 *         &lt;element name="UniqueSubjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VerificationInfo" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfanyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DcSubjectInfo", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject", propOrder = {
    "address",
    "uniqueSubjectIdentifier",
    "verificationInfo"
})
@XmlSeeAlso({
    DcInstitutionInfo.class,
    DcLegalPersonInfo.class,
    DcPersonInfo.class
})
public class DcSubjectInfo
    extends DcSubjectPublicInfo
{

    @XmlElementRef(name = "Address", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject", type = JAXBElement.class, required = false)
    protected JAXBElement<DcAddress> address;
    @XmlElementRef(name = "UniqueSubjectIdentifier", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject", type = JAXBElement.class, required = false)
    protected JAXBElement<String> uniqueSubjectIdentifier;
    @XmlElementRef(name = "VerificationInfo", namespace = "http://schemas.datacontract.org/2004/07/EDelivery.Common.DataContracts.ESubject", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfanyType> verificationInfo;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DcAddress }{@code >}
     *     
     */
    public JAXBElement<DcAddress> getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DcAddress }{@code >}
     *     
     */
    public void setAddress(JAXBElement<DcAddress> value) {
        this.address = value;
    }

    /**
     * Gets the value of the uniqueSubjectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUniqueSubjectIdentifier() {
        return uniqueSubjectIdentifier;
    }

    /**
     * Sets the value of the uniqueSubjectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUniqueSubjectIdentifier(JAXBElement<String> value) {
        this.uniqueSubjectIdentifier = value;
    }

    /**
     * Gets the value of the verificationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}
     *     
     */
    public JAXBElement<ArrayOfanyType> getVerificationInfo() {
        return verificationInfo;
    }

    /**
     * Sets the value of the verificationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfanyType }{@code >}
     *     
     */
    public void setVerificationInfo(JAXBElement<ArrayOfanyType> value) {
        this.verificationInfo = value;
    }

}
