package com.lec.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.lec.domain.Board;

/*
	QuerydslPredicateExecutor
	Spring Data JPA에서 제공하는 인터페이스.
	
	- 역할 -
	1. 동적쿼리 생성 : Querydsl을 사용해 동적쿼리(검색조건에 따라 쿼리를 유연하게 구성) 생성.
	2. 쿼리 실행 : findAll()메소드 포함 여러 실행 메소드 제공.
		findAll() : 검색조건에 매칭되는 모든 엔티티 반환
		findOne() : 검색조건에 매칭되는 하나의 엔티티 반환
		count() : 검색조건에 매칭되는 엔티티 개수 반환
		exists() : 검색조건에 매칭되는 결과 여부를 반환(boolean)
	3. 정렬 및 페이징 : findAll()메소드에 Sort/Pageable객체를 전달해 결과 정렬/페이징 가능
 */

public interface DynamicBoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
	
	
	
}
