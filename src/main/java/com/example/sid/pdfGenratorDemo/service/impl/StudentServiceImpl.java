package com.example.sid.pdfGenratorDemo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sid.pdfGenratorDemo.dto.request.CandidateRequest;
import com.example.sid.pdfGenratorDemo.dto.response.Student;
import com.example.sid.pdfGenratorDemo.registry.AdaptorService;
import com.example.sid.pdfGenratorDemo.repository.StudentRepository;
import com.example.sid.pdfGenratorDemo.service.StudentService;

@Service(value="StudentRequest")
@Transactional
public class StudentServiceImpl implements StudentService, AdaptorService<CandidateRequest> {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<Student> getStudents() {
		List<com.example.sid.pdfGenratorDemo.entity.Student> students = studentRepository.findAll();
		List<Student> StudentDtos = students.stream().map(entity -> map(entity)).collect(Collectors.toList());
		return StudentDtos;

	}

	private Student map(com.example.sid.pdfGenratorDemo.entity.Student StudentEntity) {
		Student student = modelMapper.map(StudentEntity, Student.class);
		return student;

	}

	@Override
	public void process(CandidateRequest request) {
		System.out.println("I am from candidate servie");
		
	}

}
