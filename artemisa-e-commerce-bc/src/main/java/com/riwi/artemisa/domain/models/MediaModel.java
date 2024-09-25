package com.riwi.artemisa.domain.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaModel {

    private Long id;
    private String type;
    private String url;
    private ProductModel productModel;
    private MedicationModel medication;

    //Auditable
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @Builder.Default
    private Boolean deleted = false;

}
