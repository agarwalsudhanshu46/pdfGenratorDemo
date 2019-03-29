package com.example.sid.pdfGenratorDemo.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;


@JsonTypeName("Student")
public class Student<T> extends Candidate implements Serializable {

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -7043559940360535853L;

	@JsonProperty(value= "studentId")
	private int studentId;

	@JsonProperty(value= "mobileNumber")
	private String mobileNumber;

	@JsonCreator
	public Student(int studentId, String firstName, String lastName, String mobileNumber) {
		super(firstName, lastName);
		this.studentId = studentId;
		this.mobileNumber = mobileNumber;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	

}
