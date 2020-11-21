package com.aiocdawacs.files.excel.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/test/")
public class TestExcelController {

	@GetMapping("/api/files/excel")
	public ResponseEntity<StreamingResponseBody> excel() {

		Workbook workBook = new XSSFWorkbook();
		Sheet sheet = workBook.createSheet("My Sheet");
		sheet.setColumnWidth(0, 2560);
		sheet.setColumnWidth(1, 2560);
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("Hello World");

		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"hello-world.xlsx\"")
				.body(workBook::write);
	}
}
