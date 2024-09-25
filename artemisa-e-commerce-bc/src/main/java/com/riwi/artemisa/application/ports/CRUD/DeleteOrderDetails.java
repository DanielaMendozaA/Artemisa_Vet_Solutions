package com.riwi.artemisa.application.ports.CRUD;

public interface DeleteOrderDetails<ID> {
    public String deleteOrderDetails(ID order, ID orderDetails);
}
