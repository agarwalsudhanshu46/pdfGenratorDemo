package com.example.sid.pdfGenratorDemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Getter
@Entity
@Table(name= "student")
public class Student {

	
	@Id
	@Column(name="student_id")
	private int studentId;
	
	@Column(name="first_Name")
	private String firstName;
	
	@Column(name="last_Name")
	private String lastName;
	
	@Column(name="mobile_Number")
	private String mobileNumber;
	
}
