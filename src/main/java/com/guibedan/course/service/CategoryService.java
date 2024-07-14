package com.guibedan.course.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guibedan.course.entities.Category;
import com.guibedan.course.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Map<String, Object>> findAllCategoryWithProducts() {
		List<Object[]> products = categoryRepository.findAllCategoryWithProducts();
		List<Map<String, Object>> result = new ArrayList<>();

		for (Object[] product : products) {
			Map<String, Object> map = new LinkedHashMap<>(); // da pra usar HashMap só estou usando LinkedHashMap para
																// ficar bonitinho kk
			map.put("id", product[0]);
			map.put("name", product[1]);
			map.put("product_name", product[2]);
			map.put("description", product[3]);
			map.put("price", product[4]);
			map.put("imgUrl", product[5]);
			result.add(map);
		}

		return result;
	}
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}
	
}
