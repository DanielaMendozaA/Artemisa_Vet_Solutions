package com.riwi.artemisa.application.ports.CRUD;

import java.util.List;

public interface ReadAllProductStock<Entity, Stock> {
    public List<Entity> readAllProductStock(Stock stock);
}
