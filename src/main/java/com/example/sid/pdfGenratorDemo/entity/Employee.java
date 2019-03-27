package com.example.sid.pdfGenratorDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name= "employee")
public class Employee {

	@Id
	@Column(name="empId")
	private int employeeId;
	
	@Column(name="first_Name")
	private String firstName;
	
	@Column(name="last_Name")
	private String lastName;
	
	@Column(name="city")
	private String city;
	
/*	@Column(name="city_Code")
	private String cityCode;*/
}
