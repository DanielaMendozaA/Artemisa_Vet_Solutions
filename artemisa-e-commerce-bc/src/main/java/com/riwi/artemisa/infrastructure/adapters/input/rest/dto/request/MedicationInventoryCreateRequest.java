package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventoryCreateRequest {

    @NotNull(message = "The prescribed of the medication is required")
    private Boolean prescribed;
    @NotNull(message = "The stock of the medication is required")
    private Integer stock;
    @NotNull(message = "The method Use of the medication is required")
    private String methodUse;
    @NotBlank(message = "The supplier of the product is required")
    private String supplier;
    @NotNull(message = "The supplier price of the product is required")
    private Float supplierPrice;
    @NotNull(message = "The selling price of the product is required")
    private Float sellingPrice;
    @NotNull(message = "The dueDate of the product is required")
    private LocalDate dueDate;
    @NotNull(message = "The medication is required")
    @Valid
    private MedicationCreateRequestId medication;
}
