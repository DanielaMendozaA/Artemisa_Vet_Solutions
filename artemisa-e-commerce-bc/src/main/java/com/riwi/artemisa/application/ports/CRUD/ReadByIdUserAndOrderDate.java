package com.riwi.artemisa.application.ports.CRUD;


public interface ReadByIdUserAndOrderDate<Entity, ID, Date> {
    public Entity readByIdUserAndOrderDate(ID id, Date date);
}
