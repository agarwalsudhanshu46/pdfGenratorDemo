package com.example.sid.pdfGenratorDemo.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("Employee")
public class Employee extends Candidate implements Serializable {

	private static final long serialVersionUID = -1221794581857450594L;

	@JsonProperty(value = "employeeId")
	private int employeeId;

	@JsonProperty(value = "city")
	private String city;

	public Employee(String firstName, String lastName, int employeeId, String city) {
		super(firstName, lastName);
		this.employeeId = employeeId;
		this.city = city;
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

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	

}
