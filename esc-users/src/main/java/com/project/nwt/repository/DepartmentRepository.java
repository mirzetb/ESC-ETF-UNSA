package com.project.nwt.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nwt.IRepository.IDepartmentRepository;
import com.project.nwt.model.Department;

@Service
public class DepartmentRepository {
	@Autowired
	IDepartmentRepository _iDepartmentRepository;
	
	public DepartmentRepository(IDepartmentRepository iDepartmentRepository)
	{
		if(iDepartmentRepository != null)
			_iDepartmentRepository = iDepartmentRepository;
	}
	
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return _iDepartmentRepository.findAll();
	}
	
	public Department save(Department d){
		return _iDepartmentRepository.save(d);
	}
	
	public Department getById(Long id){
		return _iDepartmentRepository.findOne(id);
	}
	
	public void delete(int id){
		_iDepartmentRepository.delete((long)id);
	}
	
	public List<Department> findByName(String name){
		return _iDepartmentRepository.findByName(name);
	}
	public Department findByCode(String code)
	{
		return _iDepartmentRepository.findByCode(code);
	}
}
