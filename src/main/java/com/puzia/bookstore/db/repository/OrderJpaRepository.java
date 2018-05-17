package com.puzia.bookstore.db.repository;

import com.puzia.bookstore.db.entity.Order;
import com.puzia.bookstore.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Integer> {

}
