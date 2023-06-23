package com.lec.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class MyOrder {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long req;
	
	@Column(nullable = false)
    private String OrderCode;
	
	@Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String cell;

    @Column(nullable = false)
    private String addr;
    
    @Column(columnDefinition = "date default now()")
    private Date orderDate;
    
    @Column(nullable = false)
    private String cateCode;

    @Column(nullable = false)
    private String cateName;
    
    private String option1;
    
    @Column(nullable = false)
    private int orderCnt;

    @Column(nullable = false)
    private int orderPrice;
	
}
