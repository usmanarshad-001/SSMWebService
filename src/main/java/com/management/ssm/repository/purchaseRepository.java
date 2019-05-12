package com.management.ssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ssm.model.Purchase;

public interface purchaseRepository extends JpaRepository<Purchase, Long>{

}
