package com.yash.jsw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author archit.kashyap
 *
 */
@Entity
@Table(name = "JOB")
public class Job {

	@Id
	private Integer job_id;
	
	@NotNull(message = "{error.jobs.job.null}")
    @NotEmpty(message = "{error.jobs.job.empty}")
    @Size(max = 50, message = "{error.jobs.job.max}")
    @Column(name = "jobName", length = 50)
	private String jobName;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getJob_id() {
		return job_id;
	}
	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}
	
	
	
}
