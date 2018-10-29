package indexbg.pdoi.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.PersistentEntity;


@Entity
@Table(name = "PDOI_PUB_THEMES")
public class PubThemes implements PersistentEntity{

	/**
	 * Основен java Entity клас на темите към публикациите 
	 */
	private static final long serialVersionUID = -2840638006007003311L;
	static final Logger LOGGER = LoggerFactory.getLogger(PubThemes.class);

	@SequenceGenerator(name = "PubThemes", sequenceName = "SEQ_PUB_THEMES", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PubThemes")
	@Column(name = "ID", nullable=false, precision = 10, scale = 0)
	private Long id;      // ид. на темата

	
	@Column(name="PUBLICATION_ID", nullable=false, precision = 10, scale = 0, insertable = false, updatable = false)	
	private Long pubId; //ид. на публикацията, към която са отнесени темите
	
	@Column(name = "THEM_VALUE", nullable=false, precision = 10, scale = 0)
	private Long themValue;  // код на темата
	
	public PubThemes() {
		
	}
	
	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPubId() {
		return pubId;
	}

	public void setPubId(Long pubId) {
		this.pubId = pubId;
	}

	public Long getThemValue() {
		return themValue;
	}

	public void setThemValue(Long themValue) {
		this.themValue = themValue;
	}


}
