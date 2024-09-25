package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsResponsePetshopPayments {

    private int quantity;

    private float unitPrice;

    private float totalPriceProduct;

    private ProductInventoryResponsePetshopPayments product;

}
