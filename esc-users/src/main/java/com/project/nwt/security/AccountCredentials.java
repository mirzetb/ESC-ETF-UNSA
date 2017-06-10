package com.project.nwt.security;


import java.util.List;


import org.springframework.security.core.GrantedAuthority;

public class AccountCredentials {

	 private String username;
	 private String password;
	 
	 private List<GrantedAuthority> grantedAuthorities;
	 
	 
  // getters & setters
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}
	public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}
	
	
}