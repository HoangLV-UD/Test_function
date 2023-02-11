package com.example.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.dto.PhoneDTO;
import com.example.demo.utils.ExcelHelper;
import com.example.demo.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;


@Repository
public class ExcelDao {

	public Object importList(MultipartFile file) {
		try {
			Sheet sheet;
			if (file.getOriginalFilename().endsWith(".xls")) {
				HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
				sheet = workbook.getSheetAt(0);
			} else if (file.getOriginalFilename().endsWith(".xlsx")) {
				XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
				sheet = workbook.getSheetAt(0);
			} else
				return null;
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			int preRow = 1;

			List<PhoneDTO> list = new ArrayList<>();
			List<String> arrayName = new ArrayList<>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				int rows = row.getRowNum() + 1;
				// check null example ->
				if (rows != preRow + 1) {
					return Utils.ResponeData(HttpStatus.BAD_REQUEST, false,
							"Dòng dữ liệu số" + (preRow + 1) + " không được bỏ trống!");
				}

				Iterator<Cell> cellIterator = row.cellIterator();
				try {
					PhoneDTO dto = new PhoneDTO();
					dto.setName(ExcelHelper.getStringFromCell(row.getCell(1)));
					dto.setPrice(ExcelHelper.getNumberFromCell(row.getCell(2)));
					dto.setQuantity(ExcelHelper.getStringFromCell(row.getCell(3)));

					// check duplicate example ->
					if (arrayName.contains(dto.getName().toString())) {
						return Utils.ResponeData(HttpStatus.BAD_REQUEST, false,
								" Tên điện thoại đã tồn tại" + dto.getName() + " ở dòng " + rows + " đã tồn tại! ");
					}
					arrayName.add(dto.getName().toString());
					list.add(dto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String str_list = "[]";
			try {
				str_list = ow.writeValueAsString(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			MapSqlParameterSource mapSql = new MapSqlParameterSource();
//			mapSql.addValue("");
			return "call pck in db ";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
