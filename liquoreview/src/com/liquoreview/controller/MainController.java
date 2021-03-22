package com.liquoreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 메인 index 컨트롤러
 * @author 이양원
 * @date 2021. 03. 08  최초생성
 * */
@Controller
public class MainController {
	
	/**
	 * 메인 페이지(서버를 기동하고 처음으로 접할 메인 페이지)
	 * @author 이양원
	 * @date 2021. 03. 08
	 * */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
