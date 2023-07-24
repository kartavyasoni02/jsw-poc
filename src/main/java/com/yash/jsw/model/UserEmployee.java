package com.yash.jsw.model;

import java.util.List;

public class UserEmployee {

	    private Integer id;
		private String username;
		private String password;
		private boolean enabled;
		private Integer employeeId;
		private String firstName;
		private String lastName;
		private String address;
		private String contact;
		private String email;
		private List<UserEmployee> employees;
		private Integer responseCode;
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
		private String message;
		
		public List<UserEmployee> getEmployees() {
			return employees;
		}
		public void setEmployees(List<UserEmployee> employees) {
			this.employees = employees;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		public Integer getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
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
