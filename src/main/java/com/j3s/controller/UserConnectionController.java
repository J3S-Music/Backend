package com.j3s.controller;


import com.j3s.model.Room;
import com.j3s.model.User;
import com.j3s.model.UserConnection;
import com.j3s.service.UserConnectionService;
import com.j3s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins ="*", allowedHeaders = "*")
@RestController
public class UserConnectionController {

    @Autowired
    UserConnectionService userConnectionService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/userconnections",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<UserConnection> getRooms(){
        return userConnectionService.getAllUserConnections();
    }

}
