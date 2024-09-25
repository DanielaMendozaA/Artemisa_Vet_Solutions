package com.riwi.artemisa.application.ports.CRUD;

import java.util.List;

public interface ReadAll<Entity>{
    public List<Entity> findAll();
}
