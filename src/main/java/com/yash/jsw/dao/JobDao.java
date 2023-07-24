/**
 * 
 */
package com.yash.jsw.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;

import com.yash.jsw.domain.JobDetails;
import com.yash.jsw.model.CommonPojo;
import com.yash.jsw.model.DynaPlanSchedulerDetails;
import com.yash.jsw.model.QuartzModel;
import com.yash.jsw.utility.CommonPojoRowMapper;

/**
 * @author kartavya.soni
 *
 */
public interface JobDao {
	public JobDetails  findJobByJobName(String jobName);
	public void updateJobIsActive(String jobName, boolean isActive);
	public void updateJobStatus(String jobName, String jobStatus);
	public List<JobDetails> findAll();
	public void save(JobDetails entity, boolean isNew);
	public void persistJobDetail(QuartzModel model, String cronExpression,
			Timestamp startTs, Class className) throws SchedulerException;
	public List<Map<String, Object>> findTrigger();
	public void persistDetailDynaplan(Integer jobId,String fireTime,Integer planId);
	

	
	public  List<CommonPojo> getBOF(DateTime firetime);
	
	public void updateBOFStatus(CommonPojo pojo);
	
	public void saveLHF(CommonPojo pojo,DateTime fireTime);
	void updateDynaPlanStatus(Integer id, String status)throws Exception;
	public void saveBofScheduledProcess(Integer id, Integer processTime,String fireTime,String status)throws Exception;
	
	public List<CommonPojo> getLHF(DateTime date);

	public void updateLHFStatus(CommonPojo bof);

	public void saveRHD(CommonPojo bof,DateTime fireTime);
	public Integer getJobId(String string);

}
