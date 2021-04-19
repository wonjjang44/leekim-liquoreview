package com.liquoreview.common.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/**
 * excel 객체를 정의한다.	
 * @author jim
 * @date 20 .12 .13 
 * 개정이력 
 *           21.03.29  
 * */
@Component
public class ExcelFileType {
	/**
	 * excel file 을 읽어 workbook 객체에 리턴한다.
	 * xls와 xlsx 확장자 비교
	 * @author jim
	 * @param filePath
	 * @return Workbook
	 * */
	public static Workbook getWorkBook(String filePath) {
		/**
		 * FileInputStream 은 파일의 경로에 있는 파일을 읽어서 byte로 가져온다.
		 * 
		 * 파일이 존재하지 않는다면 RuntimeException 발생
		 * */
		FileInputStream fis= null;
		
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		Workbook wb = null;
		
		/**
		 * 확장자 체크 후 (xls 인지 xlsx 인지) HSSF, XSSF 결정
		 * */
		if(filePath.toUpperCase().endsWith(".XLSX")) {
			try {
				wb =new XSSFWorkbook(fis);
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return wb;
	}
}
