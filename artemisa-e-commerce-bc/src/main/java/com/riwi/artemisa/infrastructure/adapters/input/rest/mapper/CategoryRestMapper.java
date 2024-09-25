package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.CategoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.CategoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponseAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryRestMapper {

    //Admin mappings
    CategoryResponseAdmin toCategoryResponseAdmin(CategoryModel categoryModel);
    List<CategoryResponseAdmin> toCategoryResponseAdminList(List<CategoryModel> categoryList);

    // Mapping for Create Request
    CategoryModel toCategory(CategoryCreateRequest request);
    CategoryCreateRequest toCategoryCreateRequest(CategoryModel categoryModel);
    List<CategoryCreateRequest> toCategoryCreateRequestList(List<CategoryModel> categoryList);

    // User mappings
    CategoryResponse toCategoryResponse(CategoryModel category);
    CategoryModel toCategoryModel(CategoryResponse categoryResponse);
    List<CategoryResponse> toCategoryResponseList(List<CategoryModel> categoryList);
}