package com.db.bts.repository;

import com.db.bts.entity.Membership;
import com.db.bts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
    User findUserByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("update User u set u.member = :member where u.id in :userIds")
    void updateMembershipStatus(@Param("userIds") List<Integer> userIds, @Param("member") Membership membership);

    @Transactional
    @Modifying
    @Query("update User u set u.member.id = :memId where u.id = :id")
    void updateMembershipStatusById(@Param("id") Integer userId, @Param("memId") Integer memId);
}
