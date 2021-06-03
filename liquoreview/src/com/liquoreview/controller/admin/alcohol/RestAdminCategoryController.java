package com.liquoreview.controller.admin.alcohol;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.common.Pager;
import com.liquoreview.model.domain.alcohol.Topcategory;
import com.liquoreview.model.service.alcohol.TopcategoryService;

@RestController
@RequestMapping("/rest")
public class RestAdminCategoryController {

	@Autowired
	TopcategoryService topcategoryService;
	
	@Autowired
	Pager pager;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	@RequestMapping(value = "/admin/topCate", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JSONObject getTopcateList(HttpServletRequest request) {
		logger.info("topcategory list 조회 요청 시작");
		JSONObject topcateResultObj = new JSONObject();
		List<Topcategory> topcateList = null;
		topcateResultObj.put("topcateList", topcateList);
		return topcateResultObj;
	}
}
