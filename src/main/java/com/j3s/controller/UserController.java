package com.j3s.controller;

import com.j3s.model.User;
import com.j3s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins ="*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<User> getUser(){
        return userService.getAllUser();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/users",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public User saveUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/users/{id}"
    )
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/users/{id}"
    )
    public User getUserByID(@PathVariable Integer id){
        return userService.getUserByID(id);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public User updateUser(@RequestBody User user, @PathVariable Integer id){
        return userService.updateUser(id, user);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public User authenticate(@RequestBody User user){
        String email = user.getEmail();
        String password = user.getPassword();
        return userService.login(email,password);
    }

}