package com.project.nwt.IRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.nwt.model.User;

public interface IUserRepositoryCustom {
	public List<User> getAll();
	public List<User> search(String firstname, String lastname);
	public Boolean delete(int id);
	
	public void addNewUser(String fn, String ln, String escid, String pass, String username, String email,  String phone_num, String unique_id, int validated, int active);
	
	
}
