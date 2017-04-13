package com.project.nwt.IRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.nwt.model.Role;
import java.lang.String;
import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByName(String name);
}
