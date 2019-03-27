package com.example.sid.pdfGenratorDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sid.pdfGenratorDemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
