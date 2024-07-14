package com.guibedan.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guibedan.course.entities.Order;
import com.guibedan.course.repository.OrderRepository;

@Service
public class OrderService {

	final OrderRepository OrderRepository;

	public OrderService(OrderRepository OrderRepository) {
		this.OrderRepository = OrderRepository;
	}

	public Order save(Order orders) {
		return OrderRepository.save(orders);
	}

	public List<Order> findAll() {
		return OrderRepository.findAll();
	}

	public Optional<Order> findById(Long id) {
		return OrderRepository.findById(id);
	}

	public void deleteById(Order order) {
		OrderRepository.delete(order);
	}

}
