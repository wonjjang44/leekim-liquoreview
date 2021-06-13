package com.liquoreview.common;

/**
 * 페이지에 페이징 버튼들을 만들기 위한 계산 클래스를 정의한다.
 * 기존에 사용하던 NewPager 클래스 사용x
 * @author someone
 * @date 21. 06. 12  최초생성
 * */
public class PageMaker {
    private Criteria cri;                       //게시글 조회 쿼리에 전달될 파라미터를 담게 될 클래스
    private int totalCount;                   //총 게시물 수
    private int startPage;                    //화면에 보여질 첫번째 페이지 번호, 시작 페이지 번호
    private int endPage;                     //화면에 보여질 마지막 페이지 번호, 끝 페이지 번호
    /*
     * 끝 페이지 번호 = (           현재 페이지 번호              / 화면에 보여질 페이지 번호의 갯수) * 화면에 보여질 페이지 번호의 갯수
	     endPage    = (int) (Math.ceil(cri.getPage() / (double) displayPageNum)  *     displayPageNum);
     * */
    private boolean prev;                   //이전 버튼 생성 여부
    private boolean next;                   //다음 버튼 생성 여부
    private int displayPageNum = 5;   //화면에 보여질 페이지 번호의 갯수(버튼의 갯수)


    public Criteria getCri() {
        return cri;
    }
    public void setCri(Criteria cri) {
        this.cri = cri;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();//총 게시글 수를 셋팅
    }
    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getDisplayPageNum() {
        return displayPageNum;
    }
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

    //페이징 관련 버튼 계산(총 게시글 수를 셋팅)
    private void calcData() {
    	//cri.getPage() == 현재 페이지 번호
        endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
 
        //시작 페이지 번호 = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 갯수) + 1
        startPage = (endPage - displayPageNum) + 1;
        if(startPage <= 0) startPage = 1;
        
        
        //마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의 갯수
        int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }
 
        /*
         * 시작 페이지 번호를 구할 때, 마지막 페이지 번호가 화면에 보여질 페이징 버튼의 갯수보다 작으면 문제 발생
         * 시작 페이지 번호가 음수가 되어버리는 상황이 발생한다. 
         * 예를들면 끝 페이지의 번호가 3이고, 보여줄 페이지 갯수가 5라면, 시작 페이지 번호는 -1이 된다. 
         * 따라서, 구한 시작페이지 번호가 0보다 작으면(음수) 시작 페이지를 1로 해주는 로직을 추가해야 한다.
         * */
        
        //이전 버튼 생성 여부 = 시작 페이지 번호 == 1 ? false : true
        prev = startPage == 1 ? false : true;
        //다음 버튼 생성 여부 = 끝 페이지 번호 * 한 페이지당 보여줄 게시글의 갯수 < 총 게시글의 수 ? true: false
        next = endPage * cri.getPerPageNum() < totalCount ? true : false;
    }
    
    
	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + "]";
	}
    
    
    

}
