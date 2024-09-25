package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @Query("SELECT od FROM Order o JOIN o.orderDetails od JOIN od.product p WHERE o.id = :orderId AND p.id = :productId")
    OrderDetails findOrderDetailsByOrderIdAndProductId(@Param("orderId") Long orderId, @Param("productId") Long productId);

    @Query("SELECT od FROM OrderDetails od WHERE od.order.id = :orderId")
    List<OrderDetails> findAllByOrderId(@Param("orderId") Long orderId);
}