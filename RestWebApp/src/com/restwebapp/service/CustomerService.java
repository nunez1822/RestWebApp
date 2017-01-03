package com.restwebapp.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.restwebapp.entity.Customer;

@LocalBean
@Stateless
public class CustomerService {

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAll() {
		return em.createNamedQuery("Customer.findAll").getResultList();
	}
}
