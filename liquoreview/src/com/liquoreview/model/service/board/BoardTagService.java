package com.liquoreview.model.service.board;

import java.util.List;

import org.json.simple.JSONObject;

import com.liquoreview.model.domain.board.Tag;

public interface BoardTagService {
	public List<Tag> selectAll();
	public Tag select(int tag_id);
	public JSONObject insert(Tag tag);
	public JSONObject update(Tag tag);
	public void update(List<Tag> updateList);
	public void delete(int tag_id);
	public void delete(List<Integer> deleteList);
}
