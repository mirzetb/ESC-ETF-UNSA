package com.project.nwt.IRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.nwt.model.Role;
import java.lang.String;
import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);
	
	@Query("SELECT r FROM Role r WHERE r.code = :code")
	Role findByCode(@Param("code") String code);
}
