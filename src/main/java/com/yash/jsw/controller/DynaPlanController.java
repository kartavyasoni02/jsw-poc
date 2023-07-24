package com.yash.jsw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.jsw.domain.DynaPlanDTO;
import com.yash.jsw.model.DynaPlan;
import com.yash.jsw.model.DynaPlanSchedulerDetails;
import com.yash.jsw.service.dynaplan.DynaPlanService;
import com.yash.jsw.utility.GlobalConstant;

@RestController
@RequestMapping(value = "/dynaplan")
public class DynaPlanController {

	static Logger logger = LoggerFactory.getLogger(DynaPlanController.class);
	static String businessObject = "dynaplan"; //used in RedirectAttributes messages 

	@Autowired
	private DynaPlanService dynaPlanService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public DynaPlanDTO listOfDynaPlans() throws Exception{
		DynaPlanDTO retrunDynaPlans = new DynaPlanDTO();
		logger.debug("IN: DynaPlan/list-GET");
		List<DynaPlan> dynaPlans = dynaPlanService.getDynaPlans();
		if(dynaPlans!=null && !dynaPlans.isEmpty()){
			List<DynaPlanDTO> dynaPlanList = this.getDynaPlans(dynaPlans);
			retrunDynaPlans.setDynaPlans(dynaPlanList);
			retrunDynaPlans.setResponseCode(GlobalConstant.OBJECT_FOUND);
		}else{
			retrunDynaPlans.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
		}
		return retrunDynaPlans;
	}              
	private List<DynaPlanDTO> getDynaPlans(List<DynaPlan> dynaPlans){
		List<DynaPlanDTO> dynaPlanList = new ArrayList<DynaPlanDTO>();
		for (DynaPlan dynaPlan : dynaPlans) {
			DynaPlanDTO dto = new DynaPlanDTO();
			dto.setId(dynaPlan.getId());
			dto.setDynaPlanName(dynaPlan.getDynaPlanName());
			dto.setStatus(dynaPlan.getStatus());
			dynaPlanList.add(dto);
		}
		return dynaPlanList;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public DynaPlanDTO addDynaPlan(@Valid @RequestBody DynaPlan dynaPlan) throws Exception {
		logger.debug("IN: DynaPlan/add-POST");
		DynaPlanDTO retrunDynaPlans = new DynaPlanDTO();
		Integer response = dynaPlanService.addDynaPlan(dynaPlan);
		retrunDynaPlans.setResponseCode(response);
		return retrunDynaPlans;

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public DynaPlanDTO deleteDynaPlanPage(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "phase", required = true) String phase) throws Exception {
		DynaPlanDTO retrunDynaPlans = new DynaPlanDTO();
		DynaPlan dynaPlan;
		logger.debug("IN: DynaPlan/delete-GET ");

		if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
			dynaPlan = dynaPlanService.getDynaPlan(id);
			if(dynaPlan!=null){
				retrunDynaPlans.setId(dynaPlan.getId());
				retrunDynaPlans.setDynaPlanName(dynaPlan.getDynaPlanName());
				retrunDynaPlans.setStatus(dynaPlan.getStatus());
				retrunDynaPlans.setResponseCode(GlobalConstant.OBJECT_FOUND);
				return retrunDynaPlans;
			}else{
				retrunDynaPlans.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
				return retrunDynaPlans;
			}

		} else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
			dynaPlanService.deleteDynaPlan(id);
			retrunDynaPlans.setResponseCode(GlobalConstant.OBJECT_FOUND);
			return retrunDynaPlans;
		}
		return retrunDynaPlans;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public DynaPlanDTO editDynaPlanPage(@RequestParam(value = "id", required = true) Integer id) throws Exception{

		logger.debug("IN: DynaPlan/edit-GET:  ID to query = " + id);
		DynaPlanDTO retrunDynaPlans = new DynaPlanDTO();
		logger.debug("IN: DynaPlan/list-GET");
		DynaPlan dynaPlan = dynaPlanService.getDynaPlan(id);
		if(dynaPlan!=null){
			retrunDynaPlans.setId(dynaPlan.getId());
			retrunDynaPlans.setDynaPlanName(dynaPlan.getDynaPlanName());
			retrunDynaPlans.setStatus(dynaPlan.getStatus());
			retrunDynaPlans.setResponseCode(GlobalConstant.OBJECT_FOUND);
			return retrunDynaPlans;
		}else{
			retrunDynaPlans.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
			return retrunDynaPlans;
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public DynaPlanDTO editDynaPlan(@Valid @RequestBody DynaPlan dynaPlan) throws Exception{

		DynaPlanDTO retrunDynaPlans = new DynaPlanDTO();
		logger.debug("DynaPlan/edit-POST:  " + dynaPlan.toString());
		Integer respose = dynaPlanService.updateDynaPlan(dynaPlan);
		retrunDynaPlans.setResponseCode(respose);
		return retrunDynaPlans;
	}
	@RequestMapping(value = "/populateDynaplan", method = RequestMethod.GET)
    public List<Object> getDynaplanDetails(@RequestParam(value = "fromDate", required = false ) String fromDate,
    		@RequestParam(value = "toDate", required = false ) String toDate) throws Exception {
		logger.debug("Received request for Scheduling the JOBs.");
       return dynaPlanService.getDynaPlanDetails();
    } 
}
