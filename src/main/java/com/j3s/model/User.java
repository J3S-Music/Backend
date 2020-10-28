package com.j3s.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="userID")
    private Long userID;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="avatarID", nullable=false)
    private Avatar avatar;

    @Column(name="email")
    private String email;

    @Column(name= "Password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserConnection> userConnections;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="roomID", nullable=false)
    private Room room;

    @ManyToOne
    @JoinColumn(name="roleID", nullable=false)
    private Role role;

    public User() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserConnection> getUserConnections() {
        return userConnections;
    }

    public void setUserConnections(List<UserConnection> userConnections) {
        this.userConnections = userConnections;
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
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", avatar=" + avatar +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userConnections=" + userConnections +
                ", room=" + room +
                ", role=" + role +
                '}';
    }
}
