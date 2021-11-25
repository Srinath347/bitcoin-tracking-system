package com.db.bts.repository;

import com.db.bts.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query(value = "SELECT * FROM admins a WHERE a.email = :email", nativeQuery = true)
    Admin findAdminByEmail(@Param("email") String email);
}
