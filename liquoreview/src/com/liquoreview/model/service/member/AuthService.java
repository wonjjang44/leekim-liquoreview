package com.liquoreview.model.service.member;

import java.util.List;

import com.liquoreview.model.domain.admin.Auth;

public interface AuthService {
	public List<Auth> selectAll();
	public Auth select(int auth_id);
	public int insert(Auth auth);
	public void update(Auth auth);
	public void delete(int auth_id);
}
