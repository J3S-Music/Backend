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

}


