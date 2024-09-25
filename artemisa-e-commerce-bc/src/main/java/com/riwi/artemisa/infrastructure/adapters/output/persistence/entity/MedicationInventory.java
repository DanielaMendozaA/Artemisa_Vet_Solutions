package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medication_inventory")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventory {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prescribed", nullable = false)
    private Boolean prescribed;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "method_use", nullable = false)
    private String methodUse;

    @Column(name = "supplier", nullable = false)
    private String supplier;

    @Column(name = "supplier_price", nullable = false)
    private Float supplierPrice;

    @Column(name = "selling_price", nullable = false)
    private Float sellingPrice;

    @Column(name="due_date")
    private LocalDate dueDate;

    @Column(name="state_medication", nullable = false)
    private Boolean isMedicationAvailable = true;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicationinventory_id")
    private Medication medication;

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