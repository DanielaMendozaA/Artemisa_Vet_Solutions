package com.riwi.artemisa.domain.models;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationModel{
    private Long id;
    private String name;
    private String description;
    private List<MediaModel> media;
    private CategoryModel category;
    @Builder.Default
    private Boolean deleted = false;
}