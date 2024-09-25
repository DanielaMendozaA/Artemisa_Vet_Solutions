package com.riwi.artemisa.application.ports.input;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.MedicationModel;

public interface MedicationServicePort extends
        Save<MedicationModel>
        , ReadByName<MedicationModel,String>
        , ReadAll<MedicationModel>
        , Update<MedicationModel, Long>
        , Delete<String> {
}
