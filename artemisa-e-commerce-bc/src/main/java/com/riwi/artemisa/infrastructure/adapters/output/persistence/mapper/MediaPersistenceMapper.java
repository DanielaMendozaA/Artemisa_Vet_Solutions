package com.riwi.artemisa.infrastructure.adapters.output.persistence.mapper;

import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.infrastructure.adapters.output.persistence.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MediaPersistenceMapper {

    Media toMedia(MediaModel mediaModel);
    MediaModel toMediaModel(Media media);
    List<MediaModel> toMediaModelList(List<Media> media);

}
