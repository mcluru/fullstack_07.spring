package com.lec.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.Service.MemberService;
import com.lec.domain.Member;
import com.lec.persistence.MemberRepository;

@Service	// 내부에서 자바 로직을 처리(서비스 레이어)
			// 스프링에게 해당 클래스가 서비스 빈으로 등록되어야함을 알려주는 역할
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepo;

	@Override
	public Member getMember(Member member) {
		// Optional<T> : 값의 존재여부를 표현하는 래퍼 클래스.
		//				 값이 있을수도, 없을수도 있는 상황에서 사용한다.
		//				즉, 쿼리의 결과가 없을수도 있으므로 사용한다는 뜻.
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if(findMember.isPresent()) return findMember.get();
		// .get()메서드는 값이 있을때만 호출 가능. 안전하게 처리하기 위해 사용함
		// findMember로 반환 : Optional<Member>객체를 반환
		// findMember.get()으로 반환 : 객체에서 실제로 값을 추출하여 반환
		else return null;
	}

	
	@Override
	public Page<Member> getMemberList(Pageable pageable, String searchType, String searchWord) {
		if(searchType.equalsIgnoreCase("id")) {	// equalsIgnoreCase : 대소문자 구분안함
												// equals : 대소문자 구분함.
			return memberRepo.findByIdContaining(searchWord, pageable);	// 검색방식이 id면 searchWord로 검색해서 페이징처리까지 해라
		} else {
			return memberRepo.findByNameContaining(searchWord, pageable);	//아니라면 searchWord로 이름을 검색해라
		}
	}


	@Override
	public void insertMember(Member member) {
		// TODO Auto-generated method stub
		
	}

}
