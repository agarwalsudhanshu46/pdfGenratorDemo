package com.example.sid.pdfGenratorDemo.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName("Employee")
public class Employee extends Candidate implements Serializable {

	private static final long serialVersionUID = -1221794581857450594L;

	@JsonProperty(value= "employeeId")
	private int employeeId;

	@JsonProperty(value= "firstName")
	private String firstName;

	@JsonProperty(value= "lastName")
	private String lastName;

	@JsonProperty(value= "city")
	private String city;

	@JsonCreator
	public Employee(int employeeId, String firstName, String lastName, String city) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}
	
	
}
