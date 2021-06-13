package com.liquoreview.common;

/**
 * String 값을 검증한다.
 * @author 이양원
 * @date 2021. 06. 05  최초생성
 * @param v
 * @param str
 * */
public class StringUtil {
	/**
	 * 해당 String이 "" 또는 null인지 검증하고 반환한다.
	 * 검증값이 null || ""이면 true, 이외의 값 false
	 * @param v : 검증할 Object형 값
	 * */
	public static boolean isNull(Object v) {
		return (v == null) || ((v.toString()).length() < 1);
		//v.toString()).length() < 1 ==> 입력받은 값, 즉 검증받을 값의 길이가 0일 때. ==> ""
	}
	
	/**
	 * 입력값이 null 일경우 ""을 반환한다.
	 * @param str : 검증받을 String 값(문자열)
	 * */
	public static String null2void(String str) {
		return (isNull(str) ? "" : str.toString());
	}
}
