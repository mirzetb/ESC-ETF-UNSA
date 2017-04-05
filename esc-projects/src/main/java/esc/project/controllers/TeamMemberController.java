package esc.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import esc.project.models.TeamMember;
import esc.project.repositories.TeamMemberRepository;
import esc.project.services.TeamMemberRepositoryImpl;

@RestController
public class TeamMemberController {

	@Autowired
	TeamMemberRepositoryImpl teamMemRepo;
	@Autowired
	TeamMemberRepository tmRep;
	
	@RequestMapping(value="/membersOnProject", method=RequestMethod.GET)
	public List<TeamMember> getTeamMembersOnProject(@RequestParam("projID") Integer projID)
	{
		return tmRep.getTMsOnProj(projID);
	}
	
	@RequestMapping(value="/memberCount", method=RequestMethod.GET)
	public int getNumberOfTMs(@RequestParam("projID") Integer projID)
	{
		return tmRep.getNoOfTmsOnProj(projID);
	}
	
	@RequestMapping(value="findMemberOnProject", method=RequestMethod.GET)
	public TeamMember findTeamMember(@RequestParam("projID") Integer projID, @RequestParam("userID") Integer userID)
	{
		return tmRep.findTMByProjID(projID, userID);
	}
}
