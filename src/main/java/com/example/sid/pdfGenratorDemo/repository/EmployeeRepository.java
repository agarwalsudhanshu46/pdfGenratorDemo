package com.example.sid.pdfGenratorDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sid.pdfGenratorDemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

