package esc.project.controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;

import esc.project.models.Project;
import esc.project.models.UsersMeta;
import esc.project.services.ProjectRepositoryImpl;
import io.jsonwebtoken.Jwts;

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
	public Timestamp setDecisionDate(@RequestParam("projID") Integer projID)
	{
		return projectRepo.setDecisionDate(projID);
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
	
	@RequestMapping(value="/getOneUser")
	public UsersMeta test(@RequestParam("userID") Integer userID)
	{
		return projectRepo.testna(userID);
	}
	
	@RequestMapping(value="/getAllProjs", method = RequestMethod.GET)
	public List<Project> getAllProjs()
	{
		return projectRepo.getAll();
	}
	
	@RequestMapping(value="/getProjectByID", method=RequestMethod.GET, produces = "application/json")
	public Project getProjById(@RequestParam("id") String id)
	{
		return projectRepo.getProjById(Integer.parseInt(id));
	}
	
	@RequestMapping(value="/getLoggedUsername", method=RequestMethod.GET)
	public String getUsername(HttpServletRequest req)
	{
		Cookie[] cookies = req.getCookies();
		String token = "";
		String username = "";
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals("Auth"))
				{
					token = c.getValue();
					break;
				}
			}
		}
		
		if(token != null && token.length() > 0)
		{
			username = Jwts.parser()
					  .setSigningKey("ThisIsASecret")
					  .parseClaimsJws(token.replace("Bearer", ""))
			          .getBody()         
			          .getSubject();
			
			
		}
		
		return username;
	}
	
	@RequestMapping(value="/suggestProject", method = RequestMethod.POST)
	public void suggestProj(@RequestParam("username") String username, @RequestParam("title") String title, @RequestParam("purpose") String purpose, @RequestParam("shortDescription") String shortDescription, @RequestParam("teamSize") String teamSize)
	{
		projectRepo.suggestProject(username, title, purpose, shortDescription, teamSize);
	}
	
	
}
