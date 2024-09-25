package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationPersistenceMapper {

    Medication toMedication(MedicationModel medicationModel);
    MedicationModel toMedicationModel(Medication medication);
    List<MedicationModel> toMedicationModelList(List<Medication> medicationList);
}
