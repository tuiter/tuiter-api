package org.corrige.ai.controllers;

import javax.servlet.ServletException;

import org.corrige.ai.models.auth.AuthenticationResponse;
import org.corrige.ai.models.auth.LoginBean;
import org.corrige.ai.models.user.User;
import org.corrige.ai.services.implementations.AuthenticationService;
import org.corrige.ai.util.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(ServerConstants.SERVER_REQUEST +
				ServerConstants.AUTHENTICATION_REQUEST)
public class LoginController {
	@Autowired
    private AuthenticationService authenticationService;
	
	@PostMapping(value = "/login")
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
