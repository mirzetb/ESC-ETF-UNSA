package esc.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.repositories.TeamMemberRepository;
import esc.project.repositories.TeamMemberRepositoryCustom;

public class TeamMemberRepositoryImpl implements TeamMemberRepositoryCustom {
	
	@Autowired
	TeamMemberRepository teamMemRepo;

	

}
