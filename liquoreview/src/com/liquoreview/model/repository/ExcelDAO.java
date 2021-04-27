package com.liquoreview.model.repository;

import java.util.Map;

public interface ExcelDAO {
	public int excelUpload(Map<String, Object> paramMap) throws Exception;
}
