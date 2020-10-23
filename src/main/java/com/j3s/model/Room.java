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
    private List<UserRoom> userRooms;

    public Room() {
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

    public List<UserRoom> getUserRooms() {
        return userRooms;
    }

    public void setUserRooms(List<UserRoom> userRooms) {
        this.userRooms = userRooms;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID=" + roomID +
                ", principle='" + principle + '\'' +
                ", userRooms=" + userRooms +
                '}';
    }

}


