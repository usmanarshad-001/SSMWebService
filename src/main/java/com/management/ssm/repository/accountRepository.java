package com.management.ssm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ssm.model.Account;

public interface accountRepository extends JpaRepository<Account,Long>{

}
