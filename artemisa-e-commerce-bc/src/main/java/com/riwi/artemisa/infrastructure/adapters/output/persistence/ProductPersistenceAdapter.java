package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.ProductPersistenPort;
import com.riwi.artemisa.domain.models.ProductModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductPersistenPort {

    private final ProductRepository repository;
    private final ProductPersistenceMapper mapper;

    @Override
    public ProductModel save(ProductModel productModel) {
        return mapper.toProductModel(repository.save(mapper.toProduct(productModel)));
    }
}
