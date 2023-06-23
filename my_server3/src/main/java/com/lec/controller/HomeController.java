package com.lec.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lec.domain.Member;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        // 세션에 저장된 데이터 사용
//        Member member = (Member) session.getAttribute("member");
//        if (member != null) {
//            model.addAttribute("name", member.getName());
//        }
        return "home";
    }
    
    @GetMapping("/home")
	public String myHome() {
		return "home";
	}
    
    @GetMapping("/no_access")
    public String noAccess() {
    	return "no_access";
    }
    
    @GetMapping("/about")
    public String about() {
    	return "about";
    }
    
}