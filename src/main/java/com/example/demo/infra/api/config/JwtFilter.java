package com.example.demo.infra.api.config;

import com.example.demo.application.security.AuthenticationService;
import com.example.demo.domain.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final AuthenticationService jwtValidation;

    public JwtFilter(AuthenticationService jwtValidation) {
        this.jwtValidation = jwtValidation;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token;
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            token = authorizationHeader.replace("Bearer ", "").trim();
            this.jwtValidation.validateToken(token);

            User responseData = new User("Andre Luis", "Franca", token);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(responseData, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
