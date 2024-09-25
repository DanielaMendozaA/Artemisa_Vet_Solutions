package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.PetshopPaymentsModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.PetshopPayments;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PetshopPaymentsPersistenceMapper {

    PetshopPayments toPetshopPayments (PetshopPaymentsModel petshopPaymentsModel);
    PetshopPaymentsModel toPetshopPaymentsModel (PetshopPayments petshopPayments);
    List<PetshopPayments> toPetshopPaymentsList (List<PetshopPaymentsModel> petshopPaymentsModel);
    List<PetshopPaymentsModel> toPetshopPaymentsModelList (List<PetshopPayments> petshopPayments);
}
