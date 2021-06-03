package com.liquoreview.model.repository.alcohol;

import java.util.List;

import com.liquoreview.model.domain.alcohol.Topcategory;

public interface TopcategoryDAO {
	public List<Topcategory> selectAll();
}
