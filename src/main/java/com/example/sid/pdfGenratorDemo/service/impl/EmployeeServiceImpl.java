package com.example.sid.pdfGenratorDemo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sid.pdfGenratorDemo.dto.request.CandidateRequest;
import com.example.sid.pdfGenratorDemo.dto.response.Employee;
import com.example.sid.pdfGenratorDemo.registry.AdaptorService;
import com.example.sid.pdfGenratorDemo.repository.EmployeeRepository;
import com.example.sid.pdfGenratorDemo.service.EmployeeService;

@Service(value="EmployeeRequest")
@Transactional
public class EmployeeServiceImpl implements EmployeeService, AdaptorService<CandidateRequest> {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<Employee> getEmployees() {
		List<com.example.sid.pdfGenratorDemo.entity.Employee> employees = employeeRepository.findAll();
		List<Employee> employeeDtos = employees.stream().map(entity -> map(entity)).collect(Collectors.toList());
		return employeeDtos;

	}

	private Employee map(com.example.sid.pdfGenratorDemo.entity.Employee employeeEntity) {
		Employee employee = modelMapper.map(employeeEntity, Employee.class);
		return employee;

	}

	@Override
	public void process(CandidateRequest request) {
		System.out.println("I am from employee servie");
		
	}
	

}
