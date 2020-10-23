package com.j3s.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j3s.model.compositeKey.UserRoomKey;

import javax.persistence.*;

@Entity
@Table(name = "user_to_room")
public class UserRoom {

    @EmbeddedId
    UserRoomKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "userID")
    private User user;

    @JsonIgnore
    @ManyToOne
    @MapsId("roomID")
    @JoinColumn(name = "roomID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "roleID")
    private Role role;

    public UserRoom() {
    }

    public UserRoomKey getId() {
        return id;
    }

    public void setId(UserRoomKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRoom{" +
                "id=" + id +
                ", user=" + user +
                ", room=" + room +
                ", role=" + role +
                '}';
    }

}

