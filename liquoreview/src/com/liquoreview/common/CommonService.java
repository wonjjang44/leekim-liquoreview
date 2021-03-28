package com.liquoreview.common;

import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 공통 제어 Service
 * @author 이양원
 * @date 21. 03. 24  최초생성
 * */
@Service("CommonService")
public class CommonService {
	/**
	 * 파라미터 값으로 페이징 방식으로 질의하여 ResultList 방식으로 리턴한다.
	 * @author 이양원
	 * @date 21. 03. 25  최초생성
	 * @param all_params
	 * 					해당 sql을 처리하기 위한 파라미터
	 * @return ResultList 형식의 조회 결과값
	 * */
	public ResultList selectResultsInTable(Map<String, Object> all_params) {
		int page = 1;
		int rows = 10;
		
		if(all_params != null) {
			//page = all_params.get("page") == null ? 1 : TypeMan.toInt(all_params.get("page").toString(), 1);
		}
		
		return selectResultsInTable(all_params, page, rows);
	}
	
	public ResultList selectResultsInTable(Map<String, Object> all_params, int page, int rows) {
		return null;
	}
	
}
