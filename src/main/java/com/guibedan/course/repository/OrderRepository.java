package com.guibedan.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guibedan.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
