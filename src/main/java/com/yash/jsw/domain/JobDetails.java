package com.yash.jsw.domain;
import java.io.Serializable;
/**
 * @author kartavya.soni
 */
import java.sql.Timestamp;

public class JobDetails implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 919893606791L;
	private Long id;
    private String jobName;
    private String jobClass;
    private String cronExpression;
    private boolean active;
	private String jobStatus;
    private Timestamp startTs;
    private Timestamp endTs;
    private Timestamp lastRunTime;
    private String updatedBy;
    private Timestamp updatedTs;
    private String scheduleType;
	private String minuteInterval;
	private String hourInterval;
	private String dayInterval;
	private String monthDate;
	private String month;
	private String dayOfWeek;
	private String time;

    public Long getId() {
		return id;
	}

	public Timestamp getStartTs() {
		return startTs;
	}

	public void setStartTs(Timestamp startTs) {
		this.startTs = startTs;
	}

	public Timestamp getEndTs() {
		return endTs;
	}

	public void setEndTs(Timestamp endTs) {
		this.endTs = endTs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public Timestamp getLastRunTime() {
		return lastRunTime;
	}

	public void setLastRunTime(Timestamp lastRunTime) {
		this.lastRunTime = lastRunTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedTs() {
		return updatedTs;
	}

	public void setUpdatedTs(Timestamp updatedTs) {
		this.updatedTs = updatedTs;
	}

	public JobDetails() {
    }

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
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

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
