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
	public static List<Map<String, Object>> read(ExcelReadOption excelReadOption) {
		
		//엑셀 파일 읽어들여 workbook할당
		Workbook wb = ExcelFileType.getWorkBook(excelReadOption.getFilePath());
		//엑셀 파일에서 첫 번째 시트 가지고 오기
		Sheet sheet = wb.getSheet("주류정보 등록 양식");
		
		//sheet에서 유효한 데이터가 있는 행의 수 추출
		int numOfRows = sheet.getPhysicalNumberOfRows();
		int numOfCells = 0;
		
		Row row = null;
		Cell cell = null;
		
		String cellName = "";
		
		/**
		 * 각 row마다의 값을 저장할 맵 객체 저장 형식
		 * put("A","이름");
		 * put("B","blahblah");
		 * */
		Map<String, Object> map = null;
		
		/*
		 * 각 Row를 하나의 List에 담는다.
		 * 하나의 row를 하나의 Map으로 표현한다.
		 * List에는 모든 Row가 포함된다.
		 * */
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		
		//row 만큼 반복
		for (int rowIndex = excelReadOption.getStartRow()-1; rowIndex<numOfRows; rowIndex++) {
			//workbook에서 가져온 시트에서 rowIndex에 해당하는 Row를 가져온다
			row = sheet.getRow(rowIndex);
			
			if (row != null) {
				//가져온 row의 cell 갯수를 구한다
				numOfCells = row.getPhysicalNumberOfCells();
				//데이터를 담을 맵 객체를 초기화 한다.
				map = new HashMap<String, Object>();
				
				//cell수만큼 반복
				for (int cellIndex = 0; cellIndex<numOfCells; cellIndex++) {
					//Row에서 cellIndex에 해당하는 cell을 가져온다.
					cell = row.getCell(cellIndex);
					//현재 cell의 이름을 얻는다.
					cellName = GetExcelCellRef.getName(cell, cellIndex);
					
					//추출대상 컬럼 여부 확인
					if (!excelReadOption.getOutputColumns().contains(cellName)) {
						continue;
					}
					//map객체의 cell이름을 key로 데이터를 담는다.
					map.put(cellName, GetExcelCellRef.getValue(cell));
				}
				
				//만들어진 map 객체를 list에 담는다
				result.add(map);
			}
		}
		return result;
	}
}
