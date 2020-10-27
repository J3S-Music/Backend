package com.j3s.repository;

import com.j3s.model.UserConnection;
import com.j3s.model.compositeKey.UserConnectionKey;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface UserConnectionRepo extends CrudRepository<UserConnection, UserConnectionKey> {
}
