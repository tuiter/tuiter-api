package org.corrige.ai.controllers;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.corrige.ai.beans.AuthenticationResponse;
import org.corrige.ai.beans.LoginBean;
import org.corrige.ai.models.User;
import org.corrige.ai.services.implementations.AuthenticationService;
import org.corrige.ai.services.implementations.UserServiceImpl;
import org.corrige.ai.services.interfaces.UserService;
import org.corrige.ai.util.SecurityConstants;
import org.corrige.ai.util.ServerConstants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST +
				ServerConstants.AUTHENTICATION_REQUEST)
public class LoginController {
	@Autowired
    private AuthenticationService authenticationService;
	
	@RequestMapping(value = "/login",
					method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE,
					consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginBean requestBody) throws ServletException {
		User user = authenticationService.authenticate(requestBody);
		
		String token;

        try {
            token = authenticationService.tokenFor(user);
            AuthenticationResponse authBean = new AuthenticationResponse(token, user);
            return new ResponseEntity<>(authBean, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}
