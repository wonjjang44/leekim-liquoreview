package com.liquoreview.common.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

/**
 * Cell의 이름과 값을 가져오는 class
 */
@Component
public class GetExcelCellRef {
	/**
	 * Cell에 해당하는  Column name을 가져온다
	 * 만약 cell이 null이면 int cellIndex의 값으로 Column Name을 가져온다
	 * 
	 * @param cell
	 * @param cellIndex
	 * @return
	 */
	public static String getName(Cell cell, int cellIndex) {
		int cellNum = 0;
		if(cell != null) {
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
	        }
	        else {
	            if( cell.getCellType() == Cell.CELL_TYPE_FORMULA ) {
	                value = cell.getCellFormula();
	            }else if( cell.getCellType() == Cell.CELL_TYPE_NUMERIC ) {
	                value = cell.getNumericCellValue() + "";
	            }else if( cell.getCellType() == Cell.CELL_TYPE_STRING ) {
	                value = cell.getStringCellValue();
	            }else if( cell.getCellType() == Cell.CELL_TYPE_BOOLEAN ) {
	                value = cell.getBooleanCellValue() + "";
	            }else if( cell.getCellType() == Cell.CELL_TYPE_ERROR ) {
	                value = cell.getErrorCellValue() + "";
	            }else if( cell.getCellType() == Cell.CELL_TYPE_BLANK ) {
	                value = "";
	            }else {
	                value = cell.getStringCellValue();
	            }
	        }
	        
	        return value;
	    }

}