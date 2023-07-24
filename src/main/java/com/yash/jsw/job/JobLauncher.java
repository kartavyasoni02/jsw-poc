package com.yash.jsw.job;
/**
 * @author kartavya.soni
 */


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yash.jsw.dao.DynaPlanDAO;
import com.yash.jsw.dao.JobDao;
import com.yash.jsw.model.CommonPojo;
import com.yash.jsw.model.DynaPlan;
import com.yash.jsw.service.quartz.QuartzService;
import com.yash.jsw.utility.DateUtility;
import com.yash.jsw.utility.GlobalConstant;



@Component
public class JobLauncher implements QuartzJob {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobLauncher.class);

	@Autowired
	private JobDao jobDao;

	@Autowired
	private QuartzService quartzService;

	@Autowired
	private DynaPlanDAO dynaPlanDAO;

	private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.info(JobLauncher.class.getName() + " started at - " + DATEFORMAT.format(new Date()));

		String jobDetail = context.getJobDetail().getKey() + "";
		String[] jobname = jobDetail.split("\\.");
		
		LOGGER.info(JobLauncher.class.getName()+" ended at - " + DATEFORMAT.format(new Date()));
		if(context.getJobDetail().getKey().toString().equalsIgnoreCase("DEFAULT.BOF")){
			try {
				List<DynaPlan> dynaPlanList = dynaPlanDAO.getDynaPlans(GlobalConstant.SCHEDULED);
				if(dynaPlanList!=null && !dynaPlanList.isEmpty()){
					
					
					
					
					
					DynaPlan dynaPlan = dynaPlanList.get(0);
					jobDao.updateDynaPlanStatus(dynaPlan.getId(), GlobalConstant.INPROCESS);
					jobDao.saveBofScheduledProcess(dynaPlan.getId(), GlobalConstant.DEFAULT_FIRE_TIME,DateUtility.convertTimeToString(context.getFireTime()),
							GlobalConstant.INPROCESS);
					Integer jobId = jobDao.getJobId( jobname[1]);
					jobDao.persistDetailDynaplan(jobId,DateUtility.convertTimeToString(context.getFireTime()),dynaPlan.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (context.getJobDetail().getKey().toString().equalsIgnoreCase("DEFAULT.LHF")) {

			LOGGER.info("in lhf");
			List<CommonPojo> boflist = null;
			boflist = jobDao.getBOF(new DateTime(context.getFireTime()));
			if (boflist != null && !boflist.isEmpty()) {
				for (CommonPojo bof : boflist) {
					jobDao.updateBOFStatus(bof);
					jobDao.saveLHF(bof,new DateTime(context.getFireTime()));
					Integer jobId = jobDao.getJobId( jobname[1]);
					jobDao.persistDetailDynaplan(jobId,DateUtility.convertTimeToString(context.getFireTime()),bof.getPlanId());
					
				}
			}
		}
		if (context.getJobDetail().getKey().toString().equalsIgnoreCase("DEFAULT.RHD")) {
			LOGGER.info("in RHD");
			List<CommonPojo> lhflist = null;
			try {
				lhflist = jobDao.getLHF(new DateTime(context.getFireTime()));
				if (lhflist != null && !lhflist.isEmpty()) {
					for (CommonPojo lhf : lhflist) {
						jobDao.updateLHFStatus(lhf);
						jobDao.updateDynaPlanStatus(lhf.getId(), GlobalConstant.COMPLETED);
						jobDao.saveRHD(lhf,new DateTime(context.getFireTime()));
						Integer jobId = jobDao.getJobId( jobname[1]);
						jobDao.persistDetailDynaplan(jobId,DateUtility.convertTimeToString(context.getFireTime()),lhf.getPlanId());
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Calendar DateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}
