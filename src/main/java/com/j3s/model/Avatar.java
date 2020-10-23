package com.j3s.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="avatarID")
    private Long avatarID;

    @Column(name="pictureName")
    private String pictureName;

    @OneToMany(mappedBy = "avatar")
    private List<User> user = new ArrayList<>();

    public Avatar() {
    }

    public Long getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(Long avatarID) {
        this.avatarID = avatarID;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    @Override
    public String toString() {
        return "Avatar{" +
                "avatarID=" + avatarID +
                ", pictureName='" + pictureName + '\'' +
                '}';
    }
}
