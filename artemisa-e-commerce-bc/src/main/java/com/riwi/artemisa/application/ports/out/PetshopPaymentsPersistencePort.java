package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.ReadAll;
import com.riwi.artemisa.application.ports.CRUD.ReadByDate;
import com.riwi.artemisa.application.ports.CRUD.ReadPetshopPaymentsByUserId;
import com.riwi.artemisa.application.ports.CRUD.Save;
import com.riwi.artemisa.domain.models.PetshopPaymentsModel;

import java.time.LocalDate;

public interface PetshopPaymentsPersistencePort extends
        Save<PetshopPaymentsModel>,
        ReadAll<PetshopPaymentsModel>,
        ReadByDate<PetshopPaymentsModel, LocalDate>,
        ReadPetshopPaymentsByUserId<PetshopPaymentsModel, Long> {}
