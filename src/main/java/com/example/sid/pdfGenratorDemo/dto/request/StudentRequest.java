package com.example.sid.pdfGenratorDemo.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName(value = "StudentRequest")
public class StudentRequest extends CandidateRequest implements Serializable {
	private static final long serialVersionUID = -2276643870717788834L;

	@JsonProperty(value = "studentId")
	private int studentId;

	@JsonProperty(value = "mobileNumber")
	private String mobileNumber;

	public StudentRequest(String firstName, String lastName, int studentId, String mobileNumber) {
		super(firstName, lastName);

		this.studentId = studentId;
		this.mobileNumber = mobileNumber;
	}

}
