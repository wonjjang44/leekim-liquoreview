package com.liquoreview.controller.admin.alcohol;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.Pager;
import com.liquoreview.model.domain.alcohol.Subcategory;
import com.liquoreview.model.domain.alcohol.Topcategory;
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
			if (subcategory_id == 0) {
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/alcohol/category/subAdd.jsp");
			} else {
				Subcategory subcateInfo = subcategoryService.select(subcategory_id);
				logger.info("subcategory_id로 조회한 topcateInfo : " + subcateInfo);
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/alcohol/category/subModify.jsp");

				request.getSession().setAttribute("topcateList", topcateList);
				request.getSession().setAttribute("subcateInfo", subcateInfo);
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
	
	//topcategory_id로 연관된 subcategory list 조회
	

	// insert top category
	@RequestMapping(value = "/admin/alcohol/topcategory", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	public String insertTopcate(Topcategory topcategory, HttpServletRequest request) {
		logger.info("상위 카테고리 등록 요청 서버 시작");
		JSONObject topcateInsertResult = topcategoryService.insert(topcategory);
		return topcateInsertResult.toString();
	}
	
	// insert sub category
}
