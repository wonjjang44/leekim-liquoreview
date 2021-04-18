package com.liquoreview.common.excel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 업로드할 Excel 파일을 읽을 때 옵션을 설정하는 util class
 * */
@Component
public class ExcelReadOption {
	//엑셀 파일의 경로
	//추출할 컬럼 명
	//추출을 시작할 행 번호
	private String filePath;
	private List<String> outputColumns;
	private int startRow;

	//getters and setters
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<String> getOutputColumns() {
		List<String> tempColList = new ArrayList<String>();
		tempColList.addAll(outputColumns);
		return tempColList;
	}
	public void setOutputColumns(List<String> outputColumns) {
		List<String> tempColList = new ArrayList<String>();
		tempColList.addAll(outputColumns);
		this.outputColumns = tempColList;
	}
	
	/**
	 * ellipsis ...
	 * method 등에서 동일한 객체의 파라미터들을 처리할 때
	 * 메서도므다 파라미터의 갯수를 늘려가며 설정하는 대신 ellipsis 형태로 설정해서
	 * 같은 타입의 파라미터를 몇 개를 받아도 처리가 가능하게 함
	 * */
	public void setOutputColumns(String ... outputColumns) {
		if (this.outputColumns == null) {
			this.outputColumns = new ArrayList<String>();
		}
		for (String outputColumn : outputColumns) {
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
