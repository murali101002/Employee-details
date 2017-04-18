package com.practice.api.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.nativejdbc.C3P0NativeJdbcExtractor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAConfig {
	@Bean
	public LocalContainerEntityManagerFactoryBean emf(){
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
		emf.setPackagesToScan("com.practice.api.entity");
		emf.setDataSource(dataSource());
		emf.setJpaProperties(jpaProperties());
		return emf;
	}
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driver.setUrl("jdbc:mysql://localhost:3306/users?useSSL=false");
		driver.setUsername("root");
		driver.setPassword("root");
		return driver;
	}
	@Bean
	public PlatformTransactionManager txnMgr(EntityManagerFactory emf){
		JpaTransactionManager txnMgr = new JpaTransactionManager(emf);
		return txnMgr;
	}
	private Properties jpaProperties(){
		Properties prop = new Properties();
		prop.setProperty(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
		prop.setProperty(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.FINE_LABEL);
		prop.setProperty(PersistenceUnitProperties.WEAVING, "false");
		return prop;
	}
}
