package com.yourmoney.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourmoney.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final Environment environment;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, Environment environment) {
        this.authenticationManager = authenticationManager;
        this.environment = environment;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            User user = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {

        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();

        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_IN_MILLIS))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("BEARER_TOKEN_SECRET_KEY")).compact();

        String bearerToken = SecurityConstants.BEARER_TOKEN_PREFIX + token;

        response.setContentType("application/json");
        response.addHeader(SecurityConstants.AUTHORIZATION_HEADER, bearerToken);

        var body = new HashMap<String, String>();
        body.put("expires_in", String.valueOf(SecurityConstants.TOKEN_EXPIRATION_IN_MILLIS/1000));
        body.put("token", bearerToken);

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    }
}