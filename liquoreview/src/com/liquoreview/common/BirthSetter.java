package com.liquoreview.common;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class BirthSetter {
	private GregorianCalendar calendar;
	private List<String> yearList;
	private List monthList;
	private List dateList;

	public BirthSetter() {
		// 생년, 생월, 생일 select의 option onChange 이벤트마다 date 초기화
		calendar = new GregorianCalendar(Locale.KOREA);
		yearList = new ArrayList();
		monthList = new ArrayList();
		dateList = new ArrayList();

		for (int i = 0; i < 100; i++) {
			yearList.add(Integer.toString(calendar.getWeekYear() - i));
		}
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				monthList.add(0 + Integer.toString(i));
			} else {
				monthList.add(Integer.toString(i));
			}
		}
		setDate(calendar.getWeekYear(), 0);
	}

	public void setDate(int year, int month) {
		dateList.clear();
		calendar.set(year, month - 1, 1);
		for (int i = 1; i <= calendar.getActualMaximum(calendar.DATE); i++) {
			if (i < 10) {
				dateList.add(0 + Integer.toString(i));
			} else {
				dateList.add(Integer.toString(i));
			}
		}
	}
	
	//getters and setters
	public GregorianCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(GregorianCalendar calendar) {
		this.calendar = calendar;
	}

	public List<String> getYearList() {
		return yearList;
	}

	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	public List getMonthList() {
		return monthList;
	}

	public void setMonthList(List monthList) {
		this.monthList = monthList;
	}

	public List getDateList() {
		return dateList;
	}

	public void setDateList(List dateList) {
		this.dateList = dateList;
	}
}
