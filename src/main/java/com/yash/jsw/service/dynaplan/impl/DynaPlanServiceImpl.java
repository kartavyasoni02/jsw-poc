package com.yash.jsw.service.dynaplan.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yash.jsw.dao.DynaPlanDAO;
import com.yash.jsw.model.DynaPlan;
import com.yash.jsw.model.DynaPlanSchedulerDetails;
import com.yash.jsw.service.dynaplan.DynaPlanService;
import com.yash.jsw.service.jobs.impl.JobsServiceImpl;
import com.yash.jsw.utility.GlobalConstant;

@Service
@Transactional
public class DynaPlanServiceImpl implements DynaPlanService{

	static Logger logger = LoggerFactory.getLogger(JobsServiceImpl.class);

	@Autowired
	private DynaPlanDAO dynaPlanDAO;



	public List<DynaPlan> getDynaPlans() throws Exception{
		return dynaPlanDAO.getDynaPlans(null);
	}

	public Integer addDynaPlan(DynaPlan dynaPlan) throws Exception{
		DynaPlan DynaPlanCheck = this.getDynaPlan(dynaPlan.getDynaPlanName());
		if(DynaPlanCheck!=null){
			logger.debug( "The role [" + DynaPlanCheck.getDynaPlanName() + "] already exists");
			return GlobalConstant.OBJECT_DUPLICATE;
		}else{
			dynaPlanDAO.addDynaPlan(dynaPlan);
			return GlobalConstant.ACTION_SUCCESSFUL;
		}
	}

	@Override
	public DynaPlan getDynaPlan(String dynaPlanName) throws Exception {
		return dynaPlanDAO.getDynaPlan(dynaPlanName);
	}

	@Override
	public void deleteDynaPlan(int id) throws Exception {
		dynaPlanDAO.deleteDynaPlan(id);
	}

	@Override
	public DynaPlan getDynaPlan(int id) throws Exception {
		return dynaPlanDAO.getDynaPlan(id);
	}

	@Override
	public Integer updateDynaPlan(DynaPlan dynaPlan) throws Exception {
		return dynaPlanDAO.updateDynaPlan(dynaPlan);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getDynaPlanDetails() throws Exception {
		List<DynaPlan> list = dynaPlanDAO.getDynaPlanDetails();
		DynaPlanSchedulerDetails sch =null;
		List<Object> dynaPlanList =new ArrayList<Object>();
		for (DynaPlan temp : list) {
			sch = new DynaPlanSchedulerDetails();
			sch.setPlanName(temp.getDynaPlanName());
			sch.setDynaPlanList(dynaPlanDAO.getDynaPlanList(temp.getId()));
			dynaPlanList.add(sch);
		}
		return dynaPlanList;
	}
}
