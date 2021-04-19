package com.liquoreview.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.NewPager;
import com.liquoreview.model.domain.Alcohol;
import com.liquoreview.model.domain.Topcategory;
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
	 * 			   개정이력  2021. 04. 18  페이징 추가
	 * @param pager
	 * @param nowPage
	 * @param cntPerPage
	 * */
	@RequestMapping(value = "alcoholLst", method = RequestMethod.GET)
	public ModelAndView alcoholLstIqr(NewPager pager, 
			@RequestParam(value = "nowPage", required=false, defaultValue = "1") String nowPage, 
			@RequestParam(value = "cntPerPage", required = false, defaultValue = "5") String cntPerPage) {
		ModelAndView mav = new ModelAndView();
		
		int total = alcoholService.countTopCate();
		
		pager = new NewPager(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<Alcohol> alcoholVal = new ArrayList<Alcohol>();
		alcoholVal = alcoholService.alcoholLst(pager);
		
		mav.addObject("pager", pager);
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
	public List<Alcohol> topLstIqr() {
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
	public List<Alcohol> subLstIqr(@RequestBody Map<String, Object> param){
		List<Alcohol> subLst = new ArrayList<Alcohol>();
		subLst = alcoholService.subLst(param);
		
		return subLst;
	}
	
	/**
	 * 주류 정보 등록
	 * vo 로 받을 시 뷰단에서 값을 못받아오므로 map로 변경
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param param
	 * */
	@RequestMapping(value = "alcoholReg", method = RequestMethod.POST)
	public int alcoholReg(@RequestBody Map<String, Object> param) {
		
		return alcoholService.alcoholReg(param);
	}

	
}







