package com.lec.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.domain.Goods;
import com.lec.persistence.GoodsRepository;
import com.lec.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsRepository goodsRepo;
	
	@Override
	public Page<Goods> getGoodsList(Pageable pageable, String searchType, String searchWord) {
		if(searchType.equalsIgnoreCase("cateCode")) {
			return goodsRepo.findByCategoryContaining(searchWord, pageable);
		} else if(searchType.equalsIgnoreCase("gdsName")) {
			return goodsRepo.findByGdsNameContaining(searchWord, pageable);
		} else {
			return goodsRepo.findByGdsNameContaining(searchWord, pageable);
		}
	}

	@Override
	public void insertGoods(Goods goods) {
		goodsRepo.save(goods);
	}

	@Override
	public void deleteGoods(Goods goods) {
		goodsRepo.deleteById(goods.getGdsNum());
	}
	
}
