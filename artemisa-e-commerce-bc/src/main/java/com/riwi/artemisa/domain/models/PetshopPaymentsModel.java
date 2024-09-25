package com.riwi.artemisa.domain.models;

import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Order;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetshopPaymentsModel {

    private long id;

    private LocalDate paymentDate;

    private Float amount;

    private String paymentMethod;

    private Order order;

}
