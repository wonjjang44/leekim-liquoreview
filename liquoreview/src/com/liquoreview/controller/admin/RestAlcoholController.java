package com.liquoreview.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.model.domain.Alcohol;
import com.liquoreview.model.service.AlcoholService;

@RestController
@RequestMapping(value = "/admin/alcohol")
public class RestAlcoholController {
	@Resource(name = "AlcoholService")
	private AlcoholService alcoholService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	/**
	 * 주류 정보 전체조회
	 * @author 이양원
	 * @date 2021. 03. 10  최초생성
	 * @param 
	 * */
	@RequestMapping(value = "alcoholLst", method = RequestMethod.GET)
	public ModelAndView alcoholLst() {
		ModelAndView mav = new ModelAndView();
		List<Alcohol> alcoholVal = new ArrayList<Alcohol>();
		alcoholVal = alcoholService.alcoholLst();
		
		mav.addObject("alcoholVal", alcoholVal);
		mav.setViewName("/admin/alcohol/alcohol-table");
		
		return mav;
	}
	
	/**
	 * 상위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param 
	 * */
	@RequestMapping(value = "topLst", method = RequestMethod.GET)
	public List<Alcohol> topLst() {
		List<Alcohol> topLst = new ArrayList<Alcohol>();
		topLst = alcoholService.topLst();
				
		return topLst;
	}
	
	/**
	 * 하위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param param 
	 * */
	@RequestMapping(value = "subLst", method = RequestMethod.POST)
	public List<Alcohol> subLst(@RequestBody Map<String, Object> param){
		List<Alcohol> subLst = new ArrayList<Alcohol>();
		subLst = alcoholService.subLst(param);
		
		return subLst;
	}
	
}
