package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.MedicationInventoryPersistencePort;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.*;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.MedicationInventoryPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.MedicationPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MedicationInventoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MedicationInventoryPersistenceAdapter implements MedicationInventoryPersistencePort {

    private final MedicationInventoryRepository repository;
    private final MedicationInventoryPersistenceMapper mapper;
    private final MedicationPersistenceMapper medicationMapper;
    private final MedicationRepository medicationRepository;

    @Override
    public MedicationInventoryModel save(MedicationInventoryModel medicationInventoryModel) {

        Medication savedMedicationEntity = medicationRepository.findById(medicationInventoryModel.getMedication().getId())
                .orElseThrow(() -> new RuntimeException("The Medication not found"));

        // Crear y asignar el inventario del medicamento ya creado
        MedicationInventory medicationInventory = MedicationInventory.builder()
                .prescribed(medicationInventoryModel.getPrescribed())
                .stock(medicationInventoryModel.getStock())
                .methodUse(medicationInventoryModel.getMethodUse())
                .supplier(medicationInventoryModel.getSupplier())
                .supplierPrice(medicationInventoryModel.getSupplierPrice())
                .sellingPrice(medicationInventoryModel.getSellingPrice())
                .dueDate(medicationInventoryModel.getDueDate())
                .isMedicationAvailable(true)
                .medication(savedMedicationEntity)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deleted(false)
                .build();

        // Guardar el inventario
        MedicationInventory savedInventory = repository.save(medicationInventory);

        // Mapear la entidad de inventario de vuelta al modelo
        return mapper.toMedicationInventoryModel(savedInventory);
    }

    @Override
    public MedicationInventoryModel update(Long id, MedicationInventoryModel medicationInventoryModel) {
        // Recupera la entidad existente por id
        MedicationInventory existingInventory = repository.findById(id).orElseThrow();

        // Actualizar los campos del inventario solo si se proporcionan valores
        if (medicationInventoryModel.getStock() != null) {
            existingInventory.setStock(medicationInventoryModel.getStock());
        }
        if (medicationInventoryModel.getMethodUse() != null) {
            existingInventory.setMethodUse(medicationInventoryModel.getMethodUse());
        }
        if (medicationInventoryModel.getSupplier() != null) {
            existingInventory.setSupplier(medicationInventoryModel.getSupplier());
        }
        if (medicationInventoryModel.getSupplierPrice() != null) {
            existingInventory.setSupplierPrice(medicationInventoryModel.getSupplierPrice());
        }
        if (medicationInventoryModel.getSellingPrice() != null) {
            existingInventory.setSellingPrice(medicationInventoryModel.getSellingPrice());
        }
        if (medicationInventoryModel.getDueDate() != null) {
            existingInventory.setDueDate(medicationInventoryModel.getDueDate());
        }
        if (medicationInventoryModel.getIsMedicationAvailable() != null) {
            existingInventory.setIsMedicationAvailable(medicationInventoryModel.getIsMedicationAvailable());
        }
            existingInventory.setUpdatedAt(LocalDateTime.now());

        // Si se proporciona un nuevo medicamento, actualiza el medicamento asociado
        if (medicationInventoryModel.getMedication() != null) {
            Medication updatedMedication = medicationMapper.toMedication(medicationInventoryModel.getMedication());
            existingInventory.setMedication(updatedMedication);
        }

        // Guardar el inventario actualizado
        MedicationInventory updatedInventory = repository.save(existingInventory);

        return mapper.toMedicationInventoryModel(updatedInventory);
    }

    @Override
    public String updateStatusProduct(Long id) {
        MedicationInventory medicationInventory = repository.findById(id).orElseThrow();
        medicationInventory.setIsMedicationAvailable(!medicationInventory.getIsMedicationAvailable());
        medicationInventory.setUpdatedAt(LocalDateTime.now());
        repository.save(medicationInventory);
        return medicationInventory.getIsMedicationAvailable() ? "Medication restore successfully" : "Medication deleted successfully";
    }

    @Override
    public List<MedicationInventoryModel> findAll() {
        List<MedicationInventory> inventoryList = repository.findAllByIsMedicationAvailableIsTrue();
        return mapper.toMedicationInventoryModelList(inventoryList);
    }

    @Override
    public MedicationInventoryModel readById(Long id) {
        MedicationInventory inventory = repository.findById(id).orElseThrow();
        return mapper.toMedicationInventoryModel(inventory);
    }

    @Override
    public List<MedicationInventoryModel> readAllCategory(Long id) {
        return mapper.toMedicationInventoryModelList(repository.findByCategoryId(id));
    }


    @Override
    public List<MedicationInventoryModel> findAllByName(String name) {
        return mapper.toMedicationInventoryModelList(repository.findAllByName(name));
    }

    @Override
    public String updateStock(Integer stock, Long id) {
        MedicationInventory medicationInventory = repository.findById(id).orElseThrow();
        medicationInventory.setStock(medicationInventory.getStock() + stock);
        medicationInventory.setUpdatedAt(LocalDateTime.now());
        medicationInventory.setCreatedAt(medicationInventory.getCreatedAt());
        medicationInventory.setDeletedAt(medicationInventory.getDeletedAt());
        return "Stock updated successfully";
    }

    @Override
    public List<MedicationInventoryModel> readAllProductStock(Integer stock) {

        return mapper.toMedicationInventoryModelList(repository.findAllMedicationInventorytock(stock));
    }

    @Override
    public List<MedicationInventoryModel> readAllIfAvailable() {
        return mapper.toMedicationInventoryModelList(repository.findAllMedicationInventoryAvailable());
    }
}
