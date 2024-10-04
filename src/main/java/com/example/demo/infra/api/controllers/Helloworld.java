package com.example.demo.infra.api.controllers;


import com.example.demo.application.security.AuthenticationService;
import com.example.demo.domain.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
@Tag(name = "USER")
public class Helloworld {
    private static final Logger log = LoggerFactory.getLogger(Helloworld.class);
    private final AuthenticationService authenticationService;

    public Helloworld(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public ResponseEntity<ResponseData> helloWorld() {
        User user = authenticationService.getLoggedUser();
        log.info(user.name());


        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData("andlre", "luis"));
    }
}
