package com.j3s.model.compositeKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserConnectionKey implements Serializable {

    private Long userID;

    private Long connectionID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getConnectionID() {
        return connectionID;
    }

    public void setConnectionID(Long connectionID) {
        this.connectionID = connectionID;
    }

    public UserConnectionKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConnectionKey that = (UserConnectionKey) o;
        return userID.equals(that.userID) &&
                connectionID.equals(that.connectionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, connectionID);
    }
}