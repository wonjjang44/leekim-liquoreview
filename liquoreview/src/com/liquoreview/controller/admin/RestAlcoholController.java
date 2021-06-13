package com.liquoreview.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.PageMaker;
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
	 * 			   개정이력  2021. 04. 18  페이징 추가
	 *                       2021. 06. 12 새로운 페이징 객체 선언으로 인한 nowPage, cntPerPage 제거(생성자로 하여금 기본 값 셋팅했으므로)
	 * @param 현재 페이지 번호와 페이지당 보여줄 게시글 수가 담긴 Criteria 객체
	 * */
	@RequestMapping(value = "alcoholLst", method = RequestMethod.GET)
	public ModelAndView alcoholLstIqr(Criteria cri) {
		ModelAndView mav = new ModelAndView();
		
		//주류 테이블 총 레코드 수
		int totalPageCnt = alcoholService.alcLstAllCnt();
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalPageCnt);
		
		//pager = new NewPager(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<Alcohol> alcoholVal = new ArrayList<Alcohol>();
		//alcoholVal = alcoholService.alcoholLst(pager);
		alcoholVal = alcoholService.alcoholLst(cri);
		
		mav.addObject("pageMaker", pageMaker);
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
	
	/**
	 * 주류 정보 상세보기
	 * @author 이양원
	 * @date 2021. 05. 29  최초생성
	 * @param alcoholId
	 * */
	@RequestMapping(value = "alcoholLstDtl", method = RequestMethod.GET)
	public ModelAndView alcoholLstIqrDtl(@RequestParam int alcoholId) {
		Alcohol alcoholDtl = alcoholService.alcoholLstIqrDtl(alcoholId);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("alcoholDtl", alcoholDtl);
		mav.setViewName("/admin/alcohol/alcohol-detail");
		
		return mav;
	}
	
	/**
	 * 주류 정보 삭제
	 * @author 이양원
	 * @date 2021. 06. 04  최초생성
	 *            개정이력  2021. 06. 05  주류 정보 삭제 후 이어서 주류 이미지 데이터 삭제    
	 * @param alcohol_id
	 * */
	@RequestMapping(value = "alcoholDel", method = RequestMethod.GET)
	public int alcoholDel(@RequestParam int alcohol_id) {
		
		return alcoholService.alcoholDel(alcohol_id);
	}

	
}







