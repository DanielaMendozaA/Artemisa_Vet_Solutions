package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Order;
import com.riwi.artemisa.utils.enums.StatesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.idUser = :idUser AND o.statesOrder = :statesOrder")
    Order findByIdUserAndByStateOrder(@Param("idUser") String idUser, @Param("statesOrder")StatesOrder statesOrder);

    @Query("SELECT o FROM Order o WHERE o.idUser = :idUser AND o.orderDate = :orderDate")
    Order findByIdUserAndByOrderDate(@Param("idUser") String idUser, @Param("orderDate") LocalDate orderDate);

}
