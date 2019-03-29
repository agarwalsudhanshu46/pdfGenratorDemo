package com.example.sid.pdfGenratorDemo.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Employee.class, name = "Employee"), @Type(value = Student.class, name = "Student") })
public class Candidate implements Serializable {

	private static final long serialVersionUID = -1769865478170350865L;

	@JsonProperty(value = "firstName")
	private  String firstName;

	@JsonProperty(value = "lastName")
	private  String lastName;

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

	public Candidate(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
