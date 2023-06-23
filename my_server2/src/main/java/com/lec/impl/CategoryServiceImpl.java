package com.lec.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lec.domain.Category;
import com.lec.persistence.CategoryRepository;
import com.lec.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public Page<Category> getCategoryList(Pageable pageable, String searchType, String searchWord) {
		if(searchType.equalsIgnoreCase("cateCode")) {
			return categoryRepo.findByCateCodeContaining(searchWord, pageable);
		} else if(searchType.equalsIgnoreCase("cateName")) {
			return categoryRepo.findByCateNameContaining(searchWord, pageable);
		} else {
			return categoryRepo.findByCateCodeContaining(searchWord, pageable);
		}
	}

	@Override
	public void insertCategory(Category category) {
		categoryRepo.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepo.deleteById(category.getCateCode());
	}

}
