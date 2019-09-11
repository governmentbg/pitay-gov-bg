package indexbg.pdoi.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.db.TrackableDAO;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.pagination.LazyDataModelSQL2Array;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DialectConstructor;

import indexbg.pdoi.db.Publication;
import indexbg.pdoi.system.Constants;

/**
 * @author ivanc
 *
 */

public class PublicationDAO extends TrackableDAO<Publication> {

	static final Logger LOGGER = LoggerFactory.getLogger(PublicationDAO.class);
	
	public PublicationDAO (Long userId){
		
		super(userId);		
	}
	
	/**Изгражда sql за извличане от БД на обекти/публикации по зададен филтър. Задължително включва/връща и ид. на обектите. 
	 * @param Date - dateFrom - начална дата на публикацията 
	 * @param Date - dateTo - крайна дата на публикацията
	 * @param Long codeSection - код на секция
	 * @param List<Long> selIdThemas - кодове на теми
	 * @return SelectMetadata - sql със сетнати параметри. 
	 */
	public SelectMetadata findPublFilter(Date dateFrom, Date dateTo, Long codeSection, List<Long> selIdThemas){
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer();
		List<String> whereStr = new ArrayList<String>();
		String vendorN = JPA.getUtil().getDbVendorName();
		String selAnot = "";
		
		if (vendorN.indexOf("ORACLE") != -1){
			selAnot="dbms_lob.substr(pub.ANNOTATION, 300, 1 ) || CASE WHEN dbms_lob.getlength(pub.ANNOTATION)>300  THEN '[...]'  END as A4 ";	    		
    	}else if (vendorN.indexOf("POSTGRESQL") != -1){
    		selAnot="substring(pub.ANNOTATION FROM 1 FOR 300) || CASE WHEN length(pub.ANNOTATION) > 300 THEN '[...]' ELSE '' END  as A4 ";
    	}else{
    		selAnot="pub.ANNOTATION A4";  
    	}
			
		sql.append("SELECT DISTINCT pub.ID A0, pub.SECTION A1, pub.TITLE A2, pub.DATE_FROM A3, "+ selAnot + ", pub.IMAGE_PUB A5 ");
		
		String fromStr = " FROM PDOI_PUBLICATION pub ";

		if (null!=selIdThemas && !selIdThemas.isEmpty()){
	    	fromStr +=" JOIN PDOI_PUB_THEMES them ON (pub.ID=them.PUBLICATION_ID) ";
	    	whereStr.add("them.THEM_VALUE IN ("+selIdThemas.toString().trim().substring(1, selIdThemas.toString().trim().length()-1)+")");
	    }
		
		if(null!=codeSection){
	    	whereStr.add("pub.SECTION=:codeSection");
	    	params.put("codeSection", codeSection); 
		}
	    
	    
	    if (null!=dateFrom && null!=dateTo){
	    	whereStr.add("pub.DATE_FROM BETWEEN "+DialectConstructor.convertDateOnlyToSQLString(vendorN, dateFrom)+ " AND "+ DialectConstructor.convertDateOnlyToSQLString(vendorN, dateTo));
		}else{
			if (null!=dateFrom && null==dateTo){
				whereStr.add("pub.DATE_FROM >="+DialectConstructor.convertDateOnlyToSQLString(vendorN, dateFrom));
			}else if(null==dateFrom && null!=dateTo){
				whereStr.add("pub.DATE_TO <="+DialectConstructor.convertDateOnlyToSQLString(vendorN, dateTo));
			}
			
		}	
			
	    sql.append(fromStr);
	    
	    String strWhere=""; 
	    
		if (!whereStr.isEmpty()) {
			strWhere+=" WHERE ";
			for (int i = 0; i < whereStr.size(); i++) {	
				strWhere+=whereStr.get(i);
				if (i != (whereStr.size() - 1)) {
					strWhere+=" AND ";
				}
			}
		}
		sql.append(strWhere); 
		
		SelectMetadata smd = new SelectMetadata();
		smd.setSql(sql.toString());
		smd.setSqlCount("SELECT COUNT(distinct pub.id) as counter  "+fromStr+strWhere);
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
	
	
	/**Изгражда sql за извличане от БД на обекти/публикации по код на секцията и текущата дата, към която секцията е актуална.
	 * @param dat - Date - текуща дата
	 * @param codeSection - код на секцията
	 * @return
	 */
	public SelectMetadata findSectFilter(Date dat, Long codeSection){
		StringBuffer sql = new StringBuffer();
		List<String> whereStr = new ArrayList<String>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		String vendorN = JPA.getUtil().getDbVendorName();
		
		sql.append("SELECT DISTINCT pub.ID A0, pub.SECTION A1, pub.TITLE A2, pub.ANNOTATION A3 ");
		
		String fromStr = " FROM PDOI_PUBLICATION pub ";
		
		if(null!=codeSection){
	    	whereStr.add("pub.SECTION=:codeSection");
	    	params.put("codeSection", codeSection); 
		}
	    
	    
	    if (null!=dat){
	    	whereStr.add("pub.DATE_FROM <="+DialectConstructor.convertDateOnlyToSQLString(vendorN, dat)+ 
	    				" AND (pub.DATE_TO IS NULL OR pub.DATE_TO >= "+DialectConstructor.convertDateOnlyToSQLString(vendorN, dat)+")");
		}	
			
	    sql.append(fromStr);
	    
	    String strWhere=""; 
	    
		if (!whereStr.isEmpty()) {
			strWhere+=" WHERE ";
			for (int i = 0; i < whereStr.size(); i++) {	
				strWhere+=whereStr.get(i);
				if (i != (whereStr.size() - 1)) {
					strWhere+=" AND ";
				}
			}
		}
		sql.append(strWhere); 
		
		SelectMetadata smd = new SelectMetadata();
		smd.setSql(sql.toString());
		smd.setSqlCount("SELECT COUNT(distinct pub.id) as counter  "+fromStr+strWhere);
		smd.setSqlParameters(params);
		
		return smd;
	
	
	}

	
	/** Извлича прикачените файлове към дадена секция
	 * @param dat - Date - текуща дата, към която секцията е актуална
	 * @param codeSection - Long - код на секцията 
	 * @param codeObjPubl - код на обекта/секцията
	 * @return
	 * @throws DbErrorException
	 */
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> findRelPublFiles(Date dat, Long codeSection, Long codeObjPubl) throws DbErrorException{
		
		String vendorN = JPA.getUtil().getDbVendorName();
		
		String sqlSel="SELECT pub.ID, COUNT(f.ID) "+    
			"FROM "+ 
			"PDOI_PUBLICATION pub "+ 
			"JOIN FILES f ON (pub.ID = f.ID_OBJECT AND f.CODE_OBJECT=?) "+ 
			"WHERE "+ 
			"pub.SECTION=? AND pub.DATE_FROM <="+DialectConstructor.convertDateOnlyToSQLString(vendorN, dat)+" "+
			"AND (pub.DATE_TO IS NULL OR pub.DATE_TO >="+DialectConstructor.convertDateOnlyToSQLString(vendorN, dat)+") "+
			"GROUP by pub.ID";
		
		try{
			Query query = createNativeQuery(sqlSel); 

			query.setParameter(1, codeObjPubl);
			query.setParameter(2, codeSection);
			
			return query.getResultList();
		
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на данни от БД", e);
		}
		
	}*/
	
	
	/** Извлича от БД само анотацията на дадена публикация
	 * @param id - по ид. на публикацията
	 * @return - връща анотацията 
	 * @throws DbErrorException
	 */
	/*@SuppressWarnings("unchecked")
	public List<Object[]> findAnnotById(Long id)  throws DbErrorException {
		
		String sqlSel="SELECT pub.ANNOTATION "+    
				"FROM "+ 
				"PDOI_PUBLICATION pub "+ 
				"WHERE "+ 
				"pub.ID=?";
			
			try{
				Query query = createNativeQuery(sqlSel); 

				query.setParameter(1, id);
				
				return query.getResultList();
			
			} catch (Exception e) {
				throw new DbErrorException("Възникна грешка при зареждане на данни от БД", e);
			}
	
	}*/
	
	
	/** Извлича от БД на данни за контакт на администраторите 
	 * @param orgCode - код на службата но администратора
	 * @param userType - код на потребител тип администратор
	 * @return 
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAdminDataByOrgCode(Long orgCode, List<Long> userType)  throws DbErrorException {
		
		String sqlSel="SELECT au.USER_ID, au.NAMES, au.EMAIL, au.PHONE, au.USER_TYPE, rs.ADDRESS "+    
				"FROM "+ 
				"ADM_USERS au "+
				"LEFT OUTER JOIN PDOI_RESPONSE_SUBJECT rs ON (au.ORG_CODE = rs.ID) "+ 
				"WHERE "+ 
				" au.STATUS = ? "+
				" and au.ORG_CODE = ? "+
				"AND au.USER_TYPE IN ("+userType.toString().trim().substring(1, userType.toString().trim().length()-1)+") "+
				"ORDER BY au.NAMES ";
			
			try{
				Query query = createNativeQuery(sqlSel); 
				query.setParameter(1, Constants.CODE_ZNACHENIE_STATUS_POTREB_ACTIVEN);
				query.setParameter(2, orgCode);
			
				
				return query.getResultList();
			
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DbErrorException("Възникна грешка при четене на данни от БД", e);
			}
	
	}
	
	/**
	 * 
	 * @param classifCode
	 * @param roleCode
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAdminData(Long classifCode, Long roleCode) throws DbErrorException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT au.names, au.email, au.phone ");
		sb.append("FROM adm_users au ");
		sb.append("JOIN adm_user_roles aur ON ");
		sb.append("aur.user_id = au.user_id AND aur.code_classif = ? and aur.code_role = ? and au.status =?");
		sb.append("UNION ");
		sb.append("SELECT au2.names, au2.email, au2.phone ");
		sb.append("FROM adm_users au2 ");
		sb.append("JOIN adm_user_group aug on au2.user_id = aug.user_id ");
		sb.append("JOIN adm_group_roles agr on agr.group_id = aug.group_id AND agr.code_classif = ? and agr.code_role = ? and au2.status =?");
		
		
		
		try{
			Query query = createNativeQuery(sb.toString()); 
			query.setParameter(1, classifCode);
			query.setParameter(2, roleCode);
			query.setParameter(3, Constants.CODE_ZNACHENIE_STATUS_POTREB_ACTIVEN);
			query.setParameter(4, classifCode);
			query.setParameter(5, roleCode);
			query.setParameter(6, Constants.CODE_ZNACHENIE_STATUS_POTREB_ACTIVEN);
			
			return (List<Object[]>) query.getResultList();
		
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DbErrorException("Възникна грешка при четене на данни от БД", e);
		}
	}
	
	
	/** Извлича от БД на EMAIL на администратора, по неговия код на службата
	 * @param orgCode - код на службата
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAdminEmailByOrgCode(Long orgCode)  throws DbErrorException {
		
		String sqlSel="SELECT au.USER_ID, au.EMAIL FROM ADM_USERS au WHERE au.ORG_CODE = ? ";
			
			try{
				Query query = createNativeQuery(sqlSel); 

				query.setParameter(1, orgCode);
			
				return query.getResultList();
			
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new DbErrorException("Възникна грешка при четене на данни от БД", e);
			}
	
	}
	
	/** Извлича от БД на динамичен текст за началната страница на портала
	 * @param Long codeSectHome - код на секция Начален екран
	 * @param dat - Date - текуща дата
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Publication> loadHomeData(Long codeSectHome, Date dat) throws DbErrorException {
		
		StringBuffer sql = new StringBuffer();
		List<String> whereStr = new ArrayList<String>();
		String vendorN = JPA.getUtil().getDbVendorName();

		if(null!=codeSectHome){
	    	whereStr.add("pub.section=:sectH");
		}

	    if (null!=dat){
	    	whereStr.add("pub.dateFrom <="+DialectConstructor.convertDateOnlyToSQLString(vendorN, dat)+ 
	    				" AND (pub.dateTo IS NULL OR pub.dateTo >= "+DialectConstructor.convertDateOnlyToSQLString(vendorN, dat)+")");
		}	

	    String strWhere=""; 
	    
		if (!whereStr.isEmpty()) {
			strWhere+=" WHERE ";
			for (int i = 0; i < whereStr.size(); i++) {	
				strWhere+=whereStr.get(i);
				if (i != (whereStr.size() - 1)) {
					strWhere+=" AND ";
				}
			}
		}
		sql.append("FROM Publication pub ");
		sql.append(strWhere); 
		
		try {
			
			Query query = JPA.getUtil().getEntityManager().createQuery(sql.toString());

			query.setParameter("sectH", codeSectHome);

			List<Publication> publ = query.getResultList();

			if (null!=publ && publ.size() > 0) {
				return publ;
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при извличане на текст Начална страница!!!", e);
		}
	}
	
	
}
