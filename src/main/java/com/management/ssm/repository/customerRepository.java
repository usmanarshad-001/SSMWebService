package com.management.ssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ssm.model.Customer;

public interface customerRepository extends JpaRepository<Customer, Long>{

}