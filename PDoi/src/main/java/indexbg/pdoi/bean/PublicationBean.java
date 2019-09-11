package indexbg.pdoi.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.indexbg.indexui.navigation.Navigation;
import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectInUseException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;
import indexbg.pdoi.db.Files;
import indexbg.pdoi.db.PubThemes;
import indexbg.pdoi.db.Publication;
import indexbg.pdoi.db.dao.FilesDAO;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

@ManagedBean(name = "infoData")
@ViewScoped
public class PublicationBean  extends PDoiBean {

	/**
	 * Основен javaBean клас за въвеждане/актуализация на публикациите
	 */
	private static final long serialVersionUID = 2754438705425537720L;
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationBean.class);
	
	
	private Publication publication = new Publication();
	private PubThemes pubThemes = new PubThemes();
	private Long idPubl = null;
	private String zagl = null;
	private String sectionText="";
	private String temaText="";
	private Long codeSection=null;
	private Long idUser=null;
	private StreamedContent imageCont=null;
	private List<Long> selIdThemas=new ArrayList<Long>();
	private List<Files> filesList = new ArrayList<>();
	private List<Files> deleteFilesList = new ArrayList<>();
	private Long param=null;
	private Date dateFrom=null;
	private Date dateTo=null;

	
	/**Инициира/сетва първоначалните стойности на атрибутите на обектите. Чете предадените параметри от други екрани 
	 * 
	 */
	@PostConstruct
	public void initData(){
		LOGGER.debug("PostConstruct!");
		
		
		try{
			this.idUser=getUserData().getUserId();
		} catch (ObjectNotFoundException e) {
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.objectNotFound"), e.getMessage());
			LOGGER.error(e.getMessage(), e);
			this.idUser = -1L;
		}

		String par =JSFUtils.getRequestParameter("par");
		  
		if (par != null && !par.trim().isEmpty()){
			this.setParam(Long.valueOf(par));
		}

		
		par =JSFUtils.getRequestParameter("idPubl");
		
		actionClear();
		
		if (par != null && !par.trim().isEmpty()){
			this.setIdPubl(Long.valueOf(par));
			loadPublById(this.idPubl);
		}

	}
	
	/**Изтрива стойностите на определени атрибути на обектите
	 * 
	 */
	public void actionClear(){
		this.publication = new Publication();
		this.publication.setPubThemes(new ArrayList<PubThemes>());
		this.setIdPubl(null);
		this.zagl = null;
		this.sectionText="";
		this.temaText="";
		this.codeSection=null;
		
		this.imageCont=null;
		this.selIdThemas=new ArrayList<Long>();
		this.filesList = new ArrayList<>();
		this.deleteFilesList = new ArrayList<>();
		this.setDateFrom(new Date());
		this.setDateTo(null);
		
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public PubThemes getPubThemes() {
		return pubThemes;
	}

	public void setPubThemes(PubThemes pubThemes) {
		this.pubThemes = pubThemes;
	}
	
	/** Записва в БД на въведените/актуализираните информационни обекти/публикации
	 * 
	 */
	public void actionSave(){
		
		if (!checkData())
			return;
	
		setAllThemasForPublication();
		
		
		try {
			 
			JPA.getUtil().begin();
			
			new PublicationDAO(this.idUser).save(this.publication);
						
			FilesDAO filesDAO = new FilesDAO(this.idUser);
			// Прикачените файлове
			if (!this.deleteFilesList.isEmpty()) {
				for (int i = 0; i < deleteFilesList.size(); i++) {
					if (deleteFilesList.get(i).getId() != null) {
						filesDAO.delFromFilesText(deleteFilesList.get(i).getId());
						filesDAO.deleteById(deleteFilesList.get(i).getId());
					}
				}
			}

			for (int i = 0; i < filesList.size(); i++) {
				
				if (filesList.get(i).getId() == null) {
					filesList.get(i).setCodeObject(this.publication.getCodeMainObject());
					filesList.get(i).setIdObject(this.publication.getId());	
					filesDAO.save(filesList.get(i));
				} else { //TODO - при четене от базата не се взема bytearrea и затова се прави update на 4 полета - description, visibleOnSite, userLastMod, dateLastMod
					filesDAO.updateFile(filesList.get(i).getDescription(), filesList.get(i).isVisibleOnSite(), this.idUser, new Date(), filesList.get(i).getId()); 		
				}			
			}
			
			JPA.getUtil().commit();
			
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesSaveMsg"));
			
			if (!this.deleteFilesList.isEmpty()) {
				deleteFilesList.clear();
			}
			
			} catch (DbErrorException e) {
			 	LOGGER.error(e.getMessage(), e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
				JPA.getUtil().rollback();
			} catch (ObjectInUseException e) {
				LOGGER.error(e.getMessage(), e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.objectInUse"), e.getMessage());	
				JPA.getUtil().rollback();
			}  catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());	
				JPA.getUtil().rollback();
			} finally {
				JPA.getUtil().closeConnection();
		} 
	
	}
	
	/** Изтрива от БД на определена публикация
	 * 
	 */
	public void actionDelete(){
		try {
			
			JPA.getUtil().begin();		
			FilesDAO filesDAO = new FilesDAO(getIdUser());
			// Прикачените файлове
			for (Files item: this.filesList) {
				if (null!=item.getId()){
					filesDAO.delFromFilesText(item.getId());
					filesDAO.deleteById(item.getId());
				}
			}
			
			for (Files item: this.deleteFilesList) {
				if (null!=item.getId()){
					filesDAO.delFromFilesText(item.getId());
					filesDAO.deleteById(item.getId());
				}
			}
			
			// Публикациите
			if (null!=this.publication.getId())
				new PublicationDAO(this.idUser).deleteById(this.publication.getId());
			
				
			JPA.getUtil().commit();			
				
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, getMessageResourceString("beanMessages", "general.succesDeleteMsg"));
			
			if (!this.deleteFilesList.isEmpty()) {
				deleteFilesList.clear();
			}
			
			Navigation navHolder = new Navigation();			
		    int i = navHolder.getNavPath().size();
		    
		    if(i > 2) {	
		    	navHolder.goTo(String.valueOf(i-3));
		    } else if(i > 1) {	navHolder.goBack();   }
			
			
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"), e.getMessage());
			JPA.getUtil().rollback();
		} catch (ObjectInUseException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.objectInUse"), e.getMessage());
			JPA.getUtil().rollback();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());	
			JPA.getUtil().rollback();
		} finally {
			JPA.getUtil().closeConnection();
		}
		
		
	}

	

	public String getZagl() {
		return zagl;
	}

	public void setZagl(String zagl) {
		this.zagl = zagl;
	}

	public String getSectionText() {
		return sectionText;
	}

	public void setSectionText(String sectionText) {
		this.sectionText = sectionText;
	}

	public Long getCodeSection() {
		return codeSection;
	}

	public void setCodeSection(Long codeSection) {
		this.codeSection = codeSection;
		this.publication.setSection(codeSection);
	}


	/**Проверява дата от и дата до на публикациите
	 * @param nom
	 */
	public boolean checkDates(int nom){
		boolean check = true;
		if (this.dateFrom != null && this.dateTo != null) {
			if(this.dateFrom.compareTo(this.dateTo) > 0) {
				check=false;
				if (nom==1){
					
//					this.publication.setDateFrom(null);
				}else{
//					this.publication.setDateTo(null);
				}
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","publ.dateFromLessDateTo"));
			}
		} 
		return check;
	}
	
	
	/** Избор на изображение към публикация 
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
        try {
        	if (null!=event){
        		this.publication.setImagePub(event.getFile().getContents());
        		InputStream imageStream = new ByteArrayInputStream(this.publication.getImagePub());
        		this.imageCont = new DefaultStreamedContent(imageStream, "image/jpeg");
        	}else{
        		
    			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Грешка при качване на файла!");
        	}
        	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Грешка при качване на файла!", e.getMessage());
		}
		
    }

	
	public Date getCurrentDate() {
		return new Date();
	}

	public StreamedContent getImageCont() {
		return imageCont;
	}

	public void setImageCont(StreamedContent imageCont) {
		this.imageCont = imageCont;
	}

	public String getTemaText() {
		return temaText;
	}

	public void setTemaText(String temaText) {
		this.temaText = temaText;
	}

	public List<Long> getSelIdThemas() {
		return selIdThemas;
	}

	public void setSelIdThemas(List<Long> selIdThemas) {

		this.selIdThemas = selIdThemas;
	}
	
	
/** Избор на файлове за присъединяване към публикациите 
 * @param event
 */
public void uploadFileListener(FileUploadEvent event){		
		
		try {
			
			UploadedFile upFile = event.getFile();
			
			if(!checkForUploadedFileByName(upFile.getFileName(), filesList)) {
				Files fileObject = new Files();
				fileObject.setFilename(upFile.getFileName());
				fileObject.setContentType(upFile.getContentType());
				fileObject.setContent(upFile.getContents());
				fileObject.setIdObject(getIdUser());
				fileObject.setCodeObject(this.publication.getCodeMainObject());			
				getFilesList().add(fileObject);
			}
		
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Грешка при качване на файла!",e.getMessage());
		} 
	}

	public List<Files> getFilesList() {
		return filesList;
	}
	
	public void setFilesList(List<Files> filesList) {
		this.filesList = filesList;
	}
	
	public void remove(Files file){
		this.deleteFilesList.add(file);
		this.filesList.remove(file);
	}

	public List<Files> getDeleteFilesList() {
		return deleteFilesList;
	}

	public void setDeleteFilesList(List<Files> deleteFilesList) {
		this.deleteFilesList = deleteFilesList;
	}
	
	public void actionChangeFileVis(Files file) {
		
		file.setVisibleOnSite(true);	}

	public void actionChangeFileNonVis(Files file) {
		
		file.setVisibleOnSite(false);	
	}
	
	/** Извлича присъединените файлове от БД и ги предлага за разглеждане/съхраняване при клиента 
	 * @param file
	 */
	public void download(Files file){
		try {
			
			if(file.getContent() == null && file.getId() != null) {
			
				FilesDAO filesDAO = new FilesDAO(getIdUser());
				file = filesDAO.findById(file.getId());
				
				if(file.getPath() != null && !file.getPath().isEmpty()){
					Path path = Paths.get(file.getPath());
					file.setContent(java.nio.file.Files.readAllBytes(path));
				}
			
			}
			
			String codedfilename = URLEncoder.encode(file.getFilename(), "UTF8");
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    externalContext.setResponseHeader("Content-Type", "application/x-download");
		    externalContext.setResponseHeader("Content-Length", file.getContent().length + "");
		    externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + codedfilename + "\"");
			externalContext.getResponseOutputStream().write(file.getContent());
			facesContext.responseComplete();
			
		} catch (DbErrorException e) {
			LOGGER.error("DbErrorException: " + e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"),e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Грешка при сваляне на файла!: ",e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Грешка при сваляне на файла!: ",e.getMessage());
		}
	}

	
	/** Подготовка на темите към дадена публикация за визуализиране 
	 * 
	 */
	public void setAllThemasForPublication(){
		
		if (this.publication.getPubThemes().size() > 0) {
			List<PubThemes> pubThemesDelList = new ArrayList<PubThemes>();
			PubThemes pt=null;
			for (PubThemes item: this.publication.getPubThemes()){
				if(!this.selIdThemas.contains(item.getThemValue()))
					pubThemesDelList.add(item);
			}
			this.publication.getPubThemes().removeAll(pubThemesDelList);
			
			List<Long> newItemsList=new ArrayList<Long>();
			int n=0;
			for (Long itemL: this.selIdThemas){
				n=0;
				for (PubThemes itemP: this.publication.getPubThemes()){
					if (itemL.equals(itemP.getThemValue())){
						n=1;
						break;
					}
				}
				if (n==0)
					newItemsList.add(itemL);
			}
			
			for (Long itemL: newItemsList){
			  pt = new PubThemes();
			  pt.setId(null);
			  pt.setPubId(this.publication.getId());
			  pt.setThemValue(itemL);
			  this.publication.getPubThemes().add(pt);
			}

		}else{
			if (this.selIdThemas != null && this.selIdThemas.size() >  0) {
			  for (Long item: this.selIdThemas){
				  PubThemes pt = new PubThemes();
				  pt.setId(null);
				  pt.setPubId(null);
				  pt.setThemValue(item);
				  this.publication.getPubThemes().add(pt);
			  }
		  }	
		}
		
	}
	
	/**Проверка на въведените данни на публикация, преди запис в БД
	 * @return
	 */
	public boolean checkData(){
		boolean ver = true;
		ver=checkDates(1);
		
		this.publication.setDateFrom(this.getDateFrom());
		this.publication.setDateTo(this.getDateTo());
		if (null==this.getPublication().getTitle() || this.getPublication().getTitle().equals("")){		
			ver=false;
			JSFUtils.addMessage("formInfoData:idTitle", FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","publ.noTitle"));
		}
		if (null==this.getPublication().getDateFrom()){		
			ver=false;
			JSFUtils.addMessage("formInfoData:dateOt", FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","publ.noDateOt") + "!" );
		}
		if (null==this.getPublication().getSection()){		
			ver=false;
			JSFUtils.addMessage("formInfoData:idSectionText", FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","publ.nosection") + "!" );
		}

		return ver;		
	}


	public Long getIdPubl() {
		return idPubl;
	}


	public void setIdPubl(Long idPubl) {
		this.idPubl = idPubl;
	}

	
	/** Извлича данни за определена публикация по ид.
	 * @param idPubl
	 */
	public void loadPublById(Long idPubl){
		
		try {
			this.publication = (Publication) new PublicationDAO(this.idUser).findById(this.idPubl);
			this.dateFrom=this.publication.getDateFrom();
			this.dateTo=this.publication.getDateTo();
			FilesDAO filesDAO = new FilesDAO(this.idUser);
			this.filesList = filesDAO.findByCodeObjAndIdObj(Constants.CODE_OBJECT_PUBLICATION, this.idPubl);

			this.codeSection = this.publication.getSection();
			if(null!=this.codeSection){
				this.sectionText= getSystemData().decodeItem(Constants.CODE_SYSCLASS_SECTION, this.codeSection, getCurrentLang(), new Date(), this.idUser);
			}
			
			if(null!=this.publication.getImagePub()){
				InputStream imageStream = new ByteArrayInputStream(this.publication.getImagePub());
				this.imageCont = new DefaultStreamedContent(imageStream, "image/jpeg");
			}
    		
			for(PubThemes item:this.publication.getPubThemes()){
				this.selIdThemas.add(item.getThemValue());
				this.temaText+=","+getSystemData().decodeItem(Constants.CODE_SYSCLASS_THEMAS, item.getThemValue(), getCurrentLang(), new Date(), this.idUser);
			}	
			
			if (null!=this.temaText && ! this.temaText.isEmpty())
				this.temaText = this.temaText.substring(1);
			
			} catch (DbErrorException e) {
			 	LOGGER.error(e.getMessage(), e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
				
			} finally {
				JPA.getUtil().closeConnection();
			}

	}
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getParam() {
		return param;
	}

	public void setParam(Long param) {
		this.param = param;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	

}
