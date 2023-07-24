package com.yash.jsw.service.jobs;

import java.util.List;

import com.yash.jsw.model.Job;

public interface JobsService {

	public List<Job> getJobs() throws Exception;
	
	public Integer addJob(Job job) throws Exception;
	
	 public Job getJob(String jobname);
}
