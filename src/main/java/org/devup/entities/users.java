package org.devup.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="users")
public class users implements Serializable{
	@Id
	private String login;
	private String pass;
	private boolean active;
	
	@ManyToMany
	@JoinTable(name="users_roles")
	private Collection<roles> roles;

	public users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public users(String login, String pass, boolean active) {
		super();
		this.login = login;
		this.pass = pass;
		this.active = active;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Collection<roles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<roles> roles) {
		this.roles = roles;
	}
	
 
}
