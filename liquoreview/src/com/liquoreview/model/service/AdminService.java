package com.liquoreview.model.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liquoreview.model.repository.AdminDAO;

@Service("AdminService")
public class AdminService {
	@Resource(name = "AdminDAO")
	private AdminDAO adminDAO;
	
}
