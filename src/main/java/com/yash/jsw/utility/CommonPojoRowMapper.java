package com.yash.jsw.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import com.yash.jsw.model.CommonPojo;

public class CommonPojoRowMapper implements RowMapper<CommonPojo>{

	@Override
	public CommonPojo mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommonPojo commonPojo = new CommonPojo();
		commonPojo.setId(rs.getInt("ID"));
		commonPojo.setPlanId(rs.getInt("PLAN_ID"));
		commonPojo.setProcessTime(rs.getInt("PROCESS_TIME"));
		commonPojo.setFireTime(new DateTime(rs.getTimestamp("FIRE_TIME")));//"11/11/11 00:00:00");//rs.getDate("FIRE_TIME"));
		commonPojo.setStatus(rs.getString("STATUS"));
		return commonPojo;
	}

}
