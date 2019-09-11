package indexbg.pdoi.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataSource;
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
import com.indexbg.system.utils.StringUtils;

import indexbg.pdoi.db.Mail;
import indexbg.pdoi.db.dao.MailerDAO;




public class MyRunnableMail implements Runnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyRunnableMail.class);
	
	private long codeShablon=0;
	
	private ArrayList<String> mail;
	
	private String uri;
	private String zdoi;
	private String srok;
	private String nameLice;
	private String eventName;
	private String link;
	
	private Long idUser;
	
	
	private String namePodatel; // imenata na podatelq za da ne se promenqt shablonite tova pole shte se polzva pri zapis v tablicata s izprateni imeili
	
	public MyRunnableMail(long codeShablon, ArrayList<String> mail, Long idUser){
		 this.mail = mail;
		 this.codeShablon = codeShablon;
		 this.idUser = idUser;
	}
	
	public MyRunnableMail(long codeShablon, ArrayList<String> mail, String uri, String zdoi, String srok, String nameLice,  Long idUser, String eventName, String link, String namePodatel){
		 this.mail = mail;
		 this.codeShablon = codeShablon;
		 this.idUser = idUser;
		 this.uri = uri;
		 this.zdoi = zdoi;
		 this.srok = srok;
		 this.nameLice = nameLice;
		 this.eventName = eventName;
		 this.link = link;
		 this.namePodatel = namePodatel;
	}
	
	 
	 /**
	 * Mail send
	 */
	private void sendMail() {
		
		
		
		//String registerDate = Base64Coder.encodeString(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()).toString());
		String confFile = "indexbg.mail3.properties";		
		
		String subject = "";
		String cont = "";
		String error=null;
		
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
			
		} catch (AddressException e) {
			LOGGER.error("Error sendMail AddressException", e);
			error=StringUtils.stack2string(e);
		} catch (InvalidParameterException e) {
			LOGGER.error("Error sendMail InvalidParameterException", e);
			error=StringUtils.stack2string(e);
		} catch (MessagingException e) {
			LOGGER.error("Error sendMail MessagingException", e);
			error=StringUtils.stack2string(e);
			
		} catch (Exception e) {
			LOGGER.error("Error sendMail Exception", e);
			error=StringUtils.stack2string(e);
		} finally {
			//the record that will handle successful/ unsuccessful sent email
			// the user email, email subject, email body, id_user, the date of send email, error string containg stacktrace , name lice ako e fizichesko lice
			String mailtxt="";
			for(String s:mail ){
				if(mailtxt.length()>0) mailtxt+=";";
				mailtxt+=s;
			}
			Mail mail=new Mail( idUser,  zdoi,  mailtxt,  namePodatel,  subject,  cont, new Date(),  error,  uri);
			//System.out.println("idUser "+  this.idUser + " zdoi "+  zdoi+" mailtxt "+ mailtxt+"  nameLice "+nameLice+" subject  "+ subject+" cont "+cont+"  date"+ new Date()+" error "+   error+"uri"+ uri);
			
			try {
				
				JPA.getUtil().begin();
				new MailerDAO(idUser).save(mail);
				JPA.getUtil().commit();
				
			} catch (DbErrorException e) {
				LOGGER.error("Error saving mail Object", e);
				JPA.getUtil().rollback();
			} catch (Exception e) {
				LOGGER.error("Error saving mail Object", e);
				JPA.getUtil().rollback();
			}
			JPA.getUtil().closeConnection();
		}
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

	public String getNamePodatel() {
		return namePodatel;
	}

	public void setNamePodatel(String namePodatel) {
		this.namePodatel = namePodatel;
	}
}
