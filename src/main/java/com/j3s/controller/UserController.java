package com.j3s.controller;

import com.j3s.model.Connection;
import com.j3s.model.User;
import com.j3s.model.UserConnection;
import com.j3s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public Long saveUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/users/{id}"
    )
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User getUserByID(@PathVariable Long id){
        return userService.getUserByID(id);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        return userService.updateUser(id, user);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Long authenticate(@RequestBody User user){
        String email = user.getEmail();
        String password = user.getPassword();
        return userService.login(email,password);
    }






}
