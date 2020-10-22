package com.j3s.repository;
import com.j3s.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepoCRUD extends CrudRepository<User, Integer> {
}


