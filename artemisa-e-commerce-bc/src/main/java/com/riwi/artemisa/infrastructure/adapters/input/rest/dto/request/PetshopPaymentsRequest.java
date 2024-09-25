package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetshopPaymentsRequest {

    @NotBlank(message = "Payment Method is required")
    private String paymentMethod;

    @NotNull(message = "Order is required")
    private OrderRequestPetshopPayments order;

}
