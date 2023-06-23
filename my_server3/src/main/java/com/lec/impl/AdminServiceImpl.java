package com.lec.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.domain.Member;
import com.lec.persistence.AdminRepository;
import com.lec.persistence.MemberRepository;
import com.lec.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private MemberRepository memberRepo;

	@Override
	public Page<Member> getAdminList(Pageable pageable, String searchType, String searchWord) {
		if(searchType.equalsIgnoreCase("id")) {
			return memberRepo.findByIdContainingAndRole(searchWord, "ADMIN", pageable);
		} else if(searchType.equalsIgnoreCase("name")) {
			return memberRepo.findByNameContainingAndRole(searchWord, "ADMIN", pageable);
		} else {
			return memberRepo.findByIdContainingAndRole(searchWord, "ADMIN", pageable);
		}
	}
	
}
