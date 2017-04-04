package esc.project.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class TeamMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Project project;
	
	@ManyToOne
	private UsersMeta user;
	
	private Timestamp date;
	
	
	public TeamMember(Project p, UsersMeta u, Timestamp d)
	{
		this.project = p;
		this.user = u;
		this.date = d;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	
	public UsersMeta getUser() {
		return user;
	}

	public void setUser(UsersMeta user) {
		this.user = user;
	}

	public TeamMember()
	{
		
	}
}
