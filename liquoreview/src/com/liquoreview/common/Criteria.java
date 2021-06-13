package com.liquoreview.common;

/**
 * ROWNUM 페이징 대신 LIMIT 함수를 이용하여 페이징 구현
 * 특정 페이지 조회를 위한 Criteria 클래스를 정의한다. (게시글 조회 쿼리에 전달될 파라미터를 담게 될 클래스)
 * 기존에 사용하던 NewPager 클래스 사용x
 * @author someone
 * @date 21. 06. 12  최초생성
 * */
public class Criteria {
	private int page;				//현재 페이지 번호
    private int perPageNum;		//한 페이지당 보여줄 게시글의 갯수
    //현재 페이지의 게시글 시작 번호 = (현재 페이지 번호 - 1) * 페이지당 보여줄 게시글 갯수
    //getPerPageNum 			  = (this.page - 1)       * perPageNum



    //특정 페이지의 게시글 시작 번호, 게시글 시작 행 번호
    public int getPageStart() {
        return (this.page-1)*perPageNum;
    }
    
    public Criteria() {
    	/*
    	 * 최초로 게시판 목록에 들어 왔을 때를 위해서 기본 셋팅.
    	 * 페이징을 처리하기 위해선 현재 페이지 번호와 페이지당 게시글 수가 필요한데 
    	 * 처음 게시판에 들어오게 되면 두 개의 정보를 가져올 방법이 없기 때문에 기본 생성자를 통해 기본 값을 셋팅.
    	 * 현재 페이지를 1페이지로, 페이지당 보여줄 게시글의 수를 5개로 셋팅.
    	 * */
        this.page = 1;
        this.perPageNum = 5;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    
    public int getPerPageNum() {
        return perPageNum;
    }
    
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
    
    
}
