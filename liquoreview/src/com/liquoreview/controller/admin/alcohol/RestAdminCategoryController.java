package com.liquoreview.controller.admin.alcohol;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.Pager;
import com.liquoreview.exception.DeleteFailException;
import com.liquoreview.exception.EditFailException;
import com.liquoreview.model.domain.alcohol.Subcategory;
import com.liquoreview.model.domain.alcohol.Topcategory;
import com.liquoreview.model.service.alcohol.AlcoholService;
import com.liquoreview.model.service.alcohol.SubcategoryService;
import com.liquoreview.model.service.alcohol.TopcategoryService;

@RestController
@RequestMapping("/rest")
public class RestAdminCategoryController {

	@Autowired
	TopcategoryService topcategoryService;
	@Autowired
	SubcategoryService subcategoryService;
	@Autowired
	AlcoholService alcoholService;

	@Autowired
	Pager pager;

	Logger logger = Logger.getLogger(this.getClass().getName());

	// top category 추가 모달
	@RequestMapping(value = "/admin/topCate/modal/{topcategory_id}", method = RequestMethod.GET, produces = "application/text;charset=UTF-8")
	public void topcateAddModal(@PathVariable("topcategory_id") int topcategory_id, HttpServletRequest request,	HttpServletResponse response) {
		RequestDispatcher requestDispatcher = null;

		try {
			if (topcategory_id == 0) {
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/alcohol/category/topAdd.jsp");
			} else {
				Topcategory topcateInfo = topcategoryService.select(topcategory_id);
				logger.info("topcategory_id로 조회한 topcateInfo : " + topcateInfo);
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/alcohol/category/topModify.jsp");

				request.getSession().setAttribute("topcateInfo", topcateInfo);
			}
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			//
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// sub category 추가 모달
	@RequestMapping(value = "/admin/subCate/modal/{subcategory_id}", method = RequestMethod.GET, produces = "application/text;charset=UTF-8")
	public void subcateAddModal(@PathVariable("subcategory_id") int subcategory_id, HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher requestDispatcher = null;

		try {
			List<Topcategory> topcateList = topcategoryService.selectAll();
			request.getSession().setAttribute("topcateList", topcateList);
			if (subcategory_id == 0) {
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/alcohol/category/subAdd.jsp");
			} else {
				//subcategoryId로 subcategory정보 조회
				Subcategory subcateInfo = subcategoryService.select(subcategory_id);
				//조회된 subcategory정보에서 topcategoryId 추출
				logger.info("subcategory_id로 특정한 subcateInfo에서 topid 추출 : " + subcateInfo.getTopcategory().getTopcategory_id());
				//추출한 topid로 topcategory info 조회
				Topcategory topcateInfo = topcategoryService.select(subcateInfo.getTopcategory().getTopcategory_id());
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/alcohol/category/subModify.jsp");

				request.getSession().setAttribute("subcateInfo", subcateInfo);
				request.getSession().setAttribute("topcateInfo", topcateInfo);
				request.getSession().setAttribute("topcateList", topcateList);
			}
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			//
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// topcategory 전체조회(페이징)
	@RequestMapping(value = "/admin/alcohol/topcategory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JSONObject getTopcateList(HttpServletRequest request) {
		logger.info("topcategory list 조회 요청 시작");
		List<Topcategory> topcateList = null;
		int pageSize = 5;
		JSONObject topcateResultObj = new JSONObject();

		Criteria criteria = new Criteria(request, pageSize);
		pager.setCriteria(criteria);
		pager.init(topcategoryService.getTotalTopcateCnt());
		topcateList = topcategoryService.selectTopcateList(criteria);

		topcateResultObj.put("topcateList", topcateList);
		topcateResultObj.put("pager", pager);

		return topcateResultObj;
	}
	
	//topcategory_id 단건으로, 연관된 subcategory list 조회
	@RequestMapping(value = "/admin/alcohol/subcategory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JSONObject getSubcateListByTopId(@RequestParam("topcategory_id") int topcategory_id, HttpServletRequest request) {
		List<Subcategory> sortedSubcateList = null;
		int pageSize = 5;
		JSONObject subcateResultObj = new JSONObject();
		
		Criteria criteria = new Criteria(request, pageSize);
		pager.setCriteria(criteria);
		pager.init(subcategoryService.getSortedSubcateCnt(topcategory_id));
		//sortedSubcateList = subcategoryService.selectAllByTopCate(topcategory_id);
		sortedSubcateList = subcategoryService.selectSortedSubcateList(criteria, topcategory_id);
		
		subcateResultObj.put("sortedSubcateList", sortedSubcateList);
		subcateResultObj.put("pager", pager);
		return subcateResultObj;
	}
	
	// topcategory_id 리스트로 연관된 subcategory list 여부조회
	@RequestMapping(value = "/admin/alcohol/subcategory/{checkArray}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JSONArray checkSubcateListByTopIdArray(@PathVariable("checkArray") List<Integer> topIdList, HttpServletRequest request) {
		JSONArray subcateResultObj = subcategoryService.selectAllByTopCate(topIdList);
		logger.info(subcateResultObj);
		return subcateResultObj;
	}
	
	// subcategory_id 연관된 alcohol list 여부 조회
	@RequestMapping(value = "/admin/alcohol/subcate/{subIdArray}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JSONArray checkAlcListBySubIdArray(@PathVariable("subIdArray") List<Integer> subIdList, HttpServletRequest request) {
		JSONArray alcoholResultObj = alcoholService.selectAllBySubCate(subIdList);
		logger.info(alcoholResultObj);
		return alcoholResultObj;
	}

	// insert top category
	@RequestMapping(value = "/admin/alcohol/topcategory", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	public String insertTopcate(Topcategory topcategory, HttpServletRequest request) {
		logger.info("상위 카테고리 등록 요청 서버 시작");
		JSONObject topcateInsertResult = topcategoryService.insert(topcategory);
		return topcateInsertResult.toString();
	}
	
	// insert sub category
	@RequestMapping(value = "/admin/alcohol/subcategory", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	public String insertSubcate(int topcategory_id, Subcategory subcategory, HttpServletRequest request) {
		logger.info("sub cate 추가위한 top_id 확인 : "+topcategory_id);
		logger.info("sub cate 추가위한 name 확인 : "+subcategory.getSubname());
		//top category info set
		Topcategory topcategory = topcategoryService.select(topcategory_id);
		//checking whether sub name is duplicated
		List<Subcategory> checkSubName = subcategoryService.cateNameCheck(subcategory.getSubname());
		JSONObject subcateInsertResult = null;
		if (checkSubName.isEmpty()) {
			subcategory.setTopcategory(topcategory);
			subcateInsertResult = subcategoryService.insert(subcategory);
			logger.info("succeeded to register sub cate");
		} else {
			logger.info("failed to register sub cate");
		}
		return subcateInsertResult.toString();
	}
	
	//topcategory 수정
	@RequestMapping(value = "/admin/alcohol/topcategory", method = RequestMethod.PUT, produces = "application/text;charset=UTF-8")
	public String updateTopcateInfo(Topcategory topcategory, HttpServletRequest request) {
		JSONObject topcateModiResult = topcategoryService.update(topcategory);
		return topcateModiResult.toString();
	}
	
	
	//subcategory 수정
	@RequestMapping(value = "/admin/alcohol/subcategory", method = RequestMethod.PUT, produces = "application/text;charset=UTF-8")
	public String updateSubcateInfo(Subcategory subcategory, HttpServletRequest request) {
		Topcategory topcategory = new Topcategory();
		topcategory.setTopcategory_id(Integer.parseInt(request.getParameter("topcategory_id")));
		subcategory.setTopcategory(topcategory);
		JSONObject subcateModiResult = subcategoryService.update(subcategory);
		return subcateModiResult.toString();
	}
	
	//topcategory 삭제
	@RequestMapping(value = "/admin/alcohol/topcategory/{topDelArray}", method = RequestMethod.DELETE)
	public String delTopcate(@PathVariable("topDelArray") List<Integer> deleteList, HttpServletRequest request) {
		logger.info("topcate deleteList 확인 : "+deleteList);
		topcategoryService.delete(deleteList);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}
	
	//subcategory 삭제
	@RequestMapping(value = "/admin/alcohol/subcategory/{subDelArray}", method = RequestMethod.DELETE)
	public String delSubcate(@PathVariable("subDelArray") List<Integer> deleteList, HttpServletRequest request) {
		logger.info("subcate deleteList 확인 : "+deleteList);
		subcategoryService.delete(deleteList);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}
	
	@ExceptionHandler(DeleteFailException.class)
	//@ResponseBody
	public String deleteFail(DeleteFailException e) {
		return "{\"resultCode\":0,\"msg\":\""+e.getMessage()+"\"}";
	}
	
	@ExceptionHandler(EditFailException.class)
	//@ResponseBody
	public String updateFail(EditFailException e) {
		return "{\"resultCode\":0,\"msg\":\""+e.getMessage()+"\"}";
	}
}
