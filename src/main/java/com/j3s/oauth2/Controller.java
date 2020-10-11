package com.j3s.oauth2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }
    @GetMapping("/restricted")
    public String restricted(){
        return("You're logged in");
    }
}
