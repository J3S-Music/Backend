package com.j3s.repository;

import com.j3s.model.Connection;
import com.j3s.model.UserConnection;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface ConnectionRepo extends CrudRepository<Connection, Long> {
}
