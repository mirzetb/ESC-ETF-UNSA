package esc.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.models.UsersMeta;
import esc.project.repositories.UsersMetaRepository;
import esc.project.services.UsersMetaRepositoryImpl;

@RestController
public class UsersMetaController {

	@Autowired
	UsersMetaRepositoryImpl usersMetaRepo;
	@Autowired
	UsersMetaRepository uMr;
	
	
	@RequestMapping(value = "/syncUsers", method = RequestMethod.POST)
	public void syncUsers(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("userID") String userID, @RequestParam("username") String username, @RequestParam("role") String role)
	{
		UsersMeta um = new UsersMeta(firstName, lastName, Integer.parseInt(userID), username, role);
		usersMetaRepo.saveUser(um);
	}
	
	
	@RequestMapping(value="/getOneUser", method = RequestMethod.GET)
	public UsersMeta getUser(@RequestParam("username") String username)
	{
		
		return uMr.findByusername(username);
	}
	
}
