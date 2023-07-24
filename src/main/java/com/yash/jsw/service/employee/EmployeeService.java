package com.yash.jsw.service.employee;

import java.util.List;

import com.yash.jsw.model.Employee;
import com.yash.jsw.model.UserEmployee;

public interface EmployeeService {

    public Integer addUpdateEmployee(Employee employee) throws Exception;

    public Employee getEmployee(int id) throws Exception;
    
    public Employee getEmployee(String firstName, String lastName) throws Exception;

    public List<UserEmployee> getEmployees() throws Exception;
}
