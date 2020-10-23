package com.j3s.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "connection")
public class Connection {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="connectionID")
    private Long connectionID;

    @Column(name="name")
    private String name;

    /*
    @JsonIgnore
    @OneToMany(mappedBy = "connection")
    private List<UserConnection> userConnections;
    */
    public Connection() {
    }

    public Long getConnectionID() {
        return connectionID;
    }

    public void setConnectionID(Long connectionID) {
        this.connectionID = connectionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /*
    public List<UserConnection> getUserConnections() {
        return userConnections;
    }

    public void setUserConnections(List<UserConnection> userConnections) {
        this.userConnections = userConnections;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "connectionID=" + connectionID +
                ", name='" + name + '\'' +
                ", userConnections=" + userConnections +
                '}';
    }

     */
}


