package com.project.nwt.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nwt.model.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long> {
	List<Department> findByName(String name);
}
