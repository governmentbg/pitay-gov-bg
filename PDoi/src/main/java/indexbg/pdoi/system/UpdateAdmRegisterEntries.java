package indexbg.pdoi.system;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.system.SysConstants;
import com.indexbg.system.db.JPA;
import com.indexbg.system.quartz.BaseJobResult;

import indexbg.pdoi.db.dao.ResponseSubjectDao;

@DisallowConcurrentExecution
public class UpdateAdmRegisterEntries implements Job {

	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateAdmRegisterEntries.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		BaseJobResult jobResult = new BaseJobResult();
		jobResult.setStatus(SysConstants.JOB_STATUS_OK);
		
		
		try {
			ResponseSubjectDao respSubDAO = new ResponseSubjectDao(null,null);
			String comment = respSubDAO.updateAdmRegisterEntries();
			
			jobResult.setComment(comment);
			context.setResult(jobResult);

		} catch (Exception e) {
			jobResult.setStatus(SysConstants.JOB_STATUS_WARN);
			LOGGER.error(e.getMessage(), e);
			JobExecutionException ex = new JobExecutionException(e);
			ex.setRefireImmediately(false);
			throw ex;
		} finally {
			JPA.getUtil().closeConnection();
		}

	}
}
