package com.example.sid.pdfGenratorDemo.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@JsonTypeName("Employee")
public class Employee  {


	@JsonProperty(value = "firstName")
	private  String firstName;

	@JsonProperty(value = "lastName")
	private  String lastName;
	
	@JsonProperty(value = "employeeId")
	private int employeeId;

	@JsonProperty(value = "city")
	private String city;

	public Employee(String firstName, String lastName, int employeeId, String city) {
		this.employeeId = employeeId;
		this.city = city;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	

}
