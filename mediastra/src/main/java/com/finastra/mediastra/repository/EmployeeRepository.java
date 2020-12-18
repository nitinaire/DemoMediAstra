package com.finastra.mediastra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finastra.mediastra.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(value = "select * from EMPLOYEES  where city like %?1", nativeQuery = true)
	List<Employee> getbycity(String chars);

	@Query(value = "select * from EMPLOYEES  where is_Intake =?1", nativeQuery = true)
	List<Employee> notTakenList(String is_Intake);

}
