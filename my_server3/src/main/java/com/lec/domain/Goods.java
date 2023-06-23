package com.lec.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString(exclude = "category")
public class Goods {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Transient
	private MultipartFile uploadFile;	
	@Column(columnDefinition = "date default now()")
	private Date gdsDate;

	
//	@Override
//	public String toString() {
//		String myString = "[gdsNum] = " + gdsNum + ", [gdsName] = " + gdsName + ", [category] = " + category
//					+ ", [gdsPrice] = " + gdsPrice + ", [gdsStock] = " + gdsStock + ", [gdsDes] = " + gdsDes
//					+ ", [gdsImg] = " + gdsImg + ", [gdsDate] = " + gdsDate;
//		return myString;
//	}
	
}
