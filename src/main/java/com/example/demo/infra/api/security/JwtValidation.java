package com.example.demo.infra.api.security;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JwtValidation {
    public void validate(String token) throws Exception {
        if (!Objects.equals(token, "1234")) throw new Exception("Erro Token");
    }
}
