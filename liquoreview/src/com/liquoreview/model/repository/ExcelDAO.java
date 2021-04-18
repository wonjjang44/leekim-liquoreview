package com.liquoreview.model.repository;

import java.util.List;
import java.util.Map;

public interface ExcelDAO {
	public List<Object> getMembers(Map<String, Object> searchMap);
}
