package com.liquoreview.model.repository.member;

import java.util.List;

import com.liquoreview.model.domain.admin.Auth;

public interface AuthDAO {
	public List<Auth> selectAll();
	public Auth select(int auth_id);
	public Auth selectDefaultAuth();
	public int insert(Auth auth);
	public int update(Auth auth);
	public int delete(int auth_id);
}
