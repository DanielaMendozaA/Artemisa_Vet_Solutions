package com.riwi.artemisa.application.ports.CRUD;

public interface CreateModel<T>{
    public T save(T entity);
}
