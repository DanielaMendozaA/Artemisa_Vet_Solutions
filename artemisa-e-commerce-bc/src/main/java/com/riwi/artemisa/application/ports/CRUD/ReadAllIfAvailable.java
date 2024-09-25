package com.riwi.artemisa.application.ports.CRUD;

import java.util.List;

public interface ReadAllIfAvailable<Entity> {
    public List<Entity> readAllIfAvailable();
}
