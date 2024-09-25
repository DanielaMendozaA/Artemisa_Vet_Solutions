package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationInventoryResponseOrder {

    private Long id;

    private float sellingPrice;

    private MedicationResponseOrder medication;

}
