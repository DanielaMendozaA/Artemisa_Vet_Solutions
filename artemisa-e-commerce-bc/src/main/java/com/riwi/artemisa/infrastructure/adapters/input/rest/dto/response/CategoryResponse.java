package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private String name;
    private String description;

}

