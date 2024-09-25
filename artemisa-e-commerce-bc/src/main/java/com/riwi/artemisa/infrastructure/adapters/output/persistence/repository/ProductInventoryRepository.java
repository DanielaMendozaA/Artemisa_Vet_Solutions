package com.riwi.artemisa.infrastructure.adapters.output.persistence.repository;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
//    @Query("SELECT n FROM productInventory n JOIN n.products m JOIN m.category c WHERE c.id = :id")
//    List<ProductInventory> findByCategoryId(@Param("id") Long id);

    @Query("SELECT p FROM ProductInventory p WHERE p.product.category.id = :id")
    List<ProductInventory> findByCategoryId(@Param("id") Long categoryId);

    @Query("SELECT p FROM ProductInventory p WHERE LOWER(p.product.name) LIKE LOWER(CONCAT(:name, '%'))")
    List<ProductInventory> findAllByName(@Param("name") String name);

    @Query("SELECT p FROM ProductInventory p WHERE p.stock = :stock")
    List<ProductInventory> findAllProductInventoryStock(@Param("stock") int stock);

    @Query("SELECT p FROM ProductInventory p WHERE p.stateProduct = true AND p.stock > 0")
    List<ProductInventory> findAllProductInventoryAvailable();

}
