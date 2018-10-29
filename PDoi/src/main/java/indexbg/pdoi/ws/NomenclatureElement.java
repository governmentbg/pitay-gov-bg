package indexbg.pdoi.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Значение от номенклатура
 *
 * @author belev
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NomenclatureElement", propOrder = { "code", "sysname", "name", "codePrev", "codeParent", "level", "ordering", "codeExt", "dateFrom",
		"dateTo" })
public class NomenclatureElement {

	@XmlElement(name = "Code")
	protected Integer code;

	@XmlElement(name = "CodeExt")
	protected String codeExt;

	@XmlElement(name = "CodeParent")
	protected Integer codeParent;

	@XmlElement(name = "CodePrev")
	protected Integer codePrev;

	@XmlElement(name = "DateFrom")
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar dateFrom;

	@XmlElement(name = "DateTo")
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar dateTo;

	@XmlElement(name = "Level")
	protected Integer level;

	@XmlElement(name = "Name")
	protected String name;

	@XmlElement(name = "Ordering")
	protected Integer ordering;

	@XmlElement(name = "SYSname")
	protected String sysname;

	@XmlTransient
	private boolean nomsas;

	/**  */
	public NomenclatureElement() {
		super();
	}

	/** @return the code */
	public Integer getCode() {
		return this.code;
	}

	/** @return the codeExt */
	public String getCodeExt() {
		return this.codeExt;
	}

	/** @return the codeParent */
	public Integer getCodeParent() {
		return this.codeParent;
	}

	/** @return the codePrev */
	public Integer getCodePrev() {
		return this.codePrev;
	}

	/** @return the dateFrom */
	public XMLGregorianCalendar getDateFrom() {
		return this.dateFrom;
	}

	/** @return the dateTo */
	public XMLGregorianCalendar getDateTo() {
		return this.dateTo;
	}

	/** @return the level */
	public Integer getLevel() {
		return this.level;
	}

	/** @return the name */
	public String getName() {
		return this.name;
	}

	/** @return the ordering */
	public Integer getOrdering() {
		return this.ordering;
	}

	/** @return the sysname */
	public String getSysname() {
		return this.sysname;
	}

	/** @return the nomsas */
	public boolean isNomsas() {
		return this.nomsas;
	}

	/**
	 * @param code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @param codeExt
	 */
	public void setCodeExt(String codeExt) {
		this.codeExt = codeExt;
	}

	/**
	 * @param codeParent
	 */
	public void setCodeParent(Integer codeParent) {
		this.codeParent = codeParent;
	}

	/**
	 * @param codePrev
	 */
	public void setCodePrev(Integer codePrev) {
		this.codePrev = codePrev;
	}

	/**
	 * @param dateFrom
	 */
	public void setDateFrom(XMLGregorianCalendar dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @param dateTo
	 */
	public void setDateTo(XMLGregorianCalendar dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param nomsas
	 */
	public void setNomsas(boolean nomsas) {
		this.nomsas = nomsas;
	}

	/**
	 * @param ordering
	 */
	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}

	/**
	 * @param sysname
	 */
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	/** @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return "NomenclatureElement [code=" + this.code + ", name=" + this.name + ", sysname=" + this.sysname + ", codeParent=" + this.codeParent
				+ ", codePrev=" + this.codePrev + ", dateFrom=" + this.dateFrom + ", dateTo=" + this.dateTo + ", level=" + this.level + ", ordering="
				+ this.ordering + ", codeExt=" + this.codeExt + ", nomsas=" + this.nomsas + "]";
	}
}