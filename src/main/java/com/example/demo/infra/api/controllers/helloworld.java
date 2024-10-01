package com.example.demo.infra.api.controllers;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
@Tag(name = "USER")
public class helloworld {

    @GetMapping
    public ResponseEntity<ResponseData> helloWorld(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData("andlre", "luis"));
    }
}
