package com.yash.jsw.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "dyna_plan")
public class DynaPlan extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -2482867328267396498L;

	static Logger logger = LoggerFactory.getLogger(DynaPlan.class);

	@NotNull(message = "{error.dynaplan.dynaPlanName.null}")
    @NotEmpty(message = "{error.dynaplan.dynaPlanName.empty}")
    @Size(max = 50, message = "{error.dynaplan.dynaPlanName.max}")
	@Column(name="DYNA_PLAN_NAME")
		private String dynaPlanName;
	
	private String status;

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
}
