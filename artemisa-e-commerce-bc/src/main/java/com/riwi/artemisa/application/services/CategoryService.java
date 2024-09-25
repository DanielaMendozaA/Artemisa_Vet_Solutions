package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.CategoryServicePort;
import com.riwi.artemisa.application.ports.out.CategoryPersistencePort;
import com.riwi.artemisa.domain.models.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final CategoryPersistencePort persistencePort;

    @Override
    public CategoryModel save(CategoryModel categoryModel) {
        return persistencePort.save(categoryModel);
    }

    @Override
    public String updateStatusProduct(String name) {
        return persistencePort.updateStatusProduct(name);
    }

    @Override
    public CategoryModel update(String name, CategoryModel categoryModel) {
        return persistencePort.update(name, categoryModel);
    }

    @Override
    public CategoryModel readByName(String name) {
        return persistencePort.readByName(name);
    }

    @Override
    public List<CategoryModel> findAll() {
        return persistencePort.findAll();
    }
}
