package com.example.demo.application.security;

import com.example.demo.domain.User;

public interface AuthenticationService {

    void validateToken(String token);

    User getLoggedUser();
}
