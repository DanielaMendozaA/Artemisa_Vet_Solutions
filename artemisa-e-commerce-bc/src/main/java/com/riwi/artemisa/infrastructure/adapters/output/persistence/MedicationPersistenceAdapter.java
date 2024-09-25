package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.MedicationPersistencePort;
import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Category;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Medication;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.CategoryPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.MediaPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.MedicationPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.CategoryRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MediaRepository;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicationPersistenceAdapter implements MedicationPersistencePort {

    //Repositories
    private final MedicationRepository repository;
    private final CategoryRepository categoryRepository;
    private final MediaRepository mediaRepository;

    //mappers
    private final MedicationPersistenceMapper mapper;
    private final CategoryPersistenceMapper categoryMapper;
    private final MediaPersistenceMapper mediaMapper;

    @Override
    public MedicationModel save(MedicationModel medicationModel) {

        // Obtener o guardar la categoría
        Category category = categoryRepository.findById(medicationModel.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Obtener las medias por sus ids
        List<Media> mediaList = mediaRepository.findAllByIdInAndDeletedIsFalse(medicationModel.getMedia()
                .stream()
                .map(MediaModel::getId)
                .collect(Collectors.toList()));

        // Asignar la categoría al medicamento
        medicationModel.setCategory(categoryMapper.toCategoryModel(category));

        // Asignar las medias al medicamento
        medicationModel.setMedia(mediaList.stream()
                .map(mediaMapper::toMediaModel)
                .collect(Collectors.toList()));

        // Asegurarse de que el medicamento tenga deleted
        medicationModel.setDeleted(false);

        // Guardar el medicamento creado
        return mapper.toMedicationModel(repository.save(mapper.toMedication(medicationModel)));
    }

    @Override
    public String deletebyId(String name) {
        Medication existingMedication = repository.findByNameIgnoreCaseAndDeletedIsFalse(name);

        if (existingMedication == null) {
            return "Medication not found or already deleted";
        }
        existingMedication.setDeleted(true);
        repository.save(existingMedication);
        if (existingMedication.getDeleted() == true) {
            return "Medication deleted successfully";
        }
        return "Medication restore successfully";
    }

    @Override
    public List<MedicationModel> findAll() {
        return mapper.toMedicationModelList(repository.findAll());
    }

    @Override
    public MedicationModel readByName(String name) {
        return mapper.toMedicationModel(repository.findByNameContaining(name));
    }

    @Override
    public MedicationModel update(Long id, MedicationModel medicationModel) {
        // Buscar el medicamento existente que no esté eliminado
        Medication existingMedication = repository.findByIdAndDeletedIsFalse(id);
        if (existingMedication == null) {
            throw new RuntimeException("Medication not found or deleted");
        }

        // Actualizar los campos del medicamento solo si son diferentes de null en el MedicationModel
        if (medicationModel.getName() != null) {
            existingMedication.setName(medicationModel.getName());
        }
        if (medicationModel.getDescription() != null) {
            existingMedication.setDescription(medicationModel.getDescription());
        }

        // Guardar el medicamento actualizado
        return mapper.toMedicationModel(repository.save(existingMedication));
    }


    @Override
    public List<MedicationModel> findAllByName(String s) {
        return List.of();
    }
}
