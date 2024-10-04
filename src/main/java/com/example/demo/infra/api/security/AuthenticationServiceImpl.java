package com.example.demo.infra.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.application.security.AuthenticationService;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${spring.security.jwt-secret}")
    private String jwtSecret;

    @Override
    public void validateToken(String token) {
        System.out.println(token);
        JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(token);
    }

    @Override
    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User user) {
            return user;
        }
        return new User("", "", "");
    }

}
