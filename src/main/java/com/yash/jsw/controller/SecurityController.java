package com.yash.jsw.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.jsw.utility.GlobalConstant;
import com.yash.jsw.utility.ResponseWrapper;

@RestController
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@PropertySource(value = { "classpath:application.properties" })
public class SecurityController {
	private static final Logger logger = LogManager.getLogger(SecurityController.class);

	@Autowired
	private Environment environment;

	@RequestMapping(value = "/checkLogin")
	public ResponseWrapper createUser(
			@RequestParam(value="error",required=false) String error,
			@RequestParam(value="logout",required=false) String logout,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("Login/logout service called ");
		if(error!=null){
			logger.info("userName or password incorrect");
			return this.getResponse(environment.getRequiredProperty("WRONG_USERNAME_PASSWORD"),GlobalConstant.WRONG_USERNAME_PASSWORD);    
		}
 		if(logout!=null){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null){    
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}
			logger.info("Logout successful");
			return this.getResponse(environment.getRequiredProperty("SUCCESSFUL_LOGOUT"),GlobalConstant.SUCCESSFUL_LOGOUT);  
		}
		return this.getResponse(null,GlobalConstant.SUCCESSFUL_LOGIN);                                                                                             
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ResponseWrapper accessDeniedPage() throws Exception{	
		logger.info("Access Denied called");
		return this.getResponse(environment.getRequiredProperty("ACCESS_DENIED"),GlobalConstant.ACCESS_DENIED);
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ResponseWrapper adminPage(ModelMap model)throws Exception {
		logger.info("/admin web service called");
		ResponseWrapper wrapper = this.getResponse(environment.getRequiredProperty("SUCCESSFUL_LOGIN"),GlobalConstant.SUCCESSFUL_LOGIN);   
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			wrapper.setUserName(((UserDetails)principal).getUsername());
		}
		return wrapper;
	}

	@RequestMapping("/user")
	public ResponseWrapper user()throws Exception {
		logger.info("/user web service called");
		ResponseWrapper wrapper = this.getResponse(environment.getRequiredProperty("SUCCESSFUL_LOGIN"),GlobalConstant.SUCCESSFUL_LOGIN);   
		Collection<? extends GrantedAuthority> authorities = null;
		List<String> roles = new ArrayList<String>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			wrapper.setUserName(((UserDetails)principal).getUsername());
			authorities = ((UserDetails)principal).getAuthorities();
			for (GrantedAuthority a : authorities) {
				roles.add(a.getAuthority());
			}
		}
		wrapper.setAuthorities(roles);
		return wrapper;
	}
	@RequestMapping("/principal")
	public Principal principal(Principal principal)throws Exception {
		logger.info("/principal web service called ");
		return principal;
	}

	private ResponseWrapper getResponse(String message, Integer code) {
		ResponseWrapper wrapper = new ResponseWrapper();
		wrapper.setMessage(message);
		wrapper.setResponseCode(code);
		return wrapper;
	}
}