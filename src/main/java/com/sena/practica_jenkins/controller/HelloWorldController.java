package com.sena.practica_jenkins.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/v1/hello/")
public class HelloWorldController {

    @GetMapping("")
    public String getMethodName() {
   
        return "Hola mundo";
    }
}
