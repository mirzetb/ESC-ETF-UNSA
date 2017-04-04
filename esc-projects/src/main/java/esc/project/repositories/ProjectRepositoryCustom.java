package esc.project.repositories;

import java.util.List;

import esc.project.models.TeamMember;

public interface ProjectRepositoryCustom {
	
	public String assignStatusToProject(int statID, int projID);
	
	public void setDecisionDate(int projID);
	
	public String setDecisionComment(int projID, String comment);
	
	public String addTeamMemberToProject(int projID, int userID);
	
	public List<String> getTMsOnProject(int projID);
	
	
}
