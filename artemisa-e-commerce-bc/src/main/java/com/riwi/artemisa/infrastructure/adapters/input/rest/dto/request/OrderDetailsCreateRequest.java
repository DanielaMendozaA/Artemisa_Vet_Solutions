package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsCreateRequest {

    @NotNull(message = "The quantity of the product is required")
    private int quantity;

    private ProductInventoryCreateOrder product;

    private MedicationInventoryCreateOrder medication;

}
