package com.j3s.service;

import com.j3s.exception.AuthenticationFailedException;
import com.j3s.exception.ResourceNotFoundException;
import com.j3s.model.Room;
import com.j3s.model.User;
import com.j3s.playlistHandler.Playlist;
import com.j3s.repository.RoomRepo;
import com.j3s.songFactory.Song;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private UserService userService;

    private HashMap<Long, Playlist> playlistList = new HashMap<>();

    @PostConstruct
    public void init(){
        for (Room room: this.getAllRooms()) {
            if(playlistList.get(room.getRoomID())==null){
                Playlist playlist = new Playlist(room.getRoomID());
                playlistList.put(room.getRoomID(), playlist);
            }
        }
    }

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
        Long roomID = roomRepo.save(room).getRoomID();
        Playlist playlist = new Playlist(roomID);
        playlistList.put(roomID, playlist);
        return roomID;
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

    public JSONArray getPlaylist(Long id) {
        return playlistList.get(id).toPlaySON();
    }

    public void addSongtoPlaylist(Song song, Long id) {
        playlistList.get(id).add(song);
    }

    public void deleteSongFromPlaylist(String songID, Long id) {
        Playlist play = playlistList.get(id);
        Song song= play.findSong(songID);
        play.remove(song);
    }

    public void upvoteSong(String songID, Long id) {
        Playlist play = playlistList.get(id);
        Song song= play.findSong(songID);
        song.upVote(true);
    }

    public void downvoteSong(String songID, Long id) {
        Playlist play = playlistList.get(id);
        Song song= play.findSong(songID);
        song.downVote(true);
    }
}
