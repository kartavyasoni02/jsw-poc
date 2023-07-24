package com.yash.jsw.configuratio;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 * 
 * @author kartavya.soni
 * This class provides Spring security (authorization and authentication) and ldap based authentication with database authorization
 *
 */
@Configuration
@EnableWebSecurity
@PropertySource(value = { "classpath:application.properties" })
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	private Environment environment;


	/**
	 * This method gives Ldap based authentication and database level authorization
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(environment.getRequiredProperty("security.usersByUsernameQuery"))
		.authoritiesByUsernameQuery(environment.getRequiredProperty("security.authoritiesByUsernameQuery"));
	}
	/**
	 * its provide url Authorities based on login user roles
	 * provides form based login protection
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/","/static/**").permitAll()
		.antMatchers("/error/**").permitAll()
		.antMatchers("/role/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/users/**").access("hasRole('ROLE_ADMIN')")

		
		.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")	
		.antMatchers("/user").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		.anyRequest().permitAll()

		.and().formLogin().loginPage(environment.getRequiredProperty("loginpage"))
		.defaultSuccessUrl("/user")
		.usernameParameter(environment.getRequiredProperty("usernameParameter"))
		.passwordParameter(environment.getRequiredProperty("passwordParameter"))
		.and().logout().logoutSuccessUrl(environment.getRequiredProperty("logoutSuccessUrl"))	
		.and().exceptionHandling().accessDeniedPage(environment.getRequiredProperty("accessDeniedPage"));


		if(Boolean.getBoolean(environment.getRequiredProperty("iscsrf"))){
			http.csrf().csrfTokenRepository(csrfTokenRepository()).and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);;	
		}else{
			http.csrf().disable();	
		}
	}
	/**
	 * Cross Site Request Forgery (CSRF) 
	 * include the CSRF token in all PATCH, POST, PUT, and DELETE methods. One way to approach this is to use the _csrf request attribute to obtain the current CsrfToken.
	 * Angular looks for XSRF-TOKEN cookie and submits it in X-XSRF-TOKEN http header, 
	 * while Django sets csrftoken cookie and expects X-CSRFToken http header. 
	 * 
	 * @return Filter
	 * @throws Exception
	 */
	private Filter csrfHeaderFilter() throws Exception{
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
					HttpServletResponse response, FilterChain filterChain)
							throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
						.getName());
				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null || (token != null
							&& !token.equals(cookie.getValue()))) {
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				filterChain.doFilter(request, response);
			}
		};
	}
	private CsrfTokenRepository csrfTokenRepository()throws Exception {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}
}
