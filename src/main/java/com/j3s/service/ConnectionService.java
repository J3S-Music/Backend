package com.j3s.service;

import com.j3s.exception.ResourceNotFoundException;
import com.j3s.model.Connection;
import com.j3s.model.User;
import com.j3s.model.UserConnection;
import com.j3s.repository.ConnectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {
    @Autowired
    private ConnectionRepo connectionRepo;

    public Connection getConnectionByID(Long connectionID) {
        if (connectionRepo.findById(connectionID).isPresent()){
            return connectionRepo.findById(connectionID).get();
        }
        else{throw new ResourceNotFoundException("Connection not found: "+connectionID);}
    }


}
