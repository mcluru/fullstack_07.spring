package com.lec.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.domain.Member;

public interface MemberService {

	Member getMember(Member member);	// id로 select
	//	페이지 관련 정보(번호,개수,정렬), 서치타입, 서치할문장을 매개변수로 받아서
	//	그래서 무슨 기능?
	Page<Member> getMemberList(Pageable pageable, String searchType, String searchWord);
	void insertMember(Member member);

}
