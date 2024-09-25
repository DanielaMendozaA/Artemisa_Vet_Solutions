package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductInventoryPersistenceMapper {

    ProductInventory toProductInventory(ProductInventoryModel productInventoryModel);

    ProductInventoryModel toProductInventoryModel(ProductInventory productInventory);

    List<ProductInventoryModel> toProductInventoryModelList(List<ProductInventory> productInventories);

}

