package com.lec.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lec.domain.Board;

/*
	Repository
	기존의 DAO(Data Access Object)와 동일한 개념. 실질적인 DB연동을 처리한다.
	Repository 중 하나를 상속해 작성한다.
	
	1. Repository 인터페이스 상속 구조
	[ JpaRepository -> PagingAndSortingRepository -> CrudRepository -> Repository ]
	
	- Repository 		: 가장 상위. 기능이 거의 x. CrudRepository를 일반적으로 사용.
	- CrudRepository 	: CRUD기능(Created, Read, Update, Delete)
	- PagingAndSortingRepository : 검색기능 + 페이징처리
	- JpaRepository	: 스프링데이터 Starter에서 추가한 기능 사용할 때
	
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

public interface BoardRepository extends CrudRepository<Board, Long> {

	List<Board> findByTitle(String searchWord);
	List<Board> findByContentContaining(String content);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String title);
//	List<Board> findByTitleContaining(String string, Pageable paging);
	Page<Board> findByTitleContaining(String string, Pageable paging);
	
//	@Query("select b from Board b " + "where b.title like %:searchKeyword% order by b.seq desc")
//	List<Board> queryAnnotation1(@Param("searchKeyword") String word);
//	
//	@Query("SELECT b.seq, b.title, b.writer, b.createDate from Board b " 
//		 + " where b.title like %:searchKeyword% order by b.seq desc")
//	List<Object[]> queryAnnotation2(@Param("searchKeyword") String searchKeyword);
//	
//	@Query(value = "select seq, title, writer, create_date from board "
//		+ " where title like '%'||?1||'%' order by seq desc ", nativeQuery=true)
//	List<Object[]> queryAnnotation3(String searchKeyword);
//	
//	@Query("select b from Board b")
//	List<Board> queryAnnotation4(Pageable paging);
}
