package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.PetshopPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PetshopPaymentsRepository extends JpaRepository<PetshopPayments, Long> {
    @Query("SELECT p FROM PetshopPayments p WHERE p.paymentDate = :date")
    List<PetshopPayments> findByPaymentDate(@Param("date")LocalDate date);

    @Query("SELECT p FROM PetshopPayments p JOIN p.order o WHERE o.idUser = :idUser")
    List<PetshopPayments> findPaymentsByUserId(@Param("idUser") Long idUser);
}
