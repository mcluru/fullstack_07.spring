package com.lec.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Goods {
	
	@Id
	private long gdsNum;
	@Column(nullable = false)
	private String gdsName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cateCode", referencedColumnName = "cateCode")
	private Category category;
	
	
	@Column(nullable = false)
	private long gdsPrice;
	@Column(columnDefinition = "bigint default 0")
	private long gdsStock;	//재고
	private String gdsDes;	//상품설명
	private String gdsImg;
	@Column(columnDefinition = "date default now()")
	private Date gdsDate;
	
}
