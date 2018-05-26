package org.tuiter.services.implementations;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tuiter.beans.LoginBean;
import org.tuiter.errors.exceptions.FailedAuthenticationException;
import org.tuiter.errors.exceptions.UserNotFoundException;
import org.tuiter.models.User;
import org.tuiter.services.interfaces.UserService;
import org.tuiter.util.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class AuthenticationService {
    private static final String ISSUER = "random";
    
    @Autowired
    private UserService userService;

    public User authenticate(LoginBean loginBean) {
        User user = userService.findByIdentifier(loginBean.getIdentifier());

        if (user != null && user.authenticate(loginBean.getPassword())) {
            return user;
        }

        throw new FailedAuthenticationException();
    }

    public String tokenFor(User user) throws IOException, URISyntaxException {

        Date expiration = Date.from(LocalDateTime.now().plusHours(24 * 7).toInstant(ZoneOffset.UTC));
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
    }

    public User getUserFromToken(String token) throws IOException, URISyntaxException, UserNotFoundException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token);
        User user = userService.findByUsername(claims.getBody().getSubject().toString());

        if (user != null)
            return user;
        else
            throw new UserNotFoundException();
    }

}
