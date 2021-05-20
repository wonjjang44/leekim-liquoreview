package com.liquoreview.controller.admin.board;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminBoardController {
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value="/admin/board", method = RequestMethod.GET)
	public ModelAndView goAdminBoard(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/board/board-manage");
		return mav;
	}
	
}
