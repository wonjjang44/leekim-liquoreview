package com.liquoreview.common.excel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Excel 파일을 읽을 때 옵션 설정 class
 * @date 21. 03. 30
 * */
@Component
public class ExcelReadOption {
	/**
	 * Excel 파일을 읽을 때 옵션 설정 class
	 * @date 21. 03. 30
	 * */
	
	private String filePath;						  //엑셀 파일의 경로
	private List<String> outputColumns;   //추출할 컬럼명
	private int startRow;							 //	추출을 시작할 행 번호
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public List<String> getOutputColumns() {
		List<String> temp = new ArrayList<String>();
		temp.addAll(outputColumns);
		return temp;
	}
	
	public void setOutputColumns(List<String> outputColumns) {
		List<String> temp = new ArrayList<String>();
		temp.addAll(outputColumns);
		this.outputColumns = temp;
	}
	
	public void setOutputColumns(String ... outputColumns) {
		if(this.outputColumns == null) {
			this.outputColumns = new ArrayList<String>();
		}
		
		for(String outputColumn : outputColumns) {
			this.outputColumns.add(outputColumn);
		}
	}
	
	public int getStartRow() {
		return startRow;
	}
	
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
}
