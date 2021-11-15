package com.db.bts.repository;

import com.db.bts.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query("update Transaction t set t.status = :status WHERE t.id = :transactionId")
    void updateStatus(@Param("transactionId") int id, @Param("status") int status);

    @Query("select * from Transaction t where t.user.id = :id")
    List<Transaction> getTransactionByUserId(@Param("id") int userId);
}
