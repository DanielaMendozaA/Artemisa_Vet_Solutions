package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.PetshopPaymentsServicePort;
import com.riwi.artemisa.application.ports.out.PetshopPaymentsPersistencePort;
import com.riwi.artemisa.domain.models.PetshopPaymentsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetshopPaymentsService implements PetshopPaymentsServicePort {

    private final PetshopPaymentsPersistencePort persistencePort;

    @Override
    public PetshopPaymentsModel save(PetshopPaymentsModel petshopPaymentsModel) {
        return persistencePort.save(petshopPaymentsModel);
    }

    @Override
    public List<PetshopPaymentsModel> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public List<PetshopPaymentsModel> findByDate(LocalDate localDate) {
        return persistencePort.findByDate(localDate);
    }

    @Override
    public List<PetshopPaymentsModel> findPetshopPaymentsByUserId(Long idUser) {
        return persistencePort.findPetshopPaymentsByUserId(idUser);
    }
}
