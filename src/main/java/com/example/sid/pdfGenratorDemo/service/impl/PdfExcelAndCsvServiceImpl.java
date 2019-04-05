package com.example.sid.pdfGenratorDemo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.example.sid.pdfGenratorDemo.constant.FileConstant;
import com.example.sid.pdfGenratorDemo.dto.response.Employee;
import com.example.sid.pdfGenratorDemo.dto.response.Student;
import com.example.sid.pdfGenratorDemo.service.PdfExcelAndCsvService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import au.com.bytecode.opencsv.CSVWriter;

@Service(value = "PdfExcelAndCsvService")
public class PdfExcelAndCsvServiceImpl<T> implements PdfExcelAndCsvService<T> {

	@Override
	public boolean createPdf(JSONArray jsonArray, ServletContext context, HttpServletRequest request,
			HttpServletResponse response, List<String> columns, String type) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);

		String filePath = context.getRealPath(FileConstant.resourceFolder);
		File file = new File(filePath);
		boolean fileExist = new File(filePath).exists();
		if (!fileExist) {
			new File(filePath).mkdirs();
		}
		try {
			PdfWriter pdfwriter = PdfWriter.getInstance(document,
					new FileOutputStream(file + "/" + FileConstant.employeePdfExtension));
			document.open();
			Font mainFont = FontFactory.getFont(FileConstant.fontType, 10, BaseColor.BLACK);
			createAndAddParagraphOnDocument(document, mainFont, type);
			PdfPTable pdfTable = createPdfTable();
			Font tableHeader = FontFactory.getFont(FileConstant.fontType, 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont(FileConstant.fontType, 9, BaseColor.BLACK);
			float[] columnWidths = { 2f, 2f, 2f, 2f };
			pdfTable.setWidths(columnWidths);
			columns.stream().forEach(column -> createEachColumnHeader(pdfTable, tableHeader, column));
			
			
/*			jsonArray.forEach(json -> {
				createPdfRow(pdfTable, tableBody, json);
			});*/
			
			for(int i = 0; i < jsonArray.length(); i++)
			{
			      JSONObject jsonObject = jsonArray.getJSONObject(i);
			      List<String> responseData = new ArrayList<>();
			      Iterator<String> iterator = jsonObject.keys();
			      while(iterator.hasNext()) {
			    	  String key = (String) iterator.next();
			    	  String keyValue = (String) jsonObject.get((String) key);
			    	  responseData.add(keyValue);
				      createPdfRow(pdfTable, tableBody, responseData);
				      responseData.clear();
			      }
			      
/*			      jsonObject.keys.forEach(keyStr ->
			      {
			          String keyValue = (String) jsonObject.get((String) keyStr);
			          responseData.add(keyValue);
			      });*/

			}
			
			
			document.add(pdfTable);
			document.close();
			pdfwriter.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	private void createPdfRow(PdfPTable pdfTable, Font tableBody, List<String> responseData) {
/*		Employee emp = null;
		Student stud = null;
		if (candidate instanceof Employee) {
			emp = (Employee) candidate;
			createPdfCell(pdfTable, tableBody, Integer.toString(emp.getEmployeeId()));
			createPdfCell(pdfTable, tableBody, emp.getFirstName());
			createPdfCell(pdfTable, tableBody, emp.getLastName());
			createPdfCell(pdfTable, tableBody, emp.getCity());
		} else if (candidate instanceof Student) {
			stud = (Student) candidate;
			createPdfCell(pdfTable, tableBody, Integer.toString(stud.getStudentId()));
			createPdfCell(pdfTable, tableBody, stud.getFirstName());
			createPdfCell(pdfTable, tableBody, stud.getLastName());
			createPdfCell(pdfTable, tableBody, stud.getMobileNumber());
		}*/
		responseData.forEach(fieldValue -> createPdfCell(pdfTable, tableBody, fieldValue));
		
	}

	private PdfPTable createPdfTable() {
		PdfPTable pdfTable = new PdfPTable(4);
		pdfTable.setWidthPercentage(100);
		pdfTable.setSpacingBefore(10f);
		pdfTable.setSpacingAfter(10);
		return pdfTable;
	}

	private void createAndAddParagraphOnDocument(Document document, Font mainFont, String type)
			throws DocumentException {
		Paragraph paragraph = new Paragraph(type, mainFont);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setIndentationLeft(50);
		paragraph.setIndentationRight(50);
		paragraph.setSpacingAfter(10);
		document.add(paragraph);
	}



	private void createEachColumnHeader(PdfPTable pdfTable, Font tableHeader, String column) {
		PdfPCell columnName = new PdfPCell(new Paragraph(column, tableHeader));
		columnName.setBorderColor(BaseColor.BLACK);
		columnName.setPaddingLeft(10);
		columnName.setHorizontalAlignment(Element.ALIGN_CENTER);
		columnName.setHorizontalAlignment(Element.ALIGN_CENTER);
		columnName.setBackgroundColor(BaseColor.GRAY);
		columnName.setExtraParagraphSpace(5f);
		pdfTable.addCell(columnName);
	}



	private void createPdfCell(PdfPTable pdfTable, Font tableBody, String value) {
		PdfPCell cellValue = new PdfPCell(new Paragraph(value, tableBody));
		cellValue.setBorderColor(BaseColor.BLACK);
		cellValue.setPaddingLeft(10);
		cellValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValue.setBackgroundColor(BaseColor.WHITE);
		cellValue.setExtraParagraphSpace(5f);
		pdfTable.addCell(cellValue);
	}

	@Override
	public boolean createExcel(List<Employee> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath(FileConstant.resourceFolder);
		File file = new File(filePath);
		boolean fileExist = new File(filePath).exists();
		if (!fileExist) {
			new File(filePath).mkdirs();
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file + "/" + "employees" + ".xls");
			HSSFWorkbook workBook = new HSSFWorkbook();
			HSSFSheet workSheet = workBook.createSheet("Employees");
			workSheet.setDefaultColumnWidth(30);

			HSSFCellStyle headerCellStyle = workBook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			HSSFCellStyle bodyCellStyle = workBook.createCellStyle();
			bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);

			createHeaderRow(workSheet, headerCellStyle);

			int i = 1;
			for (Employee employee : employees) {
				createExcelDataRow(workSheet, bodyCellStyle, i, employee);
				i++;
			}

			workBook.write(fileOutputStream);
			fileOutputStream.flush();
			fileOutputStream.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private void createExcelDataRow(HSSFSheet workSheet, HSSFCellStyle bodyCellStyle, int i, Employee employee) {
		HSSFRow bodyRow = workSheet.createRow(i);

		HSSFCell empIdValue = bodyRow.createCell(0);
		empIdValue.setCellValue(employee.getEmployeeId());
		empIdValue.setCellStyle(bodyCellStyle);

		/*
		 * HSSFCell firstNameValue = bodyRow.createCell(1);
		 * firstNameValue.setCellValue(employee.getFirstName());
		 * firstNameValue.setCellStyle(bodyCellStyle);
		 * 
		 * HSSFCell lastNameValue = bodyRow.createCell(2);
		 * lastNameValue.setCellValue(employee.getLastName());
		 * lastNameValue.setCellStyle(bodyCellStyle);
		 */

		HSSFCell cityValue = bodyRow.createCell(1);
		cityValue.setCellValue(employee.getCity());
		cityValue.setCellStyle(bodyCellStyle);
	}

	private void createHeaderRow(HSSFSheet workSheet, HSSFCellStyle headerCellStyle) {
		HSSFRow headerRow = workSheet.createRow(0);

		HSSFCell empId = headerRow.createCell(0);
		empId.setCellValue("Employee Id");
		empId.setCellStyle(headerCellStyle);

		/*
		 * HSSFCell firstName = headerRow.createCell(1);
		 * firstName.setCellValue("First Name");
		 * firstName.setCellStyle(headerCellStyle);
		 * 
		 * HSSFCell lastName = headerRow.createCell(2);
		 * lastName.setCellValue("Last Name"); lastName.setCellStyle(headerCellStyle);
		 */

		HSSFCell city = headerRow.createCell(3);
		city.setCellValue("City");
		city.setCellStyle(headerCellStyle);
	}

	@Override
	public boolean createCsv(List<Employee> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean fileExist = new File(filePath).exists();
		if (!fileExist) {
			new File(filePath).mkdirs();
		}
		try {
			FileWriter fileWriter = new FileWriter(file + "/" + "employees" + ".csv");
			CSVWriter csvWriter = new CSVWriter(fileWriter);
			String[] headerRow = { "Employee Id", /* "First Name", "Last Name", */ "City" };
			csvWriter.writeNext(headerRow);
			for (Employee employee : employees) {
				String[] dataRow = { Integer.toString(employee.getEmployeeId()),
						/*
						 * employee.getFirstName(), employee.getLastName(),
						 */ employee.getCity() };
				csvWriter.writeNext(dataRow);
			}
			csvWriter.close();
			fileWriter.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void fileDownload(String fullPath, HttpServletResponse response, String fileName, ServletContext context,
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
