package com.riwi.artemisa.application.services;

import com.riwi.artemisa.application.ports.input.MediaServicePort;
import com.riwi.artemisa.application.ports.out.MediaPersistenPort;
import com.riwi.artemisa.domain.models.MediaModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaSerivice implements MediaServicePort {

    private final MediaPersistenPort persistenPort;


    @Override
    public MediaModel save(MediaModel mediaModel) {
        return persistenPort.save(mediaModel);
    }
    @Override
    public MediaModel update(Long id, MediaModel mediaModel) {
        return persistenPort.update(id, mediaModel);
    }

    @Override
    public MediaModel findById(Long id) {
        return persistenPort.readById(id);
    }

    @Override
    public List<MediaModel> findAll() {
        return persistenPort.findAll();
    }

    @Override
    public String deletebyId(Long id) {
        return persistenPort.deletebyId(id);
    }






}
