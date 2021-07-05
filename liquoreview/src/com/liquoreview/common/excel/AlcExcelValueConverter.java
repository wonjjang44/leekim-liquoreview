package com.liquoreview.common.excel;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.liquoreview.model.domain.alcohol.Subcategory;
import com.liquoreview.model.domain.alcohol.Topcategory;

@Component
public class AlcExcelValueConverter {
	
	public static Logger logger = Logger.getRootLogger();
	public static List<Map<String, Object>> convertValue(List<Topcategory> topcateList, List<Subcategory> subcateList, List<Map<String,Object>> excelContent) {
		
		logger.info("topcategory list 확인 : "+topcateList);
		logger.info("subcategory list 확인 : "+subcateList);
		logger.info("excelContent 확인 : "+excelContent);
		//for(Map<String, String> excelResult : excelContent) {	
			
		//}
		excelContent.forEach((contentMapList) -> {
			logger.info(contentMapList);
			logger.info(contentMapList.get("A"));
			//카테고리 Str 변환
			topcateList.forEach((top) -> {
				if(top.getTopname().equals(contentMapList.get("A"))) {
					contentMapList.replace("A", Integer.toString(top.getTopcategory_id()));
				}
			});
			subcateList.forEach((sub)->{
				if(sub.getSubname().equals(contentMapList.get("B"))) {
					contentMapList.replace("B", Integer.toString(sub.getSubcategory_id()));
				}
			});
			
			//알콜도수 형변환 :: 엑셀서식 D열
			//Float.parseFloat((String) contentMapList.get("D"));
			contentMapList.replace("D",Float.parseFloat((String) contentMapList.get("D")));
			logger.info("degree 타입확인 : "+contentMapList.get("D").getClass().getName());
		});
		return excelContent;
	}
}
