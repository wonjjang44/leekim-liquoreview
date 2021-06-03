package com.liquoreview.controller.admin;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.liquoreview.model.service.ExcelService;

@Controller
public class ExcelController {
	@Resource(name = "excelService")
	private ExcelService excelService;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	/**
	 * 엑셀 업로드 컨트롤러
	 * @author someone
	 * @date 2021. 05. 04
	 * @param request
	 * */
    @RequestMapping(value = "/excelUpload", method = RequestMethod.POST)
    @ResponseBody
    public int excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
        MultipartFile excelFile =request.getFile("excelFile");//excelFile ==> 뷰단에 있는 input 태크 name 값
        logger.debug("엑셀 파일 업로드 컨트롤러");
        
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        
        logger.debug(excelFile);
        logger.debug(excelFile.getOriginalFilename());
        
        File destFile = new File("F:\\2021.04.06_developer\\excelUploadFile\\"+excelFile.getOriginalFilename());
        
        try{
            excelFile.transferTo(destFile);
        }catch(IllegalStateException | IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        
        //업로드 후 기존 엑셀 파일 삭제
        int result = excelService.excelUpload(destFile);
        destFile.delete();
        
        return result;
    }
    
    
	/**
	 * 엑셀 기본 서식 다운로드
	 * @author 이양원
	 * @date 2021. 05. 29  최초생성
	 * @param response
	 * */
    @RequestMapping(value = "/excelDownload", method = RequestMethod.GET)
    public void excelDownload(HttpServletResponse response) throws IOException{
    	
    	Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("주류등록_엑셀_서식");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        //컬럼 셋팅
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("분류");
        cell = row.createCell(1);
        cell.setCellValue("주류종류");
        cell = row.createCell(2);
        cell.setCellValue("주류명");
        cell = row.createCell(3);
        cell.setCellValue("도수");
        cell = row.createCell(4);
        cell.setCellValue("상세정보");
        
    	//예시값 셋팅
    	row = sheet.createRow(rowNum++);
    	cell = row.createCell(0);
    	cell.setCellValue("증류주");
    	cell = row.createCell(1);
    	cell.setCellValue("소주");
    	cell = row.createCell(2);
    	cell.setCellValue("진로");

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        //response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("주류등록_엑셀_서식.xlsx", "UTF8"));

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }

}
