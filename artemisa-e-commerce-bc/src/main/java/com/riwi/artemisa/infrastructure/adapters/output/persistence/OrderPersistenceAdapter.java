package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.OrderPersistencePort;
import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.MedicationInventory;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Order;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.OrderDetails;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.OrderPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MedicationInventoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.OrderDetailsRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.OrderRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductInventoryRepository;
import com.riwi.artemisa.utils.enums.StatesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderPersistencePort {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductInventoryRepository productInventoryRepository;
    private final MedicationInventoryRepository medicationInventoryRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;
    @Override
    public OrderModel save(OrderModel orderModel) {

        Order order = orderRepository.findByIdUserAndByStateOrder(orderModel.getIdUser(), StatesOrder.PENDING);

        if (order != null) {
            order.setTotalOrder(0.0f);

            // Obtener la lista existente de orderDetails
            List<OrderDetails> existingOrderDetails = order.getOrderDetails();

            orderModel.getOrderDetails().forEach(od -> {
                ProductInventory product = od.getProduct() != null ? productInventoryRepository.findById(od.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product not found")) : null;

                MedicationInventory medication = od.getMedication() != null ? medicationInventoryRepository.findById(od.getMedication().getId())
                        .orElseThrow(() -> new RuntimeException("Medication not found")) : null;

                OrderDetails findOrderDetails = product != null ? order.getOrderDetails().stream()
                        .filter(detail -> detail.getProduct() != null && detail.getProduct().getId().equals(product.getId()))
                        .findFirst().orElse(null) :
                        medication != null ? order.getOrderDetails().stream()
                                .filter(detail -> detail.getMedication() != null && detail.getMedication().getId().equals(medication.getId()))
                                .findFirst().orElse(null) : null;

                if (findOrderDetails != null) {
                    if (product != null) {
                        if (product.getStock() - od.getQuantity() >= 0) {
                            // Actualiza el OrderDetails existente
                            findOrderDetails.setQuantity(od.getQuantity());
                            findOrderDetails.setTotalPriceProduct(od.getQuantity() * product.getSellingPrice());
                            orderDetailsRepository.save(findOrderDetails);
                        } else {
                            throw new RuntimeException("we count only a quantity of " + product.getStock() + " products");
                        }
                    }
                    if (medication != null) {
                        if (medication.getStock() - od.getQuantity() >= 0) {
                            // Actualiza el OrderDetails existente
                            findOrderDetails.setQuantity(od.getQuantity());
                            findOrderDetails.setTotalPriceProduct(od.getQuantity() * medication.getSellingPrice());
                            orderDetailsRepository.save(findOrderDetails);
                        } else {
                            throw new RuntimeException("we count only a quantity of " + medication.getStock() + " medications");
                        }
                    }
                } else {
                    if (product != null) {
                        if (product.getStock() - od.getQuantity() >= 0) {
                            // Agrega un nuevo OrderDetails si no existe
                            findOrderDetails = OrderDetails.builder()
                                    .quantity(od.getQuantity())
                                    .unitPrice(product.getSellingPrice())
                                    .totalPriceProduct(od.getQuantity() * product.getSellingPrice())
                                    .product(product)
                                    .order(order)
                                    .build();
                            orderDetailsRepository.save(findOrderDetails);
                            existingOrderDetails.add(findOrderDetails);  // Agrega el nuevo detalle a la lista existente
                        } else {
                            throw new RuntimeException("we count only a quantity of " + product.getStock() + " products");
                        }
                    }
                    if (medication != null) {
                        if (medication.getStock() - od.getQuantity() >= 0) {
                            // Agrega un nuevo OrderDetails si no existe
                            findOrderDetails = OrderDetails.builder()
                                    .quantity(od.getQuantity())
                                    .unitPrice(medication.getSellingPrice())
                                    .totalPriceProduct(od.getQuantity() * medication.getSellingPrice())
                                    .medication(medication)
                                    .order(order)
                                    .build();
                            orderDetailsRepository.save(findOrderDetails);
                            existingOrderDetails.add(findOrderDetails);  // Agrega el nuevo detalle a la lista existente
                        } else {
                            throw new RuntimeException("we count only a quantity of " + medication.getStock() + " medications");
                        }
                    }
                }
            });

            existingOrderDetails.forEach(detail ->
                    order.setTotalOrder(order.getTotalOrder() + detail.getTotalPriceProduct())
            );

            order.setOrderDate(LocalDate.now());
            orderRepository.save(order);

            return orderPersistenceMapper.toOrderModel(orderRepository.findById(order.getId()).orElseThrow(() -> new RuntimeException("Order not found")));
        } else {
            Order createOrder = orderRepository.save(Order.builder()
                    .idUser(orderModel.getIdUser())
                    .statesOrder(StatesOrder.PENDING)
                    .totalOrder(0.0f)
                    .orderDate(LocalDate.now())
                    .build());

            orderModel.getOrderDetails().forEach(od -> {
                ProductInventory product = od.getProduct() != null ? productInventoryRepository.findById(od.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product not found")) : null;

                MedicationInventory medication = od.getMedication() != null ? medicationInventoryRepository.findById(od.getMedication().getId())
                        .orElseThrow(() -> new RuntimeException("Medication not found")) : null;

                if (product != null) {
                    if (product.getStock() - od.getQuantity() >= 0) {
                        orderDetailsRepository.save(OrderDetails.builder()
                                .quantity(od.getQuantity())
                                .unitPrice(product.getSellingPrice())
                                .totalPriceProduct(od.getQuantity() * product.getSellingPrice())
                                .product(product)
                                .order(createOrder)
                                .build());
                    } else {
                        throw new RuntimeException("we count only a quantity of " + product.getStock() + " products");
                    }
                }
                if (medication != null) {
                    if (medication.getStock() - od.getQuantity() >= 0) {
                        orderDetailsRepository.save(OrderDetails.builder()
                                .quantity(od.getQuantity())
                                .unitPrice(medication.getSellingPrice())
                                .totalPriceProduct(od.getQuantity() * medication.getSellingPrice())
                                .medication(medication)
                                .order(createOrder)
                                .build());
                    } else {
                        throw new RuntimeException("we count only a quantity of " + medication.getStock() + " medications");
                    }
                }
            });

            List<OrderDetails> newOrderDetails = orderDetailsRepository.findAllByOrderId(createOrder.getId());
            createOrder.setOrderDetails(newOrderDetails);

            newOrderDetails.forEach(update -> createOrder.setTotalOrder(createOrder.getTotalOrder() + update.getTotalPriceProduct()));

            return orderPersistenceMapper.toOrderModel(orderRepository.save(createOrder));
        }
    }

    @Override
    public List<OrderModel> findAll() {
        return orderPersistenceMapper.toOrderModelList(orderRepository.findAll());
    }

    @Override
    public OrderModel readByIdUserAndOrderDate(String id, LocalDate date) {
        return orderPersistenceMapper.toOrderModel(orderRepository.findByIdUserAndByOrderDate(id,date));
    }

    @Override
    public OrderModel readById(Long id) {
        return orderPersistenceMapper.toOrderModel(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @Override
    public String updateStatesOrder(Long id, StatesOrder statesOrder) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatesOrder(statesOrder);
        orderRepository.save(order);
        return "Updated successfully";
    }

    @Override
    public String deleteOrderDetails(Long idOrder, Long idOrderDetails) {

        Order order = orderRepository.findById(idOrder).orElseThrow(() -> new RuntimeException("Order not found"));

        order.getOrderDetails().removeIf(detail -> detail.getId().equals(idOrderDetails));

        if (order.getOrderDetails().isEmpty()) {
            orderRepository.delete(order);
            return "Delete Order Details successfully";

        } else {
            orderRepository.save(order);
            return "Delete Order successfully";

        }
    }
}

