/***************************************
 * Auteur : Lhoussaine IMOUGAR
 * Date   : 01/04/2017
 * Module :	Entité Role
 **************************************/
package org.devup.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class roles implements  Serializable{
	@Id
	private String role;
	private String description;
	public roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public roles(String role, String description) {
		super();
		this.role = role;
		this.description = description;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
