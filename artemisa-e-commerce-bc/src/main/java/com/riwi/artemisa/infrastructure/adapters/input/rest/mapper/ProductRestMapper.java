package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.ProductModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductRestMapper {

//    ProductModel toProductModel(ProductCreateRequest productCreateRequest);

    ProductCreateRequest toProductInventoryCreateRequest(ProductModel productModel);

//    List<ProductCreateRequest> toProductCreateRequestList(List<ProductModel> productModels);

    //Response

//    ProductModel toPtoductModel(ProductResponse productResponse);

    ProductResponse toProductResponse(ProductModel productModel);

//    List<ProductResponse> toProductResponseList(List<ProductModel> productModels);

}
