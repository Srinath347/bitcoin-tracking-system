package com.db.bts.repository;


import com.db.bts.entity.Account;
import com.db.bts.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
