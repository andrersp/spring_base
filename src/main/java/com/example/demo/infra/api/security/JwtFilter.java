package com.example.demo.infra.api.security;

import com.example.demo.infra.api.controllers.ResponseData;
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
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Iniciando");
        String token;
        var authorizationHeader = request.getHeader("Authorization");
        logger.info(authorizationHeader);

        var authentication = new UsernamePasswordAuthenticationToken(new ResponseData("andre", "luis"), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("Finalizadno");
        filterChain.doFilter(request, response);


    }
}
