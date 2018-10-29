package indexbg.pdoi.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.WebFault;

/**
 * Грешка при изпълнението на услугата
 *
 * @author belev
 */

@WebFault
@XmlType(name = "ClassificationsFault", propOrder = { "message", "detail" })
public class ClassificationsFault extends Exception {

	/**  */
	private static final long serialVersionUID = -1063562318737772190L;

	@XmlElement(name = "detail")
	protected String detail;

	@XmlElement(name = "message")
	protected String message;

	/**
	 * @param message
	 * @param cause
	 */
	public ClassificationsFault(String message, Throwable cause) {
		super(message);

		this.message = message;
		this.detail = cause != null && cause.getCause() != null ? cause.getCause().getMessage() : "cause unknown";
	}

	/** @return the detail */
	public String getDetail() {
		return this.detail;
	}

	/** @return the message */
	@Override
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}