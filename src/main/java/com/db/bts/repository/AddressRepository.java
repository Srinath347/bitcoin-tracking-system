package com.db.bts.repository;

import com.db.bts.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
	
	 @Query("from Address a where a.user.id = :id ")
	 Address findAddressByUserId(@Param("id") int id);

	 @Query("select a.user.id from Address a where a.street = :street ")
	 List<Integer> findUserIdByStreetAddress(@Param("street") String streetAddress);

	 @Query("select a.user.id from Address a where a.city = :city ")
	 List<Integer> findUserIdByCity(@Param("city") String city);
}

