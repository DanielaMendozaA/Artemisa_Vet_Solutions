package com.riwi.artemisa.infrastructure.adapters.output.persistence;

import com.riwi.artemisa.application.ports.out.MediaPersistenPort;
import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper.MediaPersistenceMapper;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MediaPersisterAdapter implements MediaPersistenPort {

    private final MediaRepository repository;
    private final MediaPersistenceMapper mapper;

    @Override
    public MediaModel save(MediaModel mediaModel) {
            mediaModel.setCreatedAt(LocalDateTime.now());
            mediaModel.setUpdatedAt(LocalDateTime.now());
            mediaModel.setDeleted(false);
        return mapper.toMediaModel(repository.save(mapper.toMedia(mediaModel)));
    }

    @Override
    public MediaModel readById(Long id) {
        Media media = repository.findByIdAndNotDeleted(id).orElseThrow();
        return mapper.toMediaModel(media);
    }


    @Override
    public List<MediaModel> findAll() {
        List<Media> mediaList = repository.findAllByDeletedIsFalse();
        return mapper.toMediaModelList(mediaList);
    }

    @Override
    public MediaModel update(Long id, MediaModel mediaModel) {
        Media existingMedia = repository.findById(id).orElseThrow();

        Media media = Media.builder()
                .id(existingMedia.getId())
                .type(mediaModel.getType())
                .url(mediaModel.getUrl())
                .createdAt(existingMedia.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .deletedAt(existingMedia.getDeletedAt())
                .build();

        return mapper.toMediaModel(repository.save(media));
    }

    @Override
    public String deletebyId(Long id) {
        Media existingMedia = repository.findById(id).orElseThrow();
        existingMedia.setDeleted(true);
        existingMedia.setCreatedAt(existingMedia.getCreatedAt());
        existingMedia.setUpdatedAt(LocalDateTime.now());
        existingMedia.setDeletedAt(LocalDateTime.now());
        repository.save(existingMedia);
        if (existingMedia.getDeleted() == true){
            return "Media deleted successfully";
        }
        return "Media restore successfully";
    }
}
