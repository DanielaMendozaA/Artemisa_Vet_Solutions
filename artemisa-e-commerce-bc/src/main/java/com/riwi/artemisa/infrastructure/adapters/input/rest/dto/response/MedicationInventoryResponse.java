package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventoryResponse {

    private Long id;
    private boolean prescribed;
    private int stock;
    private String methodUse;
    private float sellingPrice;
    private boolean StateMedication;
    private MedicationResponse medication;
}
