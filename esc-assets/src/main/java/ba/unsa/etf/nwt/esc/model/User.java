package ba.unsa.etf.nwt.esc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {
	@Id
	Long id;
	String firstName;
	String lastName;
	String escId;
	@ManyToOne
	Role role;
	
	protected User() {
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEscId() {
		return escId;
	}
	public void setEscId(String escId) {
		this.escId = escId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Long getId() {
		return id;
	}
}
