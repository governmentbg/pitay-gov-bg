package indexbg.pdoi.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.UnexpectedResultException;

import indexbg.pdoi.db.dao.ResponseSubjectDao;
import indexbg.pdoi.system.SystemData;

@ManagedBean
@ViewScoped
public class Test {
	
	static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
	
	public void updateRegistry() {
		LOGGER.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		try {
			ResponseSubjectDao respSubDAO = new ResponseSubjectDao(-1l,new SystemData());
			String comment = respSubDAO.updateAdmRegisterEntries();
			LOGGER.info("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		} catch (DbErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnexpectedResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
