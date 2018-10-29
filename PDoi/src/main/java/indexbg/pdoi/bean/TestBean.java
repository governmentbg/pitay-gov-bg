package indexbg.pdoi.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import indexbg.pdoi.system.PDoiBean;

@ManagedBean(name = "testBean")
@ViewScoped
public class TestBean extends PDoiBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7483467840810680304L;

	static final Logger LOGGER = LoggerFactory.getLogger(TestBean.class);
	
	
	@PostConstruct
	public void initData(){
		LOGGER.debug("PostConstruct!!!!");
		
	}
	
//	@Test
//	public void test(){
//		
//
//		try {
//			
//			System.out.println("isResponseSubjectCEOS---> "+new ResponseSubjectDao(-1L).responseSubjectCEOS(12603L));
//			
//			
//			
//			
////			Application app = new ApplicationDAO(-1L).findByURI("123456789");
////			if(app!=null){
////				System.out.println(""+app.getFullNames());
////			} else {
////				System.out.println("nema");
////			}
//		} catch (DbErrorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			JPA.getUtil().closeConnection();
//		}
//		
//	}
	

}
