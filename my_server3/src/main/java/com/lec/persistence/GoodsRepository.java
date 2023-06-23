package com.lec.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.domain.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long>{
	

	Page<Goods> findByGdsNameContaining(String searchWord, Pageable pageable);

	Page<Goods> findByCategoryContaining(String searchWord, Pageable pageable);

}
