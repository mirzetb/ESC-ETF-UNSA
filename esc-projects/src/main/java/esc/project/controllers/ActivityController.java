package esc.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.services.ActivityRepositoryImpl;

@RestController
public class ActivityController {

	@Autowired
	ActivityRepositoryImpl activityRepo;
}
