package com.project.nwt.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.nwt.model.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
	List<Department> findByName(String name);
	
	@Query("SELECT d FROM Department d WHERE d.code = :code")
	Department findByCode(@Param("code")String code);
}
