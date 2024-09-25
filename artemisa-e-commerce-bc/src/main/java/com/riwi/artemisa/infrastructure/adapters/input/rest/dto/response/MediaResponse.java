package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaResponse {

    private String type;
    private String url;
}