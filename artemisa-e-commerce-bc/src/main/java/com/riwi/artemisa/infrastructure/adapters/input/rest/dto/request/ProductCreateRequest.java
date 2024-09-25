package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductCreateRequest {

    private Long id;
    @NotBlank(message = "The name of the product is required")
    private String name;
    @NotBlank(message = "The description of the product is required")
    private String description;
    @NotNull(message = "The category of the product is required")
    @Valid
    private CategoryCreateRequest category;
    @NotNull(message = "The media of the product is required")
    @Valid
    private List<MediaCreateRequest> media;

}
