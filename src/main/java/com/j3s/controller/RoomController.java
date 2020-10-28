package com.j3s.controller;

import com.j3s.model.Room;
import com.j3s.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="*", allowedHeaders = "*")
@RestController
public class  RoomController {

    @Autowired
    RoomService roomService;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/rooms",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Room> getRooms(){
        return roomService.getAllRooms();
    }


    @RequestMapping(
            method = RequestMethod.GET,
            path = "/rooms/{roomID}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Room getRoom(@PathVariable Long roomID){
        return roomService.getRoom(roomID);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "/rooms/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Long getRoom(@RequestBody Room room, @RequestParam(name = "id") Long userID){
         Long id = roomService.createRoom(room);
         roomService.joinRoom(room.getRoomID(), userID, room.getRoomCode());
         return id;
    }
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/rooms/join",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void joinRoom(@RequestBody Room room, @RequestParam(name = "id") Long userID){
        roomService.joinRoom(room.getRoomID(), userID, room.getRoomCode());
    }



}
