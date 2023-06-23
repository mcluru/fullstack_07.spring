package com.lec.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//페이징 처리에 필요한 정보 클래스
@Getter
@Setter
@ToString
public class PageInfo {
	
	//입력받는 데이터
	private int totalCnt; // 총 게시물 갯수
	private int pageSize; // 한 페이지의 게시물 수
	private int naviSize = 10; // 페이지 네비게이션 크기
	private int totalPage; // 전체 페이지 갯수
	private int page = 1;// 현재 페이지
	private int beginPage; // 네비의 첫번째 페이지
	private int endPage; // 네비의 마지막 페이지
	private boolean showPrev; // 이전페이지 이동 링크 여부
	private boolean showNext; // 다음페이지 이동 링크 여부
	private boolean showBegin;
	private boolean showEnd;
	private String searchWord;
	private String searchType;
	
	
	//생성자
	public PageInfo() {
		this.pageSize = 10;
	}
	
	public PageInfo(int totalCnt, int page, int pageSize, String searchWord, String searchType) {
		this.totalCnt = totalCnt;
		this.page = page;
		this.pageSize = pageSize;
		this.searchType = searchType;
		this.searchWord = searchWord;
		
		totalPage = (int) Math.ceil(totalCnt/(double)pageSize);	// Math.ceil : 올림
		beginPage = page / naviSize * naviSize + 1;
		endPage = Math.min(beginPage + naviSize - 1, totalPage);
		showPrev = beginPage != 1;
		showNext = endPage != totalPage;
		showBegin = beginPage != 1;
		showEnd = endPage != totalPage;
		
	}
	
	//생성자 (pageSize 10으로 고정)
//	public PageInfo(int totalCnt, int page) {
//		this(totalCnt, page, 10);
//	}
	
	
	// Test 메서드
	public void pageTest() {
		System.out.println(page);
		
		System.out.print(showBegin ? "[Begin]  " : "");
		System.out.print(showPrev ? "[Prev]  " : "");
		for(int i=beginPage;i<=endPage;i++) {
			System.out.print(i + "  ");
		}
		System.out.print(showNext ? "[Next]  " : "");
		System.out.print(showEnd ? "[End]  " : "");
	}

}
