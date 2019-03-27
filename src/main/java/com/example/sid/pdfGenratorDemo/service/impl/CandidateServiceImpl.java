package com.example.sid.pdfGenratorDemo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sid.pdfGenratorDemo.dto.response.Candidate;
import com.example.sid.pdfGenratorDemo.repository.EmployeeRepository;
import com.example.sid.pdfGenratorDemo.repository.StudentRepository;
import com.example.sid.pdfGenratorDemo.service.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService {

	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public List<Candidate> getCandidates() {
		return null;
	}

}
