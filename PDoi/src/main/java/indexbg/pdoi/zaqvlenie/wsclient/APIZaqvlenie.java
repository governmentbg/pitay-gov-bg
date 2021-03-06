package indexbg.pdoi.zaqvlenie.wsclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.4
 * 2018-08-29T15:56:49.093+03:00
 * Generated source version: 3.1.4
 * 
 */
@WebService(targetNamespace = "http://ws.zaqvlenie.pdoi.indexbg/", name = "APIZaqvlenie")
@XmlSeeAlso({ObjectFactory.class})
public interface APIZaqvlenie {

    @WebMethod
    @RequestWrapper(localName = "version", targetNamespace = "http://ws.zaqvlenie.pdoi.indexbg/", className = "indexbg.pdoi.zaqvlenie.wsclient.Version")
    @ResponseWrapper(localName = "versionResponse", targetNamespace = "http://ws.zaqvlenie.pdoi.indexbg/", className = "indexbg.pdoi.zaqvlenie.wsclient.VersionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String version();

    @WebMethod
    @RequestWrapper(localName = "findApplication", targetNamespace = "http://ws.zaqvlenie.pdoi.indexbg/", className = "indexbg.pdoi.zaqvlenie.wsclient.FindApplication")
    @ResponseWrapper(localName = "findApplicationResponse", targetNamespace = "http://ws.zaqvlenie.pdoi.indexbg/", className = "indexbg.pdoi.zaqvlenie.wsclient.FindApplicationResponse")
    @WebResult(name = "return", targetNamespace = "")
    public indexbg.pdoi.zaqvlenie.wsclient.Application findApplication(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws WSFault;
}
