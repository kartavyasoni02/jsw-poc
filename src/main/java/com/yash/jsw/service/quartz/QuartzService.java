package com.yash.jsw.service.quartz;

/**
 * @author kartavya.soni
 */
import java.util.List;

import com.yash.jsw.domain.JobDetails;
import com.yash.jsw.model.DynaPlanSchedulerDetails;
import com.yash.jsw.model.QuartzModel;

public interface QuartzService {
	public QuartzModel scheduleJobs(QuartzModel model);

	public String stopJob(String job);

	List<JobDetails> getAllJobs();

	List<QuartzModel> populateAllScheduledJobs();

	public QuartzModel getScheduledJob(String jobName);

	public String startJob(String job);



}