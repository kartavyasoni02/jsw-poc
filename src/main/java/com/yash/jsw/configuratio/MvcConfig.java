package com.yash.jsw.configuratio;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class MvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LogManager.getLogger(MvcConfig.class);
	
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT              = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL             = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	
	@Autowired
	private Environment environment;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	@Bean(name = "dataSource")
	public DataSource dataSource()throws Exception {
		logger.info("datasource loading... ");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		logger.info("datasource loading completed ");
		return dataSource;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() throws Exception{
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(environment.getRequiredProperty("resolver.setPrefix"));
		resolver.setSuffix(environment.getRequiredProperty("resolver.setSuffix"));
		return resolver;
	}
	private Properties hibProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		
		properties.put("hibernate.c3p0.min_size", environment.getRequiredProperty("hibernate.c3p0.min_size"));
        properties.put("hibernate.c3p0.max_size", environment.getRequiredProperty("hibernate.c3p0.max_size"));
        properties.put("hibernate.c3p0.timeout", environment.getRequiredProperty("hibernate.c3p0.timeout"));
        properties.put("hibernate.c3p0.max_statements", environment.getRequiredProperty("hibernate.c3p0.max_statements"));
        properties.put("hibernate.c3p0.idle_test_period", environment.getRequiredProperty("hibernate.c3p0.idle_test_period"));
        
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws Exception {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws Exception {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
		sessionFactoryBean.setHibernateProperties(hibProperties());
		return sessionFactoryBean;
	}
}