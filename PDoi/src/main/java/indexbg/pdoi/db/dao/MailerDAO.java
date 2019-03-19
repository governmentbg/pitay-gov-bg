package indexbg.pdoi.db.dao;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.db.AbstractDAO;
import com.indexbg.system.db.JPA;
import com.indexbg.system.pagination.SelectMetadata;
import com.indexbg.system.utils.DateUtils;
import com.indexbg.system.utils.DialectConstructor;

import indexbg.pdoi.db.Mail;

public class MailerDAO extends AbstractDAO<Mail> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailerDAO.class);
	
	public MailerDAO(Long idUser){
		super(idUser);		
	}
	
	
	/**
	 * @author yonchev
	 * Method for the packages filter
	 */
	@SuppressWarnings("unchecked")
	//String sender, String recepient, 
	public SelectMetadata createFilterMsgSQL( String uri, String email, Long zdoi, Date dateOt, Date dateDo) {
		
		String dialect = JPA.getUtil().getProperty("hibernate.dialect").toUpperCase();
		dateOt = DateUtils.startDate(dateOt);
		dateDo = DateUtils.endDate(dateDo);	
		
		
		StringBuilder selectClause = new StringBuilder("SELECT id A1_ID, ")
				.append(" zdoi A2_ZDOI,")
				.append(" email A3_EMAIL,")
				.append(" name_lice A4_NAME_LICE,")
				.append(" subject A5_SUBJECT,")
				.append(" msg A6_MSG,")
				.append(" date_mail A7_DATE_MAIL,")
				.append(" error A8_ERROR,")
				.append(" uri A9_URI ")
				.append(", error A11_ERROR ")
				.append(" FROM  pdoi_mail " );
		
		StringBuilder whereClause=new  StringBuilder("");
		ArrayList<String> uslovia = new ArrayList<String>();
		
		if (zdoi != null){
			uslovia.add("zdoi = '" + zdoi +"'");
		}
		
		if (!email.isEmpty()){
			uslovia.add("email LIKE   %" + email +" %");
		}
		
		if (!uri.isEmpty()){
			uslovia.add("uri LIKE   %" + uri +" %");
		}
		
		if (dateOt != null) {
			uslovia.add("date_mail >= "+ DialectConstructor.convertDateToSQLString(dialect,dateOt));
		}

		if (dateDo != null) {
			uslovia.add("date_mail <= "+ DialectConstructor.convertDateToSQLString(dialect,dateDo));
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
		
		StringBuilder fromClause=		new StringBuilder( " FROM  pdoi_mail ") ;
		String sql=selectClause.toString() + whereClause.toString();
		SelectMetadata smd = new SelectMetadata();
		System.out.println("SQL: "+ sql);
		smd.setSql(sql);

		String sqlCount = "SELECT COUNT(distinct pdoi_mail.id) as counter " +fromClause.toString()+ whereClause.toString();
		smd.setSqlCount(sqlCount);
	
		return smd;
		
	}
	

}
