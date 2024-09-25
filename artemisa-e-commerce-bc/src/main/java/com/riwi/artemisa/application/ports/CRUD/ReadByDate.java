package com.riwi.artemisa.application.ports.CRUD;

import java.util.List;

public interface ReadByDate<Entity, Date> {
    public List<Entity> findByDate(Date date);
}
