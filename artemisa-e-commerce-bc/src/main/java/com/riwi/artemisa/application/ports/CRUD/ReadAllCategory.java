package com.riwi.artemisa.application.ports.CRUD;

import java.util.List;

public interface ReadAllCategory<Entity, ID> {
    public List<Entity> readAllCategory(ID id);
}
