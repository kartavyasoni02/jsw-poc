package com.yash.jsw.configuratio;

	
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@SpringBootApplication(scanBasePackages="com.yash.jsw")	
public class ApplicationConfiguration extends SpringBootServletInitializer{
	private static final Logger logger = LogManager.getLogger(ApplicationConfiguration.class);
	 
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationConfiguration.class);
    }
	public static void main(String[] args) throws Exception {
		logger.info("Application starting... ");
		SpringApplication.run(ApplicationConfiguration.class, args);
		logger.info("Go to this URL: http://localhost:8080/");
	}
	@Bean
	public Filter loggingFilter(){
	    AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {

	        @Override
	        protected void beforeRequest(HttpServletRequest request, String message) {
	        	if(request.getMethod().equalsIgnoreCase("GET") || request.getMethod().equalsIgnoreCase("POST")){
	        	logger.info(request.getMethod()+" "+message);
	        	}
	        }

	        @Override
	        protected void afterRequest(HttpServletRequest request, String message) {
	        }
	    };
	    f.setIncludePayload(true);
	    f.setIncludeQueryString(true);
	   f.setBeforeMessagePrefix("  [");
	   
	    return f;
	}
}
