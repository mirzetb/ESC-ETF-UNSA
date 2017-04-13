package com.project.nwt.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.nwt.IRepository.IRoleRepository;
import com.project.nwt.model.Role;

@Service
public class RoleRepository {
	@Autowired
	IRoleRepository _iRoleRepository;
	
	public RoleRepository(IRoleRepository iRoleRepository)
	{
		if(iRoleRepository != null)
			_iRoleRepository = iRoleRepository;
	}
	
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return _iRoleRepository.findAll();
	}
	
	public Role save(Role r){
		return _iRoleRepository.save(r);
	}
	
	public Role getById(Long id){
		return _iRoleRepository.findOne(id);
	}
	
	public void delete(int id){
		_iRoleRepository.delete((long)id);
	}
	
	public List<Role> findByName(String name){
		return _iRoleRepository.findByName(name);
	}
}