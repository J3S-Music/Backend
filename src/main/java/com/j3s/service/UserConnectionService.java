package com.j3s.service;

import com.j3s.exception.ResourceNotFoundException;
import com.j3s.model.Connection;
import com.j3s.model.Room;
import com.j3s.model.User;
import com.j3s.model.UserConnection;
import com.j3s.model.compositeKey.UserConnectionKey;
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

    /*public List<UserConnection> getUserConnection(Long UserID){
        return userConnectionRepo.findByUserConnectionKeyUserID(UserID);
    }*/

    public UserConnection getConnection(Long userID, Long connectionID) {
        UserConnectionKey key = new UserConnectionKey(userID, connectionID);
        if (userConnectionRepo.findById(key).isPresent()){
            return userConnectionRepo.findById(key).get();
        }
        else{throw new ResourceNotFoundException("UserConnection not found: "+userID +", "+connectionID);}

    }


    public UserConnection editConnection(Long userID, Long connectionID, UserConnection userConnection) {
        UserConnectionKey key = new UserConnectionKey(userID, connectionID);
        if (userConnectionRepo.findById(key).isPresent()){
            UserConnection userConnectionOld = userConnectionRepo.findById(key).get();
            if(userConnection.get_default()==null){userConnection.set_default(userConnectionOld.get_default());}
            if(userConnection.getActive()==null){userConnection.setActive(userConnectionOld.getActive());}
            if(userConnection.getKey()==null){userConnection.setKey(userConnectionOld.getKey());}
            userConnection.setId(userConnectionOld.getId());
            userConnection.setUser(userConnectionOld.getUser());
            //userConnection.setConnection(userConnection.getConnection());
        }
        else{throw new ResourceNotFoundException("UserConnection not found: "+userID +", "+connectionID);}
        System.out.println(userConnection);
        return userConnectionRepo.save(userConnection);
    }
    public void addUserConnection(){

    }

}
