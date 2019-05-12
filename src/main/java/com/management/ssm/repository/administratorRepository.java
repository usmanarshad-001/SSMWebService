package com.management.ssm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.ssm.model.Administrator;

public interface administratorRepository extends JpaRepository<Administrator,Long>{
	@Query(value="select* from TBLADMINISTRATOR where ADMINIS_ACTIVE='Y'", nativeQuery=true)
	public List<Administrator> findActive();
	
	@Query(value="select* from TBLADMINISTRATOR "
			+ "where ADMIN_NAME like '%'+?1+'%'",nativeQuery=true)
	public List<Administrator> findAllBySearch(String search);
	
	@Query(value="select* from TBLADMINISTRATOR "
			+ "where (ADMIN_NAME like '%'+?1+'%'"
			+ ")and ADMINIS_ACTIVE='Y'", nativeQuery=true)
	public List<Administrator> findBySearch(String search);
	

}