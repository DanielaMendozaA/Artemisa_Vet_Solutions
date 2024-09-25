package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponsePetshopPayments {

    private Long id;

    private float totalOrder;

    private List<OrderDetailsResponsePetshopPayments> orderDetails;

}
