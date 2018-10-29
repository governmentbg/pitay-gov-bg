package indexbg.pdoi.db.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.db.TrackableDAO;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DialectConstructor;
import com.indexbg.system.utils.SearchUtils;
import com.indexbg.system.utils.StringUtils;

import indexbg.pdoi.db.Application;
import indexbg.pdoi.system.Constants;

public class ApplicationDAO extends TrackableDAO<Application> {	

	static final Logger LOGGER = LoggerFactory.getLogger(ApplicationDAO.class);
	
	public ApplicationDAO (Long userId){
		
		super(userId);		
	}
	
	/** Ъпдейтва определени полета на заявлението спрямо подадените параметри
	 * 
	 * @param responseSubject
	 * @param userLastMod
	 * @param dateLastMod
	 * @param id
	 * @throws DbErrorException
	 */
	public void updateResponseFromEvent(Long responseSubject, Long userLastMod, Date dateLastMod, Long id) throws DbErrorException {
		
		try {

			Query query = createNativeQuery("update pdoi_application set response_subject_id = ?, user_last_mod = ?, date_last_mod = ? ,status = ?, status_date = ? where id = ?"); 
			
			query.setParameter(1, responseSubject);			
			query.setParameter(2, userLastMod);
			query.setParameter(3, dateLastMod);
			query.setParameter(4, Constants.CODE_ZNACHENIE_STATUS_APP_EXPECTED_REG);
			query.setParameter(5, new Date());
			query.setParameter(6, id);

			query.executeUpdate();

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при ъпдейтване на задълженото лице на заявлението от събитието!!!", e);
		}
		
	}

	/** Ъпдейтва статуса и други полета на заявлението спрямо подадените параметри
	 * 
	 * @param status
	 * @param dateStatus
	 * @param userLastMod
	 * @param dateLastMod
	 * @param id
	 * @throws DbErrorException
	 */
	public void updateStatusFromEvent(Long status, Date dateStatus, String reshenie, Long userLastMod, Date dateLastMod, Long id, Long appIdForView) throws DbErrorException {
	
		try {
	
			Query query = createNativeQuery("update pdoi_application set status = ?1, status_date = ?2, response_date = ?2, response = ?3, user_last_mod = ?4, date_last_mod = ?5, app_id_for_view = ?7 where id = ?6"); 
			
			query.setParameter(1, status);
			query.setParameter(2, dateStatus);
			query.setParameter(3, reshenie);
			query.setParameter(4, userLastMod);
			query.setParameter(5, dateLastMod);
			query.setParameter(6, id);
			query.setParameter(7, appIdForView);
	
			query.executeUpdate();
	
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при ъпдейтване на статуса на заявлението от събитието!!!", e);
		}	
	}

	/** Ъпдейтва крайната дата и други полета на заявлението спрямо подадените параметри
	 * 
	 * @param responseEndTime
	 * @param userLastMod
	 * @param dateLastMod
	 * @param id
	 * @throws DbErrorException
	 */
	public void updateResponseEndDate(Date responseEndTime, Long userLastMod, Date dateLastMod, Long id) throws DbErrorException {
	
		try {
	
			Query query = createNativeQuery("update pdoi_application set response_end_time = ?, user_last_mod = ?, date_last_mod = ? where id = ?"); 
			
			query.setParameter(1, responseEndTime);			
			query.setParameter(2, userLastMod);
			query.setParameter(3, dateLastMod);
			query.setParameter(4, id);
	
			query.executeUpdate();
	
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при ъпдейтване на крайния срок на заявлението от събитието!!!", e);
		}
	
	}
	
	/** Ъпдейтва registrationDate, responseEndTime, статуса и други полета на заявлението спрямо подадените параметри
	 * 
	 * @param responseDate
	 * @param responseEndTime
	 * @param status
	 * @param dateStatus
	 * @param userLastMod
	 * @param dateLastMod
	 * @param id
	 * @throws DbErrorException
	 */
	public void updateAppWithEventConfirm(Date registrationDate, Date responseEndTime, Long status, Date dateStatus, Long userLastMod, Date dateLastMod, Long id) throws DbErrorException {
	
		try {
	
			Query query = createNativeQuery("update pdoi_application set registration_date = ?, response_end_time = ?, status = ?, status_date = ?, user_last_mod = ?, date_last_mod = ? where id = ?"); 
			
			query.setParameter(1, registrationDate);	
			query.setParameter(2, responseEndTime);	
			query.setParameter(3, status);
			query.setParameter(4, dateStatus);
			query.setParameter(5, userLastMod);
			query.setParameter(6, dateLastMod);
			query.setParameter(7, id);
	
			query.executeUpdate();
	
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при ъпдейтване на заявлението от събитието за потвърждаване от деловодна система!!!", e);
		}
	
	}

	/** Изграждане на SQL за извличане на заявления по зададен филтър
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param status
	 * @param responseSubj
	 * @param text
	 * @param nomer
	 * @param selectedThemas
	 * @param userID
	 * @param userIDlock // for lock checking
	 * @return
	 */
	public SelectMetadata findApplications(Date dateFrom, Date dateTo, Long status, Long responseSubj, String text, String nomer,List<Long> selectedThemas,Long userID,boolean fromAdmin,boolean searchDateReg,Long userIDlock) {
	
		StringBuffer sql = new StringBuffer();
		 
		sql.append( " SELECT a.id as A0,"  
				+ " a.application_uri as A1,"
				+ " a.registration_date as A2,"
				+ " a.response_subject_id as A3," 
				+ " CASE WHEN LENGTH(a.request)>200 THEN  SUBSTR(a.request,1,200) || '...' ELSE request END as A4,"
				+ " a.status as A5,"
				+ " CASE  WHEN a.number_of_visits is null THEN 0 else a.number_of_visits end AS A6, "
				+ " a.date_reg as A7 ");
				if (null!=userIDlock)
					sql.append(", l.more as A8");

		String from = " FROM pdoi_application a ";
				if (null!=userIDlock)
					from+= " left outer join LOCK_OBJECTS l on a.id = l.ID_OBJECT and  l.TIP_OBJECT = "+Constants.CODE_OBJECT_APPLICATION+" and l.ID_USER <> "+userIDlock;
		
		List<String> whereStr = new ArrayList<String>();
		
		String vendorName = JPA.getUtil().getDbVendorName();
		
		if(searchDateReg) {
			if(dateFrom !=null) {
				whereStr.add(" a.date_reg >= " + DialectConstructor.convertDateToSQLString(vendorName,dateFrom));			
			}
			
			if(dateTo !=null) {
				whereStr.add(" a.date_reg <= " + DialectConstructor.convertDateToSQLString(vendorName,dateTo));			
			}
		}else {
			if(dateFrom !=null) {
				whereStr.add(" a.registration_date >= " + DialectConstructor.convertDateToSQLString(vendorName,dateFrom));			
			}
			
			if(dateTo !=null) {
				whereStr.add(" a.registration_date <= " + DialectConstructor.convertDateToSQLString(vendorName,dateTo));			
			}
		}		
		if(status !=null) {
			whereStr.add(" a.status = " + status);			
		}
		
		if(responseSubj !=null) {
			whereStr.add(" a.response_subject_id = " + responseSubj);			
		}
		
		if(nomer !=null && !"".equals(nomer)) {
			whereStr.add(" a.application_uri LIKE '" +  nomer + "%'" );			
		}
		
		if (null!=selectedThemas && !selectedThemas.isEmpty()){
	    	from +=" JOIN pdoi_app_themes at ON (a.ID=at.application_id) ";
	    	whereStr.add("at.theme_value IN ("+selectedThemas.toString().trim().substring(1, selectedThemas.toString().trim().length()-1)+")");
	    }
		
		if(userID!=null) {
			whereStr.add(" a.user_reg = " + userID);	
		}
		
		if(fromAdmin) {
			whereStr.add(" a.response_date is null " );	
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
		
       if(text!=null && !"".equals(text)) {
    	   if (!whereStr.isEmpty()) {
    		   strWhere +=" AND ";
    	   }else {
    		   strWhere+=" WHERE ";
    	   }
    	   strWhere +="(";
    	  ArrayList<String> str=  StringUtils.divideString(text);
			
			if(str!=null&&str.size()>0) {
				for(int i= 0;i<str.size();i++) {
					strWhere += " UPPER(TRIM(a.request)) LIKE '%" +  str.get(i).toUpperCase() + "%' " ;		
					if(i<str.size()-1) {
						strWhere+=" AND ";
					}
				}
			}
			strWhere +=")";
		}

		sql.append(from);
		sql.append(strWhere); 		
		SelectMetadata smd = new SelectMetadata();	
		smd.setSql(sql.toString());
		smd.setSqlCount("SELECT COUNT(distinct a.ID) as counter  "+ from + strWhere);			
		return smd;
	}
	
	/**Изграждане на SQL за извличане на заявления,за които потребителя е абониран.
	 * @param userID
	 * @return
	 */
	public SelectMetadata findSubscriptions(Long userID) {
		
		StringBuffer sql = new StringBuffer();
		 
		sql.append( " SELECT a.id as A0,"  
				+ " a.application_uri as A1,"
				+ " a.registration_date as A2,"
				+ " a.response_subject_id as A3," 
				+ " CASE WHEN LENGTH(a.request)>200 THEN  SUBSTR(a.request,1,200) || '...' ELSE request END as A4,"
				+ " a.status as A5,"
				+ " CASE  WHEN a.number_of_visits is null THEN 0 else a.number_of_visits end AS A6, "
				+ " a.date_reg as A7 ");
		String from = " FROM pdoi_application a join pdoi_subscription s on(a.id = s.application_id)";
		List<String> whereStr = new ArrayList<String>();
		
		
		
		
		if(userID!=null) {
			whereStr.add(" s.user_id = " + userID);	
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
		sql.append(from);
		sql.append(strWhere); 
		
		SelectMetadata smd = new SelectMetadata();
		
		smd.setSql(sql.toString());	
		smd.setSqlCount("SELECT COUNT(distinct a.ID) as counter  "+ from + strWhere);			
		return smd;
	}
		
	/** По Ури на заявление връща обекта
	 * 
	 * @param uri
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public Application findByURI(String uri) throws DbErrorException {
		
		try {

			Query query = JPA.getUtil().getEntityManager().createQuery("FROM Application а WHERE а.applicationUri = :urii");

			query.setParameter("urii", uri);

			List<Application> applics = query.getResultList();

			if (applics.size() > 0) {
				return applics.get(0);
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при извличане на заявление по ури!!!", e);
		}
		
	}	
	
	/** Генерира УРИ на заявление, което се нулира всяка година
	 * 
	 * @param year
	 * @return
	 */
	public Long generateUri(Long year) throws DbErrorException{
			
		Query select = JPA.getUtil().getEntityManager().createNativeQuery("SELECT uri FROM pdoi_uri WHERE uri_year = ?");
		select.setParameter(1, year);
	
		Long index = null;
		try {
			
			@SuppressWarnings("rawtypes")
			List results = select.getResultList();
			
			if(results.size() == 0) { // създава се нов запис; uri за начало е 1
				index = 1L;
				
				// параметри: ID, URI, YEAR
				String insertString = "INSERT INTO pdoi_uri VALUES(nextval('seq_uri'), 1, ?)";
				Query insert = JPA.getUtil().getEntityManager().createNativeQuery(insertString);
				insert.setParameter(1, year);
				
				EntityTransaction transaction = JPA.getUtil().getEntityManager().getTransaction();
				transaction.begin();
				insert.executeUpdate();
				transaction.commit();
				
			} else { // ъпдейтва се наличният запис; uri се увеличава и се връща
				BigDecimal currentIndex = (BigDecimal) results.get(0);
				index = currentIndex.longValue() + 1;
				String updateString = "UPDATE pdoi_uri SET uri = ? WHERE uri_year = ?";
				Query update = JPA.getUtil().getEntityManager().createNativeQuery(updateString);
				update.setParameter(1, BigDecimal.valueOf(index));
				update.setParameter(2, year);
				
				EntityTransaction transaction = JPA.getUtil().getEntityManager().getTransaction();
				transaction.begin();
				update.executeUpdate();
				transaction.commit();
			}
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при извличане на ури!!!", e);
		}

		return index;
	}
	
	public void updateNumberOfVisits(Long id,Long numberVusits) throws DbErrorException {
		
		try {

			Query query = createNativeQuery("update pdoi_application set number_of_visits =? where id = ?"); 
			
			query.setParameter(1, numberVusits+1);
			query.setParameter(2, id);			
	
			query.executeUpdate();

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при увеличаване на броя посещения на заявление!!!", e);
		}		
	}
	
	public void updateLike(Long id, Long usefulness) throws DbErrorException {
		
		try {

			Query query = createNativeQuery("update pdoi_application set usefulness = ? where id = ?"); 
			
			query.setParameter(1, usefulness + 1);
			query.setParameter(2, id);			
	
			query.executeUpdate();

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при увеличаване на броя харесвания на заявление!!!", e);
		}		
	}
	
		
	/** Намира ид на заявление по подадено ури на заявление
	 * @param uri
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public Long findAppIdByUri(String uri) throws DbErrorException{
		try {
			
			Query query = createNativeQuery(" select id from pdoi_application where application_uri = ?1 "); 
			
			query.setParameter(1, uri);			
			
			List<Long> rez = query.getResultList();
			if (rez != null && !rez.isEmpty()) {				
				return SearchUtils.asLong(rez.get(0));				
			}
			
			return null;
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при търсене ид на заявление по ури!", e);
		}
		
	}
	
}
