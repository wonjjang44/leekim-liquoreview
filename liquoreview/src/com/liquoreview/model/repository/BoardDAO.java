package com.liquoreview.model.repository;

import java.util.List;

import com.liquoreview.model.domain.Board;

public interface BoardDAO {
	/**
	 * 게시물 전체조회
	 * @author 이양원
	 * @date 2021. 02. 11
	 * @param param
	 * */
	public List<Board> lstAllIqr(Board param);
}
