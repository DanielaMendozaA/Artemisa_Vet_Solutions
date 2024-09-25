package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.MedicationServicePort;
import com.riwi.artemisa.application.ports.out.MedicationPersistencePort;
import com.riwi.artemisa.domain.models.MedicationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService  implements MedicationServicePort {

    private final MedicationPersistencePort persistencePort;


    @Override
    public String deletebyId(String name) {
        return persistencePort.deletebyId(name);
    }

    @Override
    public List<MedicationModel> findAll() {
        return persistencePort.findAll();
    }

    @Override
    public MedicationModel readByName(String name) {
        return persistencePort.readByName(name);
    }

    @Override
    public MedicationModel save(MedicationModel medicationModel) {
        return persistencePort.save(medicationModel);
    }

    @Override
    public MedicationModel update(Long id, MedicationModel medicationModel) {
        return persistencePort.update(id,medicationModel);
    }


}
