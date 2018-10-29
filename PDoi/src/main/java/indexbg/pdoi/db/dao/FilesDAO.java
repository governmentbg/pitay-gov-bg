package indexbg.pdoi.db.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import com.indexbg.system.BaseSystemData;
import com.indexbg.system.db.TrackableDAO;

import com.indexbg.system.exceptions.DbErrorException;
import com.indexbg.system.exceptions.ObjectInUseException;
import com.indexbg.system.utils.JSFUtils;

import indexbg.pdoi.db.Files;

/**
 * DAO for {@link Files}
 *
 * @author belev
 */
public class FilesDAO extends TrackableDAO<Files> {

	/**
	 * @param userId
	 */
	public FilesDAO(Long userId) {
		super(userId);
	}

	/** Записва/актуализира обект прикачен файл 
	 * @param entity - обект файл
	 * @throws DbErrorException
	 * @throws ObjectInUseException
	 */
	@Override
	public Files save(Files entity) throws DbErrorException {

		// TODO get from prop
		BaseSystemData systemData = (BaseSystemData) JSFUtils.getManagedBean("systemData");
		String diskPath = systemData.getSettingsValue("file_storage_path");
		boolean saveToDisk = diskPath != null && !diskPath.isEmpty();

		if (saveToDisk) {
			try {
				// TODO get from prop
				diskPath = "D:\\";
				// Random string
				String uuid = UUID.randomUUID().toString();
				// File name
				String generatedFilename = uuid + "-" + entity.getFilename();
				// File save path
				String fileSavePath = diskPath + generatedFilename;
				// Save file to disk
				Path path = Paths.get(fileSavePath);
				InputStream stream = new ByteArrayInputStream(entity.getContent());
				java.nio.file.Files.copy(stream, path, StandardCopyOption.REPLACE_EXISTING);
				stream.close();
				// Set path on entity
				entity.setPath(fileSavePath);
			} catch (IOException e) {
				throw new DbErrorException(e.getMessage());
			}
		}
		return super.save(entity);
	}

	/** Изтрива от БД на прикачен файл 
	 * @param entity - обект файл
	 * @throws DbErrorException
	 * @throws ObjectInUseException
	 */
	@Override
	public void delete(Files entity) throws DbErrorException, ObjectInUseException {
		if (entity != null && entity.getPath() != null && !entity.getPath().isEmpty()) {
			try {
				Path path = Paths.get(entity.getPath());
				java.nio.file.Files.delete(path);
			} catch (IOException e) {
				throw new DbErrorException(e.getMessage());
			}
		}
		super.delete(findById(entity.getId()));
	}

	
	/** Изтрива от БД на прикачен файл 
	 * @param id - ид. на прикачен файла
	 * @throws DbErrorException
	 * @throws ObjectInUseException
	 */
	@Override
	public void deleteById(Object id) throws DbErrorException, ObjectInUseException {
		Files tmp = findById(id);
		if (tmp != null && tmp.getPath() != null && !tmp.getPath().isEmpty()) {
			try {
				Path path = Paths.get(tmp.getPath());
				java.nio.file.Files.delete(path);
			} catch (IOException e) {
				throw new DbErrorException(e.getMessage());
			}
		}
		super.deleteById(id);
	}

	/** Извлича от БД на конкретни атрибути на прикачените файлове 
	 * @param codeObj - код на обекта, към който е прикачен файла
	 * @param idObj - ид. на обекта, към който е прикачен файла
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Files> findByCodeObjAndIdObj(Long codeObj, Long idObj) throws DbErrorException {

		try {

			Query query = createNativeQuery("select f.id, f.id_object, f.code_object, f.filename, f.description, f.visible_on_site, f.content_type "
										 + " from files f where f.code_object = ? and f.id_object = ? order by id ", "filterByCodeObjAndId"); 

			query.setParameter(1, codeObj);
			query.setParameter(2, idObj);

			return query.getResultList();

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на файлове по код на обекта и ид на обекта", e);
		}

	}
	
	/** Извлича от БД на конкретни атрибути на прикачените файлове 
	 * @param codeObj - код на обекта, към който е прикачен файла
	 * @param idObj - ид. на обекта, към който е прикачен файла
	 * @param visibleOnSite - статус на видимост на прикачения файл
	 * @return
	 * @throws DbErrorException
	 */
	@SuppressWarnings("unchecked")
	public List<Files> findByCodeObjAndIdObj(Long codeObj, Long idObj, boolean visibleOnSite) throws DbErrorException {

		try {

			Query query = createNativeQuery("select f.id, f.id_object, f.code_object, f.filename, f.description, f.visible_on_site, f.content_type "
										 + " from files f where f.code_object = ? and f.id_object = ? and f.visible_on_site = ? order by id ", "filterByCodeObjAndId"); 

			query.setParameter(1, codeObj);
			query.setParameter(2, idObj);
			query.setParameter(3, visibleOnSite);

			return query.getResultList();

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при зареждане на файлове по код на обекта и ид на обекта", e);
		}

	}
	
	/** Актуализира конкретни атрибути на прикачените файлове  
	 * @param description
	 * @param visibleOnSite
	 * @param userLastMod
	 * @param dateLastMod
	 * @param id
	 * @throws DbErrorException
	 */
	public void updateFile(String description, boolean visibleOnSite, Long userLastMod, Date dateLastMod, Long id) throws DbErrorException {
		
		try {

			Query query = createNativeQuery("update files set description = ?, visible_on_site = ?, user_last_mod = ?, date_last_mod = ? where id = ?"); 
			
			query.setParameter(1, description);
			query.setParameter(2, visibleOnSite);
			query.setParameter(3, userLastMod);
			query.setParameter(4, dateLastMod);
			query.setParameter(5, id);

			query.executeUpdate();

		} catch (Exception e) {
			throw new DbErrorException("Възникна грешка при ъпдейтване на файловете!!!", e);
		}
		
	}
	
	/** Изтрива от БД на връзка: запис от таблица files с таблица  files_text 
	 * @param id - ид. на прикачен файла
	 * @throws DbErrorException
	 * @throws ObjectInUseException
	 */
	
	public void delFromFilesText(Long id) throws DbErrorException, ObjectInUseException {

			try {
				Query query = createNativeQuery("DELETE FROM files_text WHERE id_files = ?"); 
				query.setParameter(1, id);
				query.executeUpdate();
			} catch (Exception e) {
				throw new DbErrorException(e.getMessage());
			}

	}
}