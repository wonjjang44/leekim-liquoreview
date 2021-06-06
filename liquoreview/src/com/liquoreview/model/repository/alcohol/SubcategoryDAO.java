package com.liquoreview.model.repository.alcohol;

import java.util.List;

import com.liquoreview.model.domain.alcohol.Subcategory;

public interface SubcategoryDAO {
	public List<Subcategory> selectAll(); 
	public Subcategory select(int subcategory_id);
}
