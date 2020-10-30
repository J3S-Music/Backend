package com.j3s.service;

import com.j3s.model.Connection;
import com.j3s.model.User;
import com.j3s.exception.*;
import com.j3s.model.UserConnection;
import com.j3s.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<User>();

        Iterable<User> allUser= userRepo.findAll();
        allUser.forEach(userList::add);

        return userList;
    }

    public Long addUser(User user) {
        if(getUserByEmail(user.getEmail())==null) {
            User u = userRepo.save(user);
            return u.getUserID();
        }
        else{throw new DuplicateDataException("User already exists: "+user.getEmail());}
    }

    public void deleteUser(Long userID){
        if (userRepo.findById(userID).isPresent()){
            userRepo.deleteById(userID);
        }
        else{throw new ResourceNotFoundException("User not found: "+userID);}
    }

    public User getUserByID(Long userID){
        if (userRepo.findById(userID).isPresent()){
            return userRepo.findById(userID).get();
        }
        else{throw new ResourceNotFoundException("User not found: "+userID);}
    }

    public User getUserByEmail(String email){
        if(userRepo.findByEmail(email)==null){
            return null;
        }
        return userRepo.findByEmail(email);
    }
    
    public User updateUser(Long userID, User user){
        if(userRepo.findById(userID).isPresent()){
            User oldUser = userRepo.findById(userID).get();
            if(user.getAvatar()==null){user.setAvatar(oldUser.getAvatar());}
            if(user.getEmail()==null){user.setEmail(oldUser.getEmail());}
            else if(getUserByEmail(user.getEmail())!=null && !user.getEmail().equals(oldUser.getEmail())){
                throw new DuplicateDataException("User already exists: "+user.getEmail());
            }
            if(user.getName()==null){user.setName(oldUser.getName());}
            if(user.getPassword()==null){user.setPassword(oldUser.getPassword());}
            if(user.getRoom()==null){user.setRoom(oldUser.getRoom());}
            if(user.getRole()==null){user.setRole(oldUser.getRole());}


        }
        else{throw new ResourceNotFoundException("User not found: "+userID);}
        user.setUserID(userID);
        return userRepo.save(user);
    }

    public Long login(String email, String password){
        User user = getUserByEmail(email);
        if(user==null){throw new ResourceNotFoundException("User not found: "+email);}
        if(password.equals(user.getPassword())){
            return user.getUserID();
        }else{
            throw new AuthenticationFailedException("Wrong Password");
        }
    }

}
