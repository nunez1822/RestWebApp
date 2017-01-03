package com.restwebapp.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@LocalBean
@Stateless
public class UserpassService {

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Boolean authenticateUser(String username, String password) {
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("authenticateUser");
		query.setParameter("in_username", username);
		query.setParameter("in_password", password);
		query.execute();
		Boolean valid = (Boolean) query.getOutputParameterValue("out_valid");
		return valid;
	}
}
