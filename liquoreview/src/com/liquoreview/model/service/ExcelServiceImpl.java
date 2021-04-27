package com.liquoreview.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liquoreview.common.excel.ExcelRead;
import com.liquoreview.common.excel.ExcelReadOption;
import com.liquoreview.model.repository.ExcelDAO;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService{
	@Resource(name = "excelDAO")
	private ExcelDAO excelDAO;
	
	/**
	 * 실제 excel 파일의 추출이 일어남
	 * */
	public int excelUpload(File destFile) throws Exception{
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D", "E");
        excelReadOption.setStartRow(2);
        
        List<Map<String, Object>>excelContent =ExcelRead.read(excelReadOption);
        
        for(Map<String, Object> excelResult : excelContent) {
        	/**
        	 * ===============================
        	 * 개선해야할 점
        	 * 1. 주류 정보가 추가됨에 따라 조건문도 추가되어야 함.
        	 * if 주류 정보가 1억개 => 조건도 1억개가 돼야함...;;
        	 * 따라서 VO 또는 Map을 정의하여 조건을 부여하도록 하자
        	 * 
        	 * Map 또는 VO에 사용자가 입력한 값이 
        	 * 존재하지 않을경우 새롭게 추가하는 로직 구현할 것.
        	 * 
        	 * 추가적으로 동일한 데이터를 업로드 했을 경우 rollback시키는
        	 * 로직도 구현할 것.
        	 * ===============================
        	 * */
        	/*
        	int count = 3;
        	
        	if(!(excelResult.get("A").equals("증류주") || excelResult.get("A").equals("발효주") || excelResult.get("A").equals("혼성주"))) {
        		count ++;
        		excelResult.put("A", count);
        	}
        	*/
        	
        	if(excelResult.get("A").equals("증류주")) {
        		excelResult.replace("A",1);
        	}else if(excelResult.get("A").equals("발효주")) {
        		excelResult.replace("A",2);
        	}else if(excelResult.get("A").equals("혼성주")) {
        		excelResult.replace("A",3);
        	}else {
        		excelResult.put("msg", excelResult.get("A")+"는(은) 등록될 수 없습니다.\n");
        	}
        	
        	if(excelResult.get("B").equals("소주")) {
        		excelResult.replace("B",1);
        	}else if(excelResult.get("B").equals("보드카")) {
        		excelResult.replace("B",2);
        	}else if(excelResult.get("B").equals("데킬라")) {
        		excelResult.replace("B",3);
        	}else if(excelResult.get("B").equals("진")) {
        		excelResult.replace("B",4);
        	}else if(excelResult.get("B").equals("럼")) {
        		excelResult.replace("B",5);
        	}else if(excelResult.get("B").equals("맥주")) {
        		excelResult.replace("B",6);
        	}else if(excelResult.get("B").equals("막걸리")) {
        		excelResult.replace("B",7);
        	}else if(excelResult.get("B").equals("리큐르")) {
        		excelResult.replace("B",8);
        	}
        	
        }
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("excelContent", excelContent);
        
        return excelDAO.excelUpload(paramMap);
	}

}
