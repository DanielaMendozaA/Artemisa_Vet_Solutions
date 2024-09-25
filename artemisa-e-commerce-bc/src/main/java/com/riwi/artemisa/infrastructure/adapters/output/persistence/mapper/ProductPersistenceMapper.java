package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.ProductModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductPersistenceMapper {

    Product toProduct(ProductModel productModel);
    ProductModel toProductModel(Product product);
    List<ProductModel> toProductModelList(List<Product> product);

}
