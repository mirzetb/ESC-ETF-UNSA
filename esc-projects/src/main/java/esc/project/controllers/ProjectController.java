package esc.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.services.ProjectRepositoryImpl;

@RestController
public class ProjectController {

	@Autowired
	ProjectRepositoryImpl projectRepo;
	
	@RequestMapping("/assignStatusToProject")
	public String assignStatusToProject(@RequestParam("projID") Integer projID, @RequestParam("statID") Integer statID)
	{
		return projectRepo.assignStatusToProject(statID, projID);
	}
	
	@RequestMapping("/setDecisionDate")
	public void setDecisionDate(@RequestParam("projID") Integer projID)
	{
		projectRepo.setDecisionDate(projID);
	}
	
	@RequestMapping(value = "/setDecisionComment", method=RequestMethod.POST)
	public String setDecisionComment(@RequestParam("projID") Integer projID, @RequestParam("comment") String comment)
	{
		return projectRepo.setDecisionComment(projID, comment);
	}
	
	@RequestMapping(value ="/assignMemberToProject")
	public String assignMemberToProject(@RequestParam("userID") Integer userID, @RequestParam("projID") Integer projID)
	{
		return projectRepo.addTeamMemberToProject(projID, userID);
	}
	
	@RequestMapping(value="/listOfTeamMembers")
	public List<String> getMembersOfProjectByName(@RequestParam("projID") Integer projID)
	{
		return projectRepo.getTMsOnProject(projID);
	}
	
	
}
