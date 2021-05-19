package com.liquoreview.model.service.board;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liquoreview.exception.DeleteFailException;
import com.liquoreview.exception.EditFailException;
import com.liquoreview.exception.RegistFailException;
import com.liquoreview.model.domain.board.Tag;
import com.liquoreview.model.repository.board.BoardTagDAO;

@Service
public class BoardTagServiceImpl implements BoardTagService{

	@Autowired
	@Qualifier("mybatisBoardTagDAO")
	BoardTagDAO boardTagDAO;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<Tag> selectAll() {
		List<Tag> boardTagList = boardTagDAO.selectAll();
		return boardTagList;
	}

	@Override
	public Tag select(int tag_id) {
		logger.info("호출받는지 확인");
		return boardTagDAO.select(tag_id);
	}

	@Override
	public JSONObject insert(Tag tag) throws RegistFailException{
		logger.info("글머리 등록 요청 서비스 impl");
		int result = boardTagDAO.insert(tag);
		logger.info("글머리 등록 요청 결과확인 : "+result);
		JSONObject resultObj = new JSONObject();
		if (result == 0) {
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "글머리 등록 실패");
			throw new RegistFailException("글머리 등록 실패");
		} else {
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "글머리 등록 성공");
		}
		return resultObj;
	}

	@Override
	public JSONObject update(Tag tag) throws EditFailException{
		int result = boardTagDAO.update(tag);
		JSONObject resultObj = new JSONObject();
		if (result == 0) {
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "글머리 수정 실패");
			throw new EditFailException("글머리 수정 실패");
		} else {
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "글머리 수정 성공");
		}
		return resultObj;
	}

	@Override
	public void update(List<Tag> updateList) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(int tag_id) throws DeleteFailException{
		int result = boardTagDAO.delete(tag_id);
		if (result == 0) {
			throw new DeleteFailException("글머리 삭제 실패");
		}
	}

	@Override
	public void delete(List<Integer> deleteList) throws DeleteFailException{
		int result = 0;
		for (Integer num : deleteList) {
			int tag_id = num;
			result = boardTagDAO.delete(tag_id);
			if (result == 0) {
				throw new DeleteFailException("글머리 삭제 실패");
			}
		}
	}


}
