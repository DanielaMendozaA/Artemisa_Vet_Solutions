package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.CategoryModel;


public interface CategoryPersistencePort extends
        Save<CategoryModel>
        , Update<CategoryModel,String>
        , UpdateStatusProduct<String>
        , ReadAll<CategoryModel>
        , ReadByName<CategoryModel,String> {

    CategoryModel update(String name, CategoryModel categoryModel);
}
