package com.yash.jsw.utility;

import java.util.List;

public class ResponseWrapper {
	Integer responseCode;
	String message;
	String userName;
	List<String> authorities;	
	Exception exception;
	
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
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
}
