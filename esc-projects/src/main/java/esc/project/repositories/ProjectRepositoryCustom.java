package esc.project.repositories;

import java.sql.Timestamp;
import java.util.List;

import esc.project.models.Project;
import esc.project.models.Task;
import esc.project.models.TeamMember;

public interface ProjectRepositoryCustom {
	
	public List<Project> getAll();
	
	public String assignStatusToProject(int statID, int projID);
	
	public Timestamp setDecisionDate(int projID);
	
	public String setDecisionComment(int projID, String comment);
	
	public String addTeamMemberToProject(int projID, int userID);
	
	public List<String> getTMsOnProject(int projID);
	
	public void suggestProject(String username, String title, String purpose, String shortDescription, String teamSize);
	
	 
	
	
}
