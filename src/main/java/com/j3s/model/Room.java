package com.j3s.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="roomID")
    private Long roomID;

    @Column(name="principle")
    private String principle;

    @OneToMany(mappedBy = "room")
    private List<User> users;

    @Column(name="roomCode")
    private String roomCode;

    @Column(name="roomName")
    private String roomName;



    public Room() {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public String getPrinciple() {
        return principle;
    }

    public void setPrinciple(String principle) {
        this.principle = principle;
    }

    @OneToMany(mappedBy = "room")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> userRooms) {
        this.users = userRooms;
    }


}


