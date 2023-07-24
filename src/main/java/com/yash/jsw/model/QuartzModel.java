package com.yash.jsw.model;

/**
 * @author kartavya.soni
 */

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class QuartzModel implements Serializable {
	
	private static final long serialVersionUID = 919893606791L;
	private String jobName;
	
	private String status;
	private String errorMsg;
	private int responseStatus;
	private String msg;
	private String scheduleRadio;
	private Integer responseCode;
	
	private String minuteInterval;
	private String hourInterval;
	private String dayInterval;
	private String monthDate;
	private String month;
	private String dayOfWeek;
	private String time;
	private String startDate;
	private boolean active;
	private String previousFireTime;
	private String nextFireTime;
	List<QuartzModel> quartzList;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	

	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getScheduleRadio() {
		return scheduleRadio;
	}
	public void setScheduleRadio(String bolScheduleRadio) {
		this.scheduleRadio = bolScheduleRadio;
	}
	public String getMinuteInterval() {
		return minuteInterval;
	}
	public void setMinuteInterval(String minuteInterval) {
		this.minuteInterval = minuteInterval;
	}
	public String getHourInterval() {
		return hourInterval;
	}
	public void setHourInterval(String hourInterval) {
		this.hourInterval = hourInterval;
	}
	public String getDayInterval() {
		return dayInterval;
	}
	public void setDayInterval(String dayInterval) {
		this.dayInterval = dayInterval;
	}
	

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public List<QuartzModel> getQuartzList() {
		return quartzList;
	}
	public void setQuartzList(List<QuartzModel> quartzList) {
		this.quartzList = quartzList;
	}
	public String getPreviousFireTime() {
		return previousFireTime;
	}
	public void setPreviousFireTime(String previousFireTime) {
		this.previousFireTime = previousFireTime;
	}
	public String getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(String nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	
}
