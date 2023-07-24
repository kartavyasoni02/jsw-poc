package com.yash.jsw.utility;

import static org.quartz.TriggerKey.triggerKey;

import java.sql.Timestamp;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yash.jsw.model.QuartzModel;
import static com.yash.jsw.utility.GlobalConstant.JOB_GROUP;

public class QuartzScheduler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzScheduler.class);
	
	private static QuartzScheduler instance;
	 
    private QuartzScheduler(){
       //Private Constructor
    }
	
    public synchronized static QuartzScheduler getInstance()
    {
           if (instance==null)
           {
              instance = new QuartzScheduler();
              LOGGER.debug("QuartzScheduler Class Object creatred...!!!");
           }
          else{
        	  LOGGER.debug("QuartzScheduler Class Object not Creatred just returned Created one...!!!");
          }
              return instance;
       }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void scheduleJob(Class jobClass, QuartzModel model, String cronExpression, String triggerName, 
			Timestamp startTs ,Scheduler sched){
		LOGGER.debug("Scheduler Service: Re-Schedulling the Job: " + model.getJobName());
		
		try {
			if(sched.checkExists(new JobKey(model.getJobName()))){
				sched.deleteJob(new JobKey(model.getJobName()));
				sched.unscheduleJob(triggerKey(triggerName, JOB_GROUP));
			}
			
			JobDetail job = JobBuilder
					.newJob(jobClass)
					.withIdentity(model.getJobName()).storeDurably(true).build();
			job.requestsRecovery();

			Trigger trigger = TriggerBuilder
					.newTrigger()
					.withIdentity(triggerName.toString(), JOB_GROUP)
					.withSchedule(
							CronScheduleBuilder
									.cronSchedule(cronExpression)
									.withMisfireHandlingInstructionFireAndProceed()).forJob(job.getKey()).build();
			trigger.getJobDataMap().putAsString("finishedAt", System.currentTimeMillis());
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("Error in schedulerJob()", e);
		}
		
	}
	public void resumeScheduleIfAlreadyScheduled(String jobName, String triggerName, Scheduler sched){
		try {
			if(sched.checkExists(new JobKey(jobName))){
			//	sched.deleteJob(new JobKey(jobName));
				//sched.pauseJob(jobName, "groupName");
				sched.resumeJob(new JobKey(jobName));
			//	sched.unscheduleJob(triggerKey(triggerName, JOB_GROUP));
			}
		} catch (SchedulerException e) {
			LOGGER.error("Error in deleteScheduleIfAlreadyScheduled()", e);
		}
	}
	public void deleteScheduleIfAlreadyScheduled(String jobName, String triggerName, Scheduler sched){
		try {
			if(sched.checkExists(new JobKey(jobName))){
			//	sched.deleteJob(new JobKey(jobName));
				//sched.pauseJob(jobName, "groupName");
				sched.pauseJob(new JobKey(jobName));
			//	sched.unscheduleJob(triggerKey(triggerName, JOB_GROUP));
			}
		} catch (SchedulerException e) {
			LOGGER.error("Error in deleteScheduleIfAlreadyScheduled()", e);
		}
	}
}
