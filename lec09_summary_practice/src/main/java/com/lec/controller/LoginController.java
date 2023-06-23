package com.lec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.lec.Service.MemberService;
import com.lec.domain.Member;

/*
	Session	https://sun-22.tistory.com/53
	여러 화면이나 여러 요청에서 사용해야 하는 객체를 공유할 때 사용한다.
	
	@SessionAttributes("이름")
	model.addAttribute의 attributeName과 @SessionAttributes의 "이름" 이 같으면
	model에 추가될때 자동으로 세션에 넣어준다.
	
	@SessionAttributes로 지정된 속성은 @ModelAttribute를 통해 모델에 추가되어야 세션에 저장된다.
	따라서 해당 속성이 모델에 추가되지 않은 경우에는 세션에 저장되지 않습니다.
	
	
	@SessionAttributes : 해당 컨트롤러 내에서만 동작
						 해당 컨트롤러 안에서 다루는 특정 모델 객체를 세션에 넣고 공유할 때 사용
						 (로그인한 사용자의 정보처럼 여러 요청에서 공유되어야 하는 속성을 세션에 유지할때)
	@SessionAttribute : 컨트롤러 밖에서 만든 세션데이터에 접근시 사용
 */
@Controller	// 웹 요청과 응답을 처리함(프레젠테이션 레이어)
@SessionAttributes("member")	// "member"라는 이름의 속성을 세션에 유지
public class LoginController {
	
	/*
	 의존성 주입(DI)
	 객체간 의존성을 객체 내부에서 직접 호출(new연산자)하는 대신
	 외부(스프링 컨테이너)에서 객체를 생성해 넣어주는 방식.
	 
	 외부에서 두 객체 간의 관계설정하는 디자인 패턴으로, 인터페이스를 사이에 두어 클래스레벨에선 의존관계 고정되지 않고
	 런타임 시 관계를 동적으로 주입해 유연성 확보, 결합도 낮출 수 있다.
	 
	 의존한다 = 의존대상(내부 객체)가 변하면 외부객체에게 전달된다는 뜻.(결과적으로 영향을 끼침)
	 두 객체간의 관계를 맺어주는 것을 의존성 주입이라고 함.
	 
	 
	 @AutoWired
	 의존성주입을 자동으로 처리하기 위한 어노테이션.
	 해당 클래스가 스프링 빈으로 등록되어야 사용가능
	 
	 - 의존성 주입 방법 -
	 1. 생성자 주입(권장) : 인스턴스 생성시 1회 호출 보장. 주입받은 객체가 변하지 않거나 반드시 객체주입이 필요한 경우 사용
	 2. 필드 주입 : 코드가 간결하지만 의존관계 정확히 파악 어려움. 필드주입 시 final키워드 선언불가로 인해 객체가 변할 수 있음.
	 				주입이 동시에 일어나 겹치면 순환참조 에러 발생
	 3. 수정자(setter) 주입 : setter/사용자정의메서드를 통해 의존관계 주입
	 						  객체가 변경될 필요성이 있을때만 사용함.
	 						  
	 객체생성 : 객체 내부에서 new연산자를 통해 객체를 메모리에 실제로 생성
	 의존성 주입 : 외부에서 객체를 생성 후 객체에 필요한 의존성을 주입하여 사용할 수 있게 하는 것
	 */
	@Autowired	//필드 주입.
	private MemberService memberService;	// MemberServiceImpl에 @Service어노테이션이 있어 클래스의 서비스 빈으로 등록되고
											// 해당 빈을 주입받아 구현된 메서드 호출이 가능하다.
											// @Service어노테이션이 없다면 MemberServic 인터페이스를 통해 의존성이 주입된다.
	
	@GetMapping("/login")
	public String loginView() {
		return "login/login";
	}
	
	@PostMapping("/login") 	//form으로 제출하면 POST방식으로 여기로 들어옴
	public String login(Member member, Model model) {
			// Spring MVC는 form요청을 처리하고 컨트롤러의 메서드에 매핑해 실행.
			// Spring MVC는 전송된 HTTP요청의 본문을 분석해 Member객체에 자동으로 매핑함
			// 		입력 필드의 이름과 Member클래스의 필드이름이 일치하면 자동으로 값을 매핑함
		
		Member findMember = memberService.getMember(member);	//id로 select한 쿼리 결과값이 변수에 담김
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {	//결과값이 있고, 결과의 비번과 로그인시 전달받았던 비번값이 일치하다면
			model.addAttribute("member", findMember);	// Member라는 이름으로 회원정보를 session에 저장
														// 로그인에 성공한 경우 세션에 로그인한 회원의 정보가 저장되어 있게된다
			return "redirect:getMemberInfo";
		} else {
			return "redirect:login";	// redirect : URL을 다시 가리키다 = 해당 URL로 다시 요청하게 된다.
		}
	}
	
	/*
	 SessionStatus
	 
	 @SessionAttributes를 활용해 Session에 남긴 데이터 정리할 때 사용하는 인터페이스
	 */
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();	//세션에 저장된 데이터를 삭제하는 메서드
		return "redirect:login";
	}
	
}
