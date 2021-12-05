package com.db.bts.repository;

import com.db.bts.entity.Payment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer>{

    @Query("from Payment p where p.trader_id = :id and p.status = 0")
    List<Payment> findPaymentsByTraderId(@Param("id") int traderId);

    @Transactional
    @Modifying
    @Query("update Payment p set p.status = :status where p.id = :id")
    Integer updatePaymentStatus(@Param("id") int paymentId, @Param("status") int status);
}
