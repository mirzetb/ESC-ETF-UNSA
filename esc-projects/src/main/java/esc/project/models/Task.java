package esc.project.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String description;
	
	private Timestamp dueDate;
	
	@ManyToOne	
	private TeamMember teamMemeber;
	
	public Task()
	{
		
	}
	
	public Task(String desc, Timestamp dd, TeamMember tm)
	{
		this.description = desc;
		this.dueDate = dd;
		this.teamMemeber = tm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public TeamMember getTeamMemeber() {
		return teamMemeber;
	}

	public void setTeamMemeber(TeamMember teamMemeber) {
		this.teamMemeber = teamMemeber;
	}
	
}
