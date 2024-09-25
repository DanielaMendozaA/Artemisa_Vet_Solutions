package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.ProductInventoryServicePort;
import com.riwi.artemisa.application.ports.out.ProductInventoryPersistencePort;
import com.riwi.artemisa.domain.models.ProductInventoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInventoryService implements ProductInventoryServicePort {

    private final ProductInventoryPersistencePort productInventoryPersistencePort;

    @Override
    public ProductInventoryModel save(ProductInventoryModel productInventoryModel) {
        return productInventoryPersistencePort.save(productInventoryModel);
    }

    @Override
    public ProductInventoryModel update(Long id, ProductInventoryModel productInventoryModel) {
        return productInventoryPersistencePort.update(id, productInventoryModel);
    }

    @Override
    public String updateStatusProduct(Long id) {
        return productInventoryPersistencePort.updateStatusProduct(id);
    }

    @Override
    public List<ProductInventoryModel> findAll() {
        return productInventoryPersistencePort.findAll();
    }

    @Override
    public ProductInventoryModel readById(Long id) {
        return productInventoryPersistencePort.readById(id);
    }

    @Override

    public List<ProductInventoryModel> readAllCategory(Long id) {
        return productInventoryPersistencePort.readAllCategory(id);
    }

    @Override
    public List<ProductInventoryModel> findAllByName(String name) {
        return productInventoryPersistencePort.findAllByName(name);
    }

    @Override
    public String updateStock(Integer stock, Long id) {
        return productInventoryPersistencePort.updateStock(stock, id);
    }

    @Override
    public List<ProductInventoryModel> readAllProductStock(Integer stock) {
        return productInventoryPersistencePort.readAllProductStock(stock);
    }

    @Override
    public List<ProductInventoryModel> readAllIfAvailable() {
        return productInventoryPersistencePort.readAllIfAvailable();
    }
}
