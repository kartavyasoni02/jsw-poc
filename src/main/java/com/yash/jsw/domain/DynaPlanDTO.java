package com.yash.jsw.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DynaPlanDTO {

	private Integer id;
	private String dynaPlanName;
	private String status;	
	private Integer responseCode;
	private String message;
	private List<DynaPlanDTO> dynaPlans;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDynaPlanName() {
		return dynaPlanName;
	}

	public void setDynaPlanName(String dynaPlanName) {
		this.dynaPlanName = dynaPlanName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<DynaPlanDTO> getDynaPlans() {
		return dynaPlans;
	}

	public void setDynaPlans(List<DynaPlanDTO> dynaPlans) {
		this.dynaPlans = dynaPlans;
	}
}
