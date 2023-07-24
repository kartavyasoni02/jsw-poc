package com.yash.jsw.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yash.jsw.dao.EmployeeDAO;
import com.yash.jsw.model.Employee;
import com.yash.jsw.model.UserEmployee;
import com.yash.jsw.utility.GlobalConstant;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	static Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void addEmployee(Employee employee) throws Exception {
		
		logger.debug("EmployeeDAOImpl.addEmployee() - [" + employee.getFirstName() +""+employee.getLastName()+ "]");
		getCurrentSession().save(employee);

	}

	@Override
	public Employee getEmployee(int id) throws Exception {
		return (Employee)getCurrentSession().get(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee getEmployee(String firstName, String lastName) throws Exception {
		logger.debug("EmployeeDAOImpl.getEmployee() - [" + firstName +""+lastName+ "]");
		
		Criteria criteria = getCurrentSession().createCriteria(Employee.class);
		criteria
		.add(Restrictions.like("firstName", firstName))
		.add(Restrictions.like("lastName", lastName));
		
		logger.debug(criteria.toString());
		List<Employee> list = (List<Employee>)criteria.list();
		if (list!=null && !list.isEmpty() ) {
			Employee employeeObject = (Employee) list.get(0);
			return employeeObject;
		} 
		return null;
	}

	@Override
	public Integer updateEmployee(Employee employee) throws Exception {
		
		Employee employeeToUpdate = getEmployee(employee.getEmployeeId());
		employeeToUpdate.setEmployeeId(employee.getEmployeeId());
		employeeToUpdate.setFirstName(employee.getFirstName());
		employeeToUpdate.setLastName(employee.getLastName());
		employeeToUpdate.setAddress(employee.getAddress());
		employeeToUpdate.setContact(employee.getContact());
		employeeToUpdate.setEmail(employee.getEmail());
		getCurrentSession().update(employeeToUpdate);	
		return GlobalConstant.ACTION_SUCCESSFUL;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEmployee> getEmployees() throws Exception {
		
		return getCurrentSession().createSQLQuery("SELECT * FROM USERS U LEFT JOIN EMPLOYEE E ON U.ID = E.EMPLOYEE_ID").list();
		/*return getCurrentSession().createCriteria(Employee.class).list();*/
	}
}
