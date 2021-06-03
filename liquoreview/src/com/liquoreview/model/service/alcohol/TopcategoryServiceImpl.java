package com.liquoreview.model.service.alcohol;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.model.domain.alcohol.Topcategory;
import com.liquoreview.model.repository.alcohol.TopcategoryDAO;

@Service
@Transactional
public class TopcategoryServiceImpl implements TopcategoryService{

	@Autowired
	@Qualifier("mybatisTopcategoryDAO")
	TopcategoryDAO topcategoryDAO;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<Topcategory> selectAll() {
		List<Topcategory> topcateList = topcategoryDAO.selectAll();
		return topcateList;
	}

	@Override
	public Topcategory select(int topcategory_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
