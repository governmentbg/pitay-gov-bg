package indexbg.pdoi.bean;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.Publication;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

@ManagedBean(name = "pubList")
@ViewScoped
public class PublicationList extends PDoiBean{

	/**
	 * Основен javaBean клас за търсене на публикации по зададени критерии за търсене и 
	 * за обръщане към javaBean клас за въвеждане/актуализация
	 */
	private static final long serialVersionUID = 8161471265537112631L;
	
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationList.class);
	private Long idUser=null;
	private String sectionText=null;
	private String temaText=null;
	private Long codeSection=null;
	private List<Long> selIdThemas=new ArrayList<Long>();
	private Date dateFrom=null;
	private Date dateTo=null;
	private String annotation=null;
	private Long period=null;
	private LazyDataModelSQL2Array pubListT = null;
	private Publication selectedPubl=null;
	private Long param=null;

	/**
	 * Инициира/сетва първоначалните стойности на атрибутите на филтъра за търсене. Чете предадените параметри от други екрани
	 */
	@PostConstruct
	public void initData(){
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
		
		actionClear();
	}
	
	

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getSectionText() {
		return sectionText;
	}

	public void setSectionText(String sectionText) {
		this.sectionText = sectionText;
	}

	public String getTemaText() {
		return temaText;
	}

	public void setTemaText(String temaText) {
		this.temaText = temaText;
	}

	public Long getCodeSection() {
		return codeSection;
	}

	public void setCodeSection(Long codeSection) {
		this.codeSection = codeSection;
	}

	public List<Long> getSelIdThemas() {
		return selIdThemas;
	}

	public void setSelIdThemas(List<Long> selIdThemas) {
		this.selIdThemas = selIdThemas;
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
	
	/**Проверява дата от и дата до на публикациите
	 * @param nom
	 */
	public boolean checkDates(int nom){
		boolean rez=true;
		this.setPeriod(null);
		if (this.getDateFrom() != null && this.getDateTo() != null) {
			if(this.getDateFrom().compareTo(this.getDateTo()) > 0) {
				rez=false;
				if (nom==1){
//					this.setDateFrom(null);
				}else{
//					this.setDateTo(null);
				}
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","publ.dateFromLessDateTo"));
			}
		} 
		return rez;
	}
	
	

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public Date getCurrentDate() {
		return new Date();
	}

	public Long getPeriod() {
		return period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}
	
	public void changePeriod () {
    	if (this.period != null) {
			Date[] di;
			di = DateUtils.calculatePeriod(this.period);
			this.setDateFrom(di[0]);
			this.setDateTo(di[1]);
		} else {
			this.setDateFrom(null);
			this.setDateTo(null);
		}
		return ;
    }
	
	/**Изтрива стойностите на филтъра за търсене на публикациите
	 * 
	 */
	public void actionClear(){
		this.sectionText=null;
		this.temaText=null;
		this.codeSection=null;
		this.selIdThemas=new ArrayList<Long>();
		this.dateFrom=null;
		this.dateTo=null;
		this.period=null;
		this.setSelectedPubl(new Publication());
		this.pubListT = null;
	}
	
	
	/** Метод за търсене в БД на публикации по зададените критерии/филтър 
	 * 
	 */
	public void actionFind(){
		 	
		this.pubListT = null;

		SelectMetadata smd = null;
		
		PublicationDAO publDao = new PublicationDAO(this.idUser);
		
		try {
			smd = publDao.findPublFilter(this.dateFrom, this.dateTo, this.codeSection, this.selIdThemas);
			String sortCol = "A3";
			this.pubListT = publDao.newLazyDataModel(smd, sortCol);
		
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages","general.errDataBaseMsg"), e.getMessage());
		} finally {
			JPA.getUtil().closeConnection();
		}
		return;
	}

	public LazyDataModelSQL2Array getPubListT() {
		return pubListT;
	}

	public void setPubListT(LazyDataModelSQL2Array pubListT) {
		this.pubListT = pubListT;
	}

	public void prepareGo(){
		this.pubListT.getResult().clear();
	}
	
	
	
	/** Метод за скалиране/редуциране на размера на изображенията, 
	 * показвани в таблицата на намерените публикации по зададен филтър на търсене в БД 
	 * @param ba
	 * @param proc
	 * @return
	 */
	public StreamedContent createImageCont(byte[] ba, int proc){
		StreamedContent imagCont=null;

		if (null!=ba){
				
	        try {
	        	
				ByteArrayInputStream inS = new ByteArrayInputStream(ba);
	        	BufferedImage img = ImageIO.read(inS);
	        	int h=img.getHeight();
	        	int w=img.getWidth();
	        	h=(img.getHeight()*proc)/100;
	        	w=(img.getWidth()*proc)/100;
	            
	            Image scaledImage = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	            BufferedImage imageBuff = new BufferedImage(w, h, img.getType());
	            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, null);

	            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	            ImageIO.write(imageBuff, "jpeg", buffer);
	            buffer.flush();
	            imageBuff.flush();
	            byte[] resizeBa=buffer.toByteArray();
	            buffer.close();
	        	
	            InputStream imageStream = new ByteArrayInputStream(resizeBa);
	            imagCont = new DefaultStreamedContent(imageStream, "image/jpeg");
	            imageStream.close();
	            
			} catch (Exception e) {
				LOGGER.error(e.getMessage(),e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Грешка при показване на изображение!", e.getMessage());
			}
		}
		return imagCont;
	}

	public Publication getSelectedPubl() {
		return selectedPubl;
	}



	public void setSelectedPubl(Publication selectedPubl) {
		this.selectedPubl = selectedPubl;
	}



	public Long getParam() {
		return param;
	}



	public void setParam(Long param) {
		this.param = param;
	}
	
}
