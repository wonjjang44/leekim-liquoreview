package com.liquoreview.model.service.alcohol;

import java.util.List;

import com.liquoreview.model.domain.alcohol.Topcategory;

public interface TopcategoryService {
	public List<Topcategory> selectAll();
	public Topcategory select(int topcategory_id);
}
