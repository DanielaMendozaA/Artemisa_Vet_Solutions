package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderPersistenceMapper {

    Order toOrder(OrderModel orderModel);
    OrderModel toOrderModel(Order order);
    List<OrderModel> toOrderModelList(List<Order> order);
    List<Order> toOrderList(List<OrderModel> OrderModel);

}
