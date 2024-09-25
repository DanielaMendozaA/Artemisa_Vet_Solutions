package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.MedicationModel;


public interface MedicationPersistencePort extends
        Save<MedicationModel>
        , ReadByName<MedicationModel,String>
        , ReadAll<MedicationModel>
        , Update<MedicationModel, Long>
        , Delete<String>
        ,ReadAllByName<MedicationModel,String> {


}
