package com.yash.jsw.service.quartz.impl;

/**
 * @author kartavya.soni
 */
import static com.cronutils.model.CronType.QUARTZ;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import com.yash.jsw.dao.DynaPlanDAO;
import com.yash.jsw.dao.JobDao;
import com.yash.jsw.domain.JobDetails;
import com.yash.jsw.job.JobLauncher;
import com.yash.jsw.model.CommonPojo;
import com.yash.jsw.model.DynaPlanSchedulerDetails;
import com.yash.jsw.model.QuartzModel;
import com.yash.jsw.service.quartz.QuartzService;
import com.yash.jsw.utility.CronGenetorUtility;
import com.yash.jsw.utility.DateUtility;
import com.yash.jsw.utility.GlobalConstant;
import com.yash.jsw.utility.QuartzScheduler;

@Service
@Transactional
public class QuartzServiceImpl implements QuartzService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzServiceImpl.class);
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private JobDao jobDao;
	@Autowired
	private	DynaPlanDAO dynaPlanDAO;
	@Override
	public QuartzModel scheduleJobs(QuartzModel model) {
		LOGGER.debug("Scheduler Service: SchedulerModel is: " + model.toString());
		Scheduler sched = schedulerFactoryBean.getScheduler();
		try {
			String cronExpression = CronGenetorUtility.generateCronExpression(model);
			Timestamp startTs = new Timestamp(System.currentTimeMillis());

			jobDao.persistJobDetail(model, cronExpression, startTs, JobLauncher.class);
			QuartzScheduler.getInstance().scheduleJob(JobLauncher.class, model, cronExpression,
					model.getJobName() + "_TRIGGER", startTs, sched);

			model.setResponseCode(GlobalConstant.OBJECT_FOUND);

		} catch (Exception ex) {
			LOGGER.error("Error in saveAndScheduleAllJobs() : ", ex);
		}
		return model;
	}

	@Override
	public String stopJob(String jobName) {
		LOGGER.debug("Scheduler Service: Stopping the Job {}", jobName);
		Scheduler sched = schedulerFactoryBean.getScheduler();
		try {

			QuartzScheduler.getInstance().deleteScheduleIfAlreadyScheduled(jobName, jobName + "_TRIGGER", sched);
			jobDao.updateJobIsActive(jobName, false);
			jobDao.updateJobStatus(jobName, GlobalConstant.JobStatus.PAUSED.toString());
		} catch (Exception e) {
			LOGGER.error("Error in stopSingleJobs() : ", e);
			LOGGER.debug("Scheduler Service: Exception occurred while Stopping the Job {}", jobName);
		}
		return "Success";
	}

	@Override
	public String startJob(String jobName) {
		LOGGER.debug("Scheduler Service: Start the Job {}", jobName);
		Scheduler sched = schedulerFactoryBean.getScheduler();
		try {
			QuartzScheduler.getInstance().resumeScheduleIfAlreadyScheduled(jobName, jobName + "_TRIGGER", sched);
			jobDao.updateJobIsActive(jobName, true);
			jobDao.updateJobStatus(jobName, GlobalConstant.JobStatus.SCHEDULED.toString());

		} catch (Exception e) {
			LOGGER.error("Error in stopSingleJobs() : ", e);
			LOGGER.debug("Scheduler Service: Exception occurred while Stopping the Job {}", jobName);
		}
		return "Success";
	}

	/**
	 *
	 * @param jobName
	 * @return String
	 */
	public String getScheduledJobDescription(String jobName) {
		String message = GlobalConstant.JOB_IS_NOT_SCHEDULED;
		JobKey jobKey = new JobKey(jobName, GlobalConstant.QUARTZ_GROUP);
		try {
			JobDetail jobDetail = schedulerFactoryBean.getScheduler().getJobDetail(jobKey);
			if (null != jobDetail) {
				List<? extends Trigger> triggersOfJob = schedulerFactoryBean.getScheduler().getTriggersOfJob(jobKey);
				if (null != triggersOfJob && !triggersOfJob.isEmpty()) {
					CronTrigger trigger = (CronTrigger) triggersOfJob.get(0);
					String cronExpression = trigger.getCronExpression();
					CronDescriptor descriptor = CronDescriptor.instance(Locale.US);
					CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(QUARTZ));
					message = descriptor.describe(parser.parse(cronExpression));
				}

			}
		} catch (SchedulerException e) {
			// BatchAdminLogger.getLogger().error(e.getMessage(), e);
		}
		return message;
	}

	@Override
	public List<JobDetails> getAllJobs() {
		LOGGER.debug("Scheduler Service: Getting All the Job From DB.");
		return jobDao.findAll();
	}

	@Override
	public List<QuartzModel> populateAllScheduledJobs() {
		LOGGER.debug("Scheduler Service: populateAllScheduledJobs");
		List<QuartzModel> modelList = null;
		List<JobDetails> jobDetails = jobDao.findAll();
		if (null != jobDetails && !jobDetails.isEmpty()) {
			List<Map<String, Object>> listOfJob = jobDao.findTrigger();
			modelList = new ArrayList<QuartzModel>();
			Map<String, Map<String, Object>> finalJobMap = this.getMapOfJob(listOfJob);
			for (JobDetails job : jobDetails) {
				QuartzModel model = new QuartzModel();
				model.setJobName(job.getJobName());
				if (finalJobMap != null) {
					Map<String, Object> jobObject = finalJobMap.get(job.getJobName());
					model.setNextFireTime(
							DateUtility.convertFromTimestampLongToString((long) jobObject.get("NEXT_FIRE_TIME")));
					if ((long) jobObject.get("PREV_FIRE_TIME") > 0) {
						model.setPreviousFireTime(
								DateUtility.convertFromTimestampLongToString((long) jobObject.get("PREV_FIRE_TIME")));
					} else {
						model.setPreviousFireTime("N/A");
					}
				}
				model.setStatus(job.getJobStatus());
				model.setScheduleRadio(job.getScheduleType());
				model.setHourInterval(job.getHourInterval());
				model.setDayInterval(job.getDayInterval());
				model.setMinuteInterval(job.getMinuteInterval());
				model.setDayOfWeek(job.getDayOfWeek());
				model.setMonth(job.getMonth());
				model.setMonthDate(job.getMonthDate());
				model.setTime(job.getTime());
				model.setActive(job.isActive());
				modelList.add(model);
			}
		}
		return modelList;
	}

	private Map<String, Map<String, Object>> getMapOfJob(List<Map<String, Object>> listOfJob) {
		Map<String, Map<String, Object>> finalMap = null;
		if (listOfJob != null && !listOfJob.isEmpty()) {
			finalMap = new HashMap<String, Map<String, Object>>();
			for (Map<String, Object> map : listOfJob) {
				finalMap.put((String) map.get("JOB_NAME"), map);
			}
		}
		return finalMap;
	}

	@Override
	public QuartzModel getScheduledJob(String jobName) {
		JobDetails job = jobDao.findJobByJobName(jobName);
		QuartzModel model = null;
		if (null != job) {
			model = new QuartzModel();
			model.setJobName(job.getJobName());
			model.setStatus(job.getJobStatus());
			model.setScheduleRadio(job.getScheduleType());
			model.setHourInterval(job.getHourInterval());
			model.setDayInterval(job.getDayInterval());
			model.setMinuteInterval(job.getMinuteInterval());
			model.setDayOfWeek(job.getDayOfWeek());
			model.setMonth(job.getMonth());
			model.setMonthDate(job.getMonthDate());
			model.setTime(job.getTime());
			model.setActive(job.isActive());
		}
		return model;
	}

}