package com.project.nwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Access;
import java.util.Date;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	private String firstName;
    private String lastName;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Department department;
    private String escID;
    private String password;
    private String username;
    private String email;
    private Date registrationDate;
    private String phoneNumber;
    private String unique_id;
    private int validated;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Role role;
    
    protected User() {}

    public User(String firstName, String lastName, Department department, String password, String username,
    		String email, Date reg_date, String phone_num, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
        this.username = username;
        this.email = email;
        this.registrationDate = reg_date;
        this.phoneNumber = phone_num;
        this.role = role;
    }
    
    @Access(AccessType.PROPERTY)
    public String getFirstName(){
    	return this.firstName;
    }
    public void setFirstName(String firstName){
    	this.firstName = firstName;
    }
    
    @Access(AccessType.PROPERTY)
    public String getLastName(){
    	return this.lastName;
    }
    public void setLastName(String lastName){
    	this.lastName = lastName;
    }
    
    @Access(AccessType.PROPERTY)
    public Department getDepartment(){
    	return this.department;
    }
    public void setDepartment(Department dept){
    	this.department = dept;
    }
    
    @Access(AccessType.PROPERTY)
    public String getPassword(){
    	return this.password;
    }
    public void setPassword(String password){
    	this.password = password;
    }
    
    @Access(AccessType.PROPERTY)
    public String getUsername(){
    	return this.username;
    }
    public void setUsername(String uname){
    	this.username = uname;
    }
    
    @Access(AccessType.PROPERTY)
    public String getEmail(){
    	return this.email;
    }
    public void setEmail(String email){
    	this.email = email;
    }
    
    @Access(AccessType.PROPERTY)
    public Date getRegistrationDate(){
    	return this.registrationDate;
    }
    public void setRegistrationDate(Date regDate){
    	this.registrationDate = regDate;
    }
    
    @Access(AccessType.PROPERTY)
    public String getPhoneNumber(){
    	return this.phoneNumber;
    }
    public void setPhoneNumber(String phone_num){
    	this.phoneNumber = phone_num;
    }
    
    @Access(AccessType.PROPERTY)
    public Role getRole(){
    	return this.role;
    }
    public void setRole(Role role){
    	this.role = role;
    }

    @Override
    public String toString() {
        return String.format(
                "User[firstName='%s', lastName='%s']",
                firstName, lastName);
    }

}
