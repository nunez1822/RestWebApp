package com.restwebapp.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.restwebapp.entity.Inventory;

@LocalBean
@Stateless
public class InventoryService {

	@PersistenceContext
	EntityManager em;
	
	public Integer getAll() {
		StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("film_in_stock");
		query.setParameter("p_film_id", 5);
		query.setParameter("p_store_id", 1);
		query.execute();
		Integer filmCount = (Integer) query.getOutputParameterValue("p_film_count");
		
		return filmCount;
	}
}
