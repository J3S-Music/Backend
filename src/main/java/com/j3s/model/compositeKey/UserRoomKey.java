package com.j3s.model.compositeKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoomKey implements Serializable {

    private Long userID;

    private Long roomID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getRoomID() {
        return roomID;
    }

    public void setRoomID(Long roomID) {
        this.roomID = roomID;
    }

    public UserRoomKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoomKey that = (UserRoomKey) o;
        return userID.equals(that.userID) &&
                roomID.equals(that.roomID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, roomID);
    }
}
