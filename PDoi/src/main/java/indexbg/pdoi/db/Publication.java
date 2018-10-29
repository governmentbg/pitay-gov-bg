package indexbg.pdoi.db;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Date;
//import java.util.List;
import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.Lob;
import javax.persistence.OneToMany;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import com.indexbg.system.db.JPA;
import com.indexbg.system.db.TrackableEntity;
//import com.indexbg.system.exceptions.DbErrorException;
import indexbg.pdoi.system.Constants;

@Entity
@Table(name = "PDOI_PUBLICATION")
public class Publication extends TrackableEntity {

	/**
	 * Основен java Entity клас на публикациите 
	 */
	private static final long serialVersionUID = -1079806292866063168L;

	static final Logger LOGGER = LoggerFactory.getLogger(Publication.class);
	
	@SequenceGenerator(name = "Publication", sequenceName = "SEQ_PUBLICATION", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Publication")
	@Column(name = "ID", nullable=false, precision = 10, scale = 0)  // ид.
	private Long id;                      // id
	
	@Column(name = "TITLE", nullable=false, length = 250)
	private String title;         // Заглавие    
	
	@Column(name = "SECTION", nullable=false, precision = 10, scale = 0)
	private Long section;      //Код на секция        
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_FROM", nullable=false, length = 7)
	private Date dateFrom;   // Нач. дата на публикацията
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_TO", length = 7)
	private Date dateTo;  // Крайна дата на публикацията

	@Column(name = "ANNOTATION", nullable=false, length = 2500)
	private String  annotation; // Анотация
	
	@Column(name = "IMAGE_PUB")
	private byte[] 	imagePub;     // Контент на изображение към публикация 

	@Column(name = "FULL_TEXT")
	private String 	fullText;    // Пълен текст на публикацията
	
	@Column(name = "OTHER", length = 2500)
	private String  other;  // Други коментари към публикацията
	
		
	/** Зададени теми на публикацията/информац.материал */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
	@JoinColumn(name="PUBLICATION_ID", nullable = false, referencedColumnName = "ID", insertable = true, updatable = true)
	private List<PubThemes> pubThemes=new ArrayList<PubThemes>(0);

	public Publication() {
		
	}
	
	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return Constants.CODE_OBJECT_PUBLICATION;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSection() {
		return section;
	}

	public void setSection(Long section) {
		this.section = section;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public byte[] getImagePub() {
		return imagePub;
	}

	public void setImagePub(byte[] imagePub) {
		this.imagePub = imagePub;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public List<PubThemes> getPubThemes() {
		return pubThemes;
	}

	public void setPubThemes(List<PubThemes> pubThemes) {
		this.pubThemes = pubThemes;
	}
	
}
