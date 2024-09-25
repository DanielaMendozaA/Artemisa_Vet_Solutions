package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.MedicationInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationInventoryRepository extends JpaRepository<MedicationInventory,Long> {
    @Query("SELECT m FROM MedicationInventory m WHERE m.medication.category.id = :id")
    List<MedicationInventory> findByCategoryId(@Param("id") Long categoryId);

    @Query("SELECT m FROM MedicationInventory m WHERE LOWER(m.medication.name) LIKE LOWER(CONCAT(:name, '%'))")
    List<MedicationInventory> findAllByName(@Param("name") String name);

    @Query("SELECT m FROM MedicationInventory m WHERE m.stock = :stock")
    List<MedicationInventory> findAllMedicationInventorytock(@Param("stock") int stock);

    @Query("SELECT m FROM MedicationInventory m WHERE m.isMedicationAvailable = true AND m.prescribed = false")
    List<MedicationInventory> findAllMedicationInventoryAvailable();

    MedicationInventory findByIdAndIsMedicationAvailableIsTrue(Long id);

    List<MedicationInventory> findAllByIsMedicationAvailableIsTrue();

    //devolver todos los inventarios de medicamentos mayores a 0 para USERS
    @Query("SELECT m FROM MedicationInventory m WHERE m.stock > 0")
    List<MedicationInventory> findAllWithStockGreaterThanZero();
}
