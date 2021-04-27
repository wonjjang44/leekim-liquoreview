package com.liquoreview.controller.admin;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
    @RequestMapping(value = "/excelUpload", method = RequestMethod.POST)
    @ResponseBody
    public int excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
        MultipartFile excelFile =request.getFile("excelFile");//excelFile ==> 뷰단에 있는 input 태크 name 값
        System.out.println("엑셀 파일 업로드 컨트롤러");
        
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.\"");
        }
        
        System.out.println(excelFile);
        System.out.println(excelFile.getOriginalFilename());
        
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

}
