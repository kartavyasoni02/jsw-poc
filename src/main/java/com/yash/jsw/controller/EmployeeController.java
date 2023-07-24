package com.yash.jsw.controller;

import java.util.List;

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

import com.yash.jsw.domain.EmployeeDTO;
import com.yash.jsw.model.Employee;
import com.yash.jsw.model.UserEmployee;
import com.yash.jsw.service.employee.EmployeeService;
import com.yash.jsw.utility.GlobalConstant;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	static String businessObject = "Employee"; //used in RedirectAttributes messages 

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MessageSource messageSource;
	
	
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public UserEmployee listOfEmployees() throws Exception{
		UserEmployee retrunEmployees = new UserEmployee();
		logger.debug("IN: Employee/list-GET");
		List<UserEmployee> employees = employeeService.getEmployees();
		if(employees!=null && !employees.isEmpty()){
			
			retrunEmployees.setEmployees(employees);
			retrunEmployees.setResponseCode(GlobalConstant.OBJECT_FOUND);
		}else{
			retrunEmployees.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
		}
		return retrunEmployees;
	}              
	
	@RequestMapping(value = {"/add", "/update"}, method = RequestMethod.POST)
	public EmployeeDTO addUpdateEmployee(@Valid @RequestBody Employee employee) throws Exception {
		logger.debug("IN: Employee/add-POST");
		EmployeeDTO retrunEmployees = new EmployeeDTO();
		Integer response = employeeService.addUpdateEmployee(employee);
		retrunEmployees.setResponseCode(response);
		return retrunEmployees;

	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public EmployeeDTO editEmployeePage(@RequestParam(value = "id", required = true) Integer id) throws Exception{

		logger.debug("IN: Employee/edit-GET:  ID to query = " + id);
		EmployeeDTO retrunEmployees = new EmployeeDTO();
		logger.debug("IN: Employee/list-GET");
		Employee employee = employeeService.getEmployee(id);
		if(employee!=null){
			retrunEmployees.setEmployeeId(employee.getEmployeeId());
			retrunEmployees.setFirstName(employee.getFirstName());
			retrunEmployees.setLastName(employee.getLastName());
			retrunEmployees.setAddress(employee.getAddress());
			retrunEmployees.setContact(employee.getContact());
			retrunEmployees.setEmail(employee.getEmail());
			retrunEmployees.setResponseCode(GlobalConstant.OBJECT_FOUND);
			return retrunEmployees;
		}else{
			retrunEmployees.setResponseCode(GlobalConstant.OBJECT_NOT_FOUND);
			return retrunEmployees;
		}
	}
}
