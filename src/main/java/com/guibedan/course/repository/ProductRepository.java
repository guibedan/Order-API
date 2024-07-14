package com.guibedan.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.guibedan.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p.id, p.name, p.description, p.price, p.imgUrl, c.name FROM Product p JOIN p.categories c")
	List<Object[]> findAllProductsWithCategories();

}
