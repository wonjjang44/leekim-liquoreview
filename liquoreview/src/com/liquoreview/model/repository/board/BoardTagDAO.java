package com.liquoreview.model.repository.board;

import java.util.List;

import com.liquoreview.model.domain.board.Tag;

public interface BoardTagDAO {
	public List<Tag> selectAll();
	public Tag select(int tag_id);
	public int insert(Tag tag);
	public int update(Tag tag);
	public int delete(int tag_id);
}
