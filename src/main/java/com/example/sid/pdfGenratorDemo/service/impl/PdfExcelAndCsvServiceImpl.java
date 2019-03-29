package com.example.sid.pdfGenratorDemo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
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
import org.springframework.stereotype.Service;

import com.example.sid.pdfGenratorDemo.dto.response.Employee;
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
	public boolean createPdf(List<T> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);

		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean fileExist = new File(filePath).exists();
		if (!fileExist) {
			new File(filePath).mkdirs();
		}
		try {
			PdfWriter pdfwriter = PdfWriter.getInstance(document,
					new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();
			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

			createAndAddParagraphOnDocument(document, mainFont);

			PdfPTable pdfTable = createPdfTable();

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f, 2f };
			pdfTable.setWidths(columnWidths);

			createPdfHeader(pdfTable, tableHeader);

/*			for (Employee employee : employees) {
				createPdfRow(pdfTable, tableBody, employee);
			}*/

			document.add(pdfTable);
			document.close();
			pdfwriter.close();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	private PdfPTable createPdfTable() {
		PdfPTable pdfTable = new PdfPTable(4);
		pdfTable.setWidthPercentage(100);
		pdfTable.setSpacingBefore(10f);
		pdfTable.setSpacingAfter(10);
		return pdfTable;
	}

	private void createAndAddParagraphOnDocument(Document document, Font mainFont) throws DocumentException {
		Paragraph paragraph = new Paragraph("All Employees", mainFont);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setIndentationLeft(50);
		paragraph.setIndentationRight(50);
		paragraph.setSpacingAfter(10);
		document.add(paragraph);
	}

	private void createPdfRow(PdfPTable pdfTable, Font tableBody, Employee employee) {
		PdfPCell idValue = new PdfPCell(new Paragraph(Integer.toString(employee.getEmployeeId()), tableBody));
		createPdfCell(pdfTable, idValue);

		/*
		 * PdfPCell firstNameValue = new PdfPCell(new Paragraph(employee.getFirstName(),
		 * tableBody)); createPdfCell(pdfTable, firstNameValue);
		 */

		/*
		 * PdfPCell lastNameValue = new PdfPCell(new Paragraph(employee.getLastName(),
		 * tableBody)); createPdfCell(pdfTable, lastNameValue);
		 */

		PdfPCell cityValue = new PdfPCell(new Paragraph(employee.getCity(), tableBody));
		createPdfCell(pdfTable, cityValue);
	}

	private void createPdfCell(PdfPTable pdfTable, PdfPCell cellValue) {
		cellValue.setBorderColor(BaseColor.BLACK);
		cellValue.setPaddingLeft(10);
		cellValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		cellValue.setBackgroundColor(BaseColor.WHITE);
		cellValue.setExtraParagraphSpace(5f);
		pdfTable.addCell(cellValue);
	}

	private PdfPCell createPdfHeader(PdfPTable pdfTable, Font tableHeader) {
		PdfPCell employeeId = new PdfPCell(new Paragraph("Employee Id", tableHeader));
		createEachColumnHeader(pdfTable, employeeId);

		/*
		 * PdfPCell firstName = new PdfPCell(new Paragraph("First Name", tableHeader));
		 * createEachColumnHeader(pdfTable, firstName);
		 * 
		 * PdfPCell lastName = new PdfPCell(new Paragraph("Last Name", tableHeader));
		 * createEachColumnHeader(pdfTable, lastName);
		 */

		PdfPCell city = new PdfPCell(new Paragraph("City", tableHeader));
		createEachColumnHeader(pdfTable, city);
		return employeeId;
	}

	private void createEachColumnHeader(PdfPTable pdfTable, PdfPCell columnName) {
		columnName.setBorderColor(BaseColor.BLACK);
		columnName.setPaddingLeft(10);
		columnName.setHorizontalAlignment(Element.ALIGN_CENTER);
		columnName.setHorizontalAlignment(Element.ALIGN_CENTER);
		columnName.setBackgroundColor(BaseColor.GRAY);
		columnName.setExtraParagraphSpace(5f);
		pdfTable.addCell(columnName);
	}

	@Override
	public boolean createExcel(List<Employee> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
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
