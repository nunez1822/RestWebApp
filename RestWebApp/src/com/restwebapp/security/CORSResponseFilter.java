package com.restwebapp.security;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@CORS
@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

//		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Origin", "http://localhost:3001"); 	
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, JSONP");			
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		headers.add("Access-Control-Max-Age", "1209600");
		
	}

}
