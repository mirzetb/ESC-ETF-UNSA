package com.project.nwt.controller;

import java.io.Console;
import java.util.List;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.nwt.repository.UserRepository;
import com.mysql.jdbc.log.Log;
import com.project.nwt.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
//@EnableAutoConfiguration
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private UserRepository _userRepository;
	
	public UserController(UserRepository userRepository)
	{
		if(userRepository != null)
			_userRepository = userRepository;
	}
	
	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAll(){
		return _userRepository.getAll();
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public User getById(@RequestParam(value = "id", required = true) String id){
		return _userRepository.getById(Integer.parseInt(id));
	}
	
	
	@RequestMapping(value = "/save",  method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody User u){
		log.info(u.getUniqueID());
		if(u != null){
			_userRepository.save(u);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Boolean delete(@RequestParam(value = "id", required = true) int id){
		return _userRepository.delete(id);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<User> search(@RequestParam(value = "firstname", required = false) String firstname, @RequestParam(value = "lastname", required = false) String lastname){
		return _userRepository.search(firstname, lastname);
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void AddUser(@RequestParam("first_name") String firstname, @RequestParam("last_name") String lastname, @RequestParam("escid") String escid, @RequestParam("password") String password, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("phone_number") String phone_number, @RequestParam("unique_id") String unique_id)
	{
		_userRepository.addNewUser(firstname, lastname, escid, password, username, email, phone_number, unique_id, 0, 0);
		
	}
}
