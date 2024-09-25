package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.MedicationInventory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationInventoryPersistenceMapper {

    MedicationInventory toMedicationInventory(MedicationInventoryModel medicationInventoryModel);

    MedicationInventoryModel toMedicationInventoryModel(MedicationInventory medicationInventory);

    List<MedicationInventoryModel> toMedicationInventoryModelList(List<MedicationInventory> medicationInventoryList);

}
