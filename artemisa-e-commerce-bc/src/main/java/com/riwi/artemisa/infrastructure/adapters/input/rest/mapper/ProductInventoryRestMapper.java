package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductResponseAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductInventoryRestMapper {

    //Admin
    ProductResponseAdmin toProductInventoryResponseAmin(ProductInventoryModel model);
    List<ProductResponseAdmin> toProductInventoryResponseAdminList(List<ProductInventoryModel> models);

    //Mappgin for create request

    ProductInventoryModel toProductInventory(ProductInventoryCreateRequest createRequest);
    ProductInventoryCreateRequest toProductInventoryCreateRequest(ProductInventoryModel model);
    List<ProductInventoryCreateRequest> toProductInventoryCreateRequestList(List<ProductInventoryModel> productInventoryModels);

    //User mappings

    ProductInventoryResponse toProductInventoryResponse(ProductInventoryModel productInventoryModel);
    ProductInventoryModel toProductInventoryModel(ProductInventoryResponse productInventoryResponse);
    List<ProductInventoryResponse> toProductResponseList(List<ProductInventoryModel> ProductsInventoryList);

}

