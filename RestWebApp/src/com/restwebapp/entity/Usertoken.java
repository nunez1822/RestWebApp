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
 * The persistent class for the usertoken database table.
 * 
 */
@Entity
@NamedQuery(name="Usertoken.findAll", query="SELECT u FROM Usertoken u")
@NamedStoredProcedureQuery(
		name = "getKey", 
		procedureName = "getKey", 
		parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "in_token"), 
			@StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "out_key")
		}
	)
public class Usertoken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String key;

	private String token;

	private String username;

	public Usertoken() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}