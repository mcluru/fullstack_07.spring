package com.lec.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.domain.Member;

/*
Repository
기존의 DAO(Data Access Object)와 동일한 개념. 실질적인 DB연동을 처리한다.
Repository 중 하나를 상속해 작성한다.

1. Repository 인터페이스 상속 구조
[ JpaRepository -> PagingAndSortingRepository -> CrudRepository -> Repository ]

- Repository 		: 가장 상위. 기능이 거의 x. CrudRepository를 일반적으로 사용.
- CrudRepository 	: CRUD기능(Created, Read, Update, Delete)
- PagingAndSortingRepository : 검색기능 + 페이징처리
- JpaRepository	: CRUD + JPA 특화기능(페이징/정렬, 쿼리 메서드 자동 생성, 배치 작업 등)
				CrudRepository보다 더 다양한 네이밍 규칙

2. 특징
2-1. 모든 인터페이스는 공통적으로 두 개의 제네릭타입을 지정해야 한다.
	- T  : 엔티티클래스 타입
	- ID : 엔티티의 식별자(@Id로 매핑한 변수)의 타입
	
	
2-2. 인터페이스는 객체생성이 불가하지만, JPA를 사용하는 경우 구현클래스 없이 인터페이스 객체생성 가능하다.
	스프링 부트가 내부적으로 인터페이스에 대한 구현객체를 자동으로 생성해주기 때문이다.
*/

/*
쿼리메서드

JPA를 이용해 구현할 땐 JPQL을 이용한다. JPQL은 테이블이 아닌 엔티티를 대상으로 한다.
JPA에선 복잡한 JPQL을 메서드로 처리할 수 있도록 쿼리메서드를 제공한다.

쿼리메서드로 가장 많이 사용하는 문법 : 검색하려는 엔티티에서 특정변수의 값만 조회

1. 메서드이름
find로 시작하면서 조회할 변수들을 적절히 조합한다.
	find + 엔티티명 + By + 변수명	ex)findBoardByTitle() -> Board엔티티에서 title변수값만 조회한다.
	
엔티티명은 생략가능하다. 엔티티명이 생략되면 현재 사용하는 Repository인터페이스에 선언된 정보를 기준으로
자동으로 엔티티이름이 적용된다
*/


//@Repository	// DB/파일 등 외부 I/O작업 처리
public interface MemberRepository extends JpaRepository<Member, String>{
	
	/*	https://bnzn2426.tistory.com/135
		페이징 처리를 위해 Pageable 타입의 객체를 파라미터로 전달해야한다.
		이때 리턴 타입은 Page<T>타입의 객체여야 한다
	 */
	//	Page<T> : 페이징 처리된 결과 반환
	//	Pageable : 인터페이스. 페이지처리에 필요한 정보를 전달하는 용도. 구현체인 PageRequest클래스를 사용해 실제객체 생성한다
	Page<Member> findByIdContaining(String id, Pageable pagealbe);	// SELECT * FROM member WHERE id LIKE '%{id}%'
	Page<Member> findByNameContaining(String name, Pageable pagealbe);	// select * from member where name like '%{name}%'
	
}
