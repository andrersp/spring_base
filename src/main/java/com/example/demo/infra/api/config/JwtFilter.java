package com.example.demo.infra.api.config;

import com.example.demo.infra.api.controllers.ResponseData;
import com.example.demo.infra.api.security.JwtValidation;
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
    private final JwtValidation jwtValidation;

    public JwtFilter(JwtValidation jwtValidation) {
        this.jwtValidation = jwtValidation;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token;
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null) {
            token = authorizationHeader.replace("Bearer ", "");
            try {
                this.jwtValidation.validate(token);
                ResponseData responseData = new ResponseData("Andre Luis", "Franca");
                //        var authentication = UsernamePasswordAuthenticationToken.authenticated(new ResponseData("andre", "franca"), "", null);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(responseData, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println(token);

        }
        logger.info(authorizationHeader);


        filterChain.doFilter(request, response);


    }
}
