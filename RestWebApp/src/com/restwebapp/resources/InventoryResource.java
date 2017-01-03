package com.restwebapp.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.restwebapp.entity.Actor;
import com.restwebapp.entity.Customer;
import com.restwebapp.security.CORS;
import com.restwebapp.security.Secured;
import com.restwebapp.service.ActorService;
import com.restwebapp.service.CustomerService;
import com.restwebapp.service.InventoryService;

@Path("inventory")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventoryResource {
	
	@EJB
	ActorService actorService;
	
	@EJB
	CustomerService customerService;
	
	@EJB
	InventoryService inventoryService;
	
    @Context
    SecurityContext securityContext;
    
	
	@GET
	@CORS
	@Secured
	@Path("actor")
	public List<Actor> getAllUsers() {
		return actorService.getAll();
	}
	
	@GET
	@CORS
	@Secured
	@Path("customer")
	public List<Customer> getAllCustomers() {
		return customerService.getAll();
	}
	
	@GET
	@Path("inventory")
	public Integer getAllInventory() {
		return inventoryService.getAll();
	}
}
