package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponseAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationInventoryRestMapper {

    //Admin mappings
    MedicationInventoryResponseAdmin toMedicationInventoryAdmin(MedicationInventoryModel medicationInventoryModel);
    List<MedicationInventoryResponseAdmin> toMedicationInventoryResponseAdminList(List<MedicationInventoryModel> medicationInventoryModels);

    //Mappgin for create request
    MedicationInventoryModel toMedicationInventroy(MedicationInventoryCreateRequest createRequest);
    MedicationInventoryCreateRequest toMedicationInventoryCreateRequest(MedicationInventoryModel medicationInventoryModel);
    List<MedicationInventoryCreateRequest> toMedicationInventoryCreateRequestList(List<MedicationInventoryModel> medicationInventoryModels);

    //User mappings
    MedicationInventoryResponse toMedicationInventoryResponse(MedicationInventoryModel medicationInventoryModel);
    MedicationInventoryModel toMedicationInventoryModel(MedicationInventoryResponse medicationInventoryResponse);
    List<MedicationInventoryResponse> toMedicationResponseList(List<MedicationInventoryModel> medicationInventoryModelList);
}
