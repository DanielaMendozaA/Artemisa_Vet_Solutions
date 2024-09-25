package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.PetshopPaymentsPersistencePort;
import com.riwi.artemisa.domain.models.PetshopPaymentsModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.MedicationInventory;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Order;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.PetshopPayments;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.PetshopPaymentsPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MedicationInventoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.OrderRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.PetshopPaymentsRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductInventoryRepository;
import com.riwi.artemisa.utils.enums.StatesOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PetshopPaymentsPersistenceAdapter implements PetshopPaymentsPersistencePort {

    private final OrderRepository orderRepository;
    private final PetshopPaymentsRepository petshopPaymentsRepository;
    private final ProductInventoryRepository productInventoryRepository;
    private final MedicationInventoryRepository medicationInventoryRepository;
    private final PetshopPaymentsPersistenceMapper mapper;

    @Override
    public PetshopPaymentsModel save(PetshopPaymentsModel petshopPaymentsModel) {

        Order order = orderRepository.findById(petshopPaymentsModel.getOrder().getId()).orElseThrow();

        order.getOrderDetails().forEach(
                od -> {
                    if(od.getProduct() != null) {

                        ProductInventory product = productInventoryRepository.findById(od.getProduct().getId())
                                .orElseThrow(() -> new RuntimeException("Product inventory not found"));

                        if (od.getQuantity() - product.getStock() >= 0) {
                            throw new RuntimeException("we count only a quantity of " + od.getProduct().getStock() + " products " + od.getProduct().getProduct().getName());
                        }

                        product.setStock(product.getStock() - od.getQuantity());
                        productInventoryRepository.save(product);

                        productInventoryRepository.save(product);

                    }else if(od.getMedication() != null) {


                        if (od.getQuantity() - od.getMedication().getStock() >= 0) {
                            throw new RuntimeException("we count only a quantity of " + od.getMedication().getStock() + " products " + od.getMedication().getMedication().getName());
                        }

                        MedicationInventory medication = medicationInventoryRepository.findById(od.getMedication().getId())
                                .orElseThrow(() -> new RuntimeException("Product inventory not found"));

                        medication.setStock(medication.getStock() - od.getQuantity());
                        medicationInventoryRepository.save(medication);

                        medicationInventoryRepository.save(medication);
                    }
                }
        );

        order.setStatesOrder(StatesOrder.COMPLETED);

        PetshopPayments petshopPayments = PetshopPayments.builder()
                .paymentDate(LocalDate.now())
                .amount(order.getTotalOrder())
                .paymentMethod(petshopPaymentsModel.getPaymentMethod())
                .order(orderRepository.save(order))
                .build();

        return mapper.toPetshopPaymentsModel(petshopPaymentsRepository.save(petshopPayments));
    }

    @Override
    public List<PetshopPaymentsModel> findAll() {
        return mapper.toPetshopPaymentsModelList(petshopPaymentsRepository.findAll());
    }


    @Override
    public List<PetshopPaymentsModel> findByDate(LocalDate localDate) {
        return mapper.toPetshopPaymentsModelList(petshopPaymentsRepository.findByPaymentDate(localDate));
    }

    @Override
    public List<PetshopPaymentsModel> findPetshopPaymentsByUserId(Long idUser) {
        return mapper.toPetshopPaymentsModelList(petshopPaymentsRepository.findPaymentsByUserId(idUser));
    }
}
