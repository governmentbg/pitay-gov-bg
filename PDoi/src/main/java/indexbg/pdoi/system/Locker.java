package indexbg.pdoi.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.InvalidParameterException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.exceptions.UnexpectedResultException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.DialectConstructor;
import com.indexbg.system.utils.JSFUtils;

/**
 * Този клас трябва да предостави методите за заключване/отключване на обекти
 * (док.,дела,задачи)
 * 
 * @author krasi
 * 
 */


public class Locker extends PDoiBean implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5520403088715949562L;
	static final Logger LOGGER = LoggerFactory.getLogger(Locker.class);

	/*
	 * Този метод трябва да се извика (но не е задължително) от страниците по
	 * следния начин: <f:actionListener
	 * type="com.indexbg.delo.system.Locker"/></br> Той всъщност ще отключи
	 * всички обекти, заключени от текущият потребител
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.event.ActionListener#processAction(javax.faces.event.ActionEvent
	 * )
	 */
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		try {
			LOGGER.debug("Inside Process Action");
			LOGGER.debug("Current user :" + getUserData().getUserId());
			JPA.getUtil().begin();
			unlockObjects(getUserData().getUserId());
			JPA.getUtil().commit();	

		} catch (ObjectNotFoundException e) {
			JPA.getUtil().rollback();
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, "Не може да се извлече потребителя от сесията. "
					+ "Моля свържете се с администратора!!!", e.getMessage());
		} catch (DbErrorException e) {
			JPA.getUtil().rollback();
			LOGGER.error(e.getMessage(), e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR, getMessageResourceString("beanMessages", "general.errDataBaseMsg"), e.getMessage());
		} finally {
			JPA.getUtil().closeConnection();
		}
	}

	/**
	 * Заключване на обект
	 * 
	 * @param idobject
	 * @param tipObject
	 * @param ud
	 * @param dat
	 * @throws DbErrorException
	 */
	
	public void lockObject(Long idobject, Long tipObject, UserData ud) throws DbErrorException {

		try {

			String textQuery = "INSERT INTO LOCK_OBJECTS(TIP_OBJECT, ID_OBJECT, ID_USER, DAT_LOCK, MORE) VALUES(:TIPO, :IDO, :USERID, :DAT, :MORE)";

			Query slqQuery = JPA.getUtil().getEntityManager().createNativeQuery(textQuery);
					
			slqQuery.setParameter("TIPO", tipObject);
			slqQuery.setParameter("IDO", idobject);
			slqQuery.setParameter("USERID", ud.getUserId());
			slqQuery.setParameter("DAT", new Date());
			slqQuery.setParameter("MORE", ud.getLiceNames());

			slqQuery.executeUpdate();

		} catch (HibernateException e) {

			throw new DbErrorException("Грешка при заключване на обект !");

		}

	}

	/**
	 * Отключване на всички обекти заключени от даден потребител
	 * 
	 * @param idUser
	 * @throws DbErrorException
	 */
	public void unlockObjects(Long idUser) throws DbErrorException {

		try {

			String textQuery = "delete from  LOCK_OBJECTS where ID_USER = :USERID";
			Query slqQuery = JPA.getUtil().getEntityManager().createNativeQuery(textQuery);
			slqQuery.setParameter("USERID", idUser);
			slqQuery.executeUpdate();

		} catch (HibernateException e) {
			throw new DbErrorException("Грешка при отключване на обекти !");

		}

	}

	/**
	 * Отключване на обект
	 * 
	 * @param idObject
	 * @param tipObject
	 * @throws DbErrorException
	 */
	public void unlockObject(Long idObject, Long tipObject)
			throws DbErrorException {

		try {

			String textQuery = "delete from  LOCK_OBJECTS where  TIP_OBJECT = :TIPO AND ID_OBJECT = :IDO";
			Query slqQuery = JPA.getUtil().getEntityManager().createNativeQuery(textQuery);
			slqQuery.setParameter("TIPO", tipObject);
			slqQuery.setParameter("IDO", idObject);

			slqQuery.executeUpdate();

		} catch (HibernateException e) {

			throw new DbErrorException("Грешка при отключване на обект !");

		}

	}
	
	/**
	 * Отключване на обекти
	 * 
	 * @param idObjects
	 * @param tipObject
	 * @throws DbErrorException
	 */
	public void unlockObjectArray(List<Long> idObjects, Long tipObject)
			throws DbErrorException {

		try {

			String textQuery = "DELETE FROM  LOCK_OBJECTS WHERE  TIP_OBJECT = :TIPO AND ID_OBJECT IN (:IDS)";

			Query slqQuery = JPA.getUtil().getEntityManager().createNativeQuery(textQuery);
			slqQuery.setParameter("TIPO", tipObject);
			slqQuery.setParameter("IDS", idObjects);

			slqQuery.executeUpdate();

		} catch (Exception e) {

			throw new DbErrorException(getMessageResourceString("beanMessages", "unLockObject.unLockedErr"));

		}

	}


	/**
	 * Проверка дали обект е заключен
	 * 
	 * @param idObject
	 * @param tipObject
	 * @param idUser
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public String isObjectLocked(Long idObject, Long tipObject, Long idUser)
			throws DbErrorException {

		try {

			String textSQL = " select more from LOCK_OBJECTS where  TIP_OBJECT = :TIPO AND ID_OBJECT = :IDO and ID_USER <> :USERID";

			Query slqQuery = JPA.getUtil().getEntityManager().createNativeQuery(textSQL);
			
			slqQuery.setParameter("TIPO", tipObject);
			slqQuery.setParameter("IDO", idObject);
			slqQuery.setParameter("USERID", idUser);

			ArrayList<String> rez = (ArrayList<String>) slqQuery.getResultList();

			if (rez.size() > 0) {
				return rez.get(0);

			} else
				return null;

		} catch (HibernateException e) {

			throw new DbErrorException(
					"Грешка при проверка за заключен обект !");

		}

	}
	
	public SelectMetadata createFilterLocked(Long codeObject, String uri, Long idUserLock, Date dateFrom ,Date dateTo, Long idLoginUser) throws DbErrorException, UnexpectedResultException ,InvalidParameterException{
		  
		 if (codeObject == null)
			throw new InvalidParameterException("Няма указан код на обект !");
		 if (idLoginUser == null)
				throw new InvalidParameterException("Няма id на администратора/модератор !");
	     
		 String vendorN = JPA.getUtil().getDbVendorName();
		 
		 ArrayList<String> uslovia = new ArrayList<String>();
		 HashMap<String, Object> params = new HashMap<String, Object>();
		 
		 uslovia.add("l.TIP_OBJECT = :codeObject");
		 params.put("codeObject", codeObject);
		 
		 uslovia.add("l.ID_OBJECT  = a.ID");
		 if (null!=idLoginUser){
//			 uslovia.add("l.ID_USER  = :idLoginUser");
			 params.put("idLoginUser", idLoginUser);
		 }
		 
		 
         String sql="";
         String fromStr="";
         String whereStr="";
        
         
         if(codeObject.longValue() == Constants.CODE_OBJECT_APPLICATION.longValue()){
        	String selAnot = "";
     		if (vendorN.indexOf("ORACLE") != -1){
     			selAnot="dbms_lob.substr(a.REQUEST, 300, 1 ) || CASE WHEN dbms_lob.getlength(a.REQUEST)>300  THEN '[...]'  END A4 ";	    		
         	}else if (vendorN.indexOf("POSTGRESQL") != -1){
         		selAnot="substring(a.REQUEST FROM 1 FOR 300) || CASE WHEN length(a.REQUEST) > 300 THEN '[...]' ELSE '' END A4 ";
         	}else{
         		selAnot="a.REQUEST A4";  
         	}
        	
     		sql = " SELECT a.ID A1, a.APPLICATION_URI A2, a.REGISTRATION_DATE A3, "+ selAnot + ", l.DAT_LOCK A5, u.NAMES A6, u.USERNAME A7 ";
        			
     		fromStr = " FROM PDOI_APPLICATION a, LOCK_OBJECTS l JOIN ADM_USERS u ON l.ID_USER IN (SELECT ui.USER_ID FROM ADM_USERS ui WHERE ui.ORG_CODE=u.ORG_CODE AND u.USER_ID = :idLoginUser) ";
        	
     		if (uri != null && uri.trim().length() > 0){
             	uslovia.add("a.APPLICATION_URI = :uri");
             	params.put("uri", uri.trim().toUpperCase());
        	}
            
        	
         } else if(codeObject.longValue() == Constants.CODE_OBJECT_PUBLICATION.longValue()){
        	 
         } else if(codeObject.longValue() == Constants.CODE_OBJECT_SUBSCRIPTION.longValue()){
        	 
         }
         
         if(dateFrom != null){
  			dateFrom = DateUtils.startDate(dateFrom);
          	uslovia.add("l.DAT_LOCK >= "+DialectConstructor.convertDateToSQLString(vendorN ,dateFrom));
     	 }
          
         if(dateTo != null){
          	dateTo = DateUtils.endDate(dateTo); 
          	uslovia.add("l.DAT_LOCK <= "+DialectConstructor.convertDateToSQLString(vendorN,dateTo));
         }
         
         if(idUserLock!=null){
        	uslovia.add("l.ID_USER =:idUserLock");
        	params.put("idUserLock", idUserLock);
         }
         
         
         whereStr=" WHERE "; 
         if (uslovia.size() > 0) {
              for (int i = 0; i < uslovia.size(); i++)  {
            	  whereStr += uslovia.get(i);
                  if (i != (uslovia.size() - 1)) whereStr += " AND ";
              }
          }
         
 		 
 		 SelectMetadata smd = new SelectMetadata();
 		 sql+=fromStr+whereStr;
 		 smd.setSql(sql);
 		 
		 String sqlQueryCount = "SELECT COUNT(DISTINCT a.ID) as counter " + fromStr+whereStr;
		 smd.setSqlCount(sqlQueryCount);
		 smd.setSqlParameters(params);

		return smd;
	}
	
	/** Извлича данни от БД по зададен sql и атрибут за сортиране на данните
	 * @param smd - sql за избор на данни 
	 * @param defaultSortColumn - колона от таблица в базата данни, по която се сортират данните
	 * @return
	 * @throws DbErrorException
	 */
	public LazyDataModelSQL2Array newLazyDataModel (SelectMetadata smd, String defaultSortColumn ) throws DbErrorException { 
		   
		if (smd == null) return null;
		 LazyDataModelSQL2Array list = null;
		try {
							
			list = new LazyDataModelSQL2Array(smd, defaultSortColumn);  
				
		} catch (DbErrorException e) { 
			LOGGER.error(e.getMessage(), e);
			throw new DbErrorException ("Грешка при търсене на записи от БД!" +e.getCause().getMessage(), e);
		}
	
		return list;
	}
	
	
}