package com.liquoreview.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liquoreview.model.domain.Board;
import com.liquoreview.model.service.BoardService;

@Controller
public class BoardController {
	@Resource(name = "BoardService")
	private BoardService boardService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 사용자 페이지_게시물 전체조회(추 후 검색조건 추가예정)
	 * @author 이양원
	 * @date 21. 02. 11
	 * @param param
	 * @param session
	 * */
	@RequestMapping(value = "/lstAllIqr", method = RequestMethod.GET)
	public String lstAllIqr(Board param, HttpSession session) {
		List<Board> boardList= new ArrayList<Board>();
		boardList= boardService.lstAllIqr(param);
		
		if(boardList != null) {
			session.setAttribute("boardSession", boardList);
		}
		
		return "index";
	}
}
