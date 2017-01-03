package com.restwebapp.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

@LocalBean
@Stateless
public class JSONTokenMachine {

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public String saveTokenKey(String username, String compactJws, String encodedKey) {
				
		Query q = em.createNativeQuery("update Usertoken set token = ?, `key` = ? where username = ?");
			q.setParameter(1, compactJws);
			q.setParameter(2, encodedKey);
			q.setParameter(3, username);
			q.executeUpdate();
			
		return compactJws;
	}
	
	public String getKey(String token) {
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("getKey");
		query.setParameter("in_token", token);
		query.execute();
		String key = (String) query.getOutputParameterValue("out_key");
		return key;
	}
}
