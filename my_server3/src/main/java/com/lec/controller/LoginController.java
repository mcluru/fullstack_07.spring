package com.lec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

import com.lec.domain.Member;
import com.lec.service.MemberService;

@Controller
@SessionAttributes("loginss")
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/login_member")
	public String loginview() {
		return "login/login_member";
	}
	
	@GetMapping("/login_admin")
	public String loginAdminview() {
		return "login/login_admin";
	}
	
	@PostMapping("/login_member")
	public String login(Member member, Model model) {
		Member findMember = memberService.getMember(member);
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("loginss", findMember);
			return "redirect:home";
		} else {
			return "redirect:login_member";
		}
	}
	
	
	@PostMapping("/login_admin")
	public String loginAdmin(Member member, Model model) {
		Member findMember = memberService.getMember(member);
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("loginss", findMember);
			return "redirect:adminmain";
		} else {
			return "redirect:login_admin";
		}
	}
	
	
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home";
	}
	
}
