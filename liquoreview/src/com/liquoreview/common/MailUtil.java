package com.liquoreview.common;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.liquoreview.exception.MessageBuildFailException;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender javaMailSender;

	// message 조립
	public boolean buildMessage(Map<String, String> mailMap) {

		MimeMessage message = javaMailSender.createMimeMessage();
		StringBuilder content = new StringBuilder();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			if (!mailMap.isEmpty()) {
				content.append("비밀번호 재설정 승인번호는 ").append(mailMap.get("num")).append(" 입니다.");
				helper.setSubject(mailMap.get("subject"));
				helper.setText(content.toString(), true);
				helper.setFrom(mailMap.get("from"));
				helper.setTo(mailMap.get("to"));
			
				boolean sendResult = sendMail(message);
				if(sendResult) {
					return sendResult;
				}else {
					return false;
				}
			} else {
				throw new MessageBuildFailException("메일발송에 필요한 정보 부족");
			}
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new MessageBuildFailException("메일발송 의뢰 실패");
		}
	}
	
	//메일발송처리
	public boolean sendMail(MimeMessage mimeMessage) {
		if (mimeMessage.equals(null)) {
			return false;
		} else {
			javaMailSender.send(mimeMessage);
			return true;
		}
	}

}
