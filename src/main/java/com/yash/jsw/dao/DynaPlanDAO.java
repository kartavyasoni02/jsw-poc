package com.yash.jsw.dao;

import java.util.List;

import org.quartz.JobExecutionContext;

import com.yash.jsw.model.DynaPlan;
import com.yash.jsw.model.DynaPlanSchedulerDetails;

public interface DynaPlanDAO {

	public void addDynaPlan(DynaPlan dynaPlan) throws Exception;
	  
	public void deleteDynaPlan(int id) throws Exception;

	public DynaPlan getDynaPlan(int id) throws Exception;

    public DynaPlan getDynaPlan(String dyna_plan_name) throws Exception;

    public Integer updateDynaPlan(DynaPlan dynaPlan) throws Exception;
	
    public List<DynaPlan> getDynaPlans(String status) throws Exception;
    
    public List<DynaPlan> getAllDynaPlan(List<Integer> ids) throws Exception ;
    
    boolean checkDynaPlan(String usersDynaPlan, Integer id) throws Exception;
    public List<DynaPlan> getDynaPlanDetails() throws Exception;

	 

	public List getDynaPlanList(Integer integer)throws Exception;

	 
}
