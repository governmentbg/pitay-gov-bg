package indexbg.pdoi.db.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.AbstractDAO;
import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.InvalidParameterException;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.DialectConstructor;
import com.indexbg.system.utils.FileUtils;
import com.indexbg.system.utils.SearchUtils;

import indexbg.pdoi.db.Application;
import indexbg.pdoi.db.EgovMessages;
import indexbg.pdoi.db.EgovMessagesFiles;
import indexbg.pdoi.db.EgovOrganisations;
import indexbg.pdoi.db.Files;
import indexbg.pdoi.system.SystemData;


public class EgovMessagesDAO extends AbstractDAO<EgovMessages> {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EgovMessagesDAO.class);
	
	public EgovMessagesDAO(Long idUser){
		super(idUser);		
	}
	
	/** 
	 * @param applic
	 * @param filesList
	 * @param eOrg
	 * @param sd
	 * @throws DbErrorException
	 */
	public void  saveEgovMessage(Application applic ,List <Files> filesList ,EgovOrganisations eOrg ,SystemData sd) throws DbErrorException{
		
		
		EgovMessages eMessage = new EgovMessages();
		
		eMessage.setDocVid("Заявление за достъп до обществена информация");
		
		eMessage.setDocGuid(applic.getDocGuid());
		
		String regNumUri="";
		if(applic.getApplicationUri()!=null) {
			
			regNumUri = applic.getApplicationUri().substring(0, applic.getApplicationUri().indexOf("-"));
		}
		
		
		eMessage.setDocRn(regNumUri); //moga i bez nego kato shte ima uri
		eMessage.setDocSubject(applic.getApplicationUri()+" "+eMessage.getDocVid()); //slagam iskaneto
		eMessage.setDocComment(applic.getApplicationUri()+" "+eMessage.getDocVid()); //slagame uri to plus vida na dok po iskane na DAEU
		
		//eMessage.setDocUriReg(applic.getApplicationUri()); // TODO komentirame i pochvame da pishem v docRn
		//eMessage.setDocUriBtch(applic.getApplicationUri()); // poredniqt nomer ot urito???
		
		eMessage.setDocDate(applic.getDateReg());
		
		eMessage.setDocSrok(null); //нямаме срок
		
		eMessage.setDocNasochen(""); //neznam dali shte imam nasochvane i kakvo se ochakva da popalnya
		
		//failovete
		if(filesList.size()>0){
			List<EgovMessagesFiles> tmpEFiles = new ArrayList<EgovMessagesFiles>();  
			for (int i = 0; i < filesList.size(); i++) {
				   Files file = filesList.get(i);
				   EgovMessagesFiles eFile = new EgovMessagesFiles();
				   
				   eFile.setFilename(file.getFilename());
				   eFile.setMime(file.getContentType());
				   eFile.setBlobcontent(file.getContent());
				   tmpEFiles.add(eFile);
			}
			
			eMessage.setEgovMessagesFiles(tmpEFiles);
		}
		
		//-------------------------------------------------------------------------
		//ИНИЦИАЛИЗАЦИЯ
		eMessage.setMsgInOut(2L);		
		eMessage.setMsgRegDate(new Date());
		eMessage.setMsgRn(null);
		eMessage.setMsgRnDate(null);
		eMessage.setMsgStatus("DS_WAIT_SENDING");
		eMessage.setMsgStatusDate(new Date());
		
		eMessage.setMsgType("MSG_DocumentRegistrationRequest"); //MessageType.MSG_DOCUMENT_REGISTRATION_REQUEST.value()
		eMessage.setMsgUrgent(Long.valueOf(1));
		//mess.setMsgXml(null);
		
		

		//информация за хедъра
		eMessage.setMsgGuid("{"+java.util.UUID.randomUUID().toString().toUpperCase()+"}");
		eMessage.setMsgDate(new Date());
		eMessage.setRecepientEik(eOrg.getEik());
		eMessage.setRecepientGuid(eOrg.getGuid());
		eMessage.setRecepientName(eOrg.getAdministrativeBodyName());
		
		//идват като се регистрират и ни ги предоставят, ще ги вземаме от пропърти файла
		eMessage.setSenderEik(sd.getSettingsValue("senderEik"));
		eMessage.setSenderGuid(sd.getSettingsValue("senderGuid"));
		eMessage.setSenderName(sd.getSettingsValue("senderName"));
		
		
		//body
				
		eMessage.setCommStatus(Long.valueOf(1));
		
		this.save(eMessage);
		
		
		// zapiswame id na porajdashtoto syobshtenie v zaqvlenieto
		
		try{
			Query query = createNativeQuery("update pdoi_application set egov_mess_id = ? where id = ?"); 
			
			query.setParameter(1, eMessage.getId());			
			query.setParameter(2, applic.getId());
			query.executeUpdate();
		} catch (HibernateException e) {
			throw new DbErrorException(	"Грешка при запис на идентификатора на съобщението от egov_messages ", e);
		}
		
	}
	
	
	/**
	 * @author yonchev
	 * Методът връща списък от различните видове съобщения, така че да може да се използва като класификация
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> createMsgTypesList() throws  DbErrorException{
		try{
			
			Query query = JPA.getUtil().getEntityManager().createNativeQuery("select STATUS_TEKST, DESCRIPTION from EGOV_NOMENKLATURI where STATUS_TEKST like 'MSG_%' order by DESCRIPTION");
			
//			query.addScalar("STATUS_TEKST", new StringType());
//			query.addScalar("DESCRIPTION", new StringType());

			return  (ArrayList<Object[]>) query.getResultList();
			
		} catch (HibernateException e) {
			throw new DbErrorException(	"Грешка при търсене на видове съобщения", e);
		}
		
	}
	
	
	/**
	 * @author yonchev
	 * Методът връща списък от различните статуси на съобщения, така че да може да се използва като класификация
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> createMsgStatusList() throws  DbErrorException{
		
		
		try{
			
			Query query = JPA.getUtil().getEntityManager().createNativeQuery("select STATUS_TEKST, DESCRIPTION from EGOV_NOMENKLATURI where STATUS_TEKST like 'DS_%' order by DESCRIPTION");
			
//			query.addScalar("STATUS_TEKST", new StringType());
//			query.addScalar("DESCRIPTION", new StringType());
			
	
			return  (ArrayList<Object[]>) query.getResultList();
			
		} catch (HibernateException e) {
			throw new DbErrorException(	"Грешка при търсене на статус на съобщения", e);
		}
		
	}
	
	/**
	 * @author yonchev
	 * Методът връща списък от различните статуси на получените съобщения, така че да може да се използва като класификация
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object[]> createCommStatusList() throws  DbErrorException{
		
		try{
			
			Query query = JPA.getUtil().getEntityManager().createNativeQuery("select STATUS, DESCRIPTION from EGOV_NOMENKLATURI where status is not null order by status");
			
//			query.addScalar("STATUS", new LongType());
//			query.addScalar("DESCRIPTION", new StringType());
			
	
			return  (ArrayList<Object[]>) query.getResultList();
			
		} catch (HibernateException e) {
			throw new DbErrorException(	"Грешка при търсене на статус на изпращане", e);
		}
		
		
	}
	
	
	/**
	 * @author yonchev
	 * Method for the packages filter
	 */
	@SuppressWarnings("unchecked")
	//String sender, String recepient, 
	public SelectMetadata createFilterMsgSQL(String msgType, String docStatus, Long sentStatus, Long inOut, Date dateOt, Date dateDo) {
		
		
//		Dialect dialect = ((SessionFactoryImpl) HibernateUtil.currentSession()
//				.getSessionFactory()).getDialect();
		
		String dialect = JPA.getUtil().getProperty("hibernate.dialect").toUpperCase();
		
		
		dateOt = DateUtils.startDate(dateOt);
		dateDo = DateUtils.endDate(dateDo);	
		
		
		StringBuilder selectClause = new StringBuilder("SELECT " )
				.append("	EGOV_MESSAGES.ID 	A00ID, ")
				.append("	MSG_TYPE			A00MSGTYPE, " ) 
				.append("  	nom3.DESCRIPTION    A01MSGTYPETEXT,  " )	
				.append(" 	MSG_DAT 			A02MSGDAT, " )				
				.append(" 	SENDER_NAME 		A03SENDER, " )
				.append(" 	RECIPIENT_NAME 		A04RECEP, " )
				.append(" 	DOC_RN 		  		A05IZHRN ,  " )
				.append( "  	DOC_DAT 			A06IZHDAT, " )						    
				.append( "  	MSG_RN 		 		A07VHRN, " )
				.append( "  	MSG_RN_DAT 			A08VHDAT, " )
				.append( "  	DOC_SUBJECT 		A09SUBJECT, " )
				.append( " 	nom2.DESCRIPTION 	A10STATDOC,	 " )
				.append("  	nom1.DESCRIPTION    A11STATIZPR,  " )						    
				.append("  	COMM_ERRROR  		A12ERR" )
				.append(" FROM " )
				.append(" 	EGOV_MESSAGES 	join EGOV_NOMENKLATURI nom1 on EGOV_MESSAGES.COMM_STATUS = nom1.STATUS " )
				.append( "                  	join EGOV_NOMENKLATURI nom2 on EGOV_MESSAGES.MSG_STATUS = nom2.STATUS_TEKST ")
				.append( "					join EGOV_NOMENKLATURI nom3 on EGOV_MESSAGES.MSG_TYPE = nom3.STATUS_TEKST ");
		
		StringBuilder whereClause=new  StringBuilder("");
		ArrayList<String> uslovia = new ArrayList<String>();
		
//		if (sender != null){
//			uslovia.add("SENDER_GUID = '" + sender +"'");
//		}
//		
//		if (recepient != null){
//			uslovia.add("RECIPIENT_GUID = '" + recepient +"'");
//		}
		
		if (msgType != null){
			uslovia.add("MSG_TYPE = '" + msgType +"'");
		}
		
		if (docStatus != null){
			uslovia.add("MSG_STATUS = '" + docStatus +"'");
		}
		
		
		if (sentStatus != null){
			uslovia.add("COMM_STATUS = " + sentStatus );
		}
		
		if (inOut != null){
			uslovia.add("MSG_INOUT = " + inOut );
		}
		
		if (dateOt != null) {
			uslovia.add("MSG_DAT >= "
					+ DialectConstructor.convertDateToSQLString(dialect,
							dateOt));
		}

		if (dateDo != null) {
			uslovia.add("MSG_DAT <= "
					+ DialectConstructor.convertDateToSQLString(dialect,
							dateDo));
		}
			
			
		if (!uslovia.isEmpty()) {
			whereClause.append(" WHERE ");
			for (int i = 0; i < uslovia.size(); i++) {
				whereClause.append(uslovia.get(i));
				if (i != (uslovia.size() - 1)) {
					whereClause.append(" AND ");
				}
			}
		}
		
		StringBuilder fromClause=		new StringBuilder( " FROM ") 
			   .append( " 	EGOV_MESSAGES 	join EGOV_NOMENKLATURI nom1 on EGOV_MESSAGES.COMM_STATUS = nom1.STATUS " )
					   .append( "                  	join EGOV_NOMENKLATURI nom2 on EGOV_MESSAGES.MSG_STATUS = nom2.STATUS_TEKST ")
					   .append( "					join EGOV_NOMENKLATURI nom3 on EGOV_MESSAGES.MSG_TYPE = nom3.STATUS_TEKST ");
		String sql=selectClause.toString() + whereClause.toString();
		SelectMetadata smd = new SelectMetadata();
		System.out.println("SQL: "+ sql);
		smd.setSql(sql);

		String sqlCount = "SELECT COUNT(distinct EGOV_MESSAGES.id) as counter " +fromClause.toString()+ whereClause.toString();;
		smd.setSqlCount(sqlCount);
	
		return smd;
		
	}
	
	
	/**
	 * @author yonchev
	 * Методът рестартира изпратените съобщения, за да може да бъдат изпратени наново при проблем
	 */
	public void resetOutgoingMessages(Long idMessage) throws DbErrorException{

		try {

			JPA.getUtil().getEntityManager().createNativeQuery("update EGOV_MESSAGES set COMM_STATUS = 1, COMM_ERRROR = null where id = :idd").setParameter("idd", idMessage).executeUpdate();
			
			
		} catch (HibernateException e) {
			throw new DbErrorException("Грешка при ресет на изходящо съобщение !", e);
		}
	}
	
	/**
	 * @author yonchev
	 * Методът проверява съобщенията за грешки, които при възникване се записват във файл
	 */
	@SuppressWarnings("unchecked")
	public void checkEgovMessages() throws DbErrorException{
		
		
		try{
			int brNeizprateni = 0;
			StringBuilder logErrors = new StringBuilder("СЪОБЩЕНИЯ С ГРЕШКИ : \r\n");
			
			ArrayList<Object> obs= (ArrayList<Object>) JPA.getUtil().getEntityManager().createNativeQuery("select id from EGOV_MESSAGES order by id desc").getResultList();
			ArrayList<Long> ids = new ArrayList<>();
			for(Object ob :obs){
				ids.add(SearchUtils.asLong(ob));
			}
			
			System.out.println("Брой записи за тест и валидация : " + ids.size());
			int count = ids.size();
			for (int i = 0; i < count; i++){
				Long id = ids.get(i);
				System.out.println("Прочитане на комуникационно съобщение " +(i+1) + " от " + count );
				EgovMessages mess = findById(id);									
				if (mess != null){
					 JPA.getUtil().getEntityManager().detach(mess);
					System.out.print("\tВалидиране на XML и проверка на комуникационнен log");	
					if (mess.getMsgXml() != null){
						FileUtils.writeBytesToFile("d:\\log\\xml"+id+".xml", mess.getMsgXml().getBytes());
						
						if (mess.getCommStatus() != null && mess.getCommStatus().longValue() == 1 ){
							brNeizprateni ++;
						}
						
						if (mess.getCommStatus() != null && mess.getCommStatus().longValue() == 2 ){
							System.out.println(" - Статус ОК. Изпратено на " + mess.getMsgDate());
						}else{						
							if (mess.getCommError() != null ){
								System.out.println(" - Статус ERROR. Код на комуникация " + mess.getCommStatus() + ". Логване на съобщението ! ");
								logErrors.append( "Получател : " + mess.getRecepientName() + "\r\n");
								logErrors.append( "Подател : " + mess.getSenderName()+ "\r\n");
								logErrors.append( "Дата : " + mess.getMsgDate()+ "\r\n");
								logErrors.append( "Тип съобщение : " + mess.getMsgType()+ "\r\n");
								logErrors.append( "Грешка : "+ "\r\n");
								logErrors.append( mess.getCommError()+ "\r\n");
								logErrors.append( "--------------------------------------------------------------------------------------"+ "\r\n"+ "\r\n");
							}else{
								if (mess.getCommStatus() != null && mess.getCommStatus().longValue() == 3 ){
									System.out.println(" - Статус ОК. Получено на " + mess.getMsgDate());
								}else{
									System.out.println(" - Статус NOERROR. Код на комуникаця " + mess.getCommStatus() + ". Логване на съобщението ! ");
								}
							}
						}
					}
				}
			}

			FileUtils.writeBytesToFile("d:\\ErrorLogEgov.log", ("Брой неизпратени " + brNeizprateni + "\r\n" + logErrors.toString()).getBytes());
			
		} catch (HibernateException e) {
			throw new DbErrorException(	"Грешка при търсене на съобщения", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
		/**
		 * @author yonchev
		 * Методът изгражда заявката за филтъра на съобщенията
		 */
		public String createFilterDocsWebServiceNew(String docGuid, String msgType, Long inOut  ) throws InvalidParameterException{
			StringBuffer sqlSelect = new StringBuffer("select " )
		.append(" 	    EGOV_MESSAGES.SENDER_NAME 		SENDER_NAME			, " )
		.append("	    EGOV_MESSAGES.RECIPIENT_NAME 	RECIPIENT_NAME		, " )
		.append("		n3.DESCRIPTION  				VID_MSG				, " )
		.append("		n2.DESCRIPTION  				STAT_IZPR			, " )
		.append("		EGOV_MESSAGES.MSG_STATUS_DAT 	MSG_STATUS_DAT		, " )
		.append("		n1.DESCRIPTION  				DOC_STAT			, " )
		.append("		EGOV_MESSAGES.MSG_RN			MSG_RN				, " )
		.append("		EGOV_MESSAGES.MSG_RN_DAT 		MSG_RN_DAT			, " )
		.append("		n2.STATUS                     	ERR_STATUS			, " )
		.append( "       EGOV_MESSAGES.id              	ID					, " )
		.append( "       EGOV_MESSAGES.COMM_ERRROR     	COMM_ERRORR_TEXT	" )
		.append("from EGOV_MESSAGES, EGOV_NOMENKLATURI n1,  EGOV_NOMENKLATURI n2, EGOV_NOMENKLATURI n3 ")
		.append("where EGOV_MESSAGES.MSG_STATUS = n1.STATUS_TEKST   and    ")
		.append("      EGOV_MESSAGES.COMM_STATUS = n2.STATUS   and  ")
		.append("      EGOV_MESSAGES.MSG_TYPE = n3.STATUS_TEKST   ");
			
			if (docGuid != null){
				sqlSelect .append("   and   EGOV_MESSAGES.DOC_GUID = '"+docGuid.toUpperCase()+"' ");
			}
			
			if (msgType != null){
				sqlSelect .append("   and   EGOV_MESSAGES.MSG_TYPE = '"+msgType+"' ");
			}
			
			if (inOut != null){
				sqlSelect .append( "   and   EGOV_MESSAGES.MSG_INOUT = "+inOut);
			}
			
			sqlSelect .append( " order by EGOV_MESSAGES.id ");
			return sqlSelect.toString();
		}	
		
		
		/**
		 * @author yonchev
		 * Методът проверява историята за изпратени предишни съобщения
		 */
		@SuppressWarnings("unchecked")
		public List<EgovMessages> createFilterDocsWebServiceHistory(String docGuid, String msgType, Long inOut  ) throws InvalidParameterException, DbErrorException{
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			List<EgovMessages> egovMessagesHistory = new ArrayList<EgovMessages>();
			try{
				String sqlSelect = createFilterDocsWebServiceNew(docGuid, msgType, inOut);
				Query query = JPA.getUtil().getEntityManager().createNativeQuery(sqlSelect);
				
//				query.addScalar("SENDER_NAME", new StringType());
//				query.addScalar("RECIPIENT_NAME", new StringType());
//				query.addScalar("VID_MSG", new StringType());
//				query.addScalar("STAT_IZPR", new StringType());
//				query.addScalar("MSG_STATUS_DAT", new TimestampType());
//				query.addScalar("DOC_STAT", new StringType());
//				query.addScalar("MSG_RN", new StringType());
//				query.addScalar("MSG_RN_DAT", new TimestampType());
//				
//				query.addScalar("ERR_STATUS", new LongType());
//				query.addScalar("ID", new LongType());
//				query.addScalar("COMM_ERRORR_TEXT", new StringType());
		
				ArrayList<Object[]> rez = (ArrayList<Object[]>) query.getResultList();
				for (int i = 0; i < rez.size(); i++) {
					Object[] row = rez.get(i);
					EgovMessages egovMessages = new EgovMessages();
					
					egovMessages.setRecepientName((String) row[1]);
					egovMessages.setMsgType((String) row[2]);
					egovMessages.setMsgStatus((String) row[3]);
					egovMessages.setMsgStatusDate((Date) row[4]);
					if (row[6] != null && row[7] != null){
						Date tmpMsgRnDate = (Date) row[7];
						String tmpThreeColumns = (String) row[5] + "  " + (String) row[6] + " / " + sdf.format(tmpMsgRnDate);
						egovMessages.setMsgComment(tmpThreeColumns);
					}else{
						egovMessages.setMsgComment((String) row[5]);
					}
					egovMessages.setCommStatus((Long) row[8]);
					egovMessages.setId((Long) row[9]);
					egovMessages.setCommError((String) row[10]);
					
					egovMessagesHistory.add(egovMessages);
				}
			} catch (HibernateException e) {
				throw new DbErrorException(	"Грешка при обработка на изходящи съобщения от буфера", e);
			}
			return egovMessagesHistory;
		}
}
