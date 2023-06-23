package com.lec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/xxx")	//:8089/xxx로 들어오는 모든것
public class HelloController {
	
	@RequestMapping("/hello")	// :8089/xxx/hello
	public void hello(Model model) {
		model.addAttribute("greeting", "Hello Thymeleaf");
	}
	
}
