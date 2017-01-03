package com.restwebapp.security;

import java.security.Key;
import java.util.Base64;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restwebapp.service.JSONTokenMachine;
import com.restwebapp.service.UserpassService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Path("authentication")
@CORS
public class AuthenticationEndpoint {
	
	@EJB
	UserpassService us;
	
	@EJB
	JSONTokenMachine jtm;

    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username, 
            @FormParam("password") String password) {
    	
    	
        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(toJSON(token)).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    	 Boolean valid = us.authenticateUser(username, password);
    	 if(valid != true) {
    		 throw new Exception();
    	 }
    	 
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
    	
		// We need a signing key, so we'll create one just for this example. Usually
		// the key would be read from your application configuration instead.
		Key key = MacProvider.generateKey();
		
		String compactJws = Jwts.builder()
				  .setSubject(username)
				  .signWith(SignatureAlgorithm.HS512, key)
				  .compact();
		
		//Turn key to a string in order to store it in the database
		String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
    	
    	jtm.saveTokenKey(username, compactJws, encodedKey);
    	return compactJws;
    }
    
    public static String toJSON(Object object) 
    { 
        if ( object == null ){
        return "{}"; 
        } 
        try { 
           ObjectMapper mapper = new ObjectMapper(); 
           return mapper.writeValueAsString(object); 
           } 
        catch (Exception e) { 
         e.printStackTrace(); 
        } 
      return "{}"; 
    }
}