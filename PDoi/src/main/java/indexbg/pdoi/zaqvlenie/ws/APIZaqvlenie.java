package indexbg.pdoi.zaqvlenie.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;

import indexbg.pdoi.db.Application;
import indexbg.pdoi.db.dao.ApplicationDAO;

/**
 * 
 * @author s.marinov
 *
 */
@WebService()
public class APIZaqvlenie {
	
	static final Logger LOGGER = LoggerFactory.getLogger(APIZaqvlenie.class);
	
	/**
	 * Version of the WebService.
	 * @return
	 */
	@WebMethod()
	public String version() {
	    return "1.0";
	}
	
	/**
	 * Method searches for Application data by given "uri".
	 * @param uri
	 * @return
	 * @throws WSFault
	 */
	@WebMethod(operationName="findApplication",action="findApplication")
	public Application findApplication(String uri) throws WSFault {
		Application ap=null;
		try {
			
			ap=new ApplicationDAO(-1L, null).findByURI(uri);

		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			throw new WSFault(e.getMessage(), e);
		}finally {
			JPA.getUtil().closeConnection();
		}
		return ap;
	}
}
