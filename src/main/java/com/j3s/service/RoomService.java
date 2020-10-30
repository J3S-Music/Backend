package com.j3s.service;

import com.j3s.exception.AuthenticationFailedException;
import com.j3s.exception.ResourceNotFoundException;
import com.j3s.model.Room;
import com.j3s.model.User;
import com.j3s.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserService userService;

    public List<Room> getAllRooms(){
        List<Room> roomList = new ArrayList<Room>();
        Iterable<Room> allRooms= roomRepo.findAll();

        allRooms.forEach(roomList::add);
        return roomList;
    }
    public Room getRoom(Long roomID){
        if (roomRepo.findById(roomID).isPresent()){
            return roomRepo.findById(roomID).get();
        }
        else{throw new ResourceNotFoundException("Room not found: "+roomID);}
    }


    public Long createRoom(Room room) {
        return roomRepo.save(room).getRoomID();
    }

    public void joinRoom(Long roomID, Long userID, String roomCode){
        if(roomRepo.findById(roomID).isPresent()){
            User u = userService.getUserByID(userID);
            Room r = roomRepo.findById(roomID).get();
            System.out.println("ID übergeben: "+roomID);
            System.out.println("Room get: "+r.getRoomID());
            System.out.println(roomCode+","+r.getRoomCode());
            if(roomCode.equals(r.getRoomCode())) {
                u.setRoom(r);
                userService.updateUser(userID, u);
            }else{
                throw new AuthenticationFailedException("Wrong Password");
            }
        }
        else{throw new ResourceNotFoundException("Room not found: "+roomID);}
    }

}
