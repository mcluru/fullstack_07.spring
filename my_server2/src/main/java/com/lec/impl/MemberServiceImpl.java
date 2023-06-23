package com.lec.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.domain.Member;
import com.lec.persistence.MemberRepository;
import com.lec.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if(findMember.isPresent()) return findMember.get();
		else return null;
	}

	@Override
	public void insertMember(Member member) {
		member.setRole("USER");
		memberRepo.save(member);
	}

	@Override
	public void insertAdmin(Member member) {
		member.setCell("-");
        member.setRole("ADMIN");
		memberRepo.save(member);
	}

	@Override
	public Page<Member> getMemberList(Pageable pageable, String searchType, String searchWord) {
		if(searchType.equalsIgnoreCase("id")) {
			return memberRepo.findByIdContainingAndRole(searchWord, "USER", pageable);
		} else if(searchType.equalsIgnoreCase("name")) {
			return memberRepo.findByNameContainingAndRole(searchWord, "USER", pageable);
		} else {
			return memberRepo.findByIdContainingAndRole(searchWord, "USER", pageable);
		}
	}
	
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

	@Override
	public void deleteMember(Member member) {
		memberRepo.deleteById(member.getId());
	}

}
