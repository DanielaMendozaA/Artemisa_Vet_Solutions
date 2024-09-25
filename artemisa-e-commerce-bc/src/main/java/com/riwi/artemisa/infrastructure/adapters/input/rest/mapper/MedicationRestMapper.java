package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponseAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationRestMapper {

    //Admin mappings
    MedicationResponseAdmin toMedicationResponseAdmin(MedicationModel model);
    List<MedicationResponseAdmin> toMediaResponseAdminList(List<MedicationModel> medicationModels);

    //Mappgin for create request

    MedicationModel toMedication(MedicationCreateRequest request);
    MedicationCreateRequest toMedicationCreateRequest(MedicationModel medicationModel);
    List<MedicationCreateRequest> toMedicationCreateRequestList(List<MedicationModel> medicationList);

    //User mappings
    MedicationResponse toMedicationResponse(MedicationModel medicationModel);
    MedicationModel toMedicationModel(MedicationResponse medicationResponse);
    List<MedicationResponse> toMedicationResponseList(List<MedicationModel> medicationList);
}
