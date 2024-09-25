package com.riwi.artemisa.domain.models;

import com.riwi.artemisa.utils.enums.StatesOrder;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {

    private long id;

    private String idUser;

    private LocalDate orderDate;

    private float totalOrder;

    private StatesOrder statesOrder;

    private List<OrderDetailsModel> orderDetails;

    
}
