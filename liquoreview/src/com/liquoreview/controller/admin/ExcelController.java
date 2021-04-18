package com.liquoreview.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.liquoreview.model.service.ExcelService;

@Controller
public class ExcelController {
	@Autowired
	ExcelService excelService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value="/admin/excelDownload",method = RequestMethod.GET)
	public String excelTransform(@RequestParam Map<String, Object> paramMap, Map<String, Object> ModelMap, HttpServletResponse response) throws Exception{
		logger.info("paramMap이 뭐가 넘어오는가? : "+ paramMap);
		/**
		 * excelDownload?target=member 같은형식으로 파라미터를 받을 예정
		 * target에 맞는 리스트를 선택하도록 함
		 * */
		String target = paramMap.get("target").toString();
		//target명을 파일명으로 설정
		response.addHeader("Content-Disposition", "attachment; filename="+target+".xlsx");
		
		//excel sheet로 작성할 데이터 리스트 조회
		List<Object> excelList = excelService.getAllObjects(target, paramMap);
		logger.info("excelController에서 excelList size 확인 : "+excelList.size());
		
		//ExcelView에 넘겨줄 값 세팅
		ModelMap.put("excelList", excelList);
		ModelMap.put("target", target);
		
		return "excelView";
		 	//servlet-context.xml에서 name이 excelView(com.liquoreview.common.ExcelView)인 것을 호출
	}
}
