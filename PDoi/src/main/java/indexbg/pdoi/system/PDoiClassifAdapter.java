package indexbg.pdoi.system;

import static com.indexbg.system.utils.SearchUtils.asDate;
import static com.indexbg.system.utils.SearchUtils.asLong;
import static com.indexbg.system.utils.SearchUtils.asString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.persistence.Query;

import com.indexbg.system.BaseSystemData;
import com.indexbg.system.SysClassifAdapter;
import com.indexbg.system.db.JPA;
import com.indexbg.system.db.dto.SystemClassif;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.utils.SearchUtils;

public class PDoiClassifAdapter extends SysClassifAdapter {

	public PDoiClassifAdapter(BaseSystemData sd) {
		super(sd);
	
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 *Изгражда динамична системна класификация на адм. регистър по нива
	 */
	public List<SystemClassif> createAdmRegisterNiva(Long codeClassif, Long lang, Long userId) throws DbErrorException {
		
		List<SystemClassif> classif = new ArrayList<SystemClassif>();
		try {
			TreeSet<Date> dates = new TreeSet<Date>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
			
			String sql = "select id, ime from ekatte_oblasti order by ime";
			Query q = JPA.getUtil().getEntityManager().createNativeQuery(sql);
			List<Object[]> obstini = (List<Object[]>)q.getResultList();
			
			
			sql = "select id, subject_name, adm_level, date_from, date_to, region from pdoi_response_subject order by subject_name";
			q = JPA.getUtil().getEntityManager().createNativeQuery(sql);
			
			List<Object[]> subjects = (List<Object[]>)q.getResultList();
			// Запълваме датите
			for (Object[] row : subjects) {
				if (row[3] != null) {
					dates.add(asDate(row[3]));
				}
				if (row[4] != null) {
					dates.add(asDate(row[4]));
				}
			}
			
			
			
			
			ArrayList<Long[]> periods = new ArrayList<Long[]>(); 
			Date datBeg;
			try {
				datBeg = sdf.parse("01.01.1900");
			} catch (ParseException e) {
				datBeg = new Date();
			}
			for (Date datEnd : dates) {
				if (datBeg.equals(datEnd)) {
					//System.out.println(datBeg + " - " + datEnd);
					Long [] period = new Long[2];
					period[0] = datBeg.getTime();
					period[1] = datEnd.getTime();
					periods.add(period);
				}		
				
				datBeg.setTime(datEnd.getTime());
			}
			Long [] period = new Long[2];
			period[0] = datBeg.getTime();
			period[1] = null;
			periods.add(period);
			
			classif = new ArrayList<>();
			for (Long[] tek : periods) {
				datBeg = new Date(tek[0]);
				List<SystemClassif> adm = sd.getSysClassification(10024L, datBeg, lang, userId);
				for (SystemClassif item : adm) {
					item.setCodeClassif(codeClassif);
					item.setCode(-item.getCode());
					item.setCodeParent(-item.getCodeParent());
					item.setCodePrev(-item.getCodePrev());
					item.setDateOt(datBeg);				
					if (tek[1]==null) {
						item.setDateDo(null);
					}else {
						item.setDateDo(new Date(tek[1]));
					}
					classif.add(item);
					if (item.getCode() == -9) {
						//Общински
						long codePrevObst = 0;
						
						for (Object[] ob : obstini) {
							SystemClassif obItem = new SystemClassif();
							obItem.setCodeClassif(codeClassif);
							obItem.setCode(asLong(ob[0]).longValue()*-10000);
							//obItem.setCode(asLong(ob[0]));
							obItem.setCodeParent(item.getCode());
							obItem.setCodePrev(codePrevObst);
							obItem.setDateOt(datBeg);
							obItem.setLevelNumber(2);
							obItem.setId(asLong(ob[0]).longValue()*-10000);
							//obItem.setId(asLong(ob[0]));
							obItem.setTekst("Област " + asString(ob[1]));
							if (tek[1]==null) {
								obItem.setDateDo(null);
							}else {
								obItem.setDateDo(new Date(tek[1]));
							}
							classif.add(obItem);
							codePrevObst = obItem.getCode();
							long codePrevAdm = 0;
							for (Object[] row : subjects) {
								
								if (row[5] != null && asLong(row[5]).longValue() == asLong(ob[0]).longValue() && asLong(row[2]).longValue() == 9) {
									SystemClassif childItem = new SystemClassif();
									childItem.setCodeClassif(codeClassif);
									childItem.setCode(asLong(row[0]));
									childItem.setCodeParent(obItem.getCode());
									childItem.setCodePrev(codePrevAdm);
									childItem.setDateOt(datBeg);
									childItem.setLevelNumber(3);
									childItem.setId(asLong(row[0]));
									childItem.setTekst(asString(row[1]));
									if (tek[1]==null) {
										childItem.setDateDo(null);
									}else {
										childItem.setDateDo(new Date(tek[1]));
									}
									classif.add(childItem);
									codePrevAdm = childItem.getCode();
								}
							}
							
						}
						
						
						
					}else {
						long codePrev = 0;
						for (Object[] row : subjects) {
							
							if (asLong(row[2]).longValue() == -item.getCode()) {
								SystemClassif childItem = new SystemClassif();
								childItem.setCodeClassif(codeClassif);
								childItem.setCode(asLong(row[0]));
								childItem.setCodeParent(item.getCode());
								childItem.setCodePrev(codePrev);
								childItem.setDateOt(datBeg);
								childItem.setLevelNumber(2);
								childItem.setId(asLong(row[0]));
								childItem.setTekst(asString(row[1]));
								if (tek[1]==null) {
									childItem.setDateDo(null);
								}else {
									childItem.setDateDo(new Date(tek[1]));
								}
								classif.add(childItem);
								codePrev = childItem.getCode();
							}
						}
					}
					
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return classif;
	}
	
	
	/**
	 *Изгражда динамична системна класификация EKATTE
	 */
	@SuppressWarnings("unchecked")
	public List<SystemClassif> createEKATTE(Long codeClassif, Long lang, Long userId) throws DbErrorException {
    	
    	
		List<SystemClassif> classif = new ArrayList<SystemClassif>();
    	
    	try {
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    		Date dat = sdf.parse("01.01.1900");
    		
    		
    		
    		long codePrev = 0;
    		
    		Map<String, Long> oblMap = new HashMap<String, Long>(); 
    		
    		//Области
    		Query q = JPA.getUtil().getEntityManager().createNativeQuery("select OBLAST, ime, id from EKATTE_OBLASTI order by ime");
    		
    		
			List<Object[]> rez = q.getResultList();
			
			for (Object[] row : rez) {
				SystemClassif item = new SystemClassif();
				
				item.setCode(asLong(row[2]));
				item.setCodeClassif(codeClassif);
				item.setCodeExt(asString(row[0]));
				item.setCodeParent(0L);
				item.setCodePrev(new Long(codePrev));
				item.setDateOt(dat);
				item.setLevelNumber(1);
				item.setTekst(asString(row[1]));
				
//				Multilang lbg = new Multilang();
//				Multilang len = new Multilang();
//				lbg.setTekst(SearchUtils.asString(row[1]));
//				len.setTekst(SearchUtils.asString(row[1]));
//				lbg.setLang(1L);
//				len.setLang(2L);
//				item.getTranslations().add(lbg);
//				item.getTranslations().add(len);
//				
//				classifDAO.doSimpleSave(item);
				
				classif.add(item);
				codePrev = item.getCode();				
				oblMap.put(item.getCodeExt(), 0L);
			}
			
			
			
			//Общини
    		q = JPA.getUtil().getEntityManager().createNativeQuery("select OBSTINA, IME, id from EKATTE_OBSTINI order by ime");
    		
    		
			rez = q.getResultList();			
			for (Object[] row : rez) {
				SystemClassif item = new SystemClassif();
				
				item.setCode(asLong(row[2]));
				item.setCodeClassif(codeClassif);
				item.setCodeExt(asString(row[0]));
				
//				if (!item.getCodeExt().startsWith("PAZ")){
//					continue;
//				}
				
				//Търсим родител
				SystemClassif parent = null;
				for (SystemClassif tek : classif){
					if (item.getCodeExt().startsWith(tek.getCodeExt())){
						parent = tek;
						//System.out.println(item.getCodeExt() + "-->" + tek.getCodeExt() );
						break;
					}
				}
				
				item.setCodeParent(parent.getCode());
				codePrev = oblMap.get(parent.getCodeExt()).longValue();
				//System.out.println("========================================================>" + codePrev);
				item.setCodePrev(new Long(codePrev));
				codePrev = item.getCode();
				oblMap.put(parent.getCodeExt(), new Long(codePrev));				
				
				item.setDateOt(dat);
				item.setLevelNumber(2);
				item.setTekst(asString(row[1]));
				
//				Multilang lbg = new Multilang();
//				Multilang len = new Multilang();
//				lbg.setTekst(SearchUtils.asString(row[1]));
//				len.setTekst(SearchUtils.asString(row[1]));
//				lbg.setLang(1L);
//				len.setLang(2L);
//				item.getTranslations().add(lbg);
//				item.getTranslations().add(len);
//				
//				classifDAO.doSimpleSave(item);
				
				classif.add(item);
				oblMap.put(item.getCodeExt(), 0L);
			}
			
			
			//Населени места
    		q = JPA.getUtil().getEntityManager().createNativeQuery("select ekatte, OBSTINA, IME, tvm from EKATTE_ATT order by ime");
    		
    		
			rez = q.getResultList();			
			for (Object[] row : rez) {
				SystemClassif item = new SystemClassif();
				
				item.setCode(asLong(row[0]));
				item.setCodeClassif(codeClassif);
				item.setCodeExt(""+asLong(row[0]));
				String obst = SearchUtils.asString(row[1]);
				
//				if (!item.getCodeExt().startsWith("PAZ")){
//					continue;
//				}
				
				//Търсим родител
				SystemClassif parent = null;
				for (SystemClassif tek : classif){
					if (obst.equals(tek.getCodeExt())){
						parent = tek;
						//System.out.println(item.getCodeExt() + "-->" + tek.getCodeExt() );
						break;
					}
				}
				
				item.setCodeParent(parent.getCode());
				codePrev = oblMap.get(parent.getCodeExt()).longValue();
				//System.out.println("========================================================>" + codePrev);
				item.setCodePrev(new Long(codePrev));
				codePrev = item.getCode();
				oblMap.put(parent.getCodeExt(), new Long(codePrev));				
				
				item.setDateOt(dat);
				item.setLevelNumber(3);
				item.setTekst(SearchUtils.asString(row[3]) + SearchUtils.asString(row[2]));
				
//				Multilang lbg = new Multilang();
//				Multilang len = new Multilang();
//				lbg.setTekst(SearchUtils.asString(row[3]) + SearchUtils.asString(row[2]));
//				len.setTekst(SearchUtils.asString(row[3]) + SearchUtils.asString(row[2]));
//				lbg.setLang(1L);
//				len.setLang(2L);
//				item.getTranslations().add(lbg);
//				item.getTranslations().add(len);
//				
//				classifDAO.doSimpleSave(item);
				
				classif.add(item);		
				
				oblMap.put(item.getCodeExt(), 0L);
			}
			
			
   			
   		} catch (Exception e) {		
   			e.printStackTrace();
   		}
    	
    	return classif;
    }
	

}
