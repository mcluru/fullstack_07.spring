package com.lec.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Category {

	@Id
	private String cateCode;	//분류
	private String cateName;
	
	@OneToMany(mappedBy = "category")
    private List<Goods> goodsList;
	
}
