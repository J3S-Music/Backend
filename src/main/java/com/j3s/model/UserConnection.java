package com.j3s.model;

import javax.persistence.*;

@Entity
@Table(name = "user_to_connection")
public class UserConnection {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="UserConnectionID")
    private Long userConnectionID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "connectionID")
    private Connection connection;

    @Column(name= "default")
    private Boolean _default;

    @Column(name= "active")
    private Boolean active;

    @Column(name= "key")
    private String key;

    public UserConnection() {
    }

    public Long getUserConnectionID() {
        return userConnectionID;
    }

    public void setUserConnectionID(Long userConnectionID) {
        this.userConnectionID = userConnectionID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Boolean get_default() {
        return _default;
    }

    public void set_default(Boolean _default) {
        this._default = _default;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "UserConnection{" +
                "userConnectionID=" + userConnectionID +
                ", user=" + user +
                ", connection=" + connection +
                ", _default=" + _default +
                ", active=" + active +
                ", key='" + key + '\'' +
                '}';
    }
}
