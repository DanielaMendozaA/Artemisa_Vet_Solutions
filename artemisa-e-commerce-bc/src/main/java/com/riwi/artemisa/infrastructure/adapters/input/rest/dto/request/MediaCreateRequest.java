package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class MediaCreateRequest {

    private Long id;
    @NotBlank(message = "The type of the media is required")
    private String type;
    @NotBlank(message = "The url of the media is required")
    private String url;

}
