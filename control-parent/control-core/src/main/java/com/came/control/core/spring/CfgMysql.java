package com.came.control.core.spring;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class CfgMysql {

	@Bean
	public DataSource dataSource() {
		return sqlServerDataSource();
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.came.control.model");
		sessionFactory.setHibernateProperties(sqlServerHibernateProper());
 
		return sessionFactory;
	}
 
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
	
	
	private DriverManagerDataSource mySqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/control");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	private DriverManagerDataSource sqlServerDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
		dataSource.setUrl("jdbc:jtds:sqlserver://localhost:1433/control;instance=SISTEMAS");
		dataSource.setUsername("ctrladmin");
		dataSource.setPassword("ctrladmin");
		
//		dataSource.setUrl("jdbc:jtds:sqlserver://localhost:55291/control;instance0=SISTEMAS");
//		dataSource.setUsername("stockadmin");
//		dataSource.setPassword("St0ck2ol6");
		
		return dataSource;
	}
	
	private Properties mySqlHibernateProper() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProperties.put("hibernate.show_sql", "true");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hbm2ddl.import_files", "sql.sql");
		return hibernateProperties;
	}
	
	private Properties sqlServerHibernateProper() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect");
		hibernateProperties.put("hibernate.show_sql", "true");
		hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.put("hbm2ddl.import_files", "sql.sql");
		return hibernateProperties;
	}
	
	
}
