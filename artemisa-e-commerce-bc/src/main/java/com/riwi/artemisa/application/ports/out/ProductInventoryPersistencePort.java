package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.ProductInventoryModel;

public interface ProductInventoryPersistencePort extends
        Save<ProductInventoryModel>,
        Update<ProductInventoryModel, Long>,
        UpdateStatusProduct<Long>,
        ReadAll<ProductInventoryModel>,
        ReadById<ProductInventoryModel, Long>,
        ReadAllCategory<ProductInventoryModel, Long>,
        ReadAllByName<ProductInventoryModel,String>,
        UpdateStock<Integer, Long>,
        ReadAllProductStock<ProductInventoryModel, Integer>,
        ReadAllIfAvailable<ProductInventoryModel> {}
