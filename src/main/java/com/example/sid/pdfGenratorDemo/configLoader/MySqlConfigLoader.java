package com.example.sid.pdfGenratorDemo.configLoader;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Configuration
@ConfigurationProperties(prefix="mysql")
@Getter
@Setter
@PropertySource("classpath:/config.properties")
public class MySqlConfigLoader {
	@NotNull
	private String url;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String driver;
	@NotNull
	private String dialect;

}
