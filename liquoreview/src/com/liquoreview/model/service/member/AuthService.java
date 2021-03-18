package com.liquoreview.model.service.member;

import java.util.List;

import org.json.simple.JSONObject;

import com.liquoreview.model.domain.admin.Auth;

public interface AuthService {
	public List<Auth> selectAll();
	public Auth select(int auth_id);
	public JSONObject insert(Auth auth);
	public JSONObject update(Auth auth);
	public void update(List<Auth> updateList);
	public void delete(int auth_id);
	public void delete(List<Integer> deleteList);
}
