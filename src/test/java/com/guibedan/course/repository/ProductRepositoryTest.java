package com.guibedan.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.guibedan.course.entities.Category;
import com.guibedan.course.entities.Product;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	EntityManager entityManager;

	private Product product;
	private Category category;

	@BeforeEach
	void setUp() {
		category = new Category(null, "Books");
		product = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		product.getCategories().add(category);
	}

	@Test
	@DisplayName("Should get list of products with categories successfully from DB")
	void findAllProductsWithCategoriesCase1() {
		createCategory(category);
		createProduct(product);

		List<Object[]> productsFound = productRepository.findAllProductsWithCategories();

		assertThat(productsFound).isNotEmpty();
		Object[] firstProduct = productsFound.get(0);
		assertThat(firstProduct[0]).isEqualTo(product.getId());
		assertThat(firstProduct[1]).isEqualTo(product.getName());
		assertThat(firstProduct[2]).isEqualTo(product.getDescription());
		assertThat(firstProduct[3]).isEqualTo(product.getPrice());
		assertThat(firstProduct[4]).isEqualTo(product.getImgUrl());
		assertThat(firstProduct[5]).isEqualTo(category.getName());
	}

	@Test
	@DisplayName("Should get empty list of products with categories from DB")
	void findAllProductsWithCategoriesCase2() {
		List<Object[]> productsFound = productRepository.findAllProductsWithCategories();

		assertThat(productsFound).isEmpty();
	}

	private Product createProduct(Product product) {
		this.entityManager.persist(product);
		entityManager.flush();
		return product;
	}

	private Category createCategory(Category category) {
		this.entityManager.persist(category);
		entityManager.flush();
		return category;
	}
}
