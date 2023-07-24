package com.yash.jsw.dao;

import java.util.List;

import com.yash.jsw.model.Employee;
import com.yash.jsw.model.UserEmployee;

public interface EmployeeDAO {

    public void addEmployee(Employee employee) throws Exception;

    public Employee getEmployee(int id) throws Exception;

    public Employee getEmployee(String firstName, String lastName) throws Exception;

    public Integer updateEmployee(Employee employee) throws Exception;

    public List<UserEmployee> getEmployees() throws Exception;

}
