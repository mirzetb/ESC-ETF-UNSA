package com.project.nwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Access;
import java.util.Date;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
    private String lastName;
    private Department department;
    private String escID;
    private String password;
    private String username;
    private String email;
    @Column(name="registration_date")
    private Date registrationDate;
    private String phoneNumber;
    private String unique_id;
    private int validated;
    
    private Role role;
    private int active;
    
    protected User() {}

    public User(String firstName, String lastName, Department department, String escId, String password, String username,
    		String email, Date reg_date, String phone_num, String un_id, Role role, int active) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.escID = escId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.registrationDate = reg_date;
        this.phoneNumber = phone_num;
        this.unique_id = un_id;
        this.role = role;
        this.active = active;
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
    @ManyToOne(cascade=CascadeType.ALL)
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
    @ManyToOne(cascade=CascadeType.ALL)
    public Role getRole(){
    	return this.role;
    }
    public void setRole(Role role){
    	this.role = role;
    }
    
    @Access(AccessType.PROPERTY)
    public int getActive(){
    	return this.active;
    }
    public void setActive(int active){
    	this.active = active;
    }
    
    @Access(AccessType.PROPERTY)
    public String getEscID(){
    	return this.escID;
    }
    public void setescID(String escID){
    	this.escID = escID;
    }
    
    @Access(AccessType.PROPERTY)
    public String getUniqueID(){
    	return this.unique_id;
    }
    public void setUniqueID(String uniqueID){
    	this.unique_id = uniqueID;
    }
    
    @Access(AccessType.PROPERTY)
    public int getValidated(){
    	return this.validated;
    }
    public void setValidated(int validated){
    	this.validated = validated;
    }

    @Override
    public String toString() {
        return String.format(
                "User[firstName='%s', lastName='%s']",
                firstName, lastName);
    }
    
}
