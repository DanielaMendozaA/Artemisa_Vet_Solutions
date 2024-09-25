package com.riwi.artemisa.domain.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel{

    private Long id;
    private String name;
    private String description;
    private List<ProductModel> products;
    private List<MedicationModel> medications;

    //Auditable
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    @Builder.Default
    private Boolean deleted = false;

}