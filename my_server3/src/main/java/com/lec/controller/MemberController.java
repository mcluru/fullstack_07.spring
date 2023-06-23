package com.lec.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.lec.domain.Member;
import com.lec.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/join")
	public String join() {
		return "member/member_join";
	}
	
	
	@PostMapping("/insertMember")
	public String insertMember(Member member, SessionStatus status) {
//		if(member.getId() == null || member.getPassword() == null || member.getName() == null || member.getCell() == null) {
//			return "redirect:member_join";
//		}
		memberService.insertMember(member);
		return "redirect:member_join_compl";
	}
	
	@GetMapping("/member_join_compl")
	public String joinCompl() {
		return "member/member_join_compl";
	}
}
