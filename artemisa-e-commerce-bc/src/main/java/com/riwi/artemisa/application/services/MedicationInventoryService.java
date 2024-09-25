package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.MedicationInventoryServicePort;
import com.riwi.artemisa.application.ports.out.MedicationInventoryPersistencePort;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationInventoryService implements MedicationInventoryServicePort {

    private final MedicationInventoryPersistencePort persistencePort;


    @Override
    public MedicationInventoryModel save(MedicationInventoryModel medicationInventoryModel) {
        return persistencePort.save(medicationInventoryModel);
    }

    @Override
    public MedicationInventoryModel update(Long id, MedicationInventoryModel medicationInventoryModel) {
        return persistencePort.update(id, medicationInventoryModel);
    }

    @Override
    public String updateStatusProduct(Long id) {
        return persistencePort.updateStatusProduct(id);
    }

    @Override
    public List<MedicationInventoryModel> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public MedicationInventoryModel readById(Long id) {
        return persistencePort.readById(id);
    }

    @Override
    public List<MedicationInventoryModel> readAllCategory(Long id) {
        return persistencePort.readAllCategory(id);
    }

    @Override
    public List<MedicationInventoryModel> findAllByName(String name) {
        return persistencePort.findAllByName(name);
    }

    @Override
    public String updateStock(Integer stock, Long id) {
        return persistencePort.updateStock(stock,id);
    }

    @Override
    public List<MedicationInventoryModel> readAllProductStock(Integer stock) {
        return persistencePort.readAllProductStock(stock);
    }

    @Override
    public List<MedicationInventoryModel> readAllIfAvailable() {
        return persistencePort.readAllIfAvailable();
    }



}

