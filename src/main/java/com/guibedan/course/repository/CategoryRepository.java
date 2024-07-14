package com.guibedan.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.guibedan.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("SELECT c.id, c.name, p.name, p.description, p.price, p.imgUrl FROM Category c JOIN c.products p")
	List<Object[]> findAllCategoryWithProducts();
	
}
