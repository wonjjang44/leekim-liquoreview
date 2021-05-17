package com.liquoreview.controller.admin.board;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.model.service.board.BoardTagService;

@Controller
public class AdminBoardTagController {

	@Autowired
	BoardTagService boardTagService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/*
	 * 페이지 이동
	 */
	
	// 게시판 글머리 관리 페이지 이동
	@RequestMapping(value="/admin/board/tag", method = RequestMethod.GET)
	public ModelAndView goAdminBoardBullet(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/board/tag-manage");
		return mav;
	}
}
