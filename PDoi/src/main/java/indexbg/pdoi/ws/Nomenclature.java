package indexbg.pdoi.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Номенклатура
 *
 * @author belev
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Nomenclature", propOrder = { "code", "sysname", "name", "elements" })
public class Nomenclature {

	@XmlElement(name = "Code")
	protected Integer code;

	@XmlElement(name = "Element")
	protected List<NomenclatureElement> elements;

	@XmlElement(name = "Name")
	protected String name;

	@XmlElement(name = "SYSname")
	protected String sysname;

	/**  */
	public Nomenclature() {
		super();
	}

	/** @return the code */
	public Integer getCode() {
		return this.code;
	}

	/** @return the elements */
	public List<NomenclatureElement> getElements() {
		if (this.elements == null) {
			this.elements = new ArrayList<>();
		}
		return this.elements;
	}

	/** @return the name */
	public String getName() {
		return this.name;
	}

	/** @return the sysname */
	public String getSysname() {
		return this.sysname;
	}

	/**
	 * @param code
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @param elements
	 */
	public void setElements(List<NomenclatureElement> elements) {
		this.elements = elements;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
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
		return "Nomenclature [code=" + this.code + ", name=" + this.name + ", sysname=" + this.sysname + "]";
	}

}