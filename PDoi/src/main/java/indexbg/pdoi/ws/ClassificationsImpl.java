package indexbg.pdoi.ws;



import static indexbg.pdoi.ws.GetNomenclatureResult.ResultStatusEnum.INVALID_INPUT;
import static indexbg.pdoi.ws.GetNomenclatureResult.ResultStatusEnum.OK;
import static indexbg.pdoi.ws.GetNomenclatureResult.ResultStatusEnum.ERROR;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.dto.SystemClassif;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.StringUtils;

import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiClassifAdapter;
import indexbg.pdoi.system.SystemData;

/**
 * Имплементация на {@link Classifications}
 *
 * @author belev
 */
@WebService(serviceName = "Classifications", portName = "ClassificationsPort", endpointInterface = "indexbg.pdoi.ws.Classifications", targetNamespace = "http://meta.data.bg/Classifications")
public class ClassificationsImpl implements Classifications {

	private static Logger LOGGER = LoggerFactory.getLogger(ClassificationsImpl.class);

	/** @see Classifications#getNomeclature(GetNomenclatureRequest) */
	@Override
	public GetNomenclatureResult getNomeclature(GetNomenclatureRequest request) throws ClassificationsFault {
		if (request == null || isEmpty(request.getRequestArgument())) {
			String error = "Некоректни входни данни! Необходима информация за изпълнението на услугата е системно име на номенклатура.";
			LOGGER.error(error);

			return new GetNomenclatureResult(error, INVALID_INPUT);
		}

		String errorMessage = null; // и се пише текста на грешката
		Nomenclature nomen = new Nomenclature();
		
		try {
			
			//request.getRequestArgument()
			
			
			Long lang = null;
			if (request.getLanguage() != null) {
				lang = Long.parseLong(request.getLanguage());
			}else {
				lang = 	Constants.CODE_DEFAULT_LANG;
			}
			
			List<SystemClassif> classif = null;
			if (request.getRequestArgument().equals("10025")) {
				classif = new PDoiClassifAdapter(new SystemData()).createAdmRegisterNivaPDoi(Constants.CODE_SYSCLASS_ADM_REGISTRY, lang, -1L);				
				nomen.setCode(10025);
				nomen.setName("Административен регистър");
				nomen.setSysname("PDoi");
			}
				
						
			if (request.getRequestArgument().equals("10026")) {
				classif = new PDoiClassifAdapter(new SystemData()).createEKATTE(Constants.CODE_SYSCLASS_EKATTE, lang, -1L);				
				nomen.setCode(10025);
				nomen.setName("EKATTE - области, общини, населени места");
				nomen.setSysname("PDoi");
				
			}
				
			if (classif != null) {	
				nomen.setElements(new ArrayList<NomenclatureElement>());
				for (SystemClassif item : classif) {
					NomenclatureElement e = new NomenclatureElement();
					e.setCode(new Integer((int)item.getCode()));
					e.setCodeExt(item.getCodeExt());
					e.setCodeParent(new Integer((int)item.getCodeParent()));
					e.setCodePrev(new Integer((int)item.getCodePrev()));
					e.setDateFrom(DateUtils.toXMLGregorianCalendar(item.getDateOt()));				
					e.setDateTo(DateUtils.toXMLGregorianCalendar(item.getDateDo()));
					e.setLevel(item.getLevelNumber());
					e.setName(item.getTekst());					
					nomen.elements.add(e);
				}
			}
				
				
			
			
			
		} catch (Exception e) {
			errorMessage = "Грешка при градене на системна класификация ! \r\n" + StringUtils.stack2string(e); 
			GetNomenclatureResult response = new GetNomenclatureResult();
			response.setMessage(errorMessage);
			response.setStatus(ERROR);
			return response;
		}

		
		
		
			

		String message = "Успешна идентификация и зарeждане на съдържание на номенклатура със име : " + nomen.getName();

		LOGGER.info(message);

		GetNomenclatureResult response = new GetNomenclatureResult(nomen);

		response.setMessage(message);
		response.setStatus(OK);

		return response;
	}

	/** @see Classifications#version() */
	@Override
	public String version() {
		return "1.00b";
	}

	
	
	
	/**
	 * Проверява дали String е празен
	 *
	 * @param s
	 * @return <code>true</code> ако е празен
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0 || s.trim().length() == 0;
	}
	
	
}
