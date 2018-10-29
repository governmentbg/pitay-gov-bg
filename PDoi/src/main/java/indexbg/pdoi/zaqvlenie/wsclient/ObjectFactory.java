
package indexbg.pdoi.zaqvlenie.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the indexbg.pdoi.zaqvlenie.wsclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindApplication_QNAME = new QName("http://ws.zaqvlenie.pdoi.indexbg/", "findApplication");
    private final static QName _FindApplicationResponse_QNAME = new QName("http://ws.zaqvlenie.pdoi.indexbg/", "findApplicationResponse");
    private final static QName _Version_QNAME = new QName("http://ws.zaqvlenie.pdoi.indexbg/", "version");
    private final static QName _VersionResponse_QNAME = new QName("http://ws.zaqvlenie.pdoi.indexbg/", "versionResponse");
    private final static QName _WSFault_QNAME = new QName("http://ws.zaqvlenie.pdoi.indexbg/", "WSFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: indexbg.pdoi.zaqvlenie.wsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindApplication }
     * 
     */
    public FindApplication createFindApplication() {
        return new FindApplication();
    }

    /**
     * Create an instance of {@link FindApplicationResponse }
     * 
     */
    public FindApplicationResponse createFindApplicationResponse() {
        return new FindApplicationResponse();
    }

    /**
     * Create an instance of {@link Version }
     * 
     */
    public Version createVersion() {
        return new Version();
    }

    /**
     * Create an instance of {@link VersionResponse }
     * 
     */
    public VersionResponse createVersionResponse() {
        return new VersionResponse();
    }

    /**
     * Create an instance of {@link Application }
     * 
     */
    public Application createApplication() {
        return new Application();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.zaqvlenie.pdoi.indexbg/", name = "findApplication")
    public JAXBElement<FindApplication> createFindApplication(FindApplication value) {
        return new JAXBElement<FindApplication>(_FindApplication_QNAME, FindApplication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.zaqvlenie.pdoi.indexbg/", name = "findApplicationResponse")
    public JAXBElement<FindApplicationResponse> createFindApplicationResponse(FindApplicationResponse value) {
        return new JAXBElement<FindApplicationResponse>(_FindApplicationResponse_QNAME, FindApplicationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Version }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.zaqvlenie.pdoi.indexbg/", name = "version")
    public JAXBElement<Version> createVersion(Version value) {
        return new JAXBElement<Version>(_Version_QNAME, Version.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VersionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.zaqvlenie.pdoi.indexbg/", name = "versionResponse")
    public JAXBElement<VersionResponse> createVersionResponse(VersionResponse value) {
        return new JAXBElement<VersionResponse>(_VersionResponse_QNAME, VersionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.zaqvlenie.pdoi.indexbg/", name = "WSFault")
    public JAXBElement<String> createWSFault(String value) {
        return new JAXBElement<String>(_WSFault_QNAME, String.class, null, value);
    }

}
