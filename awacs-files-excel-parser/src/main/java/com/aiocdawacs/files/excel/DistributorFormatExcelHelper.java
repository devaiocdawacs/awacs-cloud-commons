package com.aiocdawacs.files.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.aiocdawacs.files.service.StorageFileNotFoundException;

//Extension MIME Type
//.doc      application/msword
//.dot      application/msword
//
//.docx     application/vnd.openxmlformats-officedocument.wordprocessingml.document
//.dotx     application/vnd.openxmlformats-officedocument.wordprocessingml.template
//.docm     application/vnd.ms-word.document.macroEnabled.12
//.dotm     application/vnd.ms-word.template.macroEnabled.12
//
//.xls      application/vnd.ms-excel
//.xlt      application/vnd.ms-excel
//.xla      application/vnd.ms-excel
//
//.xlsx     application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
//.xltx     application/vnd.openxmlformats-officedocument.spreadsheetml.template
//.xlsm     application/vnd.ms-excel.sheet.macroEnabled.12
//.xltm     application/vnd.ms-excel.template.macroEnabled.12
//.xlam     application/vnd.ms-excel.addin.macroEnabled.12
//.xlsb     application/vnd.ms-excel.sheet.binary.macroEnabled.12

public class DistributorFormatExcelHelper {
	
	// example test/resources/smart_pharmacy_products.xlsx
	
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	static String[] HEADERs = { "id", "name", "price", "quantity", "distributor_name", "generic_name", "address" };
	
	static String SHEET = "data";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<DistributorFormatPojo> excelToDistributors(Resource file) throws StorageFileNotFoundException{
		try {
			return excelToDistributors(new FileInputStream(file.getFile()));
		} catch (IOException e) {
			throw new StorageFileNotFoundException(e);
		}
	}
	
	public static List<DistributorFormatPojo> excelToDistributors(InputStream is) throws StorageFileNotFoundException {
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<DistributorFormatPojo> distributors = new ArrayList<DistributorFormatPojo>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				DistributorFormatPojo pojo = new DistributorFormatPojo();

 				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						pojo.setId((long) currentCell.getNumericCellValue());
						break;
					case 1:
						pojo.setName(currentCell.getStringCellValue());
						break;
					case 2:
						pojo.setPrice(currentCell.getNumericCellValue());
						break;
					case 3:
						pojo.setQuantity((int)currentCell.getNumericCellValue());
						break;
					case 4:
						pojo.setDistributorName(currentCell.getStringCellValue());
						break;
					case 5:
						pojo.setGenericName(currentCell.getStringCellValue());
						break;
					case 6:
						pojo.setAddress(currentCell.getStringCellValue());
						break;
					default:
						break;
					}
					cellIdx++;
				}
				distributors.add(pojo);
			}

			workbook.close();
			return distributors;
		} catch (IOException e) {
			throw new StorageFileNotFoundException("Failed to parse excel file", e);
		}
	}
}