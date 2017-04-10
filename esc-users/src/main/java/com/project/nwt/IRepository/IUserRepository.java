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
	
	List<User> findByFirstName(String firstname);
	List<User> findByLastName(String lastname);
	
	
	//@Query("SELECT u.first_name, u.last_name FROM User u where u.first_name = :firstname and u.last_name = :lastname") 
	//public List<User> search(@Param("firstname") String firstname, @Param("lastname") String lastname);
}
