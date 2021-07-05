package com.liquoreview.controller.admin.alcohol;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.domain.alcohol.Topcategory;
import com.liquoreview.model.service.alcohol.AlcoholService;
import com.liquoreview.model.service.alcohol.SubcategoryService;
import com.liquoreview.model.service.alcohol.TopcategoryService;

@RestController
@RequestMapping("/rest")
public class RestAdminAlcoholController {

	@Autowired
	AlcoholService alcoholService;
	@Autowired
	TopcategoryService topcategoryService;
	@Autowired
	SubcategoryService subcategoryService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	//전체 조회
	@RequestMapping(value = "/admin/alcohol", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<Alcohol> getAlcoholListAll(HttpServletRequest request) {
		logger.info("alcohol list 전체조회 요청시작");
		List<Alcohol> alcList = alcoholService.selectAll();
		return alcList;
	}
	
	//pk로 1건 조회(alcohol_id)
	
	//fk(subcategory_id)로 해당 하위 alcohol모두 조회
	@RequestMapping(value = "/admin/alcohol/{subcategory_id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<Alcohol> getAlcoholListBySubId(@PathVariable("subcategory_id") int subcategory_id, HttpServletRequest request) {
		logger.info("조회 키로 넘어온 단건 subcategory_id 확인 : " + subcategory_id);
		List<Alcohol> sortedAlcoholList = alcoholService.selectAllBySubCate(subcategory_id);
		return sortedAlcoholList;
	}
	
	//alcohol insert modal appear
	@RequestMapping(value = "/admin/alcohol/modal", method = RequestMethod.GET, produces = "application/text;charset=UTF-8")
	public void alcAddModal(HttpServletRequest request, HttpServletResponse response) {
		logger.info("restAlcoholController에서 alcohol추가 모달 요청");
		try {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/alcohol/alcAdd.jsp");
			List<Topcategory> topcateInfo = topcategoryService.selectAll();
			request.getSession().setAttribute("topcateInfo", topcateInfo);
			
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//insert 단건
	
	//insert list by excel
	@RequestMapping(value = "/admin/alcohol/insertByExcel", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	public String insertAlcByExcel(MultipartFile uploadedFile, MultipartHttpServletRequest request) throws IOException{
		MultipartFile excelFile = request.getFile("ajaxExcelFile");
		
		if (excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택해 주세요.");
		}
		//업로드할 파일의 목적지 설정
		File destFile = new File("C:\\Users\\kjh\\Desktop\\kjh_private\\leekim_project\\uploadhere"+excelFile.getOriginalFilename());
		
		try {
			//설정한 위치로 엑셀파일 전송
			excelFile.transferTo(destFile);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		//upload 진행
		JSONObject alcExcelInsertResult = alcoholService.insertByExcel(destFile);
		
		//업로드 후 파일 삭제
		destFile.delete();
		return alcExcelInsertResult.toString();
	}
}
