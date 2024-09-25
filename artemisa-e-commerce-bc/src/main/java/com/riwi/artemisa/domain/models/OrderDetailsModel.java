package com.riwi.artemisa.domain.models;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsModel {

    private Long id;

    private int quantity;

    private float unitPrice;
    
    @Builder.Default
    private float totalPriceProduct = 0.0f;

    private ProductInventoryModel product;

    private MedicationInventoryModel medication;

}
