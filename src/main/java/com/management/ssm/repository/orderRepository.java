package com.management.ssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ssm.model.Order;

public interface orderRepository extends JpaRepository<Order,Long>{

}
