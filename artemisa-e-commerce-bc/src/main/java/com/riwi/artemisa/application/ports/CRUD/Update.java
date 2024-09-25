package com.riwi.artemisa.application.ports.CRUD;

public interface Update <Entity, ID>{
    public Entity update(ID id,Entity entity);

}
