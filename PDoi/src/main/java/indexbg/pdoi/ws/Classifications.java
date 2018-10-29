package indexbg.pdoi.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;

/**
 * Уеб услуга за достъп до номенклатурите на ЕИСПП
 *
 * @author belev
 */
@WebService(name = "ClassificationsPortType", targetNamespace = "http://meta.data.bg/Classifications")
public interface Classifications {

	/**
	 * За тест дали имаме достъп до услугата
	 *
	 * @return
	 */
	@WebMethod(operationName = "version")
	@Action(input = "http://meta.data.bg/version", output = "http://meta.data.bg/versionResponse")
	public String version();

	/**
	 * Услуга за предоставяне на номенклатура от ОСИ
	 *
	 * @param request
	 * @return response
	 * @throws ClassificationsFault
	 */
	@WebMethod(operationName = "GetNomenclature")
	@Action(input = "http://meta.data.bg/GetNomenclature", output = "http://meta.data.bg/GetNomenclatureResponse", fault = {
			@FaultAction(className = ClassificationsFault.class, value = "http://meta.data.bg/ClassificationsFault") })
	@WebResult(name = "ResultData")
	GetNomenclatureResult getNomeclature(@WebParam(name = "GetNomenclatureRequest") GetNomenclatureRequest request) throws ClassificationsFault;
}