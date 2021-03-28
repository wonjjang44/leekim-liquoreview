package com.liquoreview.controller.admin;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liquoreview.model.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	@Resource(name = "CategoryService")
	private CategoryService categoryService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	


	
}
