package com.riwi.artemisa.application.ports.CRUD;

import java.util.List;

public interface ReadPetshopPaymentsByUserId<Entity, ID> {
    public List<Entity> findPetshopPaymentsByUserId(ID idUser);
}
