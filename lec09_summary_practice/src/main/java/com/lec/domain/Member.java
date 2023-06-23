package com.lec.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity	// DB테이블과 매핑할 클래스
public class Member {
	
	@Id //해당컬럼을 PK로 설정
	private String id;
	private String password;
	private String name;
	private String role;

}
