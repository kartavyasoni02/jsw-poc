package com.yash.jsw.dao;

import java.util.List;

import com.yash.jsw.model.Job;

/**
 * 
 * @author archit.kashyap
 *
 */
public interface JobsDAO {

	public List<Job> getJobs();
	
	public void addJob(Job job) throws Exception;
	
	public Job getJob(String usersJob);
}
