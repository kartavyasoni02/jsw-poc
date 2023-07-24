package com.yash.jsw.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.yash.jsw.model.UserEmployee;

@JsonInclude(Include.NON_NULL)
public class EmployeeDTO {

	private Integer employeeId;
	private String firstName;
	private String lastName;
	private String address;
	private String contact;
	private String email;
	private Integer responseCode;
	private String message;
	private List<UserEmployee> employees;
	
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	public List<UserEmployee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<UserEmployee> employees) {
		this.employees = employees;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
