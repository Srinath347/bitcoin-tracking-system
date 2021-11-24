package com.db.bts.repository;

import com.db.bts.entity.Address;
import com.db.bts.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}

