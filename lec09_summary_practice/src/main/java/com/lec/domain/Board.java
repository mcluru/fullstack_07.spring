package com.lec.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@EntityListeners(BoardListeners.class)
// Entity Listener: 엔티티의 변화를 감지하고 데이블의 데이터를 조작(엔티티 생성,수정,삭제 등)
// BoardListeners클래스를 엔티티리스너로 등록한다는 뜻. (해당 클래스가 엔티티 생명주기 이벤트 관련 콜백 메서드 가지고 있음)
// 해당 클래스의 메서드들은 엔티티의 생성,수정,삭제 등의 이벤트가 발생할 때 실행됨.
public class Board {
	
	/*
	 기본키 매핑 https://ttl-blog.tistory.com/123
	 1. 직접 할당 : @Id만 사용
	 2. 자동 할당 : @Id @GeneratedValue 같이 사용. 4가지 전략이 있음.
	 	- AUTO : 기본설정값. 데이터베이스 방언에 따라 아래 세가지 전략 중 하나를 자동으로 선택
	 	- IDENTITY : 기본키 생성을 DB에 위임. id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT(자동으로 숫자를 메겨주는 기능) 해줌
	 				 데이터베이스에 값을 저장하고 나서야 기본 키 값을 구할 수 있을 때 사용
	 	- SEQUENCE :  유일한 값을 순서대로 생성. @SequenceGenerator를 이용해 사용할 데이터베이스 시퀀스를 매핑해야함.
	 	- TABLE : 키 생성 전용 테이블을 하나 만들고, 여기에 이름과 값으로 사용할 컬럼을 만들어 데이터베이스 시퀀스를 흉내내는 전략
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	
	/*
	 @Column 어노테이션	https://cjw-awdsd.tistory.com/46
	 DB 테이블의 컬럼과 1:1 매칭. 별다른 옵션 설정이 없다면 생략 가능.
	 Entity클래스에 정의된 모든 내부변수는 기본적으로 @Column어노테이션을 포함함.
	 */
	@Column(updatable = false) // 엔티티 수정시 값 수정 안됨
	private String writer;
	
	private String content;
	
	@Column(insertable = false, updatable = false, columnDefinition = "data default now()")
	// 엔티티 저장시 필드수정x, 수정시 값수정x, 데이터베이스 컬럼정보(데이터타입,기본값등)를 직접 부여
	private Date createDate;
	
	@Column(insertable = false, updatable = false, columnDefinition = "bigint dafault 0")
	private Long cnt;
	
	private String fileName;
	
	/*
	 @Transient
	 
	 컬럼을 영속 대상에서 제외한다 ex)비밀번호 확인 입력란
	 영속 상태의 엔티티 객체는 엔티티 매니저에 의해 
	 	1. 변화에 대한 자동 감지
	 	2. CRUD SQL 자동 생성 작업
	 	3. 그외 일련의 모든 JPA의 내부적인 동작 프로세스에서 활용된다
 	 영속 대상에서 제외되면 해당 필드/메서드는 엔티티 매니저의 관리 대상에서 제외됨을 의미.
 	 즉 테이블의 컬럼과 매핑을 하지 않는다고 해도 무방.
 	 
 	 JPA는 @Entity 클래스에 포함된 모든 필드에 대해 영속 대상으로 간주하여 테이블의 컬럼과 자동으로 매핑 시키는 작업을 수행한다.
 	 이때 필드에 대해 클래스에서만 사용되고, 테이블 컬럼으로 관리하고 싶지 않을 경우
	 */
	@Transient
	private MultipartFile uploadFile;	//파일처리 관련 클래스 https://velog.io/@ssac17/Spring-Multipar%E3%85%85File-form-%EB%B0%A9%EC%8B%9D%EC%9D%98-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C
	
	private Long board_ref;	//부모 글의 식별자
	private Long board_lev;	//부모 글과의 계층적 관계를 나타내는 값, 일반적으로 0부터 시작
	private Long board_seq;	//글이 표시되는 순서를 나타내는 값, 일반적으로 부모 글과 동일한 레벨에서는 시퀀스 값에 따라 정렬
	
}

/*
	Entity클래스 메서드 vs @EntityListeners
	
	Entity클래스 메서드 : 엔티티 객체의 동작과 비즈니스 로직 구현에 사용.
		특정 필드값 변경, 연산 수행 등의 동작 정의 가능.
		메서드는 엔티티 객체에 종속적이며, 객체 메서드로 직접호출해 실행됨.
		(엔티티의 도메인 로직을 구현하고, 엔티티의 상태를 조작하는 역할)
	
	@EntityListeners : 엔티티의 변화에 따른 이벤트(엔티티 생성,수정,삭제등) 감지 후 처리
		해당 이벤트가 발생하면 @EntityListeners로 지정된 클래스의 메서드 실행됨.
		엔티티 객체와 독립적으로 동작한다.
 */
