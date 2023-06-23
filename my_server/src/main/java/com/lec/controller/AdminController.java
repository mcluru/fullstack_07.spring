package com.lec.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.lec.domain.Member;

@Controller
@SessionAttributes("loginss")
public class AdminController {
	
	
	
	@GetMapping("/adminmain")
	public String adminMainCheck(SessionStatus status, HttpSession session) {
	    Object loginss = session.getAttribute("loginss");
	    if (loginss == null) { // loginss 객체가 비어있을 때
	        return "redirect:login_admin";
	    } else {
	    	Member member = (Member) loginss;
	        String role = member.getRole();
	        if ("USER".equals(role)) { // loginss 객체의 role이 'USER'일 때
	            return "redirect:no_access";
	        } else { // loginss 객체의 role이 관리자일 때
	            return "redirect:admin_main";
	        }
	    }
	}


	@GetMapping("/admin_main")
	public String adminMain() {
		return "admin/admin_main";
	}
	
}
