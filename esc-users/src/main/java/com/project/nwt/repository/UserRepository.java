package com.project.nwt.repository;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.project.nwt.IRepository.IUserRepositoryCustom;
import com.mysql.jdbc.log.Log;
import com.project.nwt.IRepository.IDepartmentRepository;
import com.project.nwt.IRepository.IRoleRepository;
import com.project.nwt.IRepository.IUserRepository;
import com.project.nwt.model.Department;
import com.project.nwt.model.Role;
import com.project.nwt.model.User;

@Service
public class UserRepository implements IUserRepositoryCustom {
	
	@Autowired
    IUserRepository _iUserRepository;
	
	
	@Autowired
	IDepartmentRepository depRep;
	
	@Autowired
	IRoleRepository roleRep;
	
	
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
	
	public User findByUsername(String username)
	{
		return _iUserRepository.findByUsername(username);
	}
	
	public User findByUniqueID(String uid)
	{
		return _iUserRepository.findByUniqueID(uid);
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
	public void addNewUser(String cookie, String fn, String ln, String escid, String username, String pass, String email,
		 String phone_num, String role, String department, int active, int validated) {
		
		Date registrationDate = new Date(System.currentTimeMillis()); 
		String guid = java.util.UUID.randomUUID().toString();
		
		Department d =  depRep.findByCode(department);
		Role r = roleRep.findByCode(role);		
		
		User u = new User(fn, ln, d, escid, pass, username, email, registrationDate, phone_num, guid, r, 0, 0);
		
		_iUserRepository.save(u);			
		
		User uFromDb = _iUserRepository.findByUsername(username);		
		
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", "Auth="+ cookie);
		
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		
		parameters.add("firstName", uFromDb.getFirstName());
		parameters.add("lastName", uFromDb.getLastName());
		parameters.add("userID", Integer.toString(uFromDb.getId()));
		parameters.add("username", uFromDb.getUsername());
		parameters.add("role", uFromDb.getRole().getCode());
		
		HttpEntity requestEntity = new HttpEntity(parameters, headers);
		
		rt.exchange(url(appName) + "/syncUsers", HttpMethod.POST, requestEntity, String.class);
		
		
		//rt.postForObject(url(appName) + "/syncUsers", u, User.class);
		
		
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
