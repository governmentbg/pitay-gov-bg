package indexbg.pdoi.bean;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

/**
 * 
 * @author s.marinov
 *
 */

@ManagedBean(name = "apiZaqvlenieBean")
@ViewScoped
public class ApiZaqvlenieBean extends PDoiBean {	
	
	 
	private static final long serialVersionUID = 8660833897121200803L;

	private String uri="";
	private String result="";
	
	/**
	 * Constructor
	 */
	public ApiZaqvlenieBean() {
		
	}
	
	/**
	 * Calling webService to search for data about given "uri"
	 */
	public void actionSerch(){
		
		if (this.uri==null || this.uri.trim().equals("")) {
			JSFUtils.addMessage("uri",FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "apiZaqvlenie.uri"));
		}else{
			String soapEndpointUrl = "http://10.29.1.165:8080/PDoi/APIZaqvlenie";
	        String soapAction = "findApplication";
	
	        callSoapWebService(soapEndpointUrl, soapAction);
		}
        
	}


	/**
	 * Fills the soap message.
	 * @param soapMessage
	 * @throws SOAPException
	 */
    private void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "q0";
        String myNamespaceURI = "http://ws.zaqvlenie.pdoi.indexbg/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="http://www.webserviceX.NET">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:GetInfoByCity>
                        <myNamespace:USCity>New York</myNamespace:USCity>
                    </myNamespace:GetInfoByCity>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("findApplication", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("arg0");
        soapBodyElem1.addTextNode(this.uri);
    }

    /**
     * Method creates and calls the Web Service 
     * @param soapEndpointUrl
     * @param soapAction
     */
    private void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
        	
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            
            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
//            System.out.println("Response SOAP Message:");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            soapResponse.writeTo(baos);
            this.result=baos.toString();
            		
            
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    /**
     * Creating message and forming request.
     * @param soapAction
     * @return
     * @throws Exception
     */
    private SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
//        System.out.println("Request SOAP Message:");
//        soapMessage.writeTo(System.out);
//        System.out.println("\n");

        return soapMessage;
    }
	
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	 
	

}
