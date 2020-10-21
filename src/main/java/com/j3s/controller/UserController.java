package com.j3s.controller;

import com.j3s.model.User;
import com.j3s.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins ="*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/users")
    public List<User> getUser(){
        return (List<User>) userRepo.findAll();
    }
    @PostMapping("/users")
    public Integer addUser(@RequestBody User user){
        return 1;
    }

    @GetMapping("/test")
    public List<User> helloWorld(){
        return getUser();
    }

}
