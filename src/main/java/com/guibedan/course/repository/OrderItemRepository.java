package com.guibedan.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guibedan.course.entities.OrderItem;
import com.guibedan.course.entities.pk.OrderItemPk;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPk> {}
