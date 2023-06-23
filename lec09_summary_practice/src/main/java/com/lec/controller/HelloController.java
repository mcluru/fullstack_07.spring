package com.lec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

	/*
	 /hello 경로로 GET요청이 들어오면 "hello"라는 뷰 템플릿을 렌더링하고
	 data라는 이름으로 hello!라는 데이터를 전달하는 역할.
	 */
	@GetMapping("/hello")	//Controller의 메서드는 Model이라는 타입의 객체를 파라미터로 받을 수 있다.
	public String hello(Model model) {	// Model객체 : Controller에서 생성된 데이터를 담아서 뷰에 전달할때 사용되는 객체
										//			파라미터로 선언하면 스프링이 알아서 model을 생성함.
		model.addAttribute("data", "hello!");	// addAttribute(String name, Object value) : value객체를 name이름으로 추가. 뷰 코드에서 name을 통해 value로 사용
												// addAttribute(Object value) : value를 추가. 클래스 이름을 모델 이름으로 사용. 이떄 첫글자는 소문자.
												//								value가 배열이거나 컬렉션일 경우 모델명 뒤에 List붙임
		return "hello";	//hello는 뷰의 이름. 이것과 매핑되는 뷰 템플릿을 찾아 렌더링함.
	}
	
}
