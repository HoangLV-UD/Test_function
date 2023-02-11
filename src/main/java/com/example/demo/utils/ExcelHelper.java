package com.example.demo.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelHelper {
	public static String getStringFromCell(Cell cell) throws Exception{
		try {
			if(cell == null)
				return null;
			if(cell.getCellType() == CellType.NUMERIC) {
				return "" + cell.getNumericCellValue();
			}
			if(cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			}
//			if(cell.getCellType() == CellType.FORMULA) {
//				switch (cell.getCachedFormulaResultType()) {)
//					case NUMERIC:
//						return ""+ cell.getNumericCellValue();
//					case STRING:
//						return ""+ cell.getStringCellValue();
//					case BOOLEAN:
//						if(cell.getBooleanCellValue()) {
//							return "1";
//						}else {
//							return "0";
//						}
//					default:
//						break;				
//					}
//			}
		
			return cell.getStringCellValue();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static Integer getNumberFromCell(Cell cell) throws Exception{
		try {
			if(cell == null)
				return null;
			String value ="";
			if(cell.getCellType() == CellType.NUMERIC) {
				value= "" + cell.getNumericCellValue();
			}
			if(cell.getCellType() == CellType.STRING) {
				value = cell.getStringCellValue();
			}
//			if(cell.getCellType() == CellType.FORMULA) {
//				switch (cell.getCachedFormulaResultType()) {
//					case NUMERIC:
//						return ""+ cell.getNumericCellValue();
//					case STRING:
//						return ""+ cell.getStringCellValue();
//					case BOOLEAN:
//						if(cell.getBooleanCellValue()) {
//							return "1";
//						}else {
//							return "0";
//						}
//					default:
//						break;				
//					}
//			}
			if(value.isEmpty()) {
				return 0;
			}
			return Double.valueOf(value).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
