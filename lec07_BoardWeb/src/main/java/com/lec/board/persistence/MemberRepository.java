package com.lec.board.persistence;

import org.springframework.data.repository.CrudRepository;

import com.lec.board.domain.Member;

public interface MemberRepository  extends CrudRepository<Member, String> {

}
