package indexbg.pdoi.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.InvalidParameterException;
import com.indexbg.system.mail.Mailer3;
import com.indexbg.system.mail.Mailer3.Content;
import com.indexbg.system.utils.JSFUtils;
import com.indexbg.system.utils.SearchUtils;
import com.indexbg.system.utils.StringUtils;

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
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAdminEmailByOrgCode() throws DbErrorException {

		try {
			
			Query query = JPA.getUtil().getEntityManager().createNativeQuery (" SELECT AU.USER_ID, AU.EMAIL FROM ADM_USERS AU WHERE AU.ORG_CODE IS NOT NULL AND AU.USER_TYPE = 1");

			return query.getResultList();

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DbErrorException("Възникна грешка при четене на данни от БД при измъкване на всички мейли на администраторите!", e);
		}

	}
	
	public void sendMailToAllAdmins() throws DbErrorException { 

		List<Object[]> dataForAdmin = findAdminEmailByOrgCode();

		ArrayList<String> mailsToAdm = new ArrayList<String>();		

		for (Object[] tmpObj : dataForAdmin) {			
			if (tmpObj[1] != null) {
				mailsToAdm.add(SearchUtils.asString(tmpObj[1]));				
			}
		}

		if (!mailsToAdm.isEmpty()) {
			
			for (String tmpMail : mailsToAdm) {	

			String link = "https://pitay.government.bg/PDoi/";
			String subject = "Уточняваща информация за линк на администратор-модератор на Платформата за достъп до обществена информация";
			String cont = "";
			String error = null;
			
			try {

				cont = "Вашата регистрация като администратор-модератор на Платформата за достъп до обществена информация е създадена  със следните данни: " + " <br/><br/>"
						+ "Потребителско име: " + tmpMail + " <br/><br/>" 
						+ "Парола: 123456 " + " <br/><br/>"
						+ "Моля, сменете паролата във Вашия профил. " + " <br/><br/>" 
						+ "Линк към платформата: <a href=" + link + "> " + "https://pitay.government.bg/PDoi/" + " </a> ";

				cont = "<div style='background-color:#4a6484;height:90px;font-size:20px;color:#fff'><img src='http://www.gov.bg/images/frontend/logo.png' width='100' align='center' />ДОСТЪП ДО ОБЩЕСТВЕНА ИНФОРМАЦИЯ</div>"
						+ "<div style='font-size:16px;color:#35475d'><br/> " + cont + "</div>";

				Properties mailProps = Mailer3.loadProps("indexbg.mail3.properties");
				Mailer3 mailer3 = new Mailer3();
				mailer3.sent(Content.HTML, mailProps, mailProps.getProperty("user.name"),
						mailProps.getProperty("user.password"), mailProps.getProperty("mail.from"), "PDOI",
						tmpMail, subject, cont, new ArrayList<DataSource>());
				
				JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_INFO, "Успешно изпратихте мейли на всички администратори!");

			} catch (AddressException e) {
				LOGGER.error("Error sendMail AddressException", e);
				error = StringUtils.stack2string(e);
			
			} catch (InvalidParameterException e) {
				LOGGER.error("Error sendMail InvalidParameterException", e);
				error = StringUtils.stack2string(e);
			
			} catch (MessagingException e) {
				LOGGER.error("Error sendMail MessagingException", e);
				error = StringUtils.stack2string(e);

			} catch (Exception e) {
				LOGGER.error("Error sendMail Exception", e);
				error = StringUtils.stack2string(e);
			
			} finally {

				JPA.getUtil().closeConnection();
				
				System.out.println("mailAdm: " + tmpMail + "; cont: " + cont + "; date: " + new Date() + "; error: " + error);
			}

		}
	}

	}

}
