package com.liquoreview.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.liquoreview.model.domain.Member;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	/**
	 * 관리자 페이지에 일반 회원이 접근할 수 없도록 제어
	 * @date 2021. 03. 08  최초생성
	 * @param request
	 * @param response
	 * @param obj
	 * @return boolean
	 * @throws Exception
	 * */
	@Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
	    HttpSession session = request.getSession();
	    Member member = (Member)session.getAttribute("loginSession");
	  
	    //추 후에 총관리자(AUTH_ID = 1) 이외에도 2, 3, 4 추가할 것.
	    if(member == null || member.getAUTH_ID() != 1 ) {
            response.sendRedirect("/");

		    return false;
	    }
	  
	    return true;
	   }
}
