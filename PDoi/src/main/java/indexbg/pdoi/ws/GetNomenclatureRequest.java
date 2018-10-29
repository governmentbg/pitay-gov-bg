package indexbg.pdoi.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Заявка за изпълнението на услугата за получаване на номенклатура
 *
 * @author belev
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetNomenclatureRequest", propOrder = { "requestArgument", "requestDate", "language", "toLevel" })
public class GetNomenclatureRequest {

	@XmlElement(name = "Language")
	protected String language;

	@XmlElement(name = "RequestArgument")
	protected String requestArgument;

	@XmlElement(name = "RequestDate")
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar requestDate;

	@XmlElement(name = "ToLevel")
	protected Integer toLevel;

	/**  */
	public GetNomenclatureRequest() {
		super();
	}

	/** @return the language */
	public String getLanguage() {
		return this.language;
	}

	/** @return the requestArgument */
	public String getRequestArgument() {
		return this.requestArgument;
	}

	/** @return the requestDate */
	public XMLGregorianCalendar getRequestDate() {
		return this.requestDate;
	}

	/** @return the toLevel */
	public Integer getToLevel() {
		return this.toLevel;
	}

	/**
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @param requestArgument
	 */
	public void setRequestArgument(String requestArgument) {
		this.requestArgument = requestArgument;
	}

	/**
	 * @param requestDate
	 */
	public void setRequestDate(XMLGregorianCalendar requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @param toLevel
	 */
	public void setToLevel(Integer toLevel) {
		this.toLevel = toLevel;
	}
}