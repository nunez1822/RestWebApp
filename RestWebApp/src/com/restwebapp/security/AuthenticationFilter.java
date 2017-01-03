package com.restwebapp.security;

import java.io.IOException;
import java.util.Base64;

import javax.annotation.Priority;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.restwebapp.service.JSONTokenMachine;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	
	@EJB
	JSONTokenMachine jtm;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader = 
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly 
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {

            // Validate the token
            validateToken(token);

        } catch (Exception e) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void validateToken(String token) throws Exception {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
    	try {
    		String key = jtm.getKey(token);
    		
    		// decode the base64 encoded string
    		byte[] decodedKey = Base64.getDecoder().decode(key);
    		// rebuild key using SecretKeySpec
    		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA512"); 
    		    		
    	    Jwts.parser().setSigningKey(key).parseClaimsJws(token);

    	    //OK, we can trust this JWT

    	} catch (SignatureException e) {

    	    //don't trust the JWT!
    	}
    }
}
