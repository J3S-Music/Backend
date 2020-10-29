package com.j3s.repository;

import com.j3s.model.Connection;
import com.j3s.model.User;
import com.j3s.model.UserConnection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Repository
public interface UserConnectionRepo extends CrudRepository<UserConnection, Long> {
    @Query(
            value = "SELECT * FROM user_to_connection uc WHERE uc.UserID=?1",
            nativeQuery = true
    )
    List<UserConnection> findUserConnectionsByUserID(Long UserID);

    @Query(
            value = "SELECT * FROM user_to_connection uc WHERE uc.UserID=?1 AND uc.ConnectionID=?2",
            nativeQuery = true
    )
    UserConnection findUserConnectionByUserIDAndConnectionID(Long UserID, Long ConnectionID);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE user_to_connection uc SET `key`=?3, `default`=?4, `active`=?5 WHERE `UserID`=?1 AND `ConnectionID`=?2",
            nativeQuery = true
    )
    void updateUserConnection(Long UserID, Long ConnectionID,String key, Boolean _default, Boolean active);

    @Transactional
    @Modifying
    @Query(
            value = "INSERT  INTO user_to_connection (`key`, `default`, `active`, `UserID`, `ConnectionID`) VALUES (?3,?4,?5,?1,?2)",
            nativeQuery = true
    )
    void createUserConnection(Long UserID, Long ConnectionID,String key, Boolean _default, Boolean active);



}
