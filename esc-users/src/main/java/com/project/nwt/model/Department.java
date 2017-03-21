package com.project.nwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.AccessType;
import javax.persistence.Access;

@Entity
public class Department {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	private String code;
	private String name;
	
	protected Department() {}
	

    public Department(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    @Access(AccessType.PROPERTY)
    public String getCode(){
    	return this.code;
    }
    public void setCode(String code){
    	this.code = code;
    }
    
    @Access(AccessType.PROPERTY)
    public String getName(){
    	return this.name;
    }
    public void setName(String name){
    	this.name = name;
    }
}
