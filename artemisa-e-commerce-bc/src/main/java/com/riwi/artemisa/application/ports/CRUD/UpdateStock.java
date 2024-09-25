package com.riwi.artemisa.application.ports.CRUD;

public interface UpdateStock<Stock, ID> {
    public String updateStock(Stock stock, ID id);
}
