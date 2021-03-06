package com.example.sid.pdfGenratorDemo.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.example.sid.pdfGenratorDemo.dto.response.Employee;

public interface PdfExcelAndCsvService<T> {

	public boolean createPdf(JSONArray jsonArray, ServletContext context, HttpServletRequest request,
			HttpServletResponse response, List<String> columns, String type);

	public boolean createExcel(List<Employee> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	public boolean createCsv(List<Employee> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	public void fileDownload(String fullPath, HttpServletResponse response, String fileName, ServletContext servletContext,
			String... functionalityName);
}
