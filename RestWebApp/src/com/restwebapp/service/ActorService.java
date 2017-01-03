package com.restwebapp.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.restwebapp.entity.Actor;

@LocalBean
@Stateless
public class ActorService {

	@PersistenceContext
	EntityManager em;
	
	public void saveActor(Actor actor) {
		em.merge(actor);
	}
	
	public void deleteActor(Actor actor) {
		em.remove(actor);
	}
	
	@SuppressWarnings("unchecked")
	public List<Actor> getAll() {
		Query q = em.createQuery("select firstName from Actor firstName");
		return (List<Actor>)q.getResultList();
	}
}
