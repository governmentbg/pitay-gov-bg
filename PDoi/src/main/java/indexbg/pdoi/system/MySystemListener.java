package indexbg.pdoi.system;

import java.util.Date;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.indexbg.ocr.quartz.OCRJob;
import com.indexbg.system.BaseSystemData;
import com.indexbg.system.db.JPA;
import com.indexbg.system.quartz.BaseJobListener;
import com.indexbg.system.utils.JSFUtils;


/**
 * Слуша за стартиране и спиране на приложението.
 */
public class MySystemListener implements SystemEventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(MySystemListener.class);
	
	private static Map<String, String> allParams;
	private Scheduler scheduler;
	private BaseSystemData sd;
	
	/** @see SystemEventListener#isListenerForSource(Object) */
	@Override
	public boolean isListenerForSource(Object source) {
		return source instanceof Application;
	}

	/** @see SystemEventListener#processEvent(SystemEvent) */
	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {

		if (event instanceof PostConstructApplicationEvent) {
			myApplicationInit(event);

		} else if (event instanceof PreDestroyApplicationEvent) {
			myApplicationDestroy(event);
		}
	}
	
	private void myApplicationDestroy(final SystemEvent event) {
		LOGGER.info("myApplicationDestroy - START");

		try {
			LOGGER.info("Спиране на quartz процесите.");

			// Use this properties file instead of quartz.properties
//			System.setProperty("org.quartz.properties", "pdoi_quartz.properties");
			this.scheduler = new StdSchedulerFactory("pdoi_quartz.properties").getScheduler();

			//TO DO трябва да се види кога и дали изобщо трява да се зачистват данните свързани с този scheduler
//			scheduler.clear();
			
			//scheduler.clear();
			scheduler.shutdown(); 


		} catch (Exception e) {
			LOGGER.error("Error - myApplicationDestroy !!!!", e);
		}

		try {
			LOGGER.info("Спиране на JPA.");

			JPA.shutdown();

		} catch (Exception e) {
			LOGGER.error("Error - myApplicationDestroy !!!!", e);
		}

		LOGGER.info("myApplicationDestroy - END");
	}

	private void myApplicationInit(final SystemEvent event) {
		LOGGER.info("myApplicationInit - START");

		this.sd = (BaseSystemData) JSFUtils.getManagedBean("systemData");
		allParams = sd.getSystemPropSettings();
		
		try {
			LOGGER.info("Инициализиране на quartz процесите.");
			
			// Use this properties file instead of quartz.properties
//			System.setProperty("org.quartz.properties", "pdoi_quartz.properties");
			this.scheduler = new StdSchedulerFactory("pdoi_quartz.properties").getScheduler();
			
			//добавяm joba за OCR към scheduler-а
			//scheduleOCRJob();
			
			// Проверява за обработени съобщения от ЕГОВ!
			scheduleEgovJob();
			
			boolean start = Boolean.valueOf(this.sd.getSettingsValue("quartz.scheduler.pdoi.start"));
			
			if (start) {
				this.scheduler.start();
			}
			
		} catch (Exception e) {
			LOGGER.error("Error - myApplicationInit !!!!", e);
		}

		LOGGER.info("myApplicationInit - END");
	}
	
	
	private void scheduleOCRJob () throws SchedulerException {
		
		Trigger trigOCR = TriggerBuilder.newTrigger().withIdentity("trigOcr", "pdoi") //
				.withDescription("Тригер, който се стартира на всеки 5 минути") //
				.withSchedule(CronScheduleBuilder.cronSchedule(this.sd.getSettingsValue("quartz.ocr.trigger.cron"))) //
				.startAt(new Date(System.currentTimeMillis() + 17000L)) //
				.build(); //
		JobDetail jobOCR = JobBuilder.newJob(OCRJob.class) //
				.withIdentity("jobOCR", "pdoi") //
				.withDescription("Извличане на текст от PDF и снимки (JPG, BMP, TIFF, GIF)") //
				.build(); //
		
		//Подават се параметрите през jobDataMap
		JobDataMap jobDataMap=  jobOCR.getJobDataMap();
		
		jobDataMap.put("fileTable", allParams.get("fileTable"));
		jobDataMap.put("fileId", allParams.get("fileId"));
		jobDataMap.put("fileContent", allParams.get("fileContent"));
		jobDataMap.put("fileTextTable", allParams.get("fileTextTable"));
		jobDataMap.put("fileTextFK", allParams.get("fileTextFK"));
		jobDataMap.put("fileTextContent", allParams.get("fileTextContent"));
		jobDataMap.put("ocr_tessdataPath", allParams.get("ocr_tessdataPath"));
		jobDataMap.put("ocr_lang", allParams.get("ocr_lang"));
		
		JobKey jobKey = jobOCR.getKey();
		this.scheduler.getListenerManager().addJobListener(new BaseJobListener("pdoi"), KeyMatcher.keyEquals(jobKey));
		
		if (this.scheduler.checkExists(trigOCR.getKey())) {
			this.scheduler.rescheduleJob(trigOCR.getKey(), trigOCR);
		} else {
			this.scheduler.scheduleJob(jobOCR, trigOCR);
		}

	}
	
	
	private void scheduleEgovJob () throws SchedulerException {
		
		Trigger trigEgov = TriggerBuilder.newTrigger().withIdentity("trigEgov", "pdoi") //
				.withDescription("Тригер, който се стартира на всеки 5 минути") //
				.withSchedule(CronScheduleBuilder.cronSchedule(this.sd.getSettingsValue("quartz.ocr.trigger.cron"))) //
				.startAt(new Date(System.currentTimeMillis() + 17000L)) //
				.build(); //
		JobDetail jobEgov = JobBuilder.newJob(MyRunnableEgovMessages.class) //
				.withIdentity("jobEgov", "pdoi") //
				.withDescription("Проверява за обработени съобщения от ЕГОВ!") //
				.build(); //
		
		JobKey jobKey = jobEgov.getKey();
		this.scheduler.getListenerManager().addJobListener(new BaseJobListener("pdoiEgov"), KeyMatcher.keyEquals(jobKey));
		
		if (this.scheduler.checkExists(trigEgov.getKey())) {
			this.scheduler.rescheduleJob(trigEgov.getKey(), trigEgov);
		} else {
			this.scheduler.scheduleJob(jobEgov, trigEgov);
		}

	}
	
}