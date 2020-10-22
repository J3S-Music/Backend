package com.j3s.model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userID;

    @Column(name="name")
    private String name;

    @Column(name="avatarID")
    private Integer avatarID;

    @Column(name="email")
    private String email;

    @Column(name= "Password")
    private String password;

    public User() {
    }

    public User(String name, Integer avatarID, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.avatarID = avatarID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(Integer avatarID) {
        this.avatarID = avatarID;
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

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", avatarID=" + avatarID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
