package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.CategoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryPersistenceMapper {

    Category toCategory(CategoryModel categoryModel);

    CategoryModel toCategoryModel(Category category);

    List<CategoryModel> toCategoryModels(List<Category> categories);

}

