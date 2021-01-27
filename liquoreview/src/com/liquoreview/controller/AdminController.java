package com.liquoreview.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liquoreview.model.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Resource(name = "AdminService")
	private AdminService adminService;
	
	
	/**
	 * 관리자 페이지 로그인 페이지 이동
	 * @author 이양원
	 * @date 2021. 01. 27 최초생성
	 * @param
	 * */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String adminLogin() {
		
		return "/admin/login";
	}
}
