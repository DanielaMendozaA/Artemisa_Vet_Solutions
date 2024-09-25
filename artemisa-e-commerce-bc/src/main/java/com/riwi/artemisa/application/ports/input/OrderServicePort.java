package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.utils.enums.StatesOrder;

import java.time.LocalDate;

public interface OrderServicePort extends
        ReadAll<OrderModel>,
        ReadByIdUserAndOrderDate<OrderModel, String, LocalDate>,
        ReadById<OrderModel, Long>,
        UpdateStatesOrder<Long, StatesOrder>,
        DeleteOrderDetails<Long>,
        Save<OrderModel>{}
