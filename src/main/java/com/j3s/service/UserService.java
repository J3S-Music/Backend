package com.j3s.service;

import com.j3s.model.User;
import com.j3s.exception.*;
import com.j3s.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public User addUser(User user){
        return userRepo.save(user);
    }

    public void deleteUser(Integer userID){
        userRepo.deleteById(userID);
    }

    public User getUserByID(Integer userID){
        if (userRepo.findById(userID).isPresent()){
            return userRepo.findById(userID).get();
        }
        throw new ResourceNotFoundException("User not foud: "+userID);
    }

/*
    public User updateUser(Integer userID, Optional<String> name, Optional<String> avatar, Optional<String> email, Optional<Boolean> enabled) {
        Optional<User> u = userRepo.findById(userID);
        if(u.isPresent()){
            User user = u.get();
            if(name.isPresent()){
                user.setName(name.get());
            }
            if(avatar.isPresent()){
                user.setAvatar(avatar.get());
            }
            if(email.isPresent()){
                user.setAvatar(email.get());
            }
            if(enabled.isPresent()){
                user.setEnabled(enabled.get());
            }
            return userRepo.save(user);
        }
        throw new ResourceNotFoundException("User not foud: "+userID);
    }

*/
}
