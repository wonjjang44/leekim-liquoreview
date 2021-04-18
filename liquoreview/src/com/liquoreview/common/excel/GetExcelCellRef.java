package com.liquoreview.common.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.stereotype.Component;

/**
 * upload할 cell의 이름과 값을 가져오는 util class 
 * */
@Component
public class GetExcelCellRef {
	/**
	 * cell에 해당하는 column name을 가져온다
	 * 
	 * 만약 cell이 null이면 int cellIndex의 값으로 Column Name을 가져온다
	 * 
	 * @param cell
	 * @param cellIndex
	 * @return
	 * */
	public static String getName(Cell cell, int cellIndex) {
		int cellNum = 0;
		if (cell != null) {
			cellNum = cell.getColumnIndex();
		} else {
			cellNum = cellIndex;
		}
		
		return org.apache.poi.ss.util.CellReference.convertNumToColString(cellNum);
	}
	
	public static String getValue(Cell cell) {
		String value = "";
		
		if(cell == null) {
			value = "";
		} else {
			//if(cell.getCellType()==CellType.FORMULA) {
			if(cell.getCellTypeEnum()==CellType.FORMULA) {
				value = cell.getCellFormula();
			//} else if(cell.getCellType()==CellType.NUMERIC) {
			} else if(cell.getCellTypeEnum()==CellType.NUMERIC) {
				//DataFormatter formatter = new DataFormatter();
				//value = formatter.formatCellValue(cell);
				double d = cell.getNumericCellValue();
				value = String.valueOf(d);
			//} else if(cell.getCellType()==CellType.STRING) {
			} else if(cell.getCellTypeEnum()==CellType.STRING) {
				value = cell.getStringCellValue();
			//} else if(cell.getCellType()==CellType.BOOLEAN) {
			} else if(cell.getCellTypeEnum()==CellType.BOOLEAN) {
				boolean b = cell.getBooleanCellValue(); 
				value = String.valueOf(b);
			//} else if(cell.getCellType()==CellType.BLANK) {
			} else if(cell.getCellTypeEnum()==CellType.BLANK) {
				value = "";
			//} else if(cell.getCellType()==CellType.ERROR) {
			} else if(cell.getCellTypeEnum()==CellType.ERROR) {
				value = cell.getErrorCellValue()+"";
			} else {
				value = cell.getStringCellValue();
			}
		}
		
		return value;
	}
}
