package indexbg.pdoi.db;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.indexbg.system.db.PersistentEntity;


/**
@Entity
@Table(name = "pdoi_files_relation") */
public class FilesRelation implements PersistentEntity  {

	/** Основен java Entity клас на файловете
	 * 	 
	 */
	private static final long serialVersionUID = -625136388299076952L;
	
	@SequenceGenerator(name = "FilesRelation", sequenceName = "seq_file_relation", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "Files")
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "id_object", nullable = false)
	private Long idObject;
	
	@Column(name = "code_object", nullable = false)
	private Long codeObject;
	
	@Column(name = "id_file", nullable = false)
	private Long idFile;
	
	
	
	/** default constructor */
	public FilesRelation() {
		
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


	public Long getIdFile() {
		return idFile;
	}


	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}


	@Override
	public Long getCodeMainObject() {
		// TODO Auto-generated method stub
		return null;
	}	

	
}