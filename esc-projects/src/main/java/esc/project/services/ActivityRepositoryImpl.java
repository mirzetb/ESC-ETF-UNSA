package esc.project.services;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.repositories.ActivityRepository;
import esc.project.repositories.ActivityRepositoryCustom;

public class ActivityRepositoryImpl implements ActivityRepositoryCustom {

	@Autowired
	ActivityRepository activityRepo;
	
	
}
