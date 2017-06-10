package esc.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.models.Project;
import esc.project.models.RequiredResource;
import esc.project.repositories.RequiredResourceRepository;
import esc.project.repositories.RequiredResourceRepositoryCustom;



public class RequiredResourceRepositoryImpl implements RequiredResourceRepositoryCustom{
	
	@Autowired
	RequiredResourceRepository reqResourceRepo;

	@Override
	public List<RequiredResource> getAllRRs(Project proj) {
		return reqResourceRepo.getRRsOnProject(proj);
	}
}
