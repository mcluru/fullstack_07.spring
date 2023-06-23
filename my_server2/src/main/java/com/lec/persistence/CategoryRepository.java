package com.lec.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lec.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

	Page<Category> findByCateCodeContaining(String searchWord, Pageable pageable);

	Page<Category> findByCateNameContaining(String searchWord, Pageable pageable);

}
