package com.riwi.artemisa.domain.models;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventoryModel {

    private Long id;
    private Boolean prescribed;
    private Integer stock;
    private String methodUse;
    private String supplier;
    private Float supplierPrice;
    private Float sellingPrice;
    private LocalDate dueDate;
    @Builder.Default
    private Boolean isMedicationAvailable = true;
    private MedicationModel medication;

    //Auditable
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @Builder.Default
    private Boolean deleted = false;

}
