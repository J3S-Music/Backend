package com.j3s.repository;

import com.j3s.model.User;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepo extends Repository<User, Integer> {
    User findByEmail(String email);
}
