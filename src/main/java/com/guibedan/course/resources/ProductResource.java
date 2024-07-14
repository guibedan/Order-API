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

import com.guibedan.course.entities.Product;
import com.guibedan.course.entities.Response;
import com.guibedan.course.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductResource {

	@Autowired
	ProductService productService;

	@GetMapping("/categories")
	public ResponseEntity<Response<List<Map<String, Object>>>> findAllWithCategories() {
		List<Map<String, Object>> list = productService.findAllWithCategories();
		Response<List<Map<String, Object>>> res = new Response<>("Lista de produtos cadastrados!", true, 200, list);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping
	public ResponseEntity<Response<List<Product>>> findAll() {
		List<Product> list = productService.findAll();
		Response<List<Product>> res = new Response<>("Lista de produtos cadastrados!", true, 200, list);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<Product>> findById(@PathVariable(value = "id") Long id) {
		Response<Product> res = new Response<>();
		Optional<Product> category = productService.findById(id);

		if (!category.isPresent()) {
			res.setSuccess(false);
			res.setMessage("Produto não encontrado!");
			res.setStatus(404);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}

		res.setSuccess(true);
		res.setMessage("Produto encontrado!");
		res.setStatus(200);
		res.setContent(category.get());

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

}
