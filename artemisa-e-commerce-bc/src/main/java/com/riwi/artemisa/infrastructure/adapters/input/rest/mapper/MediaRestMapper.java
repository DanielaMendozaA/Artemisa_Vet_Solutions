package com.riwi.artemisa.infrastructure.adapters.input.rest.mapper;

import com.riwi.artemisa.domain.models.MediaModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MediaCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MediaResponseAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MediaRestMapper {

    //Admin mappings
    MediaResponseAdmin toMediaResponseAdmin (MediaModel model);
    List<MediaResponseAdmin> toMediaResponseAdminList(List<MediaModel> mediaList);


    // Mapping for Create Request
    MediaModel toMedia(MediaCreateRequest request);
    MediaCreateRequest toMediaCreateRequest(MediaModel mediaModel);
    List<MediaCreateRequest> toMediaCreateRequestList(List<MediaModel> mediaModel);

    //User Mappings
    MediaResponse toMediaResponse(MediaModel mediaModel);
    MediaModel toMediaModel(MediaResponse mediaResponse);
    List<MediaResponse> toMediaResponseList(List<MediaModel> mediaModel);


}
