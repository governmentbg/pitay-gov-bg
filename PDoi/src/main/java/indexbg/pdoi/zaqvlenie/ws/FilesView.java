package indexbg.pdoi.zaqvlenie.ws;

public class FilesView {

	private Long ident;
	
	private String filename;
	
	private String contentType;
		
	private byte[] 	content;	

	private String description;
	
	
	/** default constructor */
	public FilesView() {
		
	}
	
	public FilesView(Long ident ,String filename, String description, String contentType, byte[] content) {
		this.ident= ident;
		this.filename = filename;
		this.description = description;
		this.contentType = contentType; 
		this.content = content;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdent() {
		return ident;
	}

	public void setIdent(Long ident) {
		this.ident = ident;
	}
	
}