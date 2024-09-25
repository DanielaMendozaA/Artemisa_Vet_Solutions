package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.OrderCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRestMapper {

    OrderModel orderCreateRequestToOrderModel(OrderCreateRequest orderCreateRequest);
    OrderCreateRequest orderModelToOrderCreateRequest(OrderModel orderModel);
    List<OrderModel> orderCreateRequestToOrderModelList(List<OrderCreateRequest> orderCreateRequests);
    List<OrderCreateRequest> orderModelToOrderCreateRequestList(List<OrderModel> orderModels);

    OrderModel orderResponsetoOrderModel(OrderResponse orderResponse);
    OrderResponse orderModelToOrderResponse(OrderModel orderModel);
    List<OrderModel> orderResponsetoOrderModelList(List<OrderResponse> orderResponse);
    List<OrderResponse> orderModelToOrderResponseList(List<OrderModel> orderModel);

}
