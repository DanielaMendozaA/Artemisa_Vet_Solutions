package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationResponseAdmin {
    private Long id;
    private String name;
    private String description;
    private Boolean deleted;
    private CategoryResponse category;
    private List<MediaResponse> media;
}
