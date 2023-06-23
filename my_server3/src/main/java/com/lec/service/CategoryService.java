package com.lec.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.domain.Category;

public interface CategoryService {

	Page<Category> getCategoryList(Pageable pageable, String searchType, String searchWord);

	void insertCategory(Category category);

	List<Category> getAllCategories();

	void deleteCategory(Category category);

	Category getCategory(Category category);

	void updateCategory(Category category);

}
