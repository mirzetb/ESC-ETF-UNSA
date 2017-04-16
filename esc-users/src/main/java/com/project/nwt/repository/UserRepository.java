package com.project.nwt.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.nwt.IRepository.IUserRepositoryCustom;
import com.mysql.jdbc.log.Log;
import com.project.nwt.IRepository.IUserRepository;
import com.project.nwt.model.User;

@Service
public class UserRepository implements IUserRepositoryCustom {
	
	@Autowired
    IUserRepository _iUserRepository;
	
	
	public UserRepository(IUserRepository iUserRepository)
	{
		if(iUserRepository != null)
			_iUserRepository = iUserRepository;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return _iUserRepository.findAll();
	}
	
	public User getById(int id){
		return _iUserRepository.findOne((long)id);
	}
	
	public User save(User u){
		return _iUserRepository.save(u);
	}
	
	public Boolean delete(int id){
		User u = _iUserRepository.findOne((long)id);
		if(u != null){
			u.setActive(0);
			_iUserRepository.save(u);
			return true;
		}
		return false;
	}
	
	public List<User> search(String firstname, String lastname){
		List<User> resByFirstName = _iUserRepository.findByFirstName(firstname);
		List<User> resByLastName = _iUserRepository.findByLastName(lastname);
		List<User> result = new ArrayList<User>();
		
		for(Iterator<User> u = resByFirstName.iterator(); u.hasNext(); ) {
		    User uTemp = u.next();
		    if(resByLastName.contains(uTemp))
		    	result.add(uTemp);
		}
		return result;
	}

	@Override
	public void addNewUser(String fn, String ln, String escid, String pass, String username, String email,
		 String phone_num, String unique_id, int validated, int active) {
		
				
		User u = new User(fn, ln, null, escid, pass, username, email, new Date(System.currentTimeMillis()), phone_num, unique_id, null, active, validated);
		
		_iUserRepository.save(u);
		
		RestTemplate rt = new RestTemplate();
		
		rt.postForObject(url(appName) + "/syncUsers", u, User.class);
		
		
	}
	
	private String appName = "ESC-PROJECTS";
	
	@Autowired 
	private DiscoveryClient discoveryClient;
	
	private String url(String serviceID)
	{
		List<ServiceInstance> lista = discoveryClient.getInstances(serviceID);
		if(lista != null && lista.size() > 0)
		{
			return lista.get(0).getUri().toString();
		}
		return null;
	}
	
	
	

}
