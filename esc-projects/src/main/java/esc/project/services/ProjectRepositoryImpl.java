package esc.project.services;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
	
	

	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	
	@Override
	public List<Project> getAll() {
		return (List<Project>) projectRepo.findAll();
		
	}
	
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
	public Timestamp setDecisionDate(int projID) {
		Project proj = projectRepo.findOne(projID);
		if(proj != null)
		{
			Timestamp t = new Timestamp(System.currentTimeMillis());
			proj.setDecisionDate(t);
			projectRepo.save(proj);
			return t;
		}		
		return null;
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
	
	public UsersMeta testna(int userId)
	{
		RestTemplate rm = restTemplate();
		 UsersMeta um = rm.getForObject("http://encore:8070/getById?id={id}", UsersMeta.class, userId);

		 
		 return um;
		
	}
	
	public Project getProjById(int id)
	{
		return projectRepo.findById(id);
	}


	@Override
	public void suggestProject(String username, String title, String purpose, String shortDescription, String teamSize) {
		
		Project p = new Project();
		
		
		Status s = statusRepo.findByCode("3");
		UsersMeta u = userRepo.findByusername(username);
		p.setCreationDate(new Timestamp(System.currentTimeMillis()));
		p.setDecisionComment(null);
		p.setDecisionDate(null);
		p.setPurpose(purpose);
		p.setShortDescription(shortDescription);
		p.setTitle(title);
		p.setTeamSize(Integer.parseInt(teamSize));
		p.setStatus(s);
		p.setTeamLead(u);
		
		projectRepo.save(p);
	}

	

	
	

	
	
	

	
	
	

}
