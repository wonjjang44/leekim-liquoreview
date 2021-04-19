package com.liquoreview.common.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelReader {
	public static List<Map<String, String>> read(ExcelReadOption excelReadOption){
		//엑셀파일 읽기
		Workbook wb = ExcelFileType.getWorkBook(excelReadOption.getFilePath());
		
		//엑셀 파일에서 첫 번째 시트 가져오기
		Sheet sheet = wb.getSheetAt(0);
		
		//시트에서 유효한 데이터가 있는 행의 수를 가져오기
		int numOfRows = sheet.getPhysicalNumberOfRows();
		int numOfCells = 0;
		
		Row row  = null;
		Cell cell = null;
		
		String cellName = "";
		
		/**
		 * 각 row마다의 값을 저장할 맵 객체 저장 형식
		 * put("A","이름");
		 * put("B","blahblah"); 
		 */
		Map<String, String> map = null;
		
		/**
		 * 각 Row를 하나의 List에 담음
		 * 하나의 Row를 하나의 Map으로 표현
		 * List에는 모든 Row가 포함 
		 */
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		
		//row 만큼 반복
		for(int rowIndex = excelReadOption.getStartRow() -1; rowIndex < numOfRows; rowIndex++) {
			//workbook에서 가져온 시트에서  rowIndex에 해당하는 Row 가져오기
			row = sheet.getRow(rowIndex);
			
			if(row != null) {
				//가져온 row의 cell 갯수 구하기
				numOfCells = row.getPhysicalNumberOfCells();
				
				//데이터를 담을 맵 객체 초기화
				map = new HashMap<String, String>();
				
				//cell의 수 만큼 반복
				for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
					//Row에서 CellIndex에 해당하는 cell을 가져오기
					cell = row.getCell(cellIndex);
					
					//현재 Cell의 이름 얻기
					cellName = GetExcelCellRef.getName(cell, cellIndex);
					
					//추출 대상 컬럼여부 확인하기
					if(!excelReadOption.getOutputColumns().contains(cellName)) {
						continue;
					}
					
					//map객체의 Cell의 이름을 Key로 데이터 담기
					map.put(cellName, GetExcelCellRef.getValue(cell));
				}
				
				//만들어진 map 객체를 list에 담기
				result.add(map);
			}
		}
		
		return result;
	}
}
