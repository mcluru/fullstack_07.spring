package com.lec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lec.Service.MemberService;
import com.lec.domain.Member;

@Controller
@SessionAttributes({"pagingInfo", "member"})	//{} : 여러 객체를 그룹화하여 세션에 담는 것
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/getMemberInfo")
	public String loginSuccess() {
		return "member/getMemberInfo";
	}
	
	/*
	 	@RequestParam ( = HttpServletRequest의 getParameter()메소드)
	 	
	 	HTTP요청 파라미터를 매개변수로 받아올 때 사용.
	 	설정한 매개변수는 클라이언트가 요청한 URL의 쿼리 파라미터에서 값을 받아온다.
	 	값이 없을 땐 기본값을 지정하도록 설정되어 있다.
	 	
	 	@RequestParam 어노테이션을 통해 받은 파라미터는 자동으로 타입 변환을 수행할 수 있다.
	 	이를 통해 별도의 타입 변환 로직을 구현하지 않아도 된다.
	 */
//	@GetMapping("/getMemberList")
//	public String getMemberList(Model model,
//			@RequestParam(defaultValue="0") int curPage,			//Param으로 가져오는 이유 : url요청에 파라미터 값이 생길 경우
//			@RequestParam(defaultValue="10") int rowSizePerPage,	//							맞춰서 페이징 처리하기 위함
//			@RequestParam(defaultValue="name") String searchType,
//			@RequestParam(defaultValue="") String searchWord) {  
//		
//		// of(int page, int size, Sort sort) : 페이지 번호와 개수, 정렬 관련 정보
//		// Sort.by("필드명") : 오름차순/내림차순 정렬 가능
//		Pageable pageable = PageRequest.of(curPage, rowSizePerPage, Sort.by(searchType).ascending());
//			// curPage와 rowSizePerPage 매개변수를 기반으로 페이지네이션 정보 생성, searchType 기준으로 오름차순 정렬
//		Page<Member> pageResult = memberService.getMemberList(pageable, searchType, searchWord);
//		
//		return "member/getMemberList";
//	}
	
	
	
	
	@GetMapping("/insertMember")
	public String insertMemberForm(Member member) {
		return "member/insertMember";
	}
	
	@PostMapping("/insertMember")
	public String insertMember(Member member) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		member.setRole(member.getRole() != null ? "ADMIN" : "USER"); // 삼항 연산자를 사용하여 멤버 객체의 역할(role)을 설정하는 코드
		memberService.insertMember(member);
		return "redirect:getMemberList";
	}
	
	
}
