package com.riwi.artemisa.application.ports.CRUD;

public interface ReadByName<Entity,ID>{
    Entity readByName(ID name);
}
