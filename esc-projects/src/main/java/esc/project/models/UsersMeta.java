package esc.project.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UsersMeta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private String firstName;
	private String lastName;
	private int userID;
	private String username;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UsersMeta(int id, String fn, String ln)
	{
		this.firstName = fn;
		this.lastName = ln;
		this.id = id;
	}
	
	public UsersMeta(String fn, String ln, int uID, String user, String role)
	{
		this.firstName = fn;
		this.lastName = ln;
		this.userID = uID;
		this.username = user;
		this.role = role;
	}
	
	public UsersMeta(){
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

}
