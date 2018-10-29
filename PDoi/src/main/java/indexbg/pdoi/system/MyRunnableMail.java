package indexbg.pdoi.system;

import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indexbg.system.db.JPA;

import com.indexbg.system.exceptions.InvalidParameterException;
import com.indexbg.system.mail.Mailer3;
import com.indexbg.system.mail.Mailer3.Content;
import com.indexbg.system.utils.StringUtils;




public class MyRunnableMail implements Runnable {
	
	private static final Logger LOGGER = Logger.getLogger(MyRunnableMail.class);
	
	private long codeShablon=0;
	
	private ArrayList<String> mail;
	
	private String uri;
	private String zdoi;
	private String srok;
	private String nameLice;
	private String eventName;
	private String link;
	
	private Long idJournal;
	private Long idUser;
	
	public MyRunnableMail(long codeShablon, ArrayList<String> mail, Long idJournal, Long idUser){
		 this.mail = mail;
		 this.codeShablon = codeShablon;
		 this.idJournal = idJournal;
		 this.idUser = idUser;
	}
	
	public MyRunnableMail(long codeShablon, ArrayList<String> mail, String uri, String zdoi, String srok, String nameLice, Long idJournal, Long idUser, String eventName, String link){
		 this.mail = mail;
		 this.codeShablon = codeShablon;
		 this.idJournal = idJournal;
		 this.idUser = idUser;
		 this.uri = uri;
		 this.zdoi = zdoi;
		 this.srok = srok;
		 this.nameLice = nameLice;
		 this.eventName = eventName;
		 this.link = link;
	}
	
	 
	 /**
	 * Mail send
	 */
	private void sendMail() {
		
		
		
		//String registerDate = Base64Coder.encodeString(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()).toString());
		String confFile = "indexbg.mail3.properties";		
		
		String subject = "";
		String cont = "";
		
		try {
			
			Query query = JPA.getUtil().getEntityManager().createNativeQuery ("select mail_subject, mail_body from pdoi_notification_patterns where id = " + codeShablon);
			Object[] obj = (Object[]) query.getResultList().get(0);
			
			if(obj != null) {
				subject = obj[0] != null ? obj[0].toString() : "";
				cont =  obj[1] != null ? obj[1].toString() : "";
			}			
				
			if (nameLice != null) {
				cont = "Уважаема/и г-жо/г-н " + nameLice + ", "  + "\n" + "<br/><br/>" + cont;
			}
			
			if (uri != null ) {
				 cont = cont.replace("$URI$", uri);
			}
			
			if (zdoi != null ) {
				 cont = cont.replace("$ZDOI$", zdoi);
			}
			
			if (srok != null ) {
				 cont = cont.replace("$SROK$", srok);
			}
			
			if (eventName != null ) {
				 cont = cont.replace("$EVENT$", eventName);
			}
			
			if (link != null) {				
				cont = cont.replace("$LINK$", link);
			}
			
			cont = "<div style='background-color:#4a6484;height:90px;font-size:20px;color:#fff'><img src='http://www.gov.bg/images/frontend/logo.png' width='100' align='center' />ДОСТЪП ДО ОБЩЕСТВЕНА ИНФОРМАЦИЯ</div>"
					+ "<div style='font-size:16px;color:#35475d'><br/> " + cont + "</div>";
			
			Properties mailProps = Mailer3.loadProps(confFile);
			Mailer3 mailer3=new Mailer3();
			mailer3.sent(Content.HTML,mailProps,mailProps.getProperty("user.name"),mailProps.getProperty("user.password"),
			       mailProps.getProperty("mail.from"), "PDOI",mail,subject, cont, new ArrayList<DataSource>());
			
			if(idJournal != null || idUser!=null){
				insertJournal(cont);
			}
		} catch (AddressException e) {
			LOGGER.error("Error sendMail AddressException", e);
			if(idJournal != null || idUser!=null){
				insertJournal(StringUtils.stack2string(e));
			}
		} catch (InvalidParameterException e) {
			LOGGER.error("Error sendMail InvalidParameterException", e);
			if(idJournal != null || idUser!=null){
				insertJournal(StringUtils.stack2string(e));
			}
		} catch (MessagingException e) {
			LOGGER.error("Error sendMail MessagingException", e);
			if(idJournal != null || idUser!=null){
				insertJournal(StringUtils.stack2string(e));
			}
		} catch (Exception e) {
			LOGGER.error("Error sendMail Exception", e);
			if(idJournal != null || idUser!=null){
				insertJournal(StringUtils.stack2string(e));
			}
		} finally {
			JPA.getUtil().closeConnection();
		}
	 }
	 
	  private void insertJournal(String dopInfo){
		    
//		  JPA.getUtil().begin();
//		  try {
//			    //------------------- журналиране ----------------------------------------------
//			    JournalDAO jdao = new JournalDAO(Constants.PORTAL_USER_ID,  new SystemData());
//			    
//			    Journal j = jdao.findById(idJournal);
//			    
//			    if(j==null){
//			    	j = new Journal();
//			    	j.setCodeObject(Constants.CODE_OBJECT_USER);
//			      	j.setCodeAction(Constants.CODE_DEIN_USER_REQUEST_PROMYANA_PASS);
//			      	j.setIdObject(idUser);
//			      	j.setIdUser((idUser==null?Constants.PORTAL_USER_ID:idUser));
//			    }
//				
//		      	j.setDateAction(new Date());
//		      	
//		      	if(j.getIdentObject()==null){
//		      		j.setIdentObject(dopInfo); 
//		      	} else {
//		      		j.setIdentObject(j.getIdentObject()+" <br/> Изпратено съобщение: <br/> "+dopInfo); 
//		      	}
//		      	
//		      	jdao.save(j);
//		      	JPA.getUtil().commit();
//				//-----------------------------------------------------------------------------	
//		  } catch (HibernateException e) {
//				LOGGER.error("HibernateException pri zapis v journala - sendMail na potrebitel !", e);
//				JPA.getUtil().rollback();
//				//throw new WSFault("Error pri zapis v journala - sendMail na potrebitel", StringUtils.stack2string(e));
//		  } catch (Exception e) {
//				LOGGER.error("Exception pri zapis v journala - sendMail na potrebitel !", e);
//				JPA.getUtil().rollback();
//				//throw new WSFault("Error pri zapis v journala - sendMail na potrebitel", StringUtils.stack2string(e));
//		  } finally {
//			  JPA.getUtil().closeConnection();
//		  }
	  }
	
	
	 public void run(){ 
		 sendMail();
	 }

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
