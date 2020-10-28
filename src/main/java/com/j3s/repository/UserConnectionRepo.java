package com.j3s.repository;

import com.j3s.model.UserConnection;
import com.j3s.model.compositeKey.UserConnectionKey;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface UserConnectionRepo extends CrudRepository<UserConnection, UserConnectionKey> {
   // List<UserConnection> findByUserConnectionKeyUserID(Long UserID);
}
