package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.ProductservicePort;
import com.riwi.artemisa.application.ports.out.ProductPersistenPort;
import com.riwi.artemisa.domain.models.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductservicePort {

    private final ProductPersistenPort productPersistenPort;

    @Override
    public ProductModel save(ProductModel productModel) {
        return productPersistenPort.save(productModel);
    }
}
