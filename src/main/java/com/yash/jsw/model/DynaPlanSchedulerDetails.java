package com.yash.jsw.model;
/**
 * @author neeraj.bangar
 */
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class DynaPlanSchedulerDetails {
	private Integer id;
	private Integer jobId;
	private Timestamp fierTime;
	private Integer planId;
	private String jobName;
	private String planName;
	List<Object> dynaPlanList;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public Timestamp getFierTime() {
		return fierTime;
	}
	public void setFierTime(Timestamp fierTime) {
		this.fierTime = fierTime;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public List<Object> getDynaPlanList() {
		return dynaPlanList;
	}
	public void setDynaPlanList(List<Object> dynaPlanList) {
		this.dynaPlanList = dynaPlanList;
	}
	
}
