package com.example.livreservice.controllers;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstanceController {

    Environment env;

    public InstanceController(Environment env) {
        this.env = env;
    }

    @GetMapping("livres/instance")
    public String insinance(){
        return "instance " + env.getProperty("local.server.port");
    }
}
