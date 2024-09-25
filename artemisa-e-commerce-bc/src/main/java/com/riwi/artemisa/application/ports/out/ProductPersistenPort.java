package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.ProductModel;

public interface ProductPersistenPort extends
        Save<ProductModel> {
}
