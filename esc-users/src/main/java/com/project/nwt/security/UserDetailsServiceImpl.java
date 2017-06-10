package com.project.nwt.security;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.nwt.model.User;
import com.project.nwt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username); 
		//Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();		
		if(u.getRole().getCode().equals("SuperUser"))
		{
			grantedAuthorities.add(new SimpleGrantedAuthority("SUPER_USER"));
		}			
		else
		{
			grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
		}
		return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword(), grantedAuthorities);
	}

}
