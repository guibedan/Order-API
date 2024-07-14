package com.guibedan.course.resources;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guibedan.course.entities.Category;
import com.guibedan.course.entities.Response;
import com.guibedan.course.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryResource {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/products")
	public ResponseEntity<Response<List<Map<String, Object>>>> findAllCategoryWithProducts() {
		List<Map<String, Object>> list = categoryService.findAllCategoryWithProducts();
		Response<List<Map<String, Object>>> res = new Response<>("Lista de categorias cadastradas com produtos!", true,
				200, list);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping
	public ResponseEntity<Response<List<Category>>> findAll() {
		List<Category> list = categoryService.findAll();
		Response<List<Category>> res = new Response<>("Lista de categorias cadastradas!", true, 200, list);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<Category>> findById(@PathVariable(value = "id") Long id) {
		Response<Category> res = new Response<Category>();
		Optional<Category> category = categoryService.findById(id);

		if (!category.isPresent()) {
			res.setSuccess(false);
			res.setMessage("Categoria não encontrado!");
			res.setStatus(404);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}

		res.setSuccess(true);
		res.setMessage("Categoria encontrado!");
		res.setStatus(200);
		res.setContent(category.get());

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

}
