package indexbg.pdoi.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.SysConstants;
import com.indexbg.system.db.AbstractDAO;
import com.indexbg.system.db.JPA;
import com.indexbg.system.db.TrackableDAO;
import com.indexbg.system.utils.SearchUtils;

import indexbg.pdoi.db.Event;

/**
 * @author kosev
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEventDAO {


	/** */
	@Before
	public void setUp() {
		
	}

	/** */
	@Test
	public void test_01_SaveAis() {
		try {
			
			Query query = JPA.getUtil().getEntityManager().createNativeQuery("select id, application_id, event_type, event_date, add_text from pdoi_event where application_id = ? and event_type = ? order by event_date "); 
			
			query.setParameter(1, 103);	
			query.setParameter(2, 3);	
			for (int i = 0; i < query.getResultList().size(); i++) {
				
				List<Object[]> obj = query.getResultList();
				
					Object[] tmpEvent = obj.get(i);
					Event e = new Event();
					e.setId(Long.valueOf(tmpEvent[0].toString()));
					e.setAddText((String)tmpEvent[4]);
					e.setEventDate((Date)tmpEvent[3]);
				
				/*Event e = (Event) query.getResultList().get(i);*/
				System.out.println(e.getId());
				System.out.println(e.getAddText());
				System.out.println(e.getEventDate());
			}

		} catch (Exception e) {
			JPA.getUtil().rollback();
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		} finally {
			JPA.getUtil().closeConnection();
		}
	}
}
