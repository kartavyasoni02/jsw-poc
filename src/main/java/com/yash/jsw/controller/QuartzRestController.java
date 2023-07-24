package com.yash.jsw.controller;

/**
 * @author kartavya.soni
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.jsw.model.QuartzModel;
import com.yash.jsw.service.quartz.QuartzService;
import com.yash.jsw.utility.GlobalConstant;

@RestController
public class QuartzRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzRestController.class);
    
    @Autowired
    private QuartzService quartzService;

    @RequestMapping(value = "/scheduleJobs", method = RequestMethod.POST)
    public QuartzModel scheduleJobs(@RequestBody QuartzModel model) {
        LOGGER.debug("Received request for Scheduling the JOBs.");
        return quartzService.scheduleJobs(model);
    }
    
    
    @RequestMapping(value = "/stopJob/{job}", method = RequestMethod.GET)
    public QuartzModel stopJob(@PathVariable("job") String job) {
        LOGGER.debug("stop Job called...", job);
        QuartzModel model = new QuartzModel();
        if(quartzService.stopJob(job).equalsIgnoreCase(GlobalConstant.success)){
        	model.setResponseCode(GlobalConstant.OBJECT_FOUND);
        }else{
        	model.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
        }
        return model;
    }
    
    @RequestMapping(value = "/startJob/{job}", method = RequestMethod.GET)
    public QuartzModel startJob(@PathVariable("job") String job) {
        LOGGER.debug("stop Job called...", job);
        QuartzModel model = new QuartzModel();
        if(quartzService.startJob(job).equalsIgnoreCase(GlobalConstant.success)){
        	model.setResponseCode(GlobalConstant.OBJECT_FOUND);
        }else{
        	model.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
        }
        return model;
    }
    
    
    @RequestMapping(value = "/populateJobs", method = RequestMethod.GET)
    @ResponseBody
    public  QuartzModel populateJobs() {
        LOGGER.debug("Received request for Populating the JOBs.");
        QuartzModel model = new QuartzModel();
        List<QuartzModel> list = quartzService.populateAllScheduledJobs();
        if(list!=null && !list.isEmpty()){
        	model.setQuartzList(list);
        	model.setResponseCode(GlobalConstant.OBJECT_FOUND);	
        }else{
        	model.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
        }
		return model;
    }
    @RequestMapping(value = "/getJob/{job}", method = RequestMethod.GET)
    @ResponseBody
    public  QuartzModel getJobs(@PathVariable("job") String job) {
        LOGGER.debug("Received request for Populating the JOBs.");
        return quartzService.getScheduledJob(job);
    }
}
