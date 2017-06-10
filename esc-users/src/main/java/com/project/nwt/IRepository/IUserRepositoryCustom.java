package com.project.nwt.IRepository;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.nwt.model.User;

public interface IUserRepositoryCustom {
	
	public List<User> getAll();
	public List<User> search(String firstname, String lastname);
	public Boolean delete(int id);
	
	public void addNewUser(String cookie, String fn, String ln, String escid, String username, String pass, String email,  String phone_num, String role, String department, int active, int validated);
	
	
	
	
	
}
