package esc.project.services;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.models.UsersMeta;
import esc.project.repositories.UsersMetaRepository;
import esc.project.repositories.UsersMetaRepositoryCustom;

public class UsersMetaRepositoryImpl implements UsersMetaRepositoryCustom {

	@Autowired 
	UsersMetaRepository usersMetaRepo;

	@Override
	public void saveUser(UsersMeta um) {
		usersMetaRepo.save(um);
		
	}

	
	
	
}
