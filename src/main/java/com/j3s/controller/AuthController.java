package com.j3s.controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ="*", allowedHeaders = "*")
@RestController
public class AuthController {

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World";
    }
    @GetMapping("/restricted")
    public String getSubject(){
        return("You're logged in");
    }

}

