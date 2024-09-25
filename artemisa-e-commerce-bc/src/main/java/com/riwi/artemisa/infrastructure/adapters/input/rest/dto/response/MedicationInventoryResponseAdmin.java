package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventoryResponseAdmin {
    private long id;
    private Boolean prescribed;
    private Integer stock;
    private String methodUse;
    private String supplier;
    private Float supplierPrice;
    private Float sellingPrice;
    private LocalDate dueDate;
    private Boolean isMedicationAvailable;
    private MedicationResponseAdmin medication;

    //Auditable
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean deleted;

}
