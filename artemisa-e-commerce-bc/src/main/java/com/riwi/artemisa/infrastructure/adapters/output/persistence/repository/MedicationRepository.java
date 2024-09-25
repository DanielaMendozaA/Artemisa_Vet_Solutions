package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {

    Medication findByNameContaining(String name);

    Medication findByIdAndDeletedIsFalse(Long id);

    Medication findByNameIgnoreCaseAndDeletedIsFalse(String name);


}
