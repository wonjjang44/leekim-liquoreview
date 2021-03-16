package com.liquoreview.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.model.domain.admin.Auth;
import com.liquoreview.model.service.member.AuthService;

@RestController
@RequestMapping("/rest")
public class RestAuthController {
	
	@Autowired
	AuthService authService;
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	//auth list 전체조회
	@RequestMapping(value="/admin/auth",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public List<Auth> getAuthList(HttpServletRequest request) {
		logger.debug("rest auth controller 호출 :: auth select all");
		List<Auth> authList = authService.selectAll();
		logger.info("최종수정시간확인 : "+authList.get(0).getLast_modi_ymd());
		logger.info("최종수정시간의 자료형확인 : "+authList.get(0).getLast_modi_ymd().getClass().getName());
		logger.info("authList첫 번째 리스트 통째로 확인 : "+authList.get(0));
		return authList;
		//return null;
	}
	
	//auth_id로 1건 조회
	@RequestMapping(value="/admin/auth/{auth_id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public Auth getAuth(HttpServletRequest request, @PathVariable("auth_id") int auth_id) {
		logger.info("전달받은 auth_id 확인 : "+auth_id);
		Auth auth = authService.select(auth_id);
		return auth;
	}
	
	//권한 추가/수정 모달 appear
	@RequestMapping(value="/admin/auth/modal/{auth_id}",method = RequestMethod.GET, produces = "application/text;charset=UTF-8")
	public void authAddModal(@PathVariable("auth_id") int auth_id, HttpServletRequest request, HttpServletResponse response) {
		logger.info("restAuthController에서 권한 추가 또는 수정 모달 요청");
		logger.info("auth_id 확인 : "+auth_id);
		RequestDispatcher requestDispatcher = null;
		try {
			if(auth_id==0) {
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/auth/authAdd.jsp");
			} else {
				//Auth authInfo = (Auth)request.getRequestDispatcher("/rest/admin/auth/"+auth_id);
				Auth authInfo = authService.select(auth_id);
				logger.info("auth_id로 조회한 authInfo확인 : "+authInfo);
				requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/auth/authModify.jsp");
				
				request.getSession().setAttribute("authInfo", authInfo);
				logger.info("authInfo가 request.getSession에 담겼나 확인 : "+request.getSession().getAttribute("authInfo"));
			}
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			//
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/admin/auth",method=RequestMethod.POST,produces = "application/text;charset=UTF-8")
	//public String insertAuth(Auth auth, HttpServletRequest request) {
	public ModelAndView insertAuth(Auth auth, HttpServletRequest request) {
		/*
		logger.info("adm_assign requestParam 확인 : "+request.getParameter("adm_assign"));
		logger.info("권한추가 요청 접수");
		logger.info("des확인 : "+auth.getDes());
		logger.debug("isAdm_assign 확인 "+auth.isAdm_assign());
		logger.debug("isMem_adm 확인 "+auth.isMem_adm());
		logger.debug("isCate_adm 확인 "+auth.isCate_adm());
		logger.debug("isAlc_adm 확인 "+auth.isAlc_adm());
		logger.debug("isRev_adm 확인 "+auth.isRev_adm());
		logger.debug("isRev_comm_adm 확인 "+auth.isRev_comm_adm());
		logger.debug("isBoard_adm 확인 "+auth.isBoard_adm());
		logger.debug("isBoard_comm_adm 확인 "+auth.isBoard_comm_adm());
		*/
		//String insertAuthResult = Integer.toString(authService.insert(auth));
		int insertAuthResult = authService.insert(auth);
		ModelAndView mav = new ModelAndView("/admin/auth");
		mav.addObject(insertAuthResult);
		return mav;
	}
	
	//권한 수정
	@RequestMapping(value="/admin/auth",method = RequestMethod.PUT,produces ="application/text;charset=UTF-8")
	public String updateAuth(Auth auth) {
		logger.info("restAuthController에서 권한수정 요청 접수");
		logger.info("auth_id확인 : "+auth.getAuth_id());
		logger.info("des확인 : "+auth.getDes());
		logger.info("adm_assign확인 : "+auth.isAdm_assign());
		logger.info("mem_adm확인 : "+auth.isMem_adm());
		logger.info("cate_adm확인 : "+auth.isCate_adm());
		logger.info("alc_adm확인 : "+auth.isAlc_adm());
		logger.info("rev_adm확인 : "+auth.isRev_adm());
		logger.info("rev_comm_adm확인 : "+auth.isRev_comm_adm());
		logger.info("board_adm확인 : "+auth.isBoard_adm());
		logger.info("board_comm_adm확인 : "+auth.isBoard_comm_adm());
		JSONObject authModiResult = authService.update(auth);
		logger.info("응답 받아온 result 확인 : "+authModiResult.toJSONString());
		return authModiResult.toString();
	}
	
	//권한 삭제	
	@RequestMapping(value="/admin/auth/{checkArray}", method = RequestMethod.DELETE)
	public String delAuth(@PathVariable("checkArray") List<Integer> deleteList, HttpServletRequest request) {
		logger.info(deleteList);
		/*
		List<Integer> deleteArray = new ArrayList<Integer>();
		for(int arr : deleteList) {
			deleteArray.add(arr);
		}
		authService.delete(deleteArray);
		*/
		authService.delete(deleteList);
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\":1");
		sb.append("}");
		return sb.toString();
	}
}
