package com.yash.jsw.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yash.jsw.domain.JobDTO;
import com.yash.jsw.model.Job;
import com.yash.jsw.service.jobs.JobsService;

@RestController
@RequestMapping(value = "/jobs")
public class JobsController {

	static Logger logger = LoggerFactory.getLogger(JobsController.class);
	//static String businessObject = "role"; //used in RedirectAttributes messages 

	@Autowired
	private JobsService jobsService;

	@SuppressWarnings("unused")
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public JobDTO listOfJobs() throws Exception{
		JobDTO retrunJobs = new JobDTO();
		logger.debug("IN: Job/list-GET");
		List<Job> jobs = jobsService.getJobs();
		if(jobs!=null && !jobs.isEmpty()){
			List<JobDTO> jobList = this.getJobs(jobs);
			retrunJobs.setJobs(jobList); 
			//retrunJobs.setResponseCode(GlobalConstant.OBJECT_FOUND);
		}else{
			//retrunRoles.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
		}
		return retrunJobs;
	}
	
	private List<JobDTO> getJobs(List<Job> jobs){
		List<JobDTO> jobList = new ArrayList<JobDTO>();
		for (Job job : jobs) {
			JobDTO dto = new JobDTO();
			dto.setJob_id(job.getJob_id());
			dto.setJobName(job.getJobName());
			jobList.add(dto);
		}
		return jobList;
	}
}
