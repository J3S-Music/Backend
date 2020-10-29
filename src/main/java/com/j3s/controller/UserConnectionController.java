package com.j3s.controller;


import com.j3s.model.Connection;
import com.j3s.model.Room;
import com.j3s.model.User;
import com.j3s.model.UserConnection;
import com.j3s.service.UserConnectionService;
import com.j3s.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*", allowedHeaders = "*")
@RestController
public class UserConnectionController {

    @Autowired
    UserConnectionService userConnectionService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/connections",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<UserConnection> getAllConnections(){
        return userConnectionService.getAllUserConnections();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/users/{id}/connections",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<UserConnection> getConnections(@PathVariable Long id){
        return userConnectionService.getUserConnections(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/users/{UserID}/connections/{ConnectionID}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public UserConnection getConnection(@PathVariable Long UserID, @PathVariable Long ConnectionID){
        return userConnectionService.getConnection(UserID, ConnectionID);
    }



    @RequestMapping(
            method = RequestMethod.PUT,
            path = "/users/{UserID}/connections/{ConnectionID}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void editUserConnections(@RequestBody UserConnection userConnection, @PathVariable Long UserID, @PathVariable Long ConnectionID){
         userConnectionService.editConnectionByUserUserIDAndConnectionID(UserID, ConnectionID, userConnection);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/users/{UserID}/connections/{ConnectionID}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addUserConnection(@RequestBody UserConnection userConnection, @PathVariable Long UserID, @PathVariable Long ConnectionID){
        userConnectionService.addUserConnection(userConnection, UserID, ConnectionID);
    }





}
