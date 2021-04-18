package com.liquoreview.common.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.liquoreview.model.domain.member.Member;

/**
 * Excel Download 파일 작성용 util class
 * */
@Component
public class ExcelView extends AbstractExcelPOIView{

	@SuppressWarnings("unchecked")

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String target = model.get("target").toString();
		
		//target에 따라서 엑셀 문서 작성 분기
		if(target.equals("member")) {
			//object로 넘어온 값을 각 Model에 맞게 형변환
			List<Member> memberList = (List<Member>)model.get("excelList");
			
			//excel sheet 생성
			Sheet sheet = workbook.createSheet(target);
			
			//row, cell 초기화
			Row row = null;
			int rowCount = 0;
			int cellCount = 0;
			
			//title row 생성
			row = sheet.createRow(rowCount++);
			
			//set title value to the title row cell
			row.createCell(cellCount++).setCellValue("memberSN");
			row.createCell(cellCount++).setCellValue("auth");
			row.createCell(cellCount++).setCellValue("ID");
			row.createCell(cellCount++).setCellValue("name");
			row.createCell(cellCount++).setCellValue("birth");
			row.createCell(cellCount++).setCellValue("email");
			row.createCell(cellCount++).setCellValue("phone");
			row.createCell(cellCount++).setCellValue("regdate");
			row.createCell(cellCount++).setCellValue("last_modi_ymd");
			row.createCell(cellCount++).setCellValue("hiber_yn");
			row.createCell(cellCount++).setCellValue("del_yn");
			row.createCell(cellCount++).setCellValue("last_login_ymd");
			
			//data row cell 생성
			for(Member m : memberList) {
				row = sheet.createRow(rowCount++);
				cellCount = 0;
				row.createCell(cellCount++).setCellValue(m.getMember_id());
				row.createCell(cellCount++).setCellValue(m.getAuth().getDes());
				row.createCell(cellCount++).setCellValue(m.getUserid());
				row.createCell(cellCount++).setCellValue(m.getUsername());
				row.createCell(cellCount++).setCellValue(m.getBirth());
				row.createCell(cellCount++).setCellValue(m.getEmail());
				row.createCell(cellCount++).setCellValue(m.getPhonenum());
				row.createCell(cellCount++).setCellValue(m.getRegdate());
				row.createCell(cellCount++).setCellValue(m.getLast_modi_ymd());
				row.createCell(cellCount++).setCellValue(m.isHiber_yn());
				row.createCell(cellCount++).setCellValue(m.isDel_yn());
				row.createCell(cellCount++).setCellValue(m.getLast_login_ymd());
			}
		}
	}
	
	@Override
	protected Workbook createWorkbook() {
		return new XSSFWorkbook();
	}

	
}
