package esc.project.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String title;
	private String shortDescription;
	private String purpose;
	
	private Timestamp creationDate;
	
	@Column(nullable = true)
	private Timestamp decisionDate;
	@Column(nullable = true)	
	private String decisionComment;
	
	@ManyToOne
	private Status status;
	
	private int teamSize;
	
	@ManyToOne
	private UsersMeta teamLead;
	
	
	public Project()
	{
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getShortDescription() {
		return shortDescription;
	}


	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}


	public String getPurpose() {
		return purpose;
	}


	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}


	public Timestamp getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}


	public Timestamp getDecisionDate() {
		return decisionDate;
	}


	public void setDecisionDate(Timestamp decisionDate) {
		this.decisionDate = decisionDate;
	}


	public String getDecisionComment() {
		return decisionComment;
	}


	public void setDecisionComment(String decisionComment) {
		this.decisionComment = decisionComment;
	}


	public int getTeamSize() {
		return teamSize;
	}


	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public UsersMeta getTeamLead() {
		return teamLead;
	}


	public void setTeamLead(UsersMeta teamLead) {
		this.teamLead = teamLead;
	}
	

}
