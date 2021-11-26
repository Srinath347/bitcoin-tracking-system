package com.db.bts.repository;

import com.db.bts.entity.Membership;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Integer> {

    @Query("from Membership m where m.name = :name ")
    Membership findByName(@Param("name") String name);
}
