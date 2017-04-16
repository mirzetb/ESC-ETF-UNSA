package esc.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.models.UsersMeta;
import esc.project.services.UsersMetaRepositoryImpl;

@RestController
public class UsersMetaController {

	@Autowired
	UsersMetaRepositoryImpl usersMetaRepo;
	
	
	@RequestMapping(value = "/syncUsers", method = RequestMethod.POST)
	public void syncUsers(@RequestBody  UsersMeta um)
	{
		usersMetaRepo.saveUser(um);
	}
}
