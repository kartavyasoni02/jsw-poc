package com.yash.jsw.configuratio;

import java.io.IOException;
import java.util.Properties;

import org.quartz.spi.JobFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


@Configuration
public class QuartzConfig {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuartzConfig.class);
	
	@Bean
	public SchedulerFactoryBean quartzScheduler(JobFactory jobFactory) {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
		quartzScheduler.setQuartzProperties(quartzProperties());
		quartzScheduler.setJobFactory(jobFactory);
		quartzScheduler.setWaitForJobsToCompleteOnShutdown(true);
		return quartzScheduler;
	}

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		RegisterSpringBeanJobFactory jobFactory = new RegisterSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}


	@Bean
	public Properties quartzProperties() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		Properties properties = null;
		try {
			propertiesFactoryBean.afterPropertiesSet();
			properties = propertiesFactoryBean.getObject();
		}
		catch (IOException e) {
			LOGGER.error("Exception in quartzProperties()",e);
		}
		return properties;
	}
	 
}