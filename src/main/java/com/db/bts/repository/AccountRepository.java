package com.db.bts.repository;

import com.db.bts.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("from Account a where a.user.id = :id")
    Account findAccountByUserId(@Param("id") int userId);

    @Query("select a.balance from Account a where a.user.id = :id")
    Double findBalanceByUserId(@Param("id") int userId);


    @Query("select a.bitcoin from Account a where a.user.id = :id")
    Double findBitcoinsByUserId(@Param("id") int userId);

}
