package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.OrderDetailsModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailsPersistenceMapper {

    OrderDetails toOrderDetails(OrderDetailsModel orderDetailsModel);
    OrderDetailsModel toOrderDetailsModel(OrderDetails orderDetails);
    List<OrderDetails> toOrderDetailsList(List<OrderDetailsModel> orderDetailsModels);
    List<OrderDetailsModel> toOrderDetailsModelList(List<OrderDetails> orderDetails);
    
}
