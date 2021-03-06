package com.example.sid.pdfGenratorDemo.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = EmployeeRequest.class, name = "EmployeeRequest"),
		@Type(value = StudentRequest.class, name = "StudentRequest") })
public class CandidateRequest implements Serializable {

	private static final long serialVersionUID = 9031157006224995511L;

	@JsonProperty(value = "firstName")
	private final String firstName;

	@JsonProperty(value = "lastName")
	private final String lastName;


}
