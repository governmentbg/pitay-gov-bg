package indexbg.pdoi.db;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import org.junit.Test;

import com.indexbg.system.db.JPA;

public class Test123 {

	@Test
	public void test() {
		try {
				
    		
    		
    		
    		
    		//Области
    		Query q = JPA.getUtil().getEntityManager().createNativeQuery("select * from application");
    		System.out.println(q.getResultList().size());
    		
    		
    		
   		} catch (Exception e) {		
   			e.printStackTrace();
   			JPA.getUtil().rollback();
   			fail(e.getMessage());
   		} finally {			
   			JPA.getUtil().closeConnection();
   		}
	}

}
