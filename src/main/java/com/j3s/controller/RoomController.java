package com.j3s.controller;

import com.j3s.model.Room;
import com.j3s.service.RoomService;
import com.j3s.songFactory.Song;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
            path = "/room/{roomID}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Room getRoom(@PathVariable Long roomID){
        return roomService.getRoom(roomID);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "/room/create",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Long getRoom(@RequestBody Room room, @RequestParam(name = "id") Long userID){
         Long id = roomService.createRoom(room);
         roomService.joinRoom(room.getRoomID(), userID, room.getRoomCode());
         return id;
    }
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/room/{id}/join",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void joinRoom(@PathVariable Long id, @RequestParam(name="roomCode") String roomCode, @RequestParam(name = "userID") Long userID){
        roomService.joinRoom(id, userID, roomCode);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/room/{id}/playlist",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public JSONArray getRoomPlaylist(@PathVariable Long id){
        return roomService.getPlaylist(id);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/room/{id}/playlist",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addSongtoPlaylist(@RequestBody Song song, @PathVariable Long id){
        roomService.addSongtoPlaylist(song, id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/room/{id}/playlist/{songID}"
    )
    public void deleteSongFromPlaylist(@PathVariable String songID, @PathVariable Long id){
        roomService.deleteSongFromPlaylist(songID, id);
    }



}
