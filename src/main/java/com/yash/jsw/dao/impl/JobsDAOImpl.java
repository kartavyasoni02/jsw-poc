package com.yash.jsw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.jsw.dao.JobsDAO;
import com.yash.jsw.model.Job;

@Repository
public class JobsDAOImpl implements JobsDAO {

	static Logger logger = LoggerFactory.getLogger(JobsDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addJob(Job job) throws Exception {
		logger.debug("JobsDAOImpl.addJob() - [" + job.getJobName() + "]");
		getCurrentSession().save(job);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Job getJob(String usersJob){
		logger.debug("JobsDAOImpl.getJob() - [" + usersJob + "]");
		Query query = getCurrentSession().createQuery("from Job where job_name = :usersJob ");
		query.setString("usersJob", usersJob);

		logger.debug(query.toString());
		List<Job> list = (List<Job>)query.list();
		if (list!=null && !list.isEmpty() ) {
			Job jobObject = (Job) list.get(0);
			return jobObject;
		} 
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Job> getJobs() {
		//String hql = "FROM JOB";
		//return getCurrentSession().createQuery(hql).list();
		return getCurrentSession().createCriteria(Job.class).list();
	}
	
}
