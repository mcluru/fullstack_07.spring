package com.lec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.domain.Member;

public interface MemberService {

	Member getMember(Member member);
	void insertMember(Member member);
	void insertAdmin(Member member);
	Page<Member> getMemberList(Pageable pageable, String searchType, String searchWord);
	Page<Member> getAdminList(Pageable pageable, String searchType, String searchWord);
	void deleteMember(Member member);
	void updateMember(Member member);

}
