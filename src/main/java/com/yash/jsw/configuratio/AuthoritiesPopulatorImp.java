/*package com.yash.accelerator.cofiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

@Component
public class AuthoritiesPopulatorImp implements LdapAuthoritiesPopulator {
	
	private static final Logger logger = LogManager.getLogger(AuthoritiesPopulatorImp.class);
	@Autowired
	DataSource dataSource;

	@Override
	public List<GrantedAuthority> getGrantedAuthorities(
			DirContextOperations userData, String username) {
		logger.info("LDAP AUTHENTICATED");
		logger.info("LDAP Authority called and username is : " +username);
		try {
			JdbcTemplate template = new JdbcTemplate(dataSource);
			List<GrantedAuthority> userPerms = template.query(
					"select role from user_roles_ldap where username=? ",
					new String[] { username }, new RowMapper<GrantedAuthority>() {
						*//**
						 * We're assuming here that you're using the standard
						 * convention of using the role prefix "ROLE_" to mark
						 * attributes which are supported by Spring Security's
						 * RoleVoter.
						 *//*
						public GrantedAuthority mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return new SimpleGrantedAuthority(""
									+ rs.getString(1));
						}
					});
			return userPerms;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
}
*/