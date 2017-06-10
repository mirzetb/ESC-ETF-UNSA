package com.project.nwt.controller;



import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.nwt.IRepository.IDepartmentRepository;
import com.project.nwt.model.Department;
import com.project.nwt.repository.DepartmentRepository;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository _departmentRepository;
	
	
	
	public DepartmentController(DepartmentRepository departmentRepository)
	{
		if(departmentRepository != null)
			_departmentRepository = departmentRepository;
	}
	
	@RequestMapping(value = "/getAllDepartments", method = RequestMethod.GET)
	@ResponseBody
	public List<Department> getAll(){
		return _departmentRepository.getAll();
	}
	
	@RequestMapping(value = "/getDepartmentById", method = RequestMethod.GET)
	@ResponseBody
	public Department getById(@RequestParam(value = "id", required = true) String id){
		return _departmentRepository.getById(Long.parseLong(id));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestParam(value = "id", required = true) String id){
		_departmentRepository.delete(Integer.parseInt(id));
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Department> search(@RequestParam(value = "name", required = true) String name){
		return _departmentRepository.findByName(name);
	}
	
	@RequestMapping(value = "/save",  method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Department d){
		if(d != null){
			_departmentRepository.save(d);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
}
