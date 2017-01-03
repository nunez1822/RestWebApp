package com.restwebapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;


/**
 * The persistent class for the userpass database table.
 * 
 */
@Entity
@NamedQuery(name="Userpass.findAll", query="SELECT u FROM Userpass u")
@NamedStoredProcedureQuery(
		name = "authenticateUser", 
		procedureName = "authenticateUser", 
		parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "in_username"), 
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "in_password"),
			@StoredProcedureParameter(mode = ParameterMode.OUT, type = Boolean.class, name = "out_valid")
		}
	)
public class Userpass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String password;

	private String username;

	public Userpass() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}