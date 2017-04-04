package esc.project.services;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.repositories.RequiredResourceRepository;
import esc.project.repositories.RequiredResourceRepositoryCustom;



public class RequiredResourceRepositoryImpl implements RequiredResourceRepositoryCustom{
	
	@Autowired
	RequiredResourceRepository reqResourceRepo;
}
