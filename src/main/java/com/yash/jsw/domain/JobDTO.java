package com.yash.jsw.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author archit.kashyap
 *
 */
@JsonInclude(Include.NON_NULL)
public class JobDTO {

	private Integer job_id;
	private String jobName;
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	private List<JobDTO> jobs;
	
	public Integer getJob_id() {
		return job_id;
	}
	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public List<JobDTO> getJobs() {
		return jobs;
	}
	public void setJobs(List<JobDTO> jobs) {
		this.jobs = jobs;
	}
	
}
