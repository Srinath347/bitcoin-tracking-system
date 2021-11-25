package com.db.bts.repository;

import com.db.bts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
    User findUserByEmail(@Param("email") String email);
}
