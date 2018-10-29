package indexbg.pdoi.db.dao;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;

public class TestResponsibleSubjectDao {
	
	
	@Test
	public void testUpodateAdmRegister()  {
			
			try {
				ResponseSubjectDao dao = new ResponseSubjectDao(-1L);		

				Date datBeg = new Date();
				String s = dao.updateAdmRegisterEntries();
				System.out.println(datBeg + " - " + new Date());
				System.out.println(s);
			} catch (Exception e) {
				fail();
			}
			
			
			
		
	}

}
