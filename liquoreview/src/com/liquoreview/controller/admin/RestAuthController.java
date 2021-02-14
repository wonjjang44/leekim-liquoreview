package com.liquoreview.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.model.domain.admin.Auth;
import com.liquoreview.model.service.member.AuthService;

@RestController
@RequestMapping("/rest")
public class RestAuthController {
	
	@Autowired
	AuthService authService;
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	//auth list 전체조회
	@RequestMapping(value="/admin/auth",method=RequestMethod.GET)
	public List<Auth> getAuthList(HttpServletRequest request) {
		return authService.selectAll();
	}
	
	@RequestMapping(value="/admin/auth",method=RequestMethod.POST,produces = "application/text;charset=UTF-8")
	public String insertAuth(Auth auth, HttpServletRequest request) {
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
		String insertAuthResult = Integer.toString(authService.insert(auth));
		return insertAuthResult;
	}
}
