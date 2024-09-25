package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.OrderServicePort;
import com.riwi.artemisa.application.ports.out.OrderPersistencePort;
import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.utils.enums.StatesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServicePort {

    private final OrderPersistencePort order;

    @Override
    public List<OrderModel> findAll() {
        return order.findAll();
    }

    @Override
    public OrderModel readByIdUserAndOrderDate(String id, LocalDate date) {
        return order.readByIdUserAndOrderDate(id, date);
    }

    @Override
    public OrderModel readById(Long id) {
        return order.readById(id);
    }

    @Override
    public String updateStatesOrder(Long id, StatesOrder statesOrder) {
        return order.updateStatesOrder(id, statesOrder);
    }

    @Override
    public String deleteOrderDetails(Long idOrder, Long idOrderDetails) {
        return order.deleteOrderDetails(idOrder, idOrderDetails);
    }

    @Override
    public OrderModel save(OrderModel orderModel) {
        return order.save(orderModel);
    }
}
