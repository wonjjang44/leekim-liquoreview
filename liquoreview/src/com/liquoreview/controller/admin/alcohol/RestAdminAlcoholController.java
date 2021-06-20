package com.liquoreview.controller.admin.alcohol;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.model.domain.alcohol.Alcohol;
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
	
	//전체 조회
	
	//pk로 1건 조회(alcohol_id)
	
	//fk(subcategory_id)로 해당 하위 alcohol모두 조회
	@RequestMapping(value = "/admin/alcohol/{checkArray}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public JSONArray getAlcoholListBySubId(@PathVariable("checkArray") List<Integer> subIdList, HttpServletRequest request) {
		List<Alcohol> sortedAlcoholList = null;
		JSONArray alcoholResultObj = new JSONArray();
		alcoholResultObj = alcoholService.selectAllBySubCate(subIdList);
		return alcoholResultObj;
	}
}
