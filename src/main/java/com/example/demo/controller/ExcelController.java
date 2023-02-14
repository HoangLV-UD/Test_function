package com.example.demo.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.dto.PhoneDTO;
import com.example.demo.service.ExcelService;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Validated
@RestController
@RequestMapping(value = "/api/excel")
public class ExcelController {
	@Autowired
	ExcelService excelService;
	 
	@Autowired
	private HttpServletResponse response;
	
	@GetMapping(value = "/export-list")
	@ResponseBody
	public Object exportList() {
		try {
			List<PhoneDTO> dto = new ArrayList<>();
			PhoneDTO d = new PhoneDTO();
			d.setName("iphone");
			d.setPrice(12000);
			d.setQuantity("3");
			dto.add(d);

			Resource resource = new ClassPathResource("excel_template/list.xlsx");
			InputStream ips = resource.getInputStream();
			
			Workbook wb  = WorkbookFactory.create(ips);
			Sheet sheet = wb.getSheetAt(0);
			Row defaultRow = sheet.getRow(5); // lấy row thứ 5 từ file excel
			CellStyle cellStyle = defaultRow.getRowStyle(); // lấy style từ row gốc 
			for (int i = 0; i < dto.size(); i++) {
				int rowindex = 5 + i;
				Row row = sheet.getRow(rowindex);
				if(row == null) {
					row = sheet.createRow(rowindex); //tao
					row.setRowStyle(cellStyle);
					for (int j = 0; j < 5; j++) {
						Cell newCell =  row.createCell(j);
						newCell.setCellStyle(defaultRow.getCell(j).getCellStyle());
					}
					
				}
				row.getCell(0).setCellValue(i + 1);
				
				row.getCell(1).setCellValue(dto.get(i).getName());
				row.getCell(2).setCellValue(dto.get(i).getPrice());
				row.getCell(3).setCellValue(dto.get(i).getQuantity());
				
			}
			ServletOutputStream out = response.getOutputStream();
//			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-disposition", "attachment; filename="+ "Danh_sach_dien_thoai"+ ".xlsx");
			wb.write(out);
			wb.close();
			out.close();
			
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return e;
		}
	}
	
	@PostMapping(value = "/import-list")
	public Object importList(@RequestBody(required = true) MultipartFile file) {
		
		return excelService.importList(file);
	}
}
