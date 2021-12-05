package com.db.bts.repository;

import com.db.bts.entity.Transaction;
import com.db.bts.entity.User;
import com.db.bts.model.UserTransactionAmountModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query("update Transaction t set t.status = :status WHERE t.id = :transactionId")
    void updateStatus(@Param("transactionId") int id, @Param("status") int status);

    @Query("from Transaction t where t.user = :user")
    List<Transaction> getTransactionByUser(@Param("user") User user);

    @Query("select sum(t.amount) from Transaction t where t.user = :user")
    Float findAmountSumByUser(@Param("user") User user);

    @Query("select new com.db.bts.model.UserTransactionAmountModel(sum(t.amount) as totalAmount, t.user.id as userId) from Transaction t where date(t.time) < :to and date(t.time) >= :from group by t.user")
    List<UserTransactionAmountModel> findAmountSum(@Param("from") Date from, @Param("to") Date to);

    @Query("from Transaction t where date(t.time) < :to and date(t.time) >= :from")
    List<Transaction> findTransactionsByDate(@Param("from") Date from, @Param("to") Date to);

    @Query("from Transaction t where lower(t.type) like lower(concat('%', :name,'%'))")
    List<Transaction> findTransactionByUserName(@Param("name") String name);
}
