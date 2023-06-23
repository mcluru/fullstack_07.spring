package com.lec.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Gmodel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gmodelId;
	
	@Column(nullable = false)
	private String gName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cateCode", referencedColumnName = "cateCode")
	private Category category;
}
