package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "product_inventory")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInventory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long
            id;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "supplier", nullable = false)
    private String supplier;

    @Column(name = "supplier_price", nullable = false)
    private float supplierPrice;

    @Column(name = "selling_price", nullable = false)
    private float sellingPrice;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "state_product", nullable = false)
    private boolean stateProduct;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //Auditable
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted")
    private Boolean deleted = false;
}