package indexbg.pdoi.db;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.indexbg.system.db.TrackableEntity;

@SqlResultSetMapping(name = "filterByCodeObjAndId", classes = {
		@ConstructorResult(targetClass = Files.class, 
				columns = { 
						@ColumnResult(name = "id", type = Long.class),						
						@ColumnResult(name = "id_object", type = Long.class),
						@ColumnResult(name = "code_object", type = Long.class),
						@ColumnResult(name = "filename", type = String.class),				
						@ColumnResult(name = "description", type = String.class),				
						@ColumnResult(name = "visible_on_site", type = Boolean.class),
						@ColumnResult(name = "content_type", type = String.class) 
						}) 
		})

/** */
@Entity
@Table(name = "files")
public class Files extends TrackableEntity {

	/** Основен java Entity клас на файловете
	 * 	 
	 */
	private static final long serialVersionUID = -625136388299076952L;
	
	@SequenceGenerator(name = "Files", sequenceName = "seq_files", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "Files")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "id_object", nullable = false)
	private Long idObject;
	
	@Column(name = "code_object", nullable = false)
	private Long codeObject;
	
	@Column(name = "filename", length = 200)
	private String filename;
	
	@Column(name = "content_type", length = 500)
	private String contentType;
		
	@Column(name = "content")
	private byte[] 	content;	

	@Column(name = "path", length = 255)
	private String path;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "visible_on_site")
	private boolean visibleOnSite;
	
	
	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** default constructor */
	public Files() {
		
	}
	
	public Files(Long id, Long idObject, Long codeObject, String filename, String description, boolean visibleOnSite, String contentType) {
		super();
		this.id = id;
		this.idObject = idObject;
		this.codeObject = codeObject;
		this.filename = filename;
		this.description = description;
		this.visibleOnSite = visibleOnSite;
		this.contentType = contentType; 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public Long getIdObject() {
		return idObject;
	}

	public void setIdObject(Long idObject) {
		this.idObject = idObject;
	}
	
	public Long getCodeObject() {
		return codeObject;
	}

	public void setCodeObject(Long codeObject) {
		this.codeObject = codeObject;
	}	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVisibleOnSite() {
		return visibleOnSite;
	}

	public void setVisibleOnSite(boolean visibleOnSite) {
		this.visibleOnSite = visibleOnSite;
	}

	@Override
	public Long getUserReg() {
		// TODO Auto-generated method stub
		return super.getUserReg();
	}
	
	@Override
	public Date getDateReg() {
		// TODO Auto-generated method stub
		return super.getDateReg();
	}
	
	@Override
	public Long getUserLastMod() {
		// TODO Auto-generated method stub
		return super.getUserLastMod();
	}
	
	@Override
	public Date getDateLastMod() {
		// TODO Auto-generated method stub
		return super.getDateLastMod();
	}
	
}