package com.liquoreview.model.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liquoreview.model.repository.SequenceDAO;

@Service("SequenceService")
public class SequenceServiceImpl implements SequenceService{
	@Resource(name = "SequenceDAO")
	private SequenceDAO sequenceDAO;

	
	/**
	 * 각 테이블의 pk 시퀀스를 가져온다
	 * @author 이양원
	 * @date 2021. 06. 12
	 * @param
	 * */
	@Override
	public int selectAlcImgSequence() {
		
		return sequenceDAO.selectAlcImgSequence();
	}
}
