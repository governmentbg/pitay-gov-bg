package indexbg.pdoi.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.db.dto.SystemClassif;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;
import com.indexbg.system.utils.TreeUtils;

import indexbg.pdoi.db.ResponseSubject;
import indexbg.pdoi.db.dao.PublicationDAO;
import indexbg.pdoi.db.dao.ResponseSubjectDao;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.PDoiBean;

@ManagedBean(name = "sectCont")
@ViewScoped
public class SectionContacts  extends PDoiBean{

	/**
	 * Основен javaBean клас за показване контактите за връзка с администраторите на системата
	 */
	private static final long serialVersionUID = -4711806513995908452L;
	static final Logger LOGGER = LoggerFactory.getLogger(SectionContacts.class);
	private TreeNode subectRootNode; 
	private TreeNode subectSelectedNode;
	private Long idUser;
	private Long codeSection=null;
	private Long codeAdmStruct=null;
	private String nameAdmStruct;
	private List<Object[]> admDataList=null;
	private List<Object[]> admins = new ArrayList<Object[]>();
	
	private String searchWordClass;
	private Object admAdress="";
	
	
	@PostConstruct
	public void initData(){
		try{
			this.idUser=getUserData().getUserId();
		} catch (ObjectNotFoundException e) {

			this.idUser = -1L;
		}
		this.codeSection=Constants.CODE_ZNACHENIE_SECT_CONTACTS;
		
		this.admDataList = new ArrayList<Object[]>();
		findAdmins();
		
	}
	
	
	
	/**Метод за динамично създаване на дърво с административните структури 
	 * @return
	 */
	public TreeNode getSubectRootNode() {
		if(subectRootNode==null){
			
			try {
				
				List<SystemClassif> listItems =  getSystemData().getSysClassification(Constants.CODE_SYSCLASS_ADM_REGISTRY, new Date(), getCurrentLang(), this.idUser);
				
				subectRootNode = new TreeUtils().loadTreeData3(listItems, "", false,false ,null ,null);
				
			} catch (DbErrorException e) {
				LOGGER.error(e.getMessage(),e);
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"),e.getMessage());
			} catch (Exception e) {
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages,"general.unexpectedResult"),e.getMessage());
				LOGGER.error(e.getMessage(),e);
			} finally {
				JPA.getUtil().closeConnection();
			}
		}
		return subectRootNode;
	}


	public void setSubectRootNode(TreeNode subectRootNode){
		this.subectRootNode = subectRootNode;
	}


	public TreeNode getSubectSelectedNode() {
		return subectSelectedNode;
	}


	public void setSubectSelectedNode(TreeNode subectSelectedNode) {
		this.subectSelectedNode = subectSelectedNode;
	}

	public String getSearchWordClass() {
		return searchWordClass;
	}

	public void setSearchWordClass(String searchWordClass) {
		this.searchWordClass = searchWordClass;
	}


	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getCodeSection() {
		return codeSection;
	}
	
	
	/**Метод за търсене на определена административна структура по наименование
	 * @throws DbErrorException
	 */
	public void search() throws DbErrorException{
		
		LOGGER.info("Searching for classif with: " + getSearchWordClass());
		try {
			
			List<SystemClassif> classifList =  getSystemData().getSysClassification(Constants.CODE_SYSCLASS_ADM_REGISTRY, new Date(), getCurrentLang(), this.idUser);
			TreeNode rootNode = new TreeUtils().fTree(classifList, getSearchWordClass(), true, true ,null ,null);
			setSubectRootNode(rootNode);
			
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString(Constants.beanMessages, "general.errDataBaseMsg"), e.getMessage());
		} finally {
			JPA.getUtil().closeConnection();
		}
	}
	
	public void refreshTree() throws DbErrorException{ 
		setSearchWordClass(null);
		subectRootNode =null;
		getSubectRootNode();
	}
	
	/**Метод за извличане на код на службата на администратор
	 * @param event
	 */
	public void onNodeSelect(NodeSelectEvent event) {
		SystemClassif item = (SystemClassif) event.getTreeNode().getData();
		this.admAdress="";
		if (null!=item){
			this.codeAdmStruct=Long.valueOf(item.getCode());
			this.nameAdmStruct = item.getTekst();
		
		}
		
		if (null!=this.codeAdmStruct)
			findAdmData();
    }
 
    public void onNodeUnselect(NodeUnselectEvent event) {
    	this.codeAdmStruct=null;
    }



	public Long getIdAdmStruct() {
		return codeAdmStruct;
	}

	public void setCodeAdmStruct(Long codeAdmStruct) {
		this.codeAdmStruct = codeAdmStruct;
	}
	
	/**Метод за търсене от БД на адресни данни на администраторите по зададен код на службата
	 * @param event
	 */
	public void findAdmData(){
		List<Long> userTypes=new ArrayList<Long>();
		userTypes.add(Long.valueOf(Constants.CODE_ZNACHENIE_TIP_POTR_VATR));
//		userTypes.add(Long.valueOf(Constants.CODE_ZNACHENIE_TYPE_USER_MODERATOR));
		
		try{
			
			this.admDataList=new PublicationDAO(this.idUser).findAdminDataByOrgCode(this.codeAdmStruct,userTypes);
			
			ResponseSubject adrData=new ResponseSubjectDao(this.idUser).findById(this.codeAdmStruct);
			if(null!=adrData)
				this.admAdress=adrData.getAddress();
			
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(),e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Грешка при търсене в базата данни! " + "-" + e.getMessage() + e.getCause().getMessage() );
		} finally {
			JPA.getUtil().closeConnection();
		}
	
	}

	public void findAdmins() {
		
		try {
			List<Object[]> l = new PublicationDAO(this.idUser).findAdminData(7L, 42L);
			this.setAdmins(new PublicationDAO(this.idUser).findAdminData(7L, 42L));
		} catch (DbErrorException e) {
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR,
					"Грешка при търсене в базата данни! " + "-" + e.getMessage() + e.getCause().getMessage());
		} finally {
			JPA.getUtil().closeConnection();
		}
	}

	public List<Object[]> getAdmDataList() {
		return admDataList;
	}

	public void setAdmDataList(List<Object[]> admDataList) {
		this.admDataList = admDataList;
	}

	public List<Object[]> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Object[]> admins) {
		this.admins = admins;
	}

	public String getNameAdmStruct() {
		return nameAdmStruct;
	}



	public void setNameAdmStruct(String nameAdmStruct) {
		this.nameAdmStruct = nameAdmStruct;
	}



	public Object getAdmAdress() {
		return admAdress;
	}



	public void setAdmAdress(Object admAdress) {
		this.admAdress = admAdress;
	}


	
}
