package com.example.sid.pdfGenratorDemo.dto.request;

import java.io.Serializable;

import com.example.sid.pdfGenratorDemo.dto.request.CandidateRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName("EmployeeRequest")
public class EmployeeRequest extends CandidateRequest implements Serializable {
	private static final long serialVersionUID = 3598537965994686263L;

	@JsonProperty(value = "employeeId")
	private final int employeeId;

	@JsonProperty(value = "city")
	private final String city;
	

	public EmployeeRequest(String firstName, String lastName, int employeeId, String city) {
		super(firstName, lastName);
		this.employeeId = employeeId;
		this.city = city;
	}

}
