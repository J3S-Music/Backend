package com.j3s.service;

import com.j3s.model.User;
import com.j3s.exception.*;
import com.j3s.repository.UserRepo;
import com.j3s.repository.UserRepoCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepoCRUD userRepoCRUD;
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<User>();

        Iterable<User> allUser= userRepoCRUD.findAll();
        allUser.forEach(userList::add);

        return userList;
    }

    public User addUser(User user){
        if(getUserByEmail(user.getEmail())==null) {
            return userRepoCRUD.save(user);
        }
        else{throw new DuplicateDataException("User already exists: "+user.getEmail());}
    }

    public void deleteUser(Integer userID){
        if (userRepoCRUD.findById(userID).isPresent()){
            userRepoCRUD.deleteById(userID);
        }
        else{throw new ResourceNotFoundException("User not found: "+userID);}
    }

    public User getUserByID(Integer userID){
        if (userRepoCRUD.findById(userID).isPresent()){
            return userRepoCRUD.findById(userID).get();
        }
        else{throw new ResourceNotFoundException("User not found: "+userID);}
    }

    public User getUserByEmail(String email){
        if(userRepo.findByEmail(email)==null){
            return null;
        }
        return userRepo.findByEmail(email);
    }


    public User updateUser(Integer userID, User user){
        if(userRepoCRUD.findById(userID).isPresent()){
            User oldUser = userRepoCRUD.findById(userID).get();
            if(user.getAvatarID()==null){user.setAvatarID(oldUser.getAvatarID());}
            if(user.getEmail()==null){user.setEmail(oldUser.getEmail());}
            else if(getUserByEmail(user.getEmail())!=null){
                throw new DuplicateDataException("User already exists: "+user.getEmail());
            }
            if(user.getName()==null){user.setName(oldUser.getName());}
            if(user.getPassword()==null){user.setPassword(oldUser.getPassword());}
        }
        else{throw new ResourceNotFoundException("User not found: "+userID);}
        user.setUserID(userID);
        return userRepoCRUD.save(user);
    }

    public User login(String email, String password){
        User user = getUserByEmail(email);
        if(user==null){throw new ResourceNotFoundException("User not found: "+email);}
        if(password.equals(user.getPassword())){
            return user;
        }else{
            throw new AuthenticationFailedException("Wrong Password");
        }
    }

}
