package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.PetshopPaymentsModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.PetshopPaymentsRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.PetshopPaymentsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetshopPaymentsRestMapper {

    PetshopPaymentsModel toPetshopPaymentsModel(PetshopPaymentsRequest request);
    PetshopPaymentsResponse toPetshopPaymentsResponse(PetshopPaymentsModel model);
    List<PetshopPaymentsResponse> toPetshopPaymentsResponseList (List<PetshopPaymentsModel> model);

}
