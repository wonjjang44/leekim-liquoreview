package com.liquoreview.common.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelFileType {
	/**
	 * excel file을 읽어 workbook 객체에 리턴하는 util class
	 * xls와 xlsx 확장자를 비교
	 * 
	 * @param filePath
	 * @return
	 * */
	
	public static Workbook getWorkBook(String filePath) {
		/**
		 * FileInputStream은 파일 경로에 있는 파일을 읽어 Byte로 가져온다
		 * 
		 * 파일이 존재하지 않는다면 RuntimeException 발생
		 * */
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Workbook wb = null;
		
		/*
		 * 파일 확장자를 체크해서 .xls면 HSSFWorkbook에
		 *                          .xlsx면 XSSFWorkbook에 각각 초기화
		 * */
		if(filePath.toUpperCase().endsWith(".XLS")) {
			try {
				wb = new HSSFWorkbook(fis);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage(), e);
			}
		} else if(filePath.toUpperCase().endsWith(".XLSX")) {
			try {
				wb = new XSSFWorkbook(fis);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		
		return wb;
		
	}
}
