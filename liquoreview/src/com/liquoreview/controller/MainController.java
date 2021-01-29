package com.liquoreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	/**
	 * 사용자 메인페이지(서버를 기동하고 처음으로 접할 메인 페이지)
	 * @author 이양원
	 * @date 21. 01. 29
	 * */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndex() {
		return "index";
	}
}
