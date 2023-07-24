package com.yash.jsw.dao.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.jsw.dao.DynaPlanDAO;
import com.yash.jsw.model.DynaPlan;
import com.yash.jsw.model.DynaPlanSchedulerDetails;
import com.yash.jsw.utility.GlobalConstant;

@Repository
@Transactional
public class DynaPlanDAOImpl implements DynaPlanDAO {

	static Logger logger = LoggerFactory.getLogger(DynaPlanDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addDynaPlan(DynaPlan dynaPlan) throws Exception {
		logger.debug("DynaPlanDAOImpl.addDynaPlan() - [" + dynaPlan.getDynaPlanName() + "]");
		dynaPlan.setStatus(GlobalConstant.SCHEDULED);
		getCurrentSession().save(dynaPlan);
	}

	@Override
	public void deleteDynaPlan(int id) throws Exception {
		DynaPlan dynaPlan = getDynaPlan(id);
		if (dynaPlan != null) {
			getCurrentSession().delete(dynaPlan);
		}
	}

	@Override
	public DynaPlan getDynaPlan(int id) throws Exception {
		return (DynaPlan) getCurrentSession().get(DynaPlan.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DynaPlan getDynaPlan(String dyna_plan_name) throws Exception {
		logger.debug("DynaPlanDAOImpl.getDynaPlan() - [" + dyna_plan_name + "]");
		Query query = getCurrentSession().createQuery("from DynaPlan where dyna_plan_name = :dyna_plan_name ");
		query.setString("dyna_plan_name", dyna_plan_name);

		logger.debug(query.toString());
		List<DynaPlan> list = (List<DynaPlan>)query.list();
		if (list!=null && !list.isEmpty() ) {
			DynaPlan dynaPlanObject = (DynaPlan) list.get(0);
			return dynaPlanObject;
		} 
		return null;
	}

	@Override
	public Integer updateDynaPlan(DynaPlan dynaPlan) throws Exception {
		if(!checkDynaPlan(dynaPlan.getDynaPlanName(),dynaPlan.getId())){
			DynaPlan planToUpdate = getDynaPlan(dynaPlan.getId());
			planToUpdate.setId(dynaPlan.getId());
			planToUpdate.setDynaPlanName(dynaPlan.getDynaPlanName());
			getCurrentSession().update(planToUpdate);	
			return GlobalConstant.ACTION_SUCCESSFUL;
		}else{
			return GlobalConstant.OBJECT_DUPLICATE;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DynaPlan> getDynaPlans(String status) throws Exception {
		Criteria crit = getCurrentSession().createCriteria(DynaPlan.class);
		if(status!=null && !status.isEmpty()){
			crit.add(Restrictions.eq("status", status));	
		}
		return crit.list();
	}

	@Override
	public boolean checkDynaPlan(String usersDynaPlan, Integer id) throws Exception {
		logger.debug("DynaPlanDAOImpl.getDynaPlan() - [" + usersDynaPlan + "]");
		Query query = getCurrentSession().createQuery("from DynaPlan where dyna_plan_name = :usersDynaPlan and id!=:id ");
		query.setString("usersDynaPlan", usersDynaPlan);
		query.setInteger("id", id);

		logger.debug(query.toString());
		@SuppressWarnings("unchecked")
		List<DynaPlan> list = (List<DynaPlan>)query.list();
		if (list!=null && !list.isEmpty() ) {
			return true;
		}
		return false;
	}

	public List<DynaPlan> getAllDynaPlan(List<Integer> ids) throws Exception{

		logger.debug("DynaPlanDAOImpl.getAllDynaPlan() - [" + ids + "]");
		Query query = getCurrentSession().createQuery("from DynaPlan where id in (:ids) ");
		query.setParameterList("ids", ids);
		@SuppressWarnings("unchecked")
		List<DynaPlan> listOfDynaPlan =(List<DynaPlan>) query.list();
		if (listOfDynaPlan!=null && !listOfDynaPlan.isEmpty()) {
			return listOfDynaPlan;  
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public  List<DynaPlan> getDynaPlanDetails() throws Exception {
		logger.debug("DynaPlanDAOImpl.getDynaPlanDetails() - ");
		return  getCurrentSession().createQuery("from DynaPlan").list();
	}


	@Override
	public List<Object> getDynaPlanList(Integer dynaPlan_Id) throws Exception {
		logger.debug("DynaPlanDAOImpl.getDynaPlanList() - ");
		String hql="SELECT FIER_TIME,JOB_ID from dyna_plan_details where PLAN_ID="+dynaPlan_Id;
		@SuppressWarnings("unchecked")
		List<Object> list = getCurrentSession().createSQLQuery(hql).list();
		return list;

	}
}
