package com.liquoreview.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;

@Component
public class PassSecurity {
	Logger logger = Logger.getLogger(this.getClass().getName());

	public String textToHash(String password) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes());
			byte[] data = md.digest();
			for(int i=0; i<data.length;i++) {
				sb.append(Integer.toString((data[i]&0xff)+0x100,16).substring(1));
			}
			logger.info("변환 암호 길이 : "+sb.toString().length());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	public MemberPw setHashPass(MemberPw memberPw) {
		logger.info("비번 암호화 홏출");
		String EncPass = textToHash(memberPw.getPass());
		memberPw.setPass(EncPass);
		return memberPw;
	}
}
