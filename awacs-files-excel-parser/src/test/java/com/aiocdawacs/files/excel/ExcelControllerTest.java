package com.aiocdawacs.files.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ExcelControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setUpAbstractDbTest() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testAPIExcel() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(
				MockMvcRequestBuilders.get("/test/api/files/excel"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(request().asyncStarted())
				.andReturn();

		MvcResult result = 
				this.mockMvc
				.perform(asyncDispatch(mvcResult))
				.andReturn();

		byte[] workbookBytes =  result.getResponse().getContentAsByteArray();

		
		Workbook actual = new XSSFWorkbook(new ByteArrayInputStream(workbookBytes));
		String actualValue = actual
				.getSheetAt(0)
				.getRow(0)
				.getCell(0)
				.getStringCellValue();

		assertEquals("Hello World", actualValue); 
	}
}