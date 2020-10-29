package com.j3s.service;

import com.j3s.exception.DuplicateDataException;
import com.j3s.exception.ResourceNotFoundException;
import com.j3s.model.Connection;
import com.j3s.model.User;
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

    public List<UserConnection> getUserConnections(Long UserID){
        return userConnectionRepo.findUserConnectionsByUserID(UserID);
    }

    public UserConnection getConnection(Long userID, Long connectionID){
        System.out.println(userConnectionRepo.findUserConnectionByUserIDAndConnectionID(userID,connectionID));
        return userConnectionRepo.findUserConnectionByUserIDAndConnectionID(userID,connectionID);
    }


    public void editConnectionByUserUserIDAndConnectionID(Long UserID, Long ConnectionID, UserConnection userConnection) {
        UserConnection userConnectionOld = getConnection(UserID, ConnectionID);
        String key;
        Boolean _default;
        Boolean active;

        System.out.println(userConnectionOld);
        if (userConnectionOld!=null){

            if(userConnection.get_default()==null) {_default=userConnectionOld.get_default();}else{_default=userConnection.get_default();}
            if(userConnection.get_default()==null) {key=userConnectionOld.getKey();}else{key=userConnection.getKey();}
            if(userConnection.get_default()==null) {active=userConnectionOld.getActive();}else{active=userConnection.getActive();}

        }
        else{throw new ResourceNotFoundException("UserConnection not found");}
        System.out.println(UserID+", "+ConnectionID+", "+key+", "+_default+", "+active);
        userConnectionRepo.updateUserConnection(UserID,ConnectionID,key,_default,active);
    }


    public void addUserConnection(UserConnection userConnection, Long UserID, Long ConnectionID){
        String key = userConnection.getKey();
        Boolean _default = userConnection.get_default();
        Boolean active = userConnection.getActive();
        userConnectionRepo.createUserConnection(UserID,ConnectionID,key,_default,active);
    }

}
