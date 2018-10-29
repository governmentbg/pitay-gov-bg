package indexbg.pdoi.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Резултата от изпълнението на услугата за получаване на номенклатура
 *
 * @author belev
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetNomenclatureResult", propOrder = { "status", "message", "nomenclature" })
public class GetNomenclatureResult {

	/** */
	@XmlType(name = "")
	@XmlEnum
	public enum ResultStatusEnum {

		/**  */
		OK,

		/**  */
		INVALID_INPUT,

		/**  */
		NO_DATA_FOUND,

		/**  */
		ERROR;

		/**
		 * @param v
		 * @return value
		 */
		public static ResultStatusEnum fromValue(String v) {
			return valueOf(v);
		}

		/** @return value */
		public String value() {
			return name();
		}

	}

	@XmlElement(name = "Message")
	protected String message;

	@XmlElement(name = "Nomenclature")
	protected Nomenclature nomenclature;

	@XmlElement(name = "Status")
	protected ResultStatusEnum status;

	/**  */
	public GetNomenclatureResult() {
		super();
	}

	/**
	 * @param nomenclature
	 */
	public GetNomenclatureResult(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	/**
	 * @param message
	 * @param status
	 */
	public GetNomenclatureResult(String message, ResultStatusEnum status) {
		this.message = message;
		this.status = status;
	}

	/** @return the message */
	public String getMessage() {
		return this.message;
	}

	/** @return the nomenclature */
	public Nomenclature getNomenclature() {
		return this.nomenclature;
	}

	/** @return the status */
	public GetNomenclatureResult.ResultStatusEnum getStatus() {
		return this.status;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param nomenclature
	 */
	public void setNomenclature(Nomenclature nomenclature) {
		this.nomenclature = nomenclature;
	}

	/**
	 * @param status
	 */
	public void setStatus(GetNomenclatureResult.ResultStatusEnum status) {
		this.status = status;
	}

}