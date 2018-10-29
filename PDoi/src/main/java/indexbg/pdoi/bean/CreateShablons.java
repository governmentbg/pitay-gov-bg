package indexbg.pdoi.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.aspose.words.SaveFormat;
import com.indexbg.system.db.JPA;
import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.InvalidParameterException;
import com.indexbg.system.exceptions.ObjectInUseException;
import com.indexbg.system.exceptions.ObjectNotFoundException;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.Application;
import indexbg.pdoi.db.Files;
import indexbg.pdoi.db.dao.FilesDAO;
import indexbg.pdoi.system.Constants;
import indexbg.pdoi.system.MSWordBookmarks;
import indexbg.pdoi.system.SystemData;
import indexbg.pdoi.system.UserData;


public class CreateShablons {

	private static final Logger LOGGER = Logger.getLogger(CreateShablons.class);
	
	/** Взима шаблона на заявлението от базата
	 * 
	 * @param ud
	 * @return
	 * @throws ObjectNotFoundException
	 * @throws DbErrorException
	 */
	public Files getShablon(UserData ud) throws ObjectNotFoundException, DbErrorException  { 
		Files shabl = null;
		try {
			
			List<Files> shabloni = new FilesDAO(ud.getUserId()).findByCodeObjAndIdObj(Constants.CODE_OBJECT_APPLICATION, Long.valueOf(-1));
		
			if (shabloni == null || shabloni.isEmpty()){
				throw new ObjectNotFoundException("Липсва шаблон за този вид документ !!!");				
			} else {
				shabl =  shabloni.get(0);
			}			
			
		 } catch (DbErrorException e) {
			LOGGER.error("Грешка четене  от базата!!!", e);
			throw new DbErrorException("Грешка четене  от базата!!!");
		 } 		
		
		return shabl;
		
	}
	
	/** Попълва празния шаблон с данни от заявлението
	 * 
	 * @param applic
	 * @param sd
	 * @param ud
	 * @return
	 * @throws DbErrorException
	 * @throws ObjectNotFoundException
	 * @throws Exception
	 */
	public Files createShablApplic(Application applic, SystemData sd, UserData ud) throws  DbErrorException, ObjectNotFoundException, Exception {
		
		Files filledFileShablon = null;
		
		try{
			byte [] bytearray = null;
			
			//1. Взема шаблона от базата
			Files shabl = getShablon(ud);
			shabl = new FilesDAO(ud.getUserId()).findById(shabl.getId());
			
			if (shabl == null){
				return null;
			}
			
			// 2. Зарежда лиценза за работа с MS Word documents.
			new MSWordBookmarks().setLicense();
			
			// 3. Създава празен документ от шаблона 
			com.aspose.words.Document docEmptyShablon = new com.aspose.words.Document(new ByteArrayInputStream(shabl.getContent()));
			
			// 4. Създава попълнен документ от шаблона
			com.aspose.words.Document docFilledShablon = null;
			
			docFilledShablon = fillApplicShablon(applic, docEmptyShablon, sd, ud);
			
			ByteArrayOutputStream dstStream = new ByteArrayOutputStream();
			//docFilledShablon.save(dstStream, SaveFormat.DOCX);
			docFilledShablon.save(dstStream, SaveFormat.PDF);
			bytearray = dstStream.toByteArray();
			
			if (bytearray !=null){ 
				// 5. Създава файла от създадения MS Word документ					
				String fileName = "application";
				if (fileName == null || fileName.isEmpty())
					fileName = shabl.getFilename();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
	
				//fileName =  fileName.split("\\.")[0] + "_" + sdf.format(new Date())+".docx";
				fileName =  fileName.split("\\.")[0] + "_" + sdf.format(new Date())+".pdf";
				
				filledFileShablon = new Files();
				filledFileShablon.setFilename(fileName);					
				filledFileShablon.setCodeObject(Constants.CODE_OBJECT_APPLICATION);
				filledFileShablon.setIdObject(applic.getId());
				filledFileShablon.setContent(bytearray);	
				
//					if (filledFileShablon != null){
//						delSaveFilledShablon(filledFileShablon, applic.getId(), ud, Constants.CODE_OBJECT_APPLICATION);
//					}
				
				
			}
		
		} catch (DbErrorException e) {
			LOGGER.error("Грешка при извличане на шаблон от базата!!!", e);
			throw new DbErrorException("Грешка при попълването на шаблона с данни!!!");
		} catch (ObjectNotFoundException e) {
			LOGGER.error("Липсва шаблон за този вид документ!!!", e);
			throw new ObjectNotFoundException("Липсва шаблон за този вид документ!!!");
		} catch (Exception e) {
			LOGGER.error("Грешка при попълването на шаблона с данни!!!", e);
			throw new DbErrorException("Грешка при попълването на шаблона с данни!!!");
		}	

		return filledFileShablon;
	
	}
		
	/** Запис на попълнен шаблон
	 * !!! преди да го запише, изтрива ко вече има такъв!!!
	 * @param filledFileShablon
	 * @param idObj
	 * @param ud
	 * @param sd
	 * @param codeObj
	 * @return
	 * @throws InvalidParameterException
	 * @throws DbErrorException
	 */
	public String delSaveFilledShablon(Files filledFileShablon, Long idObj, UserData ud, Long codeObj)  throws  DbErrorException {
		
		JPA.getUtil().begin();
		
		try{
			
			// Iztriva old filled shablon 
			List<Files> fil = null;
			fil = new FilesDAO(ud.getUserId()).findByCodeObjAndIdObj(codeObj, idObj);

			// Iztriva old shablon 
				
			if (fil != null && fil.size() > 0){
				
				// TODO towa otdolu e komentirano, t.k. pri flush w v AbstractDAO zavisva !!!
				new FilesDAO(ud.getUserId()).deleteById(fil.get(0).getId());
			}

			// Записва в базата  на картата попълнен шаблон 
			new FilesDAO(ud.getUserId()).save(filledFileShablon);
			
			JPA.getUtil().commit();		
		
		} catch (HibernateException e) {			
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при изтриване на попълнен шаблон в базата!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR,"Грешка при запис на попълнен шаблон в базата!!!", e.getMessage());			
			
		} catch (ObjectInUseException e) {			
			JPA.getUtil().rollback();
			LOGGER.error("Обектът се използва!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR,"Обектът се използва!!!", e.getMessage());
		
		} catch (Exception e) {
			JPA.getUtil().rollback();
			LOGGER.error("Грешка при изтриване на попълнен шаблон в базата!!!", e);
			JSFUtils.addGlobalMessage(FacesMessage.SEVERITY_ERROR,"Грешка при изтриване на попълнен шаблон в базата!!!", e.getMessage());
		
		} finally {
			JPA.getUtil().closeConnection();
		}
		
		return null;
	
	}
	
	/** Слага на букмарките данните, които се взимат от заявлението
	 * 
	 * @param applic
	 * @param pattern
	 * @param sd
	 * @param ud
	 * @return
	 * @throws DbErrorException
	 * @throws ObjectNotFoundException
	 * @throws Exception
	 */
	public com.aspose.words.Document fillApplicShablon(Application applic, com.aspose.words.Document pattern, SystemData sd, UserData ud) throws DbErrorException, ObjectNotFoundException, Exception {
		
		pattern.getRange().getBookmarks().get("zdoi").setText(sd.decodeItem(Constants.CODE_SYSCLASS_ADM_REGISTRY, applic.getResponseSubjectId(), 1L, new Date(), ud.getUserId()));
		
		pattern.getRange().getBookmarks().get("uri").setText(applic.getApplicationUri());
		
		String name = "";
		name = applic.getFullNames();
		
		if (applic.getApplicantType().equals(Constants.CODE_ZNACHENIE_TYPE_APPLICANT_URID_LICE)) {
			name += ", " + applic.getHeadoffice();				
		}
		
		pattern.getRange().getBookmarks().get("name").setText(name);
		
		pattern.getRange().getBookmarks().get("phone").setText(applic.getPhone());
		
		pattern.getRange().getBookmarks().get("mail").setText(applic.getEmail());
		
		pattern.getRange().getBookmarks().get("darjava").setText(sd.decodeItem(Constants.CODE_SYSCLASS_COUNTRIES, applic.getCountry(), 1L, new Date(), ud.getUserId()));
		
		pattern.getRange().getBookmarks().get("oblast").setText(sd.decodeItem(Constants.CODE_SYSCLASS_EKATTE, applic.getRegion(), 1L, new Date(), ud.getUserId()));
		
		pattern.getRange().getBookmarks().get("obshtina").setText(sd.decodeItem(Constants.CODE_SYSCLASS_EKATTE, applic.getMunicipality(), 1L, new Date(), ud.getUserId()));
		
		pattern.getRange().getBookmarks().get("nasMesto").setText(sd.decodeItem(Constants.CODE_SYSCLASS_EKATTE, applic.getTown(), 1L, new Date(), ud.getUserId()));
		
		pattern.getRange().getBookmarks().get("adres").setText(applic.getAddress());
		
		pattern.getRange().getBookmarks().get("postCode").setText(applic.getPostCode());
		
		pattern.getRange().getBookmarks().get("zdoi1").setText(sd.decodeItem(Constants.CODE_SYSCLASS_ADM_REGISTRY, applic.getResponseSubjectId(), 1L, new Date(), ud.getUserId()));
		
		pattern.getRange().getBookmarks().get("zapitvane").setText(applic.getRequest());
		
		List<Files> filesList = new FilesDAO(ud.getUserId()).findByCodeObjAndIdObj(applic.getCodeMainObject(), applic.getId());
		
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
		int count = 1;
		StringBuilder files = new StringBuilder();
		
		if (!filesList.isEmpty()) {			
			for (Files fileTmp : filesList) {
				files.append(count);
				files.append(". ");
				if (fileTmp.getDescription() != null && !fileTmp.getDescription().isEmpty()) {
					files.append(fileTmp.getDescription());
				} else {
					files.append(fileTmp.getFilename());
				}
				files.append(".");					
				files.append("\n");
				
				count++;
			}
			
			pattern.getRange().getBookmarks().get("files").setText(files.toString());
		}
		
		if(applic.getRegistrationDate()!=null){
			pattern.getRange().getBookmarks().get("dataReg").setText(sdfDate.format(applic.getRegistrationDate()).toString() + " г.");			
		} else {
			pattern.getRange().getBookmarks().get("dataReg").setText("");//TODO dali netrqbva da se slaga tekst
		}
		
		return pattern;			
	}	
	
}