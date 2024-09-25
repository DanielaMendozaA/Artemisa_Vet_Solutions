package com.riwi.artemisa.application.ports.CRUD;

public interface ReadById<Entity,ID>{
    public Entity readById(ID id);
}
