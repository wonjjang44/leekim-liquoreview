package com.liquoreview.model.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.JoinCode;
import com.liquoreview.common.MailUtil;
import com.liquoreview.common.PassSecurity;
import com.liquoreview.exception.LoginFailException;
import com.liquoreview.exception.RegistFailException;
import com.liquoreview.model.domain.admin.Auth;
import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;
import com.liquoreview.model.repository.member.MemberDAO;
import com.liquoreview.model.repository.member.MemberPwDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	@Qualifier("mybatisMemberDAO")
	private MemberDAO memberDAO;
	@Autowired
	@Qualifier("mybatisMemberPwDAO")
	private MemberPwDAO memberPwDAO;
	
	@Autowired
	PassSecurity security;
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	MailUtil mailUtil;

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public int getTotalMemberCnt() {
		return memberDAO.getTotalMemberCnt();
	}

	@Override
	public List<Member> selectMemberList(Criteria criteria) {
		logger.info("관리자 회원목록 조회");
		List<Member> memList = memberDAO.selectMemberList(criteria);
		return memList;
	}

	//관리자 회원목록 전체조회
	@Override
	public JSONObject adminSelectMemberList(Criteria criteria) {
		//logger.info("관리자 회원목록 조회");
		JSONObject jsonMemObj = new JSONObject();
		List<Member> memList = memberDAO.selectMemberList(criteria);
		if(memList!=null && memList.size()>0) {
			
		}
		return null;
	}

	@Override
	public List<Member> selectByAuth(int auth_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member select(int member_id) {
		Member member = memberDAO.select(member_id);
		logger.info("서비스impl에서 dao조회해온 멤버 auth 확인 : "+member.getAuth().getAuth_id());
		return member;
	}

	@Override
	public Member selectByUserid(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member loginCheck(Member member, MemberPw memberPw) {
		MemberPw loginPw = new MemberPw();
		Member loginMember = memberDAO.loginCheck(member);
		if (loginMember != null && !(loginMember.getUserid().isEmpty())) {// 조회요청받은 id 존재하면
			memberPw = security.setHashPass(memberPw);
			loginPw = memberPwDAO.loginCheck(memberPw);
			if (loginPw != null && !(loginPw.getPass().isEmpty())) {// 조회요청받은 pw 존재하면
				// id와 pw의 member_id 같은지 비교
				if (loginMember.getMember_id() == loginPw.getMember().getMember_id()) {
					logger.info("로그인 로직 성공");
					try {
						// last login time update
						memberDAO.lastLogUpdate(loginMember.getMember_id());
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("memberServiceImpl catch문 exception stack trace: " + e.getStackTrace().toString());
					}
				} else {
					logger.info(this.getClass().getName() + ":: 존재하는 id와 pw의 pk와 fk 불일치");
					throw new LoginFailException("로그인에 실패했습니다.");
				}
			}
		}
		return loginMember;
	}

	@Override
	public Member idCheck(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member emailCheck(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member passCheck(String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public String memberInsert(Member member, MemberPw memberPw) throws RegistFailException {
		logger.info("member의 권한id확인 : "+member.getAuth().getAuth_id());
		int memberResult = memberDAO.memberInsert(member);
		int memberPwResult = 0;
		StringBuffer sb = new StringBuffer();
		if (memberResult == 0) {
			logger.info("member 정보 insert 실패");
			sb.append("{");
			sb.append("\"resultCode\":\"0\",");
			sb.append("\"msg\":\"회원 가입실패. 입력정보를 확인해주세요.\"");
			sb.append("}");
			throw new RegistFailException("회원가입 실패. 정보를 확인해주세요");
		} else {
			logger.info("member 정보 insert까지는 성공");

			try {
				//pw에 멤버정보 주입
				memberPw.setMember(member);
				logger.info("memberPw에 setting한 member_id : " + memberPw.getMember().getMember_id());
				Integer memberIdInteger = new Integer(memberPw.getMember().getMember_id());
				logger.info("memberIdInteger.intValue()  : " + memberIdInteger.intValue());
				if (memberIdInteger != null) {
					//pw암호화
					memberPw = security.setHashPass(memberPw);
					logger.info("회원가입 서비스에서 비번 암호화 후 확인 : "+memberPw.getPass());
					memberPwResult = memberPwDAO.memberPwInsert(memberPw);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (memberPwResult == 0) {
				sb.append("{");
				sb.append("\"resultCode\":\"0\",");
				sb.append("\"msg\":\"회원 가입실패. 입력정보를 확인해주세요.\"");
				sb.append("}");
				throw new RegistFailException("회원가입 실패. 정보를 확인해주세요");
			} else {
				sb.append("{");
				sb.append("\"resultCode\":\"1\",");
				sb.append("\"msg\":\"회원 가입을 환영합니다.\"");
				sb.append("}");
			}
		}
		return sb.toString();
	}

	@Override
	public void memberUpdate(Member member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void memberDelete(int member_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String findId(Member member) {
		String searchedUserId = memberDAO.findId(member);
		logger.info("조회된 userid(searchedUserid) : " + searchedUserId);
		JSONObject findIdResult = new JSONObject();
		if (searchedUserId != null) {
			findIdResult.put("resultCode", "1");
			findIdResult.put("userid", searchedUserId);
			findIdResult.put("msg", "정보와 맞는 ID가 조회되었습니다.");
		} else {
			findIdResult.put("resultCode", "0");
			findIdResult.put("userid", null);
			findIdResult.put("msg", "ID찾기 실패");
		}
		logger.info("id찾기 조회결과 : "+findIdResult.toString());
		return findIdResult.toString();
	}

	@Override
	public String resetPass(MemberPw memberPw) {
		memberPw = security.setHashPass(memberPw);
		int resetPwResult = memberPwDAO.resetPass(memberPw);
		JSONObject jsonResult = new JSONObject(); 
		if(resetPwResult == 1) {
			jsonResult.put("resultCode", "1");
			jsonResult.put("msg","비밀번호가 변경되었습니다.\n 로그인 화면으로 이동합니다.");
		} else {
			jsonResult.put("resultCode", "0");
			jsonResult.put("msg","비밀번호 변경 실패.\n 다시 시도해주세요.");
		}
		return jsonResult.toString();
	}

	@Override
	public JSONObject infoCheck(Member member) {
		logger.info("비번 재설정용 infoCheck 요청 서비스 impl===============");
		logger.info("비번 재설정 확인요청 id: "+member.getUserid());
		logger.info("비번 재설정 확인요청 email: "+member.getEmail());
		Member checkedMemberInfo = memberDAO.infoCheck(member);
		JSONObject infoResult = new JSONObject();
		if(checkedMemberInfo!=null) {
			infoResult.put("resultCode", "1");
			infoResult.put("msg", "회원확인 완료. 인증메일 발송에 10초 내외의 시간이 소요됩니다.");
			infoResult.put("checkedUserid", checkedMemberInfo.getUserid());
			infoResult.put("checkedEmail", checkedMemberInfo.getEmail());
			infoResult.put("member_id", checkedMemberInfo.getMember_id());
		} else {
			infoResult.put("resultCode", "0");
			infoResult.put("msg", "회원정보 확인 실패.");
			infoResult.put("checkedUserid", null);
			infoResult.put("checkedEmail", null);
			infoResult.put("member_id", null);
		}
		return infoResult;
	}

	@Override
	public boolean send(JoinCode joinCode) {
		logger.info("memberServiceImpl에서 확인한 joinCode id : "+joinCode.getUserid());
		logger.info("memberServiceImpl에서 확인한 joinCode email : "+joinCode.getEmail());
		logger.info("memberServiceImpl에서 확인한 joinCode num : "+joinCode.getNum());
		
		String subject = "[web발신]비밀번호 재설정 승인번호입니다.";
		String fromAccount = "milkkaru1028@naver.com";
		
		Map<String, String> mailMap = new HashMap<String, String>();
		mailMap.put("subject", subject);
		mailMap.put("from", fromAccount);
		mailMap.put("to", joinCode.getEmail());
		mailMap.put("num", joinCode.getNum());
		
		boolean sendResult = mailUtil.buildMessage(mailMap);
		return sendResult;
	}

	@Override
	public JSONObject updateAuth(Member member) {
		JSONObject authEditResultObj = new JSONObject();
		int updateAuthResult = memberDAO.updateAuth(member);
		if (updateAuthResult == 0) {
			authEditResultObj.put("resultCode","0");
			authEditResultObj.put("msg", "권한수정에 실패했습니다.");
		} else {
			authEditResultObj.put("resultCode", "1");
			authEditResultObj.put("msg", "권한수정에 성공했습니다.");
		}
		return authEditResultObj;
	}

	@Override
	public List<Member> search(String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String idOverlapCheck(String userid) {
		logger.info("중복체크 요청 넘어온 userid : " + userid);
		String searchedUserId = memberDAO.idOverlapCheck(userid);
		logger.info("중복체크 조회된 userid(searchedUserid) : " + searchedUserId);
		StringBuffer sb = new StringBuffer();
		JSONObject idOverlapResult = new JSONObject();
		if (searchedUserId != null || userid.equals(searchedUserId)) {
			idOverlapResult.put("resultCode","0");
			idOverlapResult.put("msg", "중복된 아이디 입니다.");
		} else {
			idOverlapResult.put("resultCode", "1");
			idOverlapResult.put("msg", "사용 가능한 아이디 입니다.");
		}
		return idOverlapResult.toString();
	}

	@Override
	public MemberPw selectByMemId(int member_id) {
		MemberPw memberPw = memberPwDAO.selectByMemberId(member_id);
		return memberPw;
	}



}
