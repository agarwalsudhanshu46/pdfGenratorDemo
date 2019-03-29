package com.example.sid.pdfGenratorDemo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sid.pdfGenratorDemo.dto.request.CandidateRequest;
import com.example.sid.pdfGenratorDemo.dto.response.Candidate;
import com.example.sid.pdfGenratorDemo.service.PdfExcelAndCsvService;

//@Controller  -> used to show the data in view (html) format
@RestController
public class PdfExcelCSVGeneratorController {

/*	@Autowired
	private EmployeeService employeeService;*/
	


	@Autowired
	private PdfExcelAndCsvService pdfAndExcelService;
	


	@Autowired
	private ServletContext context;

	private static final String printFunctionality = "print functionality";
	private static final String resourceFolder = "/resources/reports/";
	private static final String employeePdfExtension = "employees.pdf";

	
	@PostMapping(value = "/candidates")
	public List<Candidate> getCandidates(@RequestBody CandidateRequest candidateRequest) {
/*		System.out.println(candidateRequest.getClass().getSimpleName());
		List<Candidate> candidates = candidateService.getCandidates();
       return candidates;*/
		
		
		
		return null;
	}
	
	
/*	@GetMapping(value = "/employees")
	public String getEmployees(Model model) {
		List<Employee> employees = employeeService.getEmployees();
		model.addAttribute("employees", employees);
		return "view/employees";
	}

	@GetMapping(value = "/printPdf")
	public void printPdf(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> employees = employeeService.getEmployees();
		boolean isValidPdf = pdfAndExcelService.createPdf(employees, context, request, response);

		if (isValidPdf) {
			String fullPath = request.getServletContext().getRealPath(resourceFolder + employeePdfExtension);
			fileDownload(fullPath, response, employeePdfExtension, printFunctionality);
		}
	}

	@GetMapping(value = "/downloadPdf")
	public void downloadPdf(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> employees = employeeService.getEmployees();
		boolean isValidPdf = pdfAndExcelService.createPdf(employees, context, request, response);

		if (isValidPdf) {
			String fullPath = request.getServletContext().getRealPath(resourceFolder + employeePdfExtension);
			fileDownload(fullPath, response, employeePdfExtension);
		}
	}

	@GetMapping(value = "/downloadExcel")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> employees = employeeService.getEmployees();
		boolean isValidExcel = pdfAndExcelService.createExcel(employees, context, request, response);
		String employeeExcelExtension = "employees.xls";
		if (isValidExcel) {
			String fullPath = request.getServletContext().getRealPath(resourceFolder + employeeExcelExtension);
			fileDownload(fullPath, response, employeeExcelExtension);
		}
	}
	
	@GetMapping(value = "/downloadCsv")
	public void downloadCsv(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> employees = employeeService.getEmployees();
		boolean isValidExcel = pdfAndExcelService.createCsv(employees, context, request, response);
		String employeeExcelExtension = "employees.csv";
		if (isValidExcel) {
			String fullPath = request.getServletContext().getRealPath(resourceFolder + employeeExcelExtension);
			fileDownload(fullPath, response, employeeExcelExtension);
		}
	}*/

	private void fileDownload(String fullPath, HttpServletResponse response, String fileName,
			String... functionalityName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);

				// the below if condition used for downloading the pdf
				if (functionalityName.length == 0) {
					response.setHeader("content-disposition", "attachment; filename=" + fileName);
				}
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
