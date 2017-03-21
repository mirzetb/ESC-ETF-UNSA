package com.project.nwt.IRepository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.project.nwt.model.User;

public interface IUserRepository extends CrudRepository<User, Long> {
	
	User findOne(int id);
	List<User> findAll();
	@SuppressWarnings("unchecked")
	User save(User newUser);
	String delete(int id);
}
