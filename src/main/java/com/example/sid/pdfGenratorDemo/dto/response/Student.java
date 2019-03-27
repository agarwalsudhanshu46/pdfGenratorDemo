package com.example.sid.pdfGenratorDemo.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName("Student")
public class Student extends Candidate implements Serializable {

	private static final long serialVersionUID = -7043559940360535853L;

	@JsonProperty(value= "studentId")
	private int studentId;

	@JsonProperty(value= "firstName")
	private String firstName;

	@JsonProperty(value= "lastName")
	private String lastName;

	@JsonProperty(value= "mobileNumber")
	private String mobileNumber;

	@JsonCreator
	public Student(int studentId, String firstName, String lastName, String mobileNumber) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
	}
	
	

}
