
package com.yash.jsw.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yash.jsw.dao.JobDao;
import com.yash.jsw.domain.JobDetails;
import com.yash.jsw.model.CommonPojo;
import com.yash.jsw.model.QuartzModel;
import com.yash.jsw.utility.CommonPojoRowMapper;
import com.yash.jsw.utility.GlobalConstant;
import com.yash.jsw.utility.JobRowMapper;

/**
 * @author kartavya.soni
 *
 */

@Repository
public class JobDaoImpl implements JobDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobDaoImpl.class);
	private static final String WHERE = " WHERE ";
	private static final String JOB_NAME = " JOB_NAME ";
	private int id1;

	@Autowired
	JdbcTemplate jdbcTemplate;

	DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public JobDetails findJobByJobName(String jobName) {
		JobDetails job = null;
		try {
			job = jdbcTemplate.queryForObject("SELECT * FROM JOB_DETAILS WHERE JOB_NAME ='" + jobName + "'",
					new JobRowMapper());
		} catch (DataAccessException ex) {
			LOGGER.error("JobDaoImpl : No data found for jobName", ex);
		}
		return job;
	}

	@Override
	public void updateJobIsActive(String jobName, boolean isActive) {
		StringBuilder query = new StringBuilder("UPDATE JOB_DETAILS SET IS_ACTIVE=? ").append(WHERE)
				.append(JOB_NAME + " = ?");
		jdbcTemplate.update(query.toString(), isActive, jobName);
	}

	@Override
	public void updateJobStatus(String jobName, String jobStatus) {
		StringBuilder query = new StringBuilder("UPDATE JOB_DETAILS SET JOB_STATUS='").append(jobStatus).append("'")
				.append(" " + WHERE).append(" " + JOB_NAME + " = '").append(jobName).append("'");
		jdbcTemplate.update(query.toString());
	}

	@Override
	public List<JobDetails> findAll() {
		return jdbcTemplate.query("SELECT * FROM JOB_DETAILS", new JobRowMapper());
	}

	@Override
	public void save(JobDetails entity, boolean isNew) {
		if (isNew) {
			String insertBolHistorySql = "insert into JOB_DETAILS(JOB_NAME , JOB_CLASS , CRON_EXPSN ,IS_ACTIVE  ,JOB_STATUS , START_TS,END_TS,UPDT_BY,UPDT_TS,LAST_RUN_TS,SCHD_TYPE,MINUTE_INTRVL,HOUR_INTRVL,DAY_INTRVL,MONTH_DATE,MONTH,DAY_OF_WEEK,TIME) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			jdbcTemplate.update(insertBolHistorySql, entity.getJobName(), entity.getJobClass(),
					entity.getCronExpression(), true, entity.getJobStatus(), entity.getStartTs(), entity.getEndTs(),
					entity.getUpdatedBy(), entity.getUpdatedTs(), entity.getLastRunTime(), entity.getScheduleType(),
					entity.getMinuteInterval(), entity.getHourInterval(), entity.getDayInterval(),
					entity.getMonthDate(), entity.getMonth(), entity.getDayOfWeek(), entity.getTime());
		} else {
			String updateQuery = "update JOB_DETAILS set JOB_NAME=?, JOB_CLASS=?, CRON_EXPSN=?, IS_ACTIVE=?, JOB_STATUS=?, START_TS=?, END_TS=?,UPDT_BY=?,UPDT_TS=?,LAST_RUN_TS=?,SCHD_TYPE=?, MINUTE_INTRVL=?, HOUR_INTRVL=?, DAY_INTRVL=?, MONTH_DATE=?, MONTH=?, DAY_OF_WEEK=?, TIME=? WHERE ID="
					+ entity.getId();
			jdbcTemplate.update(updateQuery, entity.getJobName(), entity.getJobClass(), entity.getCronExpression(),
					true, entity.getJobStatus(), entity.getStartTs(), entity.getEndTs(), entity.getUpdatedBy(),
					entity.getUpdatedTs(), entity.getLastRunTime(), entity.getScheduleType(),
					entity.getMinuteInterval(), entity.getHourInterval(), entity.getDayInterval(),
					entity.getMonthDate(), entity.getMonth(), entity.getDayOfWeek(), entity.getTime());
		}
	}


	@Override
	public void persistJobDetail(QuartzModel model, String cronExpression, Timestamp startTs,
			@SuppressWarnings("rawtypes") Class className) throws SchedulerException {
		LOGGER.debug("Scheduler Service: Updating the Job :" + model.getJobName() + " with CronExpression : "
				+ cronExpression);

		JobDetails job = null;
		job = findJobByJobName(model.getJobName());
		// Create New Job if not exist
		boolean isNew = true;
		if (null != job) {
			isNew = false;
		} else {
			job = new JobDetails();
		}
		job.setJobName(model.getJobName());
		job.setCronExpression(cronExpression);
		job.setUpdatedTs(new Timestamp(System.currentTimeMillis()));
		job.setScheduleType(model.getScheduleRadio());
		job.setMinuteInterval(model.getMinuteInterval());
		job.setHourInterval(model.getHourInterval());
		job.setDayInterval(model.getDayInterval());
		job.setMonthDate(model.getMonthDate());
		job.setMonth(model.getMonth());
		job.setDayOfWeek(model.getDayOfWeek());
		job.setTime(model.getTime());
		job.setStartTs(startTs);
		job.setActive(true);
		job.setJobStatus(GlobalConstant.JobStatus.SCHEDULED.toString());
		job.setJobClass(className.getName());
		save(job, isNew);
	}


	@Override

	public List<Map<String, Object>> findTrigger() {
		List<Map<String, Object>> obj = null;
		try {
			obj = jdbcTemplate.queryForList(
					"SELECT JOB_NAME,START_TIME,END_TIME,NEXT_FIRE_TIME,PREV_FIRE_TIME FROM qrtz_triggers");
		} catch (DataAccessException ex) {
			LOGGER.error("JobDaoImpl : No data found for jobName", ex);
		}
		return obj;
	}

	@Override
	public void persistDetailDynaplan(Integer jobId,String fireTime,Integer planId) {
		// TODO Auto-generated method stub
		String insertBolHistorySql = "insert into DYNA_PLAN_DETAILS( JOB_ID,FIER_TIME,PLAN_ID) values(?,?,?) ";
		jdbcTemplate.update(insertBolHistorySql,jobId,fireTime,planId );

	}	

	 
	public List<CommonPojo> getBOF(DateTime date) {

		List<CommonPojo> pojoList = null;
		try {
			pojoList = jdbcTemplate.query(
					"SELECT * FROM bof_scheduled_process WHERE DATE_ADD(FIRE_TIME,INTERVAL PROCESS_TIME MINUTE) <= '"
							+ date.toString("yyyy-MM-dd HH:mm:ss") + "' AND STATUS = 'Inprocess'",
					new CommonPojoRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojoList;
	}

	public void updateBOFStatus(CommonPojo bof) {
		String sql = "UPDATE bof_scheduled_process SET STATUS = 'Completed' where ID = '" + bof.getId() + "'";
		jdbcTemplate.update(sql);
	}

	public void saveLHF(CommonPojo bof,DateTime fireTime) {
		String query = "INSERT INTO lhf_scheduled_process(PLAN_ID,PROCESS_TIME,FIRE_TIME,STATUS) VALUES(?,?,?,?)";
		jdbcTemplate.update(query,
				new Object[] { bof.getPlanId(), bof.getProcessTime(), fireTime.toDate(), bof.getStatus() });

	}
	
	public List<CommonPojo> getLHF(DateTime date) {

		List<CommonPojo> pojoList = null;
		try {
			pojoList = jdbcTemplate.query(
					"SELECT * FROM lhf_scheduled_process WHERE DATE_ADD(FIRE_TIME,INTERVAL PROCESS_TIME MINUTE) <= '"
							+ date.toString("yyyy-MM-dd HH:mm:ss") + "' AND STATUS = 'Inprocess'",
					new CommonPojoRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojoList;
	}

	public void updateLHFStatus(CommonPojo lhf) {
		String sql = "UPDATE lhf_scheduled_process SET STATUS = 'Completed' where ID = '" + lhf.getId() + "'";
		jdbcTemplate.update(sql);
	}

	public void saveRHD(CommonPojo lhf,DateTime fireTime) {
		String query = "INSERT INTO rhd_scheduled_process(PLAN_ID,PROCESS_TIME,FIRE_TIME,STATUS) VALUES(?,?,?,?)";
		jdbcTemplate.update(query,
				new Object[] { lhf.getPlanId(), lhf.getProcessTime(), fireTime.toDate(), GlobalConstant.COMPLETED });

	}
	
	@Override
	public void updateDynaPlanStatus(Integer id,String status) {

		String updateQuery = "update dyna_plan SET Status=? WHERE ID="+id;
		jdbcTemplate.update(updateQuery,status);

	}

	@Override
	public void saveBofScheduledProcess(Integer planId, Integer processTime,String fireTime,String status)throws Exception {
		String insertBofsql = "INSERT INTO bof_scheduled_process(PLAN_ID,PROCESS_TIME,FIRE_TIME,STATUS)VALUES (?,?,?,?)";
		jdbcTemplate.update(insertBofsql,planId,processTime,fireTime,status);
	}

	@Override
	public Integer getJobId(String string) {
		Map<String, Object> idMap = jdbcTemplate.queryForMap("select job_id from job where jobName='"+string+"'");
		if(idMap!=null && !idMap.isEmpty()){
			return (Integer)idMap.get("job_id");
		}
		return null;
	}

}
