package com.lec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lec.Service.BoardService;
import com.lec.domain.Board;
import com.lec.domain.Member;
import com.lec.domain.PageInfo;

/*
	Page 객체 구성
	
	int getNumber()				: 현재 페이지 정보
	int getSize()				: 한 페이지 크기
	int getTotalpages			: 전체 페이지의 수
	int getNumberOfElements()	: 결과 데이터 수
	boolean hasPreviousPage()	: 이전 페이지 존재 여부
	boolean hasNestPage()		: 다음 페이지 존재 여부
	boolean isLastPage()		: 마지막 페이지 여부
	Pageable nextPageable()		: 다음 페이지 객체
	Pagealbe previousPageable() : 이전 페이지 객체
	List<T> getContent()		: 조회된 데이터
	boolean hasContent()		: 결과 존재 여부
	Sort getSort()				: 검색 시 사용된 Sort 정보
 */

/*
	Pageable (객체) https://tecoble.techcourse.co.kr/post/2021-08-15-pageable/
	
	Pagination : 정렬 바익, 페이지 크기 등 요청에 따라 정보를 전달하는 것.
	
	JPA에서 Pagination을 편하게 사용할 수 있게 제공하는 객체.
	'page=3&size=10&sort=id,DESC'형식의 QueryParameter를 추가로 요청하면 원하는 형식의 데이터를 얻을 수 있다.
	Controller에선 Pageable 객체를 인수로 설정 후 위와 같은 요청이 들어오면, 요청에 해당하는 Pageable객체를 자동생성해준다.
	별다른 수정 없이 Repository로 Pageable을 넘겨주면 된다.
	
	- 반환값 -
	1. Page<T>	: 전체 Page를 알아야 하므로, 요청과 함께 전체 페이지수를 계산하는 count쿼리가 별도로 실행된다.
	2. Slice<T> : 전후의 Slice 존재 여부에 대한 정보를 가짐.
	3. List<T>
	
	※ spring.jpa.show-sql=true 옵션 :  Page를 반환하는 메소드 실행 시, 실제로 count 명령어가 실행되는 것을 확인가능
	
	@PageDefault : 기본설정이 아닌 사용자 설정대로 보내준다.
		ex) public List<UserResponse> findByLastName(@RequestParam String lastName,
                                           			@PageDefault(size=100, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {}
	
	- 우선순위 -
	1. @PageDefault
	2. CustomPageableConfiguration에서 설정 진행.(2번과 동일한 설정을 java코드에서 진행)
	3. application-properties에 별도로 ‘spring.data.web.pageable.default-page-size=100’과 같은 설정을 추가
 */

@Controller
@SessionAttributes({"member", "pageInfo"})
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	Environment environment;
	
	public PageInfo pageInfo = new PageInfo();
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}
	
	
	@RequestMapping("/getBoardList") //모든 HTTP 메서드(GET, POST, PUT, DELETE 등)에 대해 동작
	public String getBoardList(Model model,
					@RequestParam(defaultValue = "0") int page,
					@RequestParam(defaultValue = "10") int pageSize,
					@RequestParam(defaultValue="title") String searchType,
					@RequestParam(defaultValue="") String searchWord) {	//매개변수 선언만 하고 직접 값 부여하는 것과 비슷한데, 파라미터 값이 없을때 부여됨.
		
	
	/*
		PageRequest.of
		
		PageRequest.of(int page, int size) : 페이지번호(0부터 시작), 페이지당 데이터수
		PageRequest.of(int page, int size, Sort.Direction deriction, String ...props) : 페이지번호, 페이지당 데이터수, 정렬방향, 속성(칼럼)들
		PageRequest.of(int page, int size Sort sort) : 페이지번호, 페이지당 데이터수, 정렬방향
	 */
		// 페이징 처리 후, 페이징 정보와 검색조건을 기반으로 실제 데이터베이스에서 쿼리 실행
		// 두 메서드 실행 순서 바뀌어도 상관x. Pageable객체와 필요한 파라미터가 적절히 전달되는게 중요
		// Page객체 : 페이징 처리된 결과를 담고 있는 객체.
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("seq").descending());
		Page<Board> pageResult = boardService.getBoardList(pageable, searchType, searchWord);

		
		List<Board> boardList = pageResult.getContent();

//		for (Board board : boardList) {
//		    System.out.println(board.toString());
//		}
		
		
		int resultTotalCnt = pageResult.getNumberOfElements();
		
//		System.out.println(resultTotalCnt);
		
		PageInfo pageInfo = new PageInfo((int)pageResult.getTotalElements(), page, pageSize, searchType, searchWord);
		
		model.addAttribute("pageable", pageable);		// 쿼리문 실행 전 페이징 정보
		model.addAttribute("pageInfo", pageInfo);		// 쿼리문 실행 후 페이징 정보
		model.addAttribute("pageResult", pageResult);	// 페이징 처리된 데이터
		
		model.addAttribute("pg", page);
		model.addAttribute("bp", pageInfo.getBeginPage());
		model.addAttribute("ep", pageInfo.getEndPage());
		model.addAttribute("ns", pageInfo.getNaviSize());
		model.addAttribute("ps", pageSize);
		model.addAttribute("tp", pageInfo.getTotalPage());
		model.addAttribute("st", searchType);
		model.addAttribute("sw", searchWord);	
		
		return "board/getBoardList";
	}
	
	
	@RequestMapping("/getBoardList2") //모든 HTTP 메서드(GET, POST, PUT, DELETE 등)에 대해 동작
	public String getBoardList2(Model model,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue="title") String searchType,
			@RequestParam(defaultValue="") String searchWord) {	//매개변수 선언만 하고 직접 값 부여하는 것과 비슷한데, 파라미터 값이 없을때 부여됨.
		
		
		/*
		PageRequest.of
		
		PageRequest.of(int page, int size) : 페이지번호(0부터 시작), 페이지당 데이터수
		PageRequest.of(int page, int size, Sort.Direction deriction, String ...props) : 페이지번호, 페이지당 데이터수, 정렬방향, 속성(칼럼)들
		PageRequest.of(int page, int size Sort sort) : 페이지번호, 페이지당 데이터수, 정렬방향
		 */
		// 페이징 처리 후, 페이징 정보와 검색조건을 기반으로 실제 데이터베이스에서 쿼리 실행
		// 두 메서드 실행 순서 바뀌어도 상관x. Pageable객체와 필요한 파라미터가 적절히 전달되는게 중요
		// Page객체 : 페이징 처리된 결과를 담고 있는 객체.
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("seq").descending());
		Page<Board> pageResult = boardService.getBoardList(pageable, searchType, searchWord);

		
		List<Board> boardList = pageResult.getContent();

		for (Board board : boardList) {
		    System.out.println(board.toString());
		}
		
		
		int resultTotalCnt = pageResult.getNumberOfElements();
		
		System.out.println(resultTotalCnt);
		
		PageInfo pageInfo = new PageInfo((int)pageResult.getTotalElements(), page, pageSize, searchType, searchWord);
		
		model.addAttribute("pageable", pageable);		// 쿼리문 실행 전 페이징 정보
		model.addAttribute("pageInfo", pageInfo);		// 쿼리문 실행 후 페이징 정보
		model.addAttribute("pageResult", pageResult);	// 페이징 처리된 데이터
		
		model.addAttribute("pg", page);
		model.addAttribute("bp", pageInfo.getBeginPage());
		model.addAttribute("ep", pageInfo.getEndPage());
		model.addAttribute("ns", pageInfo.getNaviSize());
		model.addAttribute("ps", pageSize);
		model.addAttribute("tp", pageInfo.getTotalPage());
		model.addAttribute("st", searchType);
		model.addAttribute("sw", searchWord);	
		
		return "board/getBoardList2";
	}
	

}
