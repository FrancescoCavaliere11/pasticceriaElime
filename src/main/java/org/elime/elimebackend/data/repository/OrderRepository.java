package org.elime.elimebackend.data.repository;

import jakarta.persistence.LockModeType;
import org.elime.elimebackend.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<Order, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT COALESCE(SUM(oi.quantity), 0)
            FROM Order o
            JOIN o.orderItems oi
            JOIN oi.product p
            JOIN p.category c
            WHERE c.id = :categoryId
            AND o.date = :date""")
    long sumQuantitiesByCategoryIdAndDate(
            @Param("categoryId") String categoryId,
            @Param("date") LocalDate date
    );
}
