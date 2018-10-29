package indexbg.pdoi.db.dao;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.TrackableDAO;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.utils.SearchUtils;

import indexbg.pdoi.db.Subscription;

public class SubscriptionDAO extends TrackableDAO<Subscription> {	

	static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionDAO.class);
	
	public SubscriptionDAO (Long userId){
		
		super(userId);		
	}
	
	/** Намира всички абонаменти на потребителя
	 * 
	 * @param userId
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public Map<Long,String> findSubscriptionByUserId(Long userId) throws DbErrorException{
		try {
			
			Query query = createNativeQuery(" select application_id, date_from from pdoi_subscription where user_id = ? "); 
			
			query.setParameter(1, userId);	
			
			List <Object[]> rez = query.getResultList();
			
			Map <Long,String> rezult =new HashMap<Long,String>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			
			for(Object[] item:rez){
				if(item[0]!=null && item[1]!=null){
					String dateTxt =(item[1]!=null?sdf.format(SearchUtils.asDate(item[1])):""); 
					rezult.put(SearchUtils.asLong(item[0]), dateTxt);
				}
			}
			
			return rezult;			
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане абонаментите на потребителя", e);
		}		
	}
	
	/** Намира ид на абонамент по ид на потребител и ид на заявление
	 * 
	 * @param userId
	 * @param appId
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public Long findIdSubscrByUserIdAndAppId(Long userId, Long appId) throws DbErrorException{
		try {
			
			Query query = createNativeQuery(" select id from pdoi_subscription where user_id = ?1 and application_id =?2  "); 
			
			query.setParameter(1, userId);
			query.setParameter(2, appId);
			
			List<Long> rez = query.getResultList();
			if (rez != null && !rez.isEmpty()) {				
				return SearchUtils.asLong(rez.get(0));				
			}
			
			return null;
			
		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане абонаментите на потребителя", e);
		}
		
	}
	
}