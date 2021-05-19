package com.liquoreview.controller.admin.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.model.domain.board.Tag;
import com.liquoreview.model.service.board.BoardTagService;

@RestController
@RequestMapping("/rest")
public class RestAdminBoardTagController {
	
	@Autowired
	BoardTagService boardTagService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	//글머리(boardTag) list 전체조회
	@RequestMapping(value="/admin/board/tag", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<Tag> getBoardTagList(HttpServletRequest request) {
		 List<Tag> boardTagList = boardTagService.selectAll();
		 return boardTagList;
	 }
	
	//tag_id로 1건 조회
	@RequestMapping(value="/admin/board/tag/{tag_id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Tag getBoardTag(HttpServletRequest request, @PathVariable("tag_id") int tag_id) {
		Tag tag = boardTagService.select(tag_id);
		return tag;
	}
	
	//글머리 추가 또는 수정 모달 appear
	@RequestMapping(value="/admin/board/tag/modal/{tag_id}", method = RequestMethod.GET, produces = "application/text;charset=UTF-8")
	public void boardTagModalShow(@PathVariable("tag_id") int tag_id, HttpServletRequest request, HttpServletResponse response) {
		logger.info("글머리 추가 또는 수정 모달 팝 호출은 받는지 확인");
		logger.info("tag_id 확인: "+tag_id);
		RequestDispatcher requestDispatcher = null;
		try {
			if (tag_id == 0) {
				logger.info("글머리 추가 모달 호출예정");
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/board/boardTagAdd.jsp");
			} else {
				logger.info("글머리 수정 모달 호출예정");
				Tag tagInfo = boardTagService.select(tag_id);
				logger.info("tagInfo 확인 : "+tagInfo);
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/board/boardTagModi.jsp");
				request.getSession().setAttribute("tagInfo", tagInfo);
			}
			logger.info("도달확인");
			response.setContentType("text/html;charset=UTF-8");
			logger.info("도달확인2");
			response.setCharacterEncoding("UTF-8");
			logger.info("도달확인3");
			//
			requestDispatcher.forward(request, response);
			logger.info("도달확인4");
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 글머리 추가
	@RequestMapping(value="/admin/board/tag", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	public String insertBoardTag(Tag tag, HttpServletRequest request) {
		logger.info("글머리 등록 요청 접수");
		JSONObject boardTagInsertResult = boardTagService.insert(tag);
		return boardTagInsertResult.toString();
	}
	
	// 글머리 수정
	@RequestMapping(value="/admin/board/tag", method = RequestMethod.PUT, produces = "application/text;charset=UTF-8")
	public String updateBoardTag(Tag tag) {
		JSONObject boardTagModiResult = boardTagService.update(tag);
		return boardTagModiResult.toString();
	}
	
	// 글머리 삭제
	@RequestMapping(value="/admin/board/tag/{checkArray}", method = RequestMethod.DELETE)
	public String delBoardTag(@PathVariable("checkArray") List<Integer> deleteList, HttpServletRequest request) {
		boardTagService.delete(deleteList);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}
}
