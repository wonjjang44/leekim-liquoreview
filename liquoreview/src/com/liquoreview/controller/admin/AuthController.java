package com.liquoreview.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.Pager;
import com.liquoreview.model.domain.admin.Auth;
import com.liquoreview.model.service.member.AuthService;

@Controller
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	Pager pager;
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	//페이지 이동
	@RequestMapping(value="/admin/auth",method=RequestMethod.GET)
	public ModelAndView adminGetAuthList(HttpServletRequest request) {
		logger.info("권한관리 페이지 이동");
		//ModelAndView mav = new ModelAndView("admin/auth/auth-manage");
		ModelAndView mav = new ModelAndView();
		List<Auth> authList = authService.selectAll();
		int totalRecord = authList.size();
		int pageSize = 10;
		int blockSize = 10;
		pager.init(request, totalRecord, pageSize, blockSize);
		
		mav.addObject("authList", authList);
		mav.addObject("pager",pager);
		mav.setViewName("admin/auth/auth-manage");
		return mav;
	}
}
