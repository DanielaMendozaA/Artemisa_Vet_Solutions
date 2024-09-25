package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.ProductInventoryPersistencePort;
import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Category;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Product;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.ProductInventory;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.ProductInventoryPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.CategoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MediaRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductInventoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ProductInventoryPersistenceAdapter implements ProductInventoryPersistencePort {

    private final ProductInventoryRepository productInventoryRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MediaRepository mediaRepository;
    private final ProductInventoryPersistenceMapper productInventoryPersistenceMapper;

    @Override
    public ProductInventoryModel save(ProductInventoryModel productInventoryModel) {

        ProductInventory productInventory = ProductInventory.builder().
                stock(productInventoryModel.getStock()).
                supplier(productInventoryModel.getSupplier()).
                supplierPrice(productInventoryModel.getSupplierPrice()).
                sellingPrice(productInventoryModel.getSellingPrice()).
                dueDate(productInventoryModel.getDueDate()).
                stateProduct(true).
                createdAt(LocalDateTime.now()).
                updatedAt(LocalDateTime.now()).
                build();


        Product product = Product.builder().
                name(productInventoryModel.getProduct().getName()).
                description(productInventoryModel.getProduct().getDescription())
            .build();


        Category category = categoryRepository.findById(
                productInventoryModel
                        .getProduct()
                        .getCategory()
                        .getId()).orElseThrow(RuntimeException::new);

        product.setCategory(category);

        List<Media> media = productInventoryModel.getProduct().getMedia().stream().map(
                mediaModel -> Media.builder()
                            .type(mediaModel.getType())
                            .url(mediaModel.getUrl())
                            .build()
        ).toList();

        product.setMedia(mediaRepository.saveAll(media));


        product.setMedia(media);

        Product savedProduct = productRepository.save(product);

        productInventory.setProduct(savedProduct);

        return productInventoryPersistenceMapper.toProductInventoryModel(productInventoryRepository.save(productInventory));
    }

    @Override
    public ProductInventoryModel update(Long id, ProductInventoryModel productInventoryModel) {
        ProductInventory existingInventory = productInventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product inventory not found"));

        // Actualiza los atributos de ProductInventory
        existingInventory.setStock(productInventoryModel.getStock());
        existingInventory.setSupplier(productInventoryModel.getSupplier());
        existingInventory.setSupplierPrice(productInventoryModel.getSupplierPrice());
        existingInventory.setSellingPrice(productInventoryModel.getSellingPrice());
        existingInventory.setDueDate(productInventoryModel.getDueDate());
        existingInventory.setUpdatedAt(LocalDateTime.now()); // Actualiza la fecha de modificación

        // Actualiza o crea el producto
        Product existingProduct = productRepository.findById(productInventoryModel.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productInventoryModel.getProduct().getName());
        existingProduct.setDescription(productInventoryModel.getProduct().getDescription());

        // Actualiza medias
        List<Media> mediaList = new ArrayList<>();
        for (MediaModel mediaModel : productInventoryModel.getProduct().getMedia()) {
            Media mediaEntity = mediaRepository.findById(mediaModel.getId())
                    .orElseThrow(() -> new RuntimeException("Media not found"));
            mediaEntity.setType(mediaModel.getType());
            mediaEntity.setUrl(mediaModel.getUrl());
            mediaList.add(mediaRepository.save(mediaEntity));
        }
        existingProduct.setMedia(mediaList);

        // Guarda el producto actualizado
        productRepository.save(existingProduct);
        existingInventory.setProduct(existingProduct);

        // Guarda el inventario actualizado
        return productInventoryPersistenceMapper.toProductInventoryModel(productInventoryRepository.save(existingInventory));
    }

    @Override
    public String updateStatusProduct(Long id) {
        ProductInventory productInventory = productInventoryRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("The product does not exist, therefore it could not be eliminated."));

        // Cambia el estado del producto
        productInventory.setStateProduct(!productInventory.isStateProduct());
        productInventory.setUpdatedAt(LocalDateTime.now());

        // Guarda el estado actualizado en la base de datos
        productInventoryRepository.save(productInventory);

        // Devuelve un mensaje según el nuevo estado del producto
        return productInventory.isStateProduct() ? "Product restored successfully" : "Product deleted successfully";
    }


    @Override
    public List<ProductInventoryModel> findAll() {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findAll());
    }

    @Override
    public ProductInventoryModel readById(Long id) {
        return productInventoryPersistenceMapper.toProductInventoryModel(productInventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("The Product not found")));
    }

    @Override
    public List<ProductInventoryModel> readAllCategory(Long id) {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findByCategoryId(id));
    }

    @Override
    public List<ProductInventoryModel> findAllByName(String name) {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findAllByName(name));
    }

    @Override
    public String updateStock(Integer stock, Long id) {
        ProductInventory productInventory = productInventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product inventory not found"));
        productInventory.setStock(productInventory.getStock() + stock);
        productInventory.setCreatedAt(productInventory.getCreatedAt());
        productInventory.setUpdatedAt(LocalDateTime.now());
        productInventoryRepository.save(productInventory);
        return "Stock updated successfully";
    }

    @Override
    public List<ProductInventoryModel> readAllProductStock(Integer stock) {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findAllProductInventoryStock(stock));
    }


    //User
    @Override
    public List<ProductInventoryModel> readAllIfAvailable() {
        return productInventoryPersistenceMapper.toProductInventoryModelList(productInventoryRepository.findAllProductInventoryAvailable());
    }
}
