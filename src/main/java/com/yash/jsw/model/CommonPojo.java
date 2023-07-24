package com.yash.jsw.model;

import org.joda.time.DateTime;

public class CommonPojo {
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer planId;
	private Integer processTime;
	private DateTime fireTime;
	private String status;
	
	public DateTime getFireTime() {
		return fireTime;
	}
	public void setFireTime(DateTime fireTime) {
		this.fireTime = fireTime;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
	}
	public Integer getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Integer processTime) {
		this.processTime = processTime;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
