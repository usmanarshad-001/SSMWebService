package com.management.ssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ssm.model.OrderStatus;

public interface orderStatusRepository extends JpaRepository<OrderStatus,Long>{

}
