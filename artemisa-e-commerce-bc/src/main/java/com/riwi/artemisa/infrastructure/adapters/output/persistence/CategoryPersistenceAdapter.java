package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.CategoryPersistencePort;
import com.riwi.artemisa.domain.models.CategoryModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Category;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.CategoryPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryPersistencePort {

    private final CategoryRepository repository;
    private final CategoryPersistenceMapper mapper;

    @Override
    public CategoryModel save(CategoryModel categoryModel) {

        Category category = Category.builder()
                .name(categoryModel.getName())
                .name(categoryModel.getName())
                .description(categoryModel.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deleted(false)
                .build();

        return mapper.toCategoryModel(repository.save(category));
    }

    @Override
    public CategoryModel update(String name, CategoryModel categoryModel) {
        // Recupera la entidad existente por nombre
        Category existingCategory = repository.findByNameAndDeletedIsFalse(name);
        // Crea la nueva entidad de categoría con los datos actualizados
        Category category = Category.builder()
                .id(existingCategory.getId())
                .name(categoryModel.getName())
                .description(categoryModel.getDescription())
                .createdAt(existingCategory.getCreatedAt())
                .deleted(existingCategory.getDeleted())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapper.toCategoryModel(repository.save(category));
    }

    @Override
    public String updateStatusProduct(String name) {
        Category category = repository.findByNameAndDeletedIsFalse(name);
        category.setDeleted(true);
        category.setCreatedAt(category.getCreatedAt());
        category.setUpdatedAt(LocalDateTime.now());
        category.setDeletedAt(LocalDateTime.now());
        repository.save(category);
        if (category.getDeleted() == true) {
            return "Product deleted successfully";
        }
        return "Product restore successfully";
    }

    @Override
    public CategoryModel readByName(String name) {
        Category category = repository.findByIdAndNotDeleted(name).orElseThrow();
        return mapper.toCategoryModel(category);
    }

    @Override
    public List<CategoryModel> findAll() {
        List<Category> categoryList = repository.findAllByDeletedIsFalse();
        return mapper.toCategoryModels(categoryList);
    }

}