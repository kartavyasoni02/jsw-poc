/**
 * 
 */
package com.yash.jsw.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yash.jsw.domain.JobDetails;

/**
 * @author kartavya.soni
 *
 */
public class JobRowMapper implements RowMapper<JobDetails> {
	
	private static final String END_TS = "END_TS";

	@Override
	public JobDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		JobDetails jobDetail = new JobDetails();
		jobDetail.setId(rs.getLong("ID"));
		jobDetail.setJobName(rs.getString("JOB_NAME"));
		jobDetail.setJobClass(rs.getString("JOB_CLASS"));
		jobDetail.setCronExpression(rs.getString("CRON_EXPSN"));
		jobDetail.setActive(rs.getBoolean("IS_ACTIVE"));
		jobDetail.setJobStatus(rs.getString("JOB_STATUS"));
		jobDetail.setStartTs(rs.getTimestamp("START_TS"));
		jobDetail.setEndTs(rs.getTimestamp(END_TS));
		jobDetail.setUpdatedBy(rs.getString("UPDT_BY"));
		jobDetail.setUpdatedTs(rs.getTimestamp(END_TS));
		jobDetail.setLastRunTime(rs.getTimestamp(END_TS));
		jobDetail.setScheduleType(rs.getString("SCHD_TYPE"));
		jobDetail.setMinuteInterval(rs.getString("MINUTE_INTRVL"));
		jobDetail.setHourInterval(rs.getString("HOUR_INTRVL"));
		jobDetail.setDayInterval(rs.getString("DAY_INTRVL"));
		jobDetail.setMonthDate(rs.getString("MONTH_DATE"));
		jobDetail.setMonth(rs.getString("MONTH"));
		jobDetail.setDayOfWeek(rs.getString("DAY_OF_WEEK"));
		jobDetail.setTime(rs.getString("TIME"));
		return jobDetail;
	}

}
