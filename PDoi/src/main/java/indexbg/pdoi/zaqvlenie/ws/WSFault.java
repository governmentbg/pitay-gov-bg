package indexbg.pdoi.zaqvlenie.ws;

import javax.xml.ws.WebFault;

@WebFault
public class WSFault extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8768894805673794293L;
	String info;

	public WSFault(String message, Exception ex) {
		super(message, ex);

	}

	public WSFault(String message) {
		super(message);

	}

	public WSFault(String message, String detail) {
		super(message);
		this.info = detail;
	}

	public String getFaultInfo() {
		return info;
	}

	public String getMessage() {

		return super.getMessage();
	}
}
