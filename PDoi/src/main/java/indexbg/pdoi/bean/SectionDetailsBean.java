package indexbg.pdoi.bean;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import indexbg.pdoi.db.Files;
import indexbg.pdoi.db.Publication;
import indexbg.pdoi.db.dao.FilesDAO;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;


@ManagedBean(name = "sectDetails")
@ViewScoped
public class SectionDetailsBean  extends PDoiBean{
	
	
	/**
	 * Основен javaBean клас за показване конкретно избрана публикация от дадена секция
	 */
	private static final long serialVersionUID = -810676963267894500L;

	static final Logger LOGGER = LoggerFactory.getLogger(SectionDetailsBean.class);
	
	private Long idUser=null;
	private Long codeSection=null;
	private Publication publ = new Publication();
	private Long idPubl=null;
	private List<Files> filesList = new ArrayList<>();
	private HashMap<Long,String> iconTypFil=new HashMap<Long,String>(); 
	
	@PostConstruct
	
	/**
	 * Инициира/сетва първоначалните стойности на атрибутите, свързани с показване на данните на конкретна публикация от дадена секция. 
	 * Чете предадените параметри от други екрани
	 */
	public void initData(){
		
		try{
			this.idUser=getUserData().getUserId();
		} catch (ObjectNotFoundException e) {
			this.idUser = -1L;
		}
		
		this.iconTypFil=new HashMap<Long,String>();
		
		this.codeSection=null;
		this.setPubl(new Publication());
		
		
		String par =JSFUtils.getRequestParameter("codeSect");
		if (par != null && !par.trim().isEmpty()){
			this.codeSection=Long.valueOf(par);
		}
		 	par =JSFUtils.getRequestParameter("idPubl");
		if (par != null && !par.trim().isEmpty()){
			this.idPubl=Long.valueOf(par);
			loadPublById(this.idPubl);
		}
		
		
	}

	
	
	public Long getIdUser() {
		return idUser;
	}


	public Long getCodeSection() {
		return codeSection;
	}


	
	public Date getCurrentDate() {
		return new Date();
	}

	public Long getLang() {
		return getCurrentLang();
	}

	
	
	/** Метод за извличане данни за конкретна публикация от БД
	 * @param idPubl - ид.
	 */
	public void loadPublById(Long idPubl){
		
		try {
		
			this.publ = (Publication) new PublicationDAO(this.idUser).findById(this.idPubl);
						
			FilesDAO filesDAO = new FilesDAO(this.idUser);
			this.filesList=filesDAO.findByCodeObjAndIdObj(Constants.CODE_OBJECT_PUBLICATION, this.idPubl);
			String typ="";
			for (Files item: this.filesList) {
				if (null!=item.getId() && null!=item.getContentType() && !item.getContentType().isEmpty()){
					typ=item.getContentType().trim().toLowerCase();
					if (typ.contains("image")){
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-file-image-o");
					}else if (typ.contains("pdf")){
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-file-pdf-o");
					}else if (typ.contains("sheet")){
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-file-excel-o");	
					}else if (typ.contains("document")){
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-file-word-o");
					}else if (typ.contains("html")){
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-html5");		
					}else if (typ.contains("text")){
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-file-text-o");
					}else if (typ.contains("word")){
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-file-word-o");
					}else if (typ.contains("xml")){
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-file");
					
					}else{
						this.getIconTypFil().put(item.getId(),"fa fa-fw fa-file");
					}
				}
								
			}
			
			
			
			} catch (DbErrorException e) {
				LOGGER.error(e.getMessage(), e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
				
			} finally {
				JPA.getUtil().closeConnection();
			}

	}
	
	
	/** Извличане на прикачен файл от БД и предоставянето му на потребителя за разглеждане/запис  
	 * @param id
	 */
	public void download(Object id){
		Files file=null;
		try {
			if(null!=id) {
				FilesDAO filesDAO = new FilesDAO(getIdUser());
				file = filesDAO.findById(Long.valueOf(id.toString()));
				if(file.getPath() != null && !file.getPath().isEmpty()){
					Path path = Paths.get(file.getPath());
					file.setContent(java.nio.file.Files.readAllBytes(path));
				}
			}
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
			
			HttpServletRequest request =(HttpServletRequest)externalContext.getRequest();
			String agent = request.getHeader("user-agent");

			
			String codedfilename = ""; 
			
			if (null != agent &&  (-1 != agent.indexOf("MSIE") || (-1 != agent.indexOf("Mozilla") && -1 != agent.indexOf("rv:11")) || (-1 != agent.indexOf("Edge"))  ) ) {
				codedfilename = URLEncoder.encode(file.getFilename(), "UTF8");
			} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
				codedfilename = MimeUtility.encodeText(file.getFilename(), "UTF8", "B");
			} else {
				codedfilename = URLEncoder.encode(file.getFilename(), "UTF8");
			}
		    externalContext.setResponseHeader("Content-Type", "application/x-download");
		    externalContext.setResponseHeader("Content-Length", file.getContent().length + "");
		    externalContext.setResponseHeader("Content-Disposition", "attachment;filename=\"" + codedfilename + "\"");
			externalContext.getResponseOutputStream().write(file.getContent());
			facesContext.responseComplete();
			
		} catch (DbErrorException e) {
			LOGGER.error("DbErrorException: " + e.getMessage());
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
		} catch (IOException e) {
			LOGGER.error("IOException: " + e.getMessage());
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.unexpectedResult"), e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Exception: " + e.getMessage());
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.unexpectedResult"), e.getMessage());
		}
	}


	public Long getIdPubl() {
		return idPubl;
	}

	public void setIdPubl(Long idPubl) {
		this.idPubl = idPubl;
	}

	public Publication getPubl() {
		return publ;
	}

	public void setPubl(Publication publ) {
		this.publ = publ;
	}



	public List<Files> getFilesList() {
		return filesList;
	}



	public void setFilesList(List<Files> filesList) {
		this.filesList = filesList;
	}



	public HashMap<Long,String> getIconTypFil() {
		return iconTypFil;
	}



	public void setIconTypFil(HashMap<Long,String> iconTypFil) {
		this.iconTypFil = iconTypFil;
	}



	
}