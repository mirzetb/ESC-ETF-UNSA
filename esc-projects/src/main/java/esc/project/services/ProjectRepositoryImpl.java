package esc.project.services;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import esc.project.models.Project;
import esc.project.models.Status;
import esc.project.models.TeamMember;
import esc.project.models.UsersMeta;
import esc.project.repositories.ProjectRepository;
import esc.project.repositories.ProjectRepositoryCustom;
import esc.project.repositories.StatusRepository;
import esc.project.repositories.TeamMemberRepository;
import esc.project.repositories.UsersMetaRepository;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom{

	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	StatusRepository statusRepo;
	
	@Autowired
	UsersMetaRepository userRepo;
	
	@Autowired
	TeamMemberRepository teamMemRepo;

	@Override
	public String assignStatusToProject(int statID, int projID) {
		
		Project proj = projectRepo.findOne(projID);
		Status stat = statusRepo.findOne(statID);
		if(proj != null && stat != null)
		{
			proj.setStatus(stat);
			projectRepo.save(proj);
			return "Dodijeljeni status projektu " + proj.getTitle() + " je " + stat.getText() + ".";
		}
		
		return "Nepostojeći status sa ID: " +  statID + " ili projekat sa ID: " + projID + "!";
	}

	@Override
	public void setDecisionDate(int projID) {
		Project proj = projectRepo.findOne(projID);
		if(proj != null)
		{
			Timestamp t = new Timestamp(System.currentTimeMillis());
			proj.setDecisionDate(t);
			projectRepo.save(proj);
		}		
	}

	@Override
	public String setDecisionComment(int projID, String comment) {
		Project proj = projectRepo.findOne(projID);
		if(null != proj)
		{
			proj.setDecisionComment(comment);
			projectRepo.save(proj);
			return "Uspješno ste dodali komentar odluke!";
		}
		return "Nepostojeći projekat sa ID: " + projID;
	}

	@Override
	public String addTeamMemberToProject(int projID, int userID) {
		
		Project proj = projectRepo.findOne(projID);
		UsersMeta user = userRepo.findOne(userID);
		if(null != user && null != proj)
		{
			List<TeamMember> TMsOnProj = teamMemRepo.getTMsOnProj(projID);
			for(TeamMember tm : TMsOnProj)
			{
				if(tm.getUser().getId() == userID)
					return tm.getUser().getFirstName() + " " + tm.getUser().getLastName() + " je već član tima na ovom projektu.";
			}
			if(teamMemRepo.getNoOfTmsOnProj(projID) >= proj.getTeamSize())
			{			
				return "Kapaciteti članova tima za odabrani projekat su popunjeni!";
			}			
			TeamMember tm = new TeamMember(proj, user, new Timestamp(System.currentTimeMillis()));
			teamMemRepo.save(tm);
			
		}
		else 
			return "Nepostojeći član ili projekat.";
		
		return "Uspješno ste dodali člana tima na projekat ' " + proj.getTitle() + "'.";
	}

	@Override
	public List<String> getTMsOnProject(int projID) {
		List<String> teamMemberNames = new ArrayList<String>();
		List<TeamMember> TMsOnProj = teamMemRepo.getTMsOnProj(projID);
		for(TeamMember tm: TMsOnProj)
		{
			teamMemberNames.add(tm.getUser().getFirstName() + " " + tm.getUser().getLastName());
		}
		return teamMemberNames;
	}

	
	

	
	
	

	
	
	

}
