package com.j3s.repository;
import com.j3s.model.Room;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository
public interface RoomRepo extends CrudRepository<Room, Long> {
}


