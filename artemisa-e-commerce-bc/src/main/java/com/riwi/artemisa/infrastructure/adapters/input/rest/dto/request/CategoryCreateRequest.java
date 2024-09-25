package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CategoryCreateRequest {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotEmpty
    private String description;

}
