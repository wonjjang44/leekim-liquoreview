package com.liquoreview.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liquoreview.model.domain.Board;
import com.liquoreview.model.repository.BoardDAO;

@Service("BoardService")
public class BoardServiceImpl implements BoardService{
	@Resource(name = "BoardDAO")
	private BoardDAO boardDAO;

	/**
	 * 게시물 전체조회
	 * @author 이양원
	 * @date 2021. 02. 11
	 * @param param
	 * */
	@Override
	public List<Board> lstAllIqr(Board param) {

		return boardDAO.lstAllIqr(param);
	}
	
}
