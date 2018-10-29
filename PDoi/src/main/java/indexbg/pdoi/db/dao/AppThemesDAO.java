package indexbg.pdoi.db.dao;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.TrackableDAO;
import com.indexbg.system.exceptions.DbErrorException;

import indexbg.pdoi.db.AppThemes;

public class AppThemesDAO extends TrackableDAO<AppThemes> {	

	static final Logger LOGGER = LoggerFactory.getLogger(AppThemesDAO.class);
	
	public AppThemesDAO (Long userId){
		
		super(userId);		
	}
	
	/** Намира всички тематики по ид на заявление
	 * 
	 * @param applicId
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<AppThemes> findByApplicId(Long applicId) throws DbErrorException {

		try {

			Query query = createNativeQuery("select id, theme_value from pdoi_app_themes where application_id = ? order by id", "filterByIdApplic"); 

			query.setParameter(1, applicId);

			return query.getResultList();

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на тематики към заявление", e);
		}

	}
	
}