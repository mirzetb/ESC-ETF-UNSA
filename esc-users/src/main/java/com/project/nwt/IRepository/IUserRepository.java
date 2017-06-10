package com.project.nwt.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.project.nwt.model.User;
import java.lang.String;

//@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface IUserRepository extends JpaRepository<User, Long> {
	
	
	@Query("SELECT u FROM User u WHERE LOWER (u.username) = LOWER(:user) AND LOWER(u.password) = LOWER(:pass) ")
	public User login(@Param("user") String user, @Param("pass") String pass);
	User findByUsername(String username);
	List<User> findByFirstName(String firstname);
	List<User> findByLastName(String lastname);
	
	@Query("SELECT u FROM User u WHERE u.unique_id = :uid")
	public User findByUniqueID(@Param("uid") String uid);
	
	
	
}
