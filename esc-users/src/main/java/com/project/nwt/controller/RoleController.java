package com.project.nwt.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.nwt.model.Role;
import com.project.nwt.model.User;
import com.project.nwt.repository.RoleRepository;

@RestController
@RequestMapping("/roles")
public class RoleController {
	private RoleRepository _roleRepository;
	
	public RoleController(RoleRepository roleRepository)
	{
		if(roleRepository != null)
			_roleRepository = roleRepository;
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> getAll(){
		return _roleRepository.getAll();
	}
	
	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@ResponseBody
	public Role getById(@RequestParam(value = "id", required = true) String id){
		return _roleRepository.getById(Long.parseLong(id));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestParam(value = "id", required = true) String id){
		_roleRepository.delete(Integer.parseInt(id));
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> search(@RequestParam(value = "name", required = true) String name){
		return _roleRepository.findByName(name);
	}
	
	@RequestMapping(value = "/save",  method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Role r){
		if(r != null){
			_roleRepository.save(r);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
