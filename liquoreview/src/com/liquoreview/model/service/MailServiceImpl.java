package com.liquoreview.model.service;

import org.springframework.stereotype.Service;

@Service("MailService")
public class MailServiceImpl implements MailService{
	/** 메일 전송
	 * @author anotherCoder
	 * @date 2021. 02. 08
     * @param subject 제목
     * @param text 내용
     * @param from 보내는 메일 주소
     * @param to 받는 메일 주소
     * @param filePath 첨부 파일 경로: 첨부파일 없을시 null 
     * */
	@Override
	public boolean mailSend(String subject, String text, String from, String to, String filePath) {
		
		return false;
	}
		

}
