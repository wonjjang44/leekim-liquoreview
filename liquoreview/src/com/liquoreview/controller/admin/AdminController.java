package com.liquoreview.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	/**
	 * 관리자 메인 페이지 이동
	 * auth_id 값에 따라서 관리자 메인페이지, 사용자 메인페이지 나눌 것.
	 * @author 이양원
	 * @date 21. 01. 29  최초생성
	 * @param
	 * */
	@RequestMapping(value = "/index", method =  RequestMethod.GET)
	public String getIndex() {
		
		return "/admin/main/index";
	}
	
}
