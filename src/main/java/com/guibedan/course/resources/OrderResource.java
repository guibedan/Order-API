package com.guibedan.course.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guibedan.course.entities.Response;
import com.guibedan.course.entities.Order;
import com.guibedan.course.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	final OrderService orderService;

	public OrderResource(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<Response<List<Order>>> findAll() {
		List<Order> list = orderService.findAll();
		Response<List<Order>> res = new Response<List<Order>>("Lista de pedidos cadastrados!", true, 200, list);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<Order>> findById(@PathVariable(value = "id") Long id) {
		Response<Order> res = new Response<Order>();
		Optional<Order> user = orderService.findById(id);

		if (!user.isPresent()) {
			res.setSuccess(false);
			res.setMessage("Pedido não encontrado!");
			res.setStatus(404);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}

		res.setSuccess(true);
		res.setMessage("Pedido encontrado!");
		res.setStatus(200);
		res.setContent(user.get());

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

}
