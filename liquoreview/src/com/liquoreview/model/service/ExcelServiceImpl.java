package com.liquoreview.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.model.repository.ExcelDAO;

@Service
@Transactional
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	@Qualifier("mybatisExcelDAO")
	private ExcelDAO excelDAO;

	@Override
	public List<Object> getAllObjects(String target, Map<String, Object> searchMap) {
		if (target.equals("member")) {
			return excelDAO.getMembers(searchMap);
		}
		return null;
	}
}
