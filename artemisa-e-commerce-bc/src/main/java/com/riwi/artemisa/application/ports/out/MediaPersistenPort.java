package com.riwi.artemisa.application.ports.out;

import com.riwi.artemisa.application.ports.CRUD.*;
import com.riwi.artemisa.domain.models.MediaModel;

public interface MediaPersistenPort extends
        Save<MediaModel>
        , Delete<Long>
        , ReadAll<MediaModel>
        , ReadById<MediaModel,Long>
        , Update<MediaModel,Long> {}
