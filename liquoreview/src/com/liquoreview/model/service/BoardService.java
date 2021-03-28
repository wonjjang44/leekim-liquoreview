package com.liquoreview.model.service;

import java.util.List;

import com.liquoreview.model.domain.Board;

public interface BoardService {
	/**
	 * 게시물 전체조회
	 * @author 이양원
	 * @date 2021. 02. 11
	 * @param param
	 * */
	public List<Board> lstAllIqr(Board param);
}
