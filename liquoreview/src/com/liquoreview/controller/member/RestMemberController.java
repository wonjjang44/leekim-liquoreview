package com.liquoreview.controller.member;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liquoreview.common.JoinCode;
import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;
import com.liquoreview.model.service.member.MemberService;

@RestController
@RequestMapping("/rest")
public class RestMemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	JoinCode joinCode;

	Logger logger = Logger.getLogger(this.getClass().getName());

	// id중복체크
	@RequestMapping(value = "/member/regist/idoverlap", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String idOverlapCheck(@RequestParam(value = "userid") String userid) {
		logger.info("RestMemberController :: 아이디 중복체크 요청");
		logger.info("RestMemberController 중복체크용 입력받은 id : " + userid);
		String idOverlapResultStr = memberService.idOverlapCheck(userid);
		logger.info("restMemberController ======= idOverlapResultStr : " + idOverlapResultStr.toString());
		return idOverlapResultStr.toString();
	}

	// 회원가입
	@RequestMapping(value = "/member/regist", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public String registMember(Member member, MemberPw memberPw, HttpServletRequest request) {
		logger.info("RestMemberController :: 회원가입 요청");
		String memberResultStr = memberService.memberInsert(member, memberPw);
		return memberResultStr.toString();
	}

	// 로그인
	@RequestMapping(value = "/member/login", method = RequestMethod.POST, produces = "application/json;charset=utf8")
	public String login(Member member, MemberPw memberPw, HttpServletRequest request) {
		Member loginMember = memberService.loginCheck(member, memberPw);
		if (loginMember != null) {
			request.getSession().setAttribute("member", loginMember);
			if (loginMember.getAuth().getAuth_id() == 1) {
				request.getSession().setAttribute("admin", loginMember);
			}
			return "{\"resultCode\":1}";
		} else {
			return "{\"resultCode\":0}";
		}

	}

	// id찾기 모달 appear
	// WEB-INF 하위의 jsp는 ajax url 요청 직접접근이 안되고 servlet으로만 접근해야 함
	@RequestMapping(value = "/member/findId", method = RequestMethod.GET, produces = "application/text;charset=UTF-8")
	public void findIdModal(HttpServletRequest request, HttpServletResponse response) {
		logger.info("restMemberController에서 id찾기 요청접수========================");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/client/member/findId.jsp");
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// id찾기 요청처리
	@RequestMapping(value = "/member/findId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String findId(Member member) {
		logger.info("id찾기 요청한 멤버 이름 : " + member.getUsername() + ", 전화번호 : " + member.getPhonenum());
		String searchedMember = memberService.findId(member);
		logger.info("컨트롤러에서 반환 전 찍어보는 id찾기 결과 : " + searchedMember.toString());
		return searchedMember.toString();
	}

	// 비번 재설정 모달 appear
	@RequestMapping(value = "/member/resetPw", method = RequestMethod.GET, produces = "application/text;charset=UTF-8")
	public void resetPwModal(HttpServletRequest request, HttpServletResponse response) {
		logger.info("restMemberController에서 pw재설정 요청접수========================");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/client/member/resetPw.jsp");
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			requestDispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 비번 재설정 1단계 :: 회원정보 check 요청처리
	@RequestMapping(value = "/member", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	public String infoCheck(Member member, HttpServletRequest request) {
		logger.info("restMemberController에서 비번 재설정 1단계 infoCheck 요청접수==========");
		logger.info("넘겨받은 id : " + member.getUserid());
		logger.info("넘겨받은 이메일 : " + member.getEmail());
		JSONObject checkedMemInfo = memberService.infoCheck(member);
		logger.info("조회해온 checkedMemInfo 확인 : " + checkedMemInfo);
		if (checkedMemInfo.get("resultCode").equals("1")) {
			request.getSession().setAttribute("userid", checkedMemInfo.get("checkedUserid"));
			request.getSession().setAttribute("email", checkedMemInfo.get("checkedEmail"));
		}
		logger.info("restMemberController에서 리턴 전 checkedMemInfo확인 : " + checkedMemInfo.toString());
		return checkedMemInfo.toString();
	}

	// 비번 재설정 2단계 :: 인증메일 발송 요청처리
	@RequestMapping(value = "/member/sendMail", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	public String sendMail(HttpServletRequest request, HttpSession session) {
		logger.info("infoCheck 성공 후 sendMail요청접수");
		int randomCode = new Random().nextInt(10000) + 1000;
		String num = String.valueOf(randomCode);
		joinCode.setUserid((String) request.getSession().getAttribute("userid"));
		joinCode.setEmail((String) request.getSession().getAttribute("email"));
		joinCode.setNum(num);
		session.setAttribute("joinCode", joinCode);

		// return String.valueOf(memberService.send(joinCode));
		return Boolean.toString(memberService.send(joinCode));
	}

	// 비번 재설정 3단계 :: 인증코드 확인
	@RequestMapping(value = "/member/codeCheck", method = RequestMethod.POST, produces = "application/text;charset=UTF-8")
	public String codeCheck(HttpSession session, JoinCode clientJoinCode) {
		JoinCode sendedCode = (JoinCode) session.getAttribute("joinCode");
		JSONObject codeResult = new JSONObject();
		if (clientJoinCode.getUserid().equals(sendedCode.getUserid())) {
			if (clientJoinCode.getNum().equals(sendedCode.getNum())) {
				codeResult.put("resultCode", "1");
				codeResult.put("msg", "승인코드 일치");
			} else {
				codeResult.put("resultCode", "0");
				codeResult.put("msg", "승인코드 불일치");
			}
		} else {
			codeResult.put("resultCode", "2");
			codeResult.put("msg", "메일수신 계정 아이디 확인과정 오류발생. 관리자에게 문의하세요");
		}
		return codeResult.toString();
	}

	// 비번 재설정 4단계 :: 재설정
	@RequestMapping(value = "/member/resetPw", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String resetPass(MemberPw memberPw, Member member) {
		logger.info("비번재설정 요청 접수");
		memberPw.setMember(member);
		String resetPassResult = memberService.resetPass(memberPw);
		return resetPassResult.toString();
	}
}
