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
}
