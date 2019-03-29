package com.example.sid.pdfGenratorDemo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.sid.pdfGenratorDemo.configLoader.MySqlConfigLoader;
import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
@EnableJpaRepositories(
		basePackages ="com.example",
		entityManagerFactoryRef="entityManagerFactoryMYsql",
		transactionManagerRef="transactionManagerMySql"
		)
@EnableTransactionManagement
public class MySqlConfig {
	private static final String basePackage = "com.example";
	
	@Autowired
	private MySqlConfigLoader mySqlConfigLoader;
	
	@Bean(name = "dataSourceMySql")
	public DataSource getMYSqlDataSource() {
		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		mysqlDataSource.setUser(mySqlConfigLoader.getUsername());
		mysqlDataSource.setPassword(mySqlConfigLoader.getPassword());
		mysqlDataSource.setURL(mySqlConfigLoader.getUrl());
		return mysqlDataSource;
		
	}
	
	@Bean(name = "entityManagerFactoryMYsql")
	public LocalContainerEntityManagerFactoryBean getEntityManager() {
		LocalContainerEntityManagerFactoryBean entityManagerBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerBean.setPackagesToScan(basePackage);
		entityManagerBean.setDataSource(getMYSqlDataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", mySqlConfigLoader.getDialect());
		HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
		entityManagerBean.setJpaProperties(hibernateProperties);
		entityManagerBean.setJpaVendorAdapter(jpaAdapter);
		return entityManagerBean;
	}
	
	@Bean(name = "transactionManagerMySql")
	public PlatformTransactionManager geTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(getEntityManager().getObject()); 
		return jpaTransactionManager;
	}

}
