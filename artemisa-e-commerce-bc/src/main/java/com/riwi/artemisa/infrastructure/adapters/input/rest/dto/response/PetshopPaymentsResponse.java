package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetshopPaymentsResponse {

    private Long id;

    private Float amount;

    private String paymentMethod;

    private OrderResponsePetshopPayments order;

}
