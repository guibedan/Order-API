package com.guibedan.course.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guibedan.course.entities.Product;
import com.guibedan.course.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Map<String, Object>> findAllWithCategories() {
		List<Object[]> products = productRepository.findAllProductsWithCategories();
		List<Map<String, Object>> result = new ArrayList<>();

		for (Object[] product : products) {
			Map<String, Object> map = new LinkedHashMap<>(); // da pra usar HashMap só estou usando LinkedHashMap para
																// ficar bonitinho kk
			map.put("id", product[0]);
			map.put("name", product[1]);
			map.put("description", product[2]);
			map.put("price", product[3]);
			map.put("imgUrl", product[4]);
			map.put("category_name", product[5]);
			result.add(map);
		}

		return result;
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

}
