package esc.project.services;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.repositories.UsersMetaRepository;
import esc.project.repositories.UsersMetaRepositoryCustom;

public class UsersMetaRepositoryImpl implements UsersMetaRepositoryCustom {

	@Autowired 
	UsersMetaRepository usersMetaRepo;
}
