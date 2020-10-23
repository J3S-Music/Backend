package com.j3s.service;

import com.j3s.model.Room;
import com.j3s.model.User;
import com.j3s.repository.RoomRepo;
import com.j3s.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo;

    public List<Room> getAllRooms(){
        List<Room> roomList = new ArrayList<Room>();
        Iterable<Room> allRooms= roomRepo.findAll();

        allRooms.forEach(roomList::add);
        return roomList;
    }
}
