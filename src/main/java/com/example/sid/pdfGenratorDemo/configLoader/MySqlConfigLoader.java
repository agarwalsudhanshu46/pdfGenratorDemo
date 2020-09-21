package com.example.sid.pdfGenratorDemo.configLoader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

//@Configuration
@ConfigurationProperties(prefix = "mysql")
@Data
@PropertySource("classpath:config.properties")
public class MySqlConfigLoader {

	@Value("${mysql.url}")
	private String url;
	@Value("${mysql.username}")
	private String username;
	@Value("${mysql.password}")
	private String password;
	@Value("${mysql.driver}")
	private String driver;
	@Value("${mysql.dialect}")
	private String dialect;

	public String getUrl() {
		return url;
	}

}
