package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;


public interface MedicationInventoryServicePort extends
        Save<MedicationInventoryModel>,
        Update<MedicationInventoryModel,Long>,
        UpdateStatusProduct<Long>,
        ReadAll<MedicationInventoryModel>,
        ReadById<MedicationInventoryModel, Long>,
        ReadAllCategory<MedicationInventoryModel, Long>,
        ReadAllByName<MedicationInventoryModel,String>,
        UpdateStock<Integer, Long>,
        ReadAllProductStock<MedicationInventoryModel, Integer>,
        ReadAllIfAvailable<MedicationInventoryModel> {
}
