package com.j3s.service;

import com.j3s.model.Room;
import com.j3s.model.UserConnection;
import com.j3s.repository.UserConnectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserConnectionService {

    @Autowired
    UserConnectionRepo userConnectionRepo;

    public List<UserConnection> getAllUserConnections(){
        List<UserConnection> userConnections = new ArrayList<UserConnection>();
        Iterable<UserConnection> allConnections= userConnectionRepo.findAll();

        allConnections.forEach(userConnections::add);
        return userConnections;
    }
}
